package br.ufsm.projetosoftware.appdoacao;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import br.ufsm.projetosoftware.appdoacao.models.Avaliacao;
import br.ufsm.projetosoftware.appdoacao.models.Solicitacao;
import br.ufsm.projetosoftware.appdoacao.network.AvaliacaoResponse;
import br.ufsm.projetosoftware.appdoacao.network.IResultString;
import br.ufsm.projetosoftware.appdoacao.network.VolleyServiceString;
import br.ufsm.projetosoftware.appdoacao.view.AvaliacaoView;
import br.ufsm.projetosoftware.appdoacao.view.AvaliacaoViewImpl;

public class AvaliacaoActivity extends AppCompatActivity implements AvaliacaoView.ConfirmarButtonListener {

    private AvaliacaoView avaliacaoView;
    private IResultString resultCallback = null;
    private VolleyServiceString volleyService;
    private static String POSTAVALIACAO = "POSTAVALIACAO";
    private String AVALIACAO_URL;
    private SharedPreferences loginSettings;
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacao);
        avaliacaoView = new AvaliacaoViewImpl(getWindow().getDecorView().getRootView());
        avaliacaoView.setConfirmarButtonListener(this);
        extras = getIntent().getExtras();
        AVALIACAO_URL = getString(R.string.avaliacaoURL);
        initCallback();
        volleyService = new VolleyServiceString(resultCallback, this);
    }

    @Override
    public void onConfirmarButtonClick() {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setAvaliacao(avaliacaoView.getAvaliacao());
        avaliacao.setComentario(avaliacaoView.getComentario());
        avaliacao.setIdSolicitacao(extras.getInt("idSolicitacao"));
        avaliacao.setIdAvaliado(extras.getInt("idAvaliado"));
        avaliacao.setIdAvaliador(extras.getInt("idAvaliador"));
        String avaliacaoJson = new Gson().toJson(avaliacao, Avaliacao.class);
        volleyService.postDataVolley(POSTAVALIACAO, AVALIACAO_URL, avaliacaoJson);
    }

    private void postAvaliacaoSuccess(String response){
        AvaliacaoResponse avaliacaoResponse = new Gson().fromJson(response, AvaliacaoResponse.class);
        if(avaliacaoResponse.getSuccess() == 0){
            Toast.makeText(AvaliacaoActivity.this,"Doacao concluida",Toast.LENGTH_LONG).show();
            Intent toMain= new Intent(AvaliacaoActivity.this, MainActivity.class);
            startActivity(toMain);
            finish();
        }

    }

    private void initCallback(){
        resultCallback = new IResultString() {
            /**
             * Sucesso na conexão com o servidor
             * @param requestType
             * @param response
             */
            @Override
            public void notifySuccess(String requestType, String response) {
                if(requestType.equals(POSTAVALIACAO)){
                    postAvaliacaoSuccess(response);
                }
            }
            /**
             * Erro na conexão com o servidor(Ex: Sem internet)
             * @param requestType
             * @param error
             */
            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("Erro na conexao", error.toString());
                Toast.makeText(AvaliacaoActivity.this,error.toString(),Toast.LENGTH_LONG).show();
            }
        };
    }
}
