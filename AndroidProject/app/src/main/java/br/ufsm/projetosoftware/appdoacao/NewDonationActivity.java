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

/**
 * Activity de cadastrar produto
 */
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
    private String uid;

    /**
     * Inicializa a Activity
     * @param savedInstanceState
     */
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
/**
 * Cria um adaptador do ENUM com os tipos para o spiner da View
 */
        newDonationView.setTipoValues(new ArrayAdapter<TipoEnum>(this, android.R.layout.simple_list_item_1, TipoEnum.values()));
        image = null;
        DONATION_URL = getString(R.string.newDonationURL);
        initCallback();//Recebe
        volleyService = new VolleyServiceString(resultCallback, this);//Envia
    }

    /**
     * Pede autorização para acessar os arquivos do celular;
     * @return
     */
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

    /**
     * Espera a resposta da solicitação de permição do usuário
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_STORAGE) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                pickImage();
            }
        }
    }

    /**
     * Ao clicar em selecionar imagem;
     */
    @Override
    public void onSelectImageClick() {
        if(!mayRequestStorage()){
            return;
        }
        pickImage();

    }

    /**
     * Método parta escolher a img;
     */
    private void pickImage(){
        Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
        getIntent.setType("image/*");

        Intent pickIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");

        Intent chooserIntent = Intent.createChooser(getIntent, "Selecione a imagem");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});
/**
 * Inicia a activity para escolher a imagem, espera o resultado;
 */
        startActivityForResult(chooserIntent, PICK_IMAGE);
    }

    /**
     * Ao selecionar imagem, abre este metodo
     * @param requestCode
     * @param resultCode
     * @param data
     */
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

    /**
     * Ao clicar em registrar produto
     */
    @Override
    public void onRegisterDonationClick() {
        //TODO
        doacao = new Produto();
        doacao.setTipo(newDonationView.getTipo());
        doacao.setCategoria(newDonationView.getCategoria());
        doacao.setTitulo(newDonationView.getTitulo());
        doacao.setDescricao(newDonationView.getDescricao());
        doacao.setAuthToken(authToken);
        if(image!=null){
            doacao.setImagem(image);
        }
        //Log.d("Token", authToken);
        String doacaoJSON = new Gson().toJson(doacao, Produto.class);
        largeLog("json", doacaoJSON);
        volleyService.postDataVolley(POSTDOACAO, DONATION_URL, doacaoJSON);
    }

    /**
     * Abre a activity do produto e mostra a doação cadastrada
     */
    private void onRegisterDonationComplete(){
        Intent i = new Intent(NewDonationActivity.this, DonationActivity.class);
        i.putExtra("Titulo", doacao.getTitulo());
        i.putExtra("Categoria", doacao.getTipoCategoria());
        i.putExtra("Descricao", doacao.getDescricao());
        i.putExtra("ImageId", doacao.getImageId());
        i.putExtra("uid", uid);
        startActivity(i);
    }

    /**
     * Se foi recebido uma resposta do servidor, avisa o usuário;
     * @param response
     */
    private void postDoacaoSucess(String response){
        System.out.println(response);
        NewDonationResponse donationResponse = new Gson().fromJson(response, NewDonationResponse.class);
        doacao.setImageId(donationResponse.getImageId());
        uid = donationResponse.getUid();
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
                if(requestType.equals(POSTDOACAO)){
                    postDoacaoSucess(response);
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
                Toast.makeText(NewDonationActivity.this,error.toString(),Toast.LENGTH_LONG).show();
            }
        };
    }

    /**
     * Quando o usuário seleciona o tipo, carrega as categorias
     * @param id
     */
    @Override
    public void onSelectTipo(long id) {
        if(id == 0){//Materiais construção
            newDonationView.setCategoriaValues(new ArrayAdapter<CategoriaMaterialConstrucaoEnum>(this, android.R.layout.simple_list_item_1, CategoriaMaterialConstrucaoEnum.values()));
        }
        else if(id == 1){//Roupas
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
