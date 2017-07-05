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

import br.ufsm.projetosoftware.appdoacao.models.Produto;
import br.ufsm.projetosoftware.appdoacao.network.IResultString;
import br.ufsm.projetosoftware.appdoacao.network.RequestPost;
import br.ufsm.projetosoftware.appdoacao.network.RequestResult;
import br.ufsm.projetosoftware.appdoacao.network.VolleyServiceString;
import br.ufsm.projetosoftware.appdoacao.view.RequestView;
import br.ufsm.projetosoftware.appdoacao.view.RequestViewImpl;

/**
 * Activity da tela de solicitação de doação
 */
public class RequestActivity
        extends AppCompatActivity
        implements RequestView.SolicitarButtonListener {

    RequestView requestView;
    private IResultString resultCallback = null;
    private VolleyServiceString volleyService;
    private static String POSTSOLICITACAO = "POSTSOLICITACAO";
    private String SOLICITACAO_URL;
    private SharedPreferences loginSettings;
    private String authToken;
    private Bundle extras;
    private String doadorId;
    private int uid;
    private int doacaoId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        requestView = new RequestViewImpl(getWindow().getDecorView().getRootView());
        requestView.setSolicitarButtonListener(this);
        loginSettings = getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
        authToken = loginSettings.getString("authToken", null);
        uid = loginSettings.getInt("uid", 0);
        SOLICITACAO_URL = getString(R.string.solicitarDoacaoURL);
        initCallback();
        volleyService = new VolleyServiceString(resultCallback, this);
        extras = getIntent().getExtras();
        requestView.setTitulo(extras.getString("Titulo"));
    }

    /**
     * Ao apertar no botão de solicitar envia dados para o servidor
     */
    @Override
    public void onSolicitarButton() {
        doacaoId = extras.getInt("doacaoId");
        Log.d("doacaoId", String.valueOf(doacaoId));
        Log.d("uid", String.valueOf(uid));
        RequestPost requestPost = new RequestPost(doacaoId, uid);
        requestPost.setJustificativa(requestView.getJustificativa());
        requestPost.setAuthToken(authToken);
        String requestPostJson = new Gson().toJson(requestPost, RequestPost.class);
        Log.d("requestJson", requestPostJson);
        volleyService.postDataVolley(POSTSOLICITACAO, SOLICITACAO_URL, requestPostJson);
    }

    /**
     * Exibe resposta do cadastro de solicitação ao usuário
     * @param response
     */
    private void postSolicitacaoSucess(String response){
        Log.d("solicitacaoResponse", response);
        RequestResult requestResult = new Gson().fromJson(response, RequestResult.class);
        switch (requestResult.getSuccess()){
            case 0:
                Toast.makeText(RequestActivity.this, "Solicitação efetuada com sucesso.",Toast.LENGTH_LONG).show();
                toListRequest();
                break;
            case 1:
                Toast.makeText(RequestActivity.this, "Erro na solicitação.",Toast.LENGTH_LONG).show();
                break;
            case 2:
                Toast.makeText(RequestActivity.this, "Você não pode solicitar sua própria doação.",Toast.LENGTH_LONG).show();
                break;
            case 3:
                Toast.makeText(RequestActivity.this, "Produto já solicitado",Toast.LENGTH_LONG).show();
                break;
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
                if(requestType.equals(POSTSOLICITACAO)){
                    postSolicitacaoSucess(response);
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
                Toast.makeText(RequestActivity.this,error.toString(),Toast.LENGTH_LONG).show();
            }
        };
    }

    /**
     * Inicia activity de lista de solicitações
     */
    private void toListRequest(){
        Intent toListRequest = new Intent(RequestActivity.this, ListRequestActivity.class);
        startActivity(toListRequest);
        finish();
    }
}
