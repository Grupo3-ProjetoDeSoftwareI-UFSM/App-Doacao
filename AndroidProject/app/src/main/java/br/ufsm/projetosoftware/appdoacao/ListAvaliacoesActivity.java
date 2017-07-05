package br.ufsm.projetosoftware.appdoacao;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

import br.ufsm.projetosoftware.appdoacao.models.Avaliacao;
import br.ufsm.projetosoftware.appdoacao.models.Solicitacao;
import br.ufsm.projetosoftware.appdoacao.network.AvaliacoesPost;
import br.ufsm.projetosoftware.appdoacao.network.AvaliacoesResponse;
import br.ufsm.projetosoftware.appdoacao.network.IResultString;
import br.ufsm.projetosoftware.appdoacao.network.SolicitacoesResponse;
import br.ufsm.projetosoftware.appdoacao.network.VolleyServiceString;
import br.ufsm.projetosoftware.appdoacao.view.ListAvaliacoesView;
import br.ufsm.projetosoftware.appdoacao.view.ListAvaliacoesViewImpl;

/**
 * Activity da tela de Lista de Avaliações
 */
public class ListAvaliacoesActivity extends Activity {

    ListAvaliacoesView listAvaliacoesView;
    private IResultString resultCallback = null;
    private VolleyServiceString volleyService;
    private final String POSTLIST = "POSTLIST";
    private String listURL;
    private List<Avaliacao> listaAvaliacao;
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_avaliacoes);
        listAvaliacoesView = new ListAvaliacoesViewImpl(getWindow().getDecorView().getRootView());
        listURL = getString(R.string.listAvaliacoesURL);
        initCallback();
        volleyService = new VolleyServiceString(resultCallback, this);
        extras = getIntent().getExtras();
        getList();
    }

    /**
     * Solicita lista de avaliações do solicitante para o servidor.
     */
    private void getList(){
        AvaliacoesPost avaliacoesPost = new AvaliacoesPost();
        avaliacoesPost.setIdSolicitacao(extras.getInt("idSolicitacao"));
        String avaliacoesPostJson = new Gson().toJson(avaliacoesPost, AvaliacoesPost.class);
        volleyService.postDataVolley(POSTLIST, listURL, avaliacoesPostJson);
    }

    /**
     * Exibe lista retornada pelo servidor no ListView
     * @param response
     */
    private void postListSucess(String response) {
        Log.d("ListAvaliacoes", response);
        AvaliacoesResponse avaliacoesResponse = new Gson().fromJson(response, AvaliacoesResponse.class);
        if (avaliacoesResponse != null) {
            listaAvaliacao = avaliacoesResponse.getListaAvaliacao();
            SimpleAdapter.ViewBinder binder = new SimpleAdapter.ViewBinder() {
                @Override
                public boolean setViewValue(View view, Object data, String textRepresentation) {
                    if(view.getId() == R.id.rbListAvaliacao){
                        //Float rating = Float.valueOf(((HashMap<String, String>)data).get(AvaliacoesResponse.KEY_AVALIACAO));
                        Float rating = Float.parseFloat((String)data);
                        ((RatingBar)view).setRating(rating);
                        return true;
                    }
                    return false;
                }
            };
            ListAdapter adapter = new SimpleAdapter(ListAvaliacoesActivity.this,
                    avaliacoesResponse.getMapSolicitacao(),
                    R.layout.avaliacao_list,
                    new String[]{avaliacoesResponse.KEY_AVALIACAO, avaliacoesResponse.KEY_COMENTARIO},
                    new int[]{R.id.rbListAvaliacao, R.id.tvListComentario});
            ((SimpleAdapter)adapter).setViewBinder(binder);
            listAvaliacoesView.setListAdapter(adapter);
        }
    }

    /**
     * Recebe resposta de sucesso ou falho na comunicação com o servidor.
     * Executa o método notifySuccess e passa a resposta pro método postListSucess em caso de sucesso.
     * Notifica o usuário do erro na conexão em caso de falha.
     */
    private void initCallback(){
        resultCallback = new IResultString() {
            @Override
            public void notifySuccess(String requestType, String response) {
                Log.d("notify", response);
                if(requestType.equals(POSTLIST)){
                    postListSucess(response);
                }
            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("Erro na conexao", error.toString());
                Toast.makeText(ListAvaliacoesActivity.this,error.toString(),Toast.LENGTH_LONG).show();
            }
        };
    }
}
