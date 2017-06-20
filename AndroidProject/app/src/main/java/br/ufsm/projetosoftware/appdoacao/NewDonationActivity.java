package br.ufsm.projetosoftware.appdoacao;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.io.IOException;

import br.ufsm.projetosoftware.appdoacao.models.CategoriaMaterialConstrucaoEnum;
import br.ufsm.projetosoftware.appdoacao.models.CategoriaRoupaEnum;
import br.ufsm.projetosoftware.appdoacao.models.Produto;
import br.ufsm.projetosoftware.appdoacao.models.TipoEnum;
import br.ufsm.projetosoftware.appdoacao.network.IResultString;
import br.ufsm.projetosoftware.appdoacao.network.NewDonationResponse;
import br.ufsm.projetosoftware.appdoacao.network.VolleyServiceString;
import br.ufsm.projetosoftware.appdoacao.view.NewDonationView;
import br.ufsm.projetosoftware.appdoacao.view.NewDonationViewImpl;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

public class NewDonationActivity extends AppCompatActivity
        implements NewDonationView.SelectImageListener,
        NewDonationView.RegisterDonationListener,
        NewDonationView.SelectTipoListener{

    private NewDonationView newDonationView;
    private IResultString resultCallback = null;
    private VolleyServiceString volleyService;
    private static String POSTDOACAO = "POSTDOACAO";
    private String DONATION_URL;
    private Bitmap image;
    private static int PICK_IMAGE = 1;
    private static int REQUEST_READ_STORAGE = 2;
    private SharedPreferences loginSettings;
    private String authToken;
    private Produto doacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_donation);
        loginSettings = getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
        authToken = loginSettings.getString("authToken", null);
        newDonationView = new NewDonationViewImpl(getWindow().getDecorView().getRootView());
        newDonationView.setSelectImageListener(this);
        newDonationView.setRegisterDonationListener(this);
        newDonationView.setSelectTipoListener(this);
        newDonationView.setTipoValues(new ArrayAdapter<TipoEnum>(this, android.R.layout.simple_list_item_1, TipoEnum.values()));
        image = null;
        DONATION_URL = getString(R.string.newDonationURL);
        initCallback();
        volleyService = new VolleyServiceString(resultCallback, this);
    }

    private boolean mayRequestStorage() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_EXTERNAL_STORAGE)) {
            //TODO exibir justificativa da permissão
            requestPermissions(new String[]{READ_EXTERNAL_STORAGE}, REQUEST_READ_STORAGE);
        } else {
            requestPermissions(new String[]{READ_EXTERNAL_STORAGE}, REQUEST_READ_STORAGE);
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_STORAGE) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                pickImage();
            }
        }
    }

    @Override
    public void onSelectImageClick() {
        if(!mayRequestStorage()){
            return;
        }
        pickImage();

    }

    private void pickImage(){
        Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
        getIntent.setType("image/*");

        Intent pickIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");

        Intent chooserIntent = Intent.createChooser(getIntent, "Selecione a imagem");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});

        startActivityForResult(chooserIntent, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE){
            if(data != null){
                try {
                    image = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                newDonationView.setImage(image);
            }
        }
    }

    @Override
    public void onRegisterDonationClick() {
        //TODO
        doacao = new Produto();
        doacao.setTipo(newDonationView.getTipo());
        doacao.setCategoria(newDonationView.getCategoria());
        doacao.setTitulo(newDonationView.getTitulo());
        doacao.setDescricao(newDonationView.getDescricao());
        doacao.setImagem(image);
        doacao.setAuthToken(authToken);
        //Log.d("Token", authToken);
        String doacaoJSON = new Gson().toJson(doacao, Produto.class);
        largeLog("json", doacaoJSON);
        volleyService.postDataVolley(POSTDOACAO, DONATION_URL, doacaoJSON);
    }

    private void onRegisterDonationComplete(){
        Intent i = new Intent(NewDonationActivity.this, DonationActivity.class);
        i.putExtra("Titulo", doacao.getTitulo());
        i.putExtra("Categoria", doacao.getTipoCategoria());
        i.putExtra("Descricao", doacao.getDescricao());
        i.putExtra("ImageId", doacao.getImageId());
        startActivity(i);
    }

    private void postDoacaoSucess(String response){
        System.out.println(response);
        NewDonationResponse donationResponse = new Gson().fromJson(response, NewDonationResponse.class);
        doacao.setImageId(donationResponse.getImageId());
        switch (donationResponse.getReturnCode()){
            case 0:
                Toast.makeText(NewDonationActivity.this,donationResponse.getMessage(),Toast.LENGTH_LONG).show();
                onRegisterDonationComplete();
                break;
            case 1:
                Toast.makeText(NewDonationActivity.this,donationResponse.getMessage(),Toast.LENGTH_LONG).show();
                break;
            case 2:
                Toast.makeText(NewDonationActivity.this, donationResponse.getMessage(), Toast.LENGTH_LONG).show();
                //TODO voltar para tela de login
                break;
            case 3:
                Toast.makeText(NewDonationActivity.this, donationResponse.getMessage(), Toast.LENGTH_LONG).show();
                break;
        }
    }

    private void initCallback(){
        resultCallback = new IResultString() {
            @Override
            public void notifySuccess(String requestType, String response) {
                if(requestType.equals(POSTDOACAO)){
                    postDoacaoSucess(response);
                }
            }

            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("Erro na conexao", error.toString());
                Toast.makeText(NewDonationActivity.this,error.toString(),Toast.LENGTH_LONG).show();
            }
        };
    }

    @Override
    public void onSelectTipo(long id) {
        if(id == 0){
            newDonationView.setCategoriaValues(new ArrayAdapter<CategoriaMaterialConstrucaoEnum>(this, android.R.layout.simple_list_item_1, CategoriaMaterialConstrucaoEnum.values()));
        }
        else if(id == 1){
            newDonationView.setCategoriaValues(new ArrayAdapter<CategoriaRoupaEnum>(this, android.R.layout.simple_list_item_1, CategoriaRoupaEnum.values()));
        }
    }

    public static void largeLog(String tag, String content) {
        if (content.length() > 4000) {
            Log.d(tag, content.substring(0, 4000));
            largeLog(tag, content.substring(4000));
        } else {
            Log.d(tag, content);
        }
    }
}
