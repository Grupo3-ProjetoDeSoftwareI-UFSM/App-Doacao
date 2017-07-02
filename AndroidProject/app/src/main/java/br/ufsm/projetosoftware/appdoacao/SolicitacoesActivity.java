package br.ufsm.projetosoftware.appdoacao;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.List;

import br.ufsm.projetosoftware.appdoacao.models.Solicitacao;
import br.ufsm.projetosoftware.appdoacao.network.IResultString;
import br.ufsm.projetosoftware.appdoacao.network.SearchResponse;
import br.ufsm.projetosoftware.appdoacao.network.SolicitacoesPost;
import br.ufsm.projetosoftware.appdoacao.network.SolicitacoesResponse;
import br.ufsm.projetosoftware.appdoacao.network.VolleyServiceString;
import br.ufsm.projetosoftware.appdoacao.view.SolicitacoesView;
import br.ufsm.projetosoftware.appdoacao.view.SolicitacoesViewImpl;

public class SolicitacoesActivity extends AppCompatActivity implements SolicitacoesView.SolicitacaoListClick{

    private SolicitacoesView solicitacoesView;
    private IResultString resultCallback = null;
    private VolleyServiceString volleyService;
    private final String POSTLIST = "POSTLIST";
    private String listURL;
    private List<Solicitacao> listaSolicitacao;
    private Bundle extras;
    private String authToken;
    private SharedPreferences loginSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitacoes);
        solicitacoesView = new SolicitacoesViewImpl(getWindow().getDecorView().getRootView());
        solicitacoesView.setSolicitacaoListListener(this);
        listURL = getString(R.string.listSolicitacoesURL);
        initCallback();
        volleyService = new VolleyServiceString(resultCallback, this);
        extras = getIntent().getExtras();
        loginSettings = getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
        authToken = loginSettings.getString("authToken", null);
        getList();
    }

    @Override
    public void onSolicitacaoClick(int id) {
        //TODO startchat
    }

    private void getList(){
        SolicitacoesPost solicitacoesPost = new SolicitacoesPost();
        solicitacoesPost.setDoacao(extras.getInt("doacaoId"));
        solicitacoesPost.setAuthToken(authToken);
        String solicitacoesPostJson = new Gson().toJson(solicitacoesPost, SolicitacoesPost.class);
        Log.d("solicitacoesPostJson", solicitacoesPostJson);
        volleyService.postDataVolley(POSTLIST, listURL, solicitacoesPostJson);
    }

    private void postListSucess(String response) {
        Log.d("ListSolicitacoes", response);
        SolicitacoesResponse solicitaoesResponse = new Gson().fromJson(response, SolicitacoesResponse.class);
        if (solicitaoesResponse != null) {
            listaSolicitacao = solicitaoesResponse.getListaSolicitacao();
            ListAdapter adapter = new SimpleAdapter(SolicitacoesActivity.this,
                    solicitaoesResponse.getMapSolicitacao(),
                    R.layout.request_list,
                    new String[]{solicitaoesResponse.KEY_NOME, solicitaoesResponse.KEY_JUSTIFICATIVA},
                    new int[]{R.id.nome, R.id.justificativa});
            solicitacoesView.setListAdapter(adapter);
        }
    }

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
                Toast.makeText(SolicitacoesActivity.this,error.toString(),Toast.LENGTH_LONG).show();
            }
        };
    }
}
