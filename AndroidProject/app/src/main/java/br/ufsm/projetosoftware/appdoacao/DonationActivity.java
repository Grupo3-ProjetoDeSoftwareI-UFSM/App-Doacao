package br.ufsm.projetosoftware.appdoacao;

import android.content.Context;
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
import br.ufsm.projetosoftware.appdoacao.network.VolleyServiceString;
import br.ufsm.projetosoftware.appdoacao.view.DonationView;
import br.ufsm.projetosoftware.appdoacao.view.DonationViewImpl;

/**
 * Created by Felipe on 17/06/2017.
 */

public class DonationActivity extends AppCompatActivity
        implements DonationView.ImageButtonListener,
        DonationView.SolicitarButtonListener{

    private DonationView donationView;
    private IResultString resultCallback = null;
    private VolleyServiceString volleyService;
    private static String POSTSOLICITACAO = "POSTSOLICITACAO";
    private static String GETIMAGE = "GETIMAGE";
    private String SOLICITACAO_URL;
    private String IMAGEM_URL;
    private Bitmap image;
    private SharedPreferences loginSettings;
    private String authToken;
    private Bundle extras;
    Produto doacao;
    private String imageId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);
        donationView = new DonationViewImpl(getWindow().getDecorView().getRootView());
        donationView.setImageListener(this);
        donationView.setSolicitarListener(this);
        loginSettings = getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
        authToken = loginSettings.getString("authToken", null);
        SOLICITACAO_URL = getString(R.string.solicitarDoacaoURL);
        IMAGEM_URL = getString(R.string.getImageURL);
        initCallback();
        volleyService = new VolleyServiceString(resultCallback, this);
        extras = getIntent().getExtras();
        initialize();
    }

    private void initialize() {
        if(extras != null){
            donationView.setTitulo(extras.getString("Titulo", "Titulo"));
            donationView.setCategoria(extras.getString("Categoria", "Categoria"));
            donationView.setDescricao(extras.getString("Descricao", "Descricao"));
            imageId = extras.getString("ImageId");
        }

    }

    @Override
    public void onImageClick() {
        doacao = new Produto();
        doacao.setImageId(imageId);
        String doacaoJson = new Gson().toJson(doacao);
        volleyService.postDataVolley(GETIMAGE, IMAGEM_URL, doacaoJson);
    }

    @Override
    public void onSolicitarClick() {
        //TODO
    }

    private void postSolicitacaoSucess(String response){
        //TODO
    }

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

    private void initCallback(){
        resultCallback = new IResultString() {
            @Override
            public void notifySuccess(String requestType, String response) {
                if(requestType.equals(POSTSOLICITACAO)){
                    postSolicitacaoSucess(response);
                }
                if(requestType.equals(GETIMAGE)){
                    getImageSucess(response);
                }
            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("Erro na conexao", error.toString());
                Toast.makeText(DonationActivity.this,error.toString(),Toast.LENGTH_LONG).show();
            }
        };
    }
}
