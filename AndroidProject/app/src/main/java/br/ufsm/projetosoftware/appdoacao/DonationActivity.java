package br.ufsm.projetosoftware.appdoacao;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import br.ufsm.projetosoftware.appdoacao.models.Produto;
import br.ufsm.projetosoftware.appdoacao.network.IResultString;
import br.ufsm.projetosoftware.appdoacao.network.ImageResponse;
import br.ufsm.projetosoftware.appdoacao.network.RequestPost;
import br.ufsm.projetosoftware.appdoacao.network.RequestResult;
import br.ufsm.projetosoftware.appdoacao.network.VolleyServiceString;
import br.ufsm.projetosoftware.appdoacao.view.DonationView;
import br.ufsm.projetosoftware.appdoacao.view.DonationViewImpl;

/**
 * View de visualização do produto
 * Created on 17/06/2017.
 */

public class DonationActivity extends AppCompatActivity
        implements DonationView.ImageButtonListener,
        DonationView.SolicitarButtonListener{

    private DonationView donationView;
    private IResultString resultCallback = null;
    private VolleyServiceString volleyService;
    private static String POSTCANCELAR = "POSTCANCELAR";
    private static String GETIMAGE = "GETIMAGE";
    private String CANCELAR_URL;
    private String IMAGEM_URL;
    private Bitmap image;
    private SharedPreferences loginSettings;
    private String authToken;
    private Bundle extras;
    private Produto doacao;
    private String imageId;
    private String doadorId;
    private int uid;
    private int doacaoId;
    public static final int ISOLICITA = 1;
    public static final int ICANCELA = 2;
    public static final int IVISUALIZA = 3;
    int intent;

    /**
     * Inicializa a Activity
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);
        donationView = new DonationViewImpl(getWindow().getDecorView().getRootView());
        donationView.setImageListener(this);
        donationView.setSolicitarListener(this);
        loginSettings = getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
        authToken = loginSettings.getString("authToken", null);
        uid = loginSettings.getInt("uid", 0);
        CANCELAR_URL = getString(R.string.cancelarDoacaoURL);
        IMAGEM_URL = getString(R.string.getImageURL);
        initCallback();
        volleyService = new VolleyServiceString(resultCallback, this);
        extras = getIntent().getExtras();
        initialize();
    }

    /**
     * Recebe os dados das activitys e carrega na view;
     */
    private void initialize() {
        if(extras != null){
            donationView.setTitulo(extras.getString("Titulo", "Titulo"));
            donationView.setCategoria(extras.getString("Categoria", "Categoria"));
            donationView.setDescricao(extras.getString("Descricao", "Descricao"));
            imageId = extras.getString("ImageId");
            doadorId = extras.getString("uid");
            doacaoId = extras.getInt("doacaoId");
            intent = extras.getInt("intent");
            setButton();
        }

    }

    private void setButton() {
        switch (intent){
            case ISOLICITA:
                donationView.setButtonText("CONFIRMAR SOLICITAÇÃO");
                break;
            case ICANCELA:
                donationView.setButtonText("CANCELAR DOAÇÃO");
                break;
            case IVISUALIZA:
                donationView.disableSolicitarButton();
                break;
        }
    }

    /**
     * Ao clicar no botão "Ver imagem", carrega a img;
     */
    @Override
    public void onImageClick() {
        doacao = new Produto();
        doacao.setImageId(imageId);
        String doacaoJson = new Gson().toJson(doacao);
        volleyService.postDataVolley(GETIMAGE, IMAGEM_URL, doacaoJson);
    }

    /**
     * Ao clicar no botão "Solicitar doação" abre a view de solicitar pedido(enviar msg)
     */
    @Override
    public void onSolicitarClick() {
        switch (intent){
            case ISOLICITA:
                solicitar();
                break;
            case ICANCELA:
                cancelar();
                break;
            case IVISUALIZA:
                donationView.disableSolicitarButton();
                break;
        }
    }

    private void solicitar(){
        Intent toRequest = new Intent(DonationActivity.this, RequestActivity.class);
        toRequest.putExtra("Titulo", extras.getString("Titulo"));
        toRequest.putExtra("doacaoId", doacaoId);
        startActivity(toRequest);
    }

    private void cancelar(){
        RequestPost requestPost = new RequestPost();
        requestPost.setAuthToken(authToken);
        requestPost.setDoacao(doacaoId);
        String requestPostJson = new Gson().toJson(requestPost, RequestPost.class);
        Log.d("requestCancela", requestPostJson);
        volleyService.postDataVolley(POSTCANCELAR, CANCELAR_URL, requestPostJson);
    }

    /**
     * Recebe a resposta do servidor, referente a solicitação do produto
     * @param response
     */
    private void postCancelarSucess(String response){
        Log.d("cancelamentoResponse", response);
        RequestResult requestResult = new Gson().fromJson(response, RequestResult.class);
        switch (requestResult.getSuccess()){
            case 0:
                Toast.makeText(DonationActivity.this, "Doacao cancelada com sucesso.",Toast.LENGTH_LONG).show();
                Intent toListDonation= new Intent(DonationActivity.this, ListDonationActivity.class);
                startActivity(toListDonation);
                break;
            case 1:
                Toast.makeText(DonationActivity.this, "Erro no cancelamento.",Toast.LENGTH_LONG).show();
                break;
        }
    }

    /**
     * Recebe a img do servidor e mostra na tela
     * @param response
     */
    private void getImageSucess(String response){
        Log.d("response", response);
        ImageResponse imageResponse = new Gson().fromJson(response, ImageResponse.class);
        switch (imageResponse.getReturnCode()){
            case 0:
                doacao.setImagem(imageResponse.getImage());
                donationView.setImagem(doacao.getImagem());
                break;
            case 1:
                Toast.makeText(DonationActivity.this, imageResponse.getMessage(),Toast.LENGTH_LONG).show();
                break;
        }
    }
    /**
     * Receber uma resposta do servidor
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
                if(requestType.equals(POSTCANCELAR)){
                    postCancelarSucess(response);
                }
                if(requestType.equals(GETIMAGE)){
                    getImageSucess(response);
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
                Toast.makeText(DonationActivity.this,error.toString(),Toast.LENGTH_LONG).show();
            }
        };
    }
}
