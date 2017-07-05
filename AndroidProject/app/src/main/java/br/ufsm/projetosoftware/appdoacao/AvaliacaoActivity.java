package br.ufsm.projetosoftware.appdoacao;

import android.content.Context;
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

/**
 * Activity da tela de avaliação do doador ou beneficiário
 */
public class AvaliacaoActivity extends AppCompatActivity implements AvaliacaoView.ConfirmarButtonListener {

    private AvaliacaoView avaliacaoView;
    private IResultString resultCallback = null;
    private VolleyServiceString volleyService;
    private static String POSTAVALIACAO = "POSTAVALIACAO";
    private static String UPDATEDOACAO = "UPDATEDOACAO";
    private String AVALIACAO_URL;
    private String UPDATE_DOACAO_URL;
    private SharedPreferences loginSettings;
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacao);
        avaliacaoView = new AvaliacaoViewImpl(getWindow().getDecorView().getRootView());
        avaliacaoView.setConfirmarButtonListener(this);
        extras = getIntent().getExtras();
        loginSettings = getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
        AVALIACAO_URL = getString(R.string.avaliacaoURL);
        UPDATE_DOACAO_URL = getString(R.string.updateDoacaoURL);
        initCallback();
        volleyService = new VolleyServiceString(resultCallback, this);
    }

    /**
     * Envia dados da avaliação para o servidor ao clicar no botão de confirmação
     */
    @Override
    public void onConfirmarButtonClick() {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setAvaliacao(avaliacaoView.getAvaliacao());
        avaliacao.setComentario(avaliacaoView.getComentario());
        avaliacao.setIdSolicitacao(extras.getInt("idSolicitacao"));
        avaliacao.setIdAvaliado(extras.getInt("idAvaliado"));
        avaliacao.setIdAvaliador(loginSettings.getInt("uid", -1));
        String avaliacaoJson = new Gson().toJson(avaliacao, Avaliacao.class);
        if(extras.containsKey("intent")){
            volleyService.postDataVolley(UPDATEDOACAO, UPDATE_DOACAO_URL, avaliacaoJson);
        }
        volleyService.postDataVolley(POSTAVALIACAO, AVALIACAO_URL, avaliacaoJson);
    }

    /**
     * Recebe a resposta do servidor sobre o cadastro de nova avaliação.
     * Volta a tela inicial em caso de sucesso.
     * @param response
     */
    private void postAvaliacaoSuccess(String response){
        AvaliacaoResponse avaliacaoResponse = new Gson().fromJson(response, AvaliacaoResponse.class);
        if(avaliacaoResponse.getSuccess() == 0){
            Toast.makeText(AvaliacaoActivity.this,"Doacao concluida",Toast.LENGTH_LONG).show();
            Intent toMain= new Intent(AvaliacaoActivity.this, MainActivity.class);
            startActivity(toMain);
            finish();
        }

    }

    /**
     * Recebe resposta de sucesso ou falho na comunicação com o servidor.
     * Executa o método notifySuccess e passa a resposta pro método postAvaliacaoSucess em caso de sucesso.
     * Notifica o usuário do erro na conexão em caso de falha.
     */
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
