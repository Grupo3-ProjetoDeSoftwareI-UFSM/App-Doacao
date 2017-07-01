package br.ufsm.projetosoftware.appdoacao;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.List;

import br.ufsm.projetosoftware.appdoacao.models.Produto;
import br.ufsm.projetosoftware.appdoacao.models.StatusEnum;
import br.ufsm.projetosoftware.appdoacao.network.IResultString;
import br.ufsm.projetosoftware.appdoacao.network.ListPost;
import br.ufsm.projetosoftware.appdoacao.network.SearchResponse;
import br.ufsm.projetosoftware.appdoacao.network.VolleyServiceString;
import br.ufsm.projetosoftware.appdoacao.view.ListDonationView;
import br.ufsm.projetosoftware.appdoacao.view.ListDonationViewImpl;

public class ListDonationActivity extends AppCompatActivity
        implements ListDonationView.SelectSpinnerListener, ListDonationView.SelectListListener{

    private ListDonationView listDonationView;
    private IResultString resultCallback = null;
    private VolleyServiceString volleyService;
    private final String POSTLIST = "POSTLIST";
    private String listURL;
    private int uid;
    private String authToken;
    private SharedPreferences loginSettings;
    private List<Produto> listaProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_donation);
        listDonationView = new ListDonationViewImpl(getWindow().getDecorView().getRootView());
        listDonationView.setSelectListListener(this);
        listDonationView.setSelectSpinnerListener(this);
        listDonationView.setSpinnerStatus(new ArrayAdapter<StatusEnum>(this, android.R.layout.simple_list_item_1, StatusEnum.values()));
        listURL = getString(R.string.listDonationURL);
        initCallback();
        volleyService = new VolleyServiceString(resultCallback, this);
        loginSettings = getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
        authToken = loginSettings.getString("authToken", null);
        uid = loginSettings.getInt("uid", 0);

    }

    @Override
    public void onSelectSpinner(int pos) {
        getDonationList(listDonationView.getStatus());
    }

    @Override
    public void onSelectList(int id) {
        Log.d("IdfromLista", String.valueOf(id));
        Produto produto = listaProduto.get(id);
        Log.d("IdProduto", produto.getPid().toString());
        if(produto != null){
            Intent toDonationActivity = new Intent(ListDonationActivity.this, DonationActivity.class);
            toDonationActivity.putExtra("Titulo", produto.getTitulo());
            toDonationActivity.putExtra("Categoria", produto.getTipoCategoria());
            toDonationActivity.putExtra("Descricao", produto.getDescricao());
            toDonationActivity.putExtra("ImageId", produto.getImageId());
            toDonationActivity.putExtra("doacaoId", produto.getPid());
            toDonationActivity.putExtra("intent", DonationActivity.ICANCELA);
            startActivity(toDonationActivity);

        }
    }

    private void getDonationList(String status){
        ListPost listPost = new ListPost();
        listPost.setUid(uid);
        listPost.setAuthToken(authToken);
        listPost.setStatus(status);
        String listPostJson = new Gson().toJson(listPost, ListPost.class);
        Log.d("listPostJson", listPostJson + listURL);
        volleyService.postDataVolley(POSTLIST, listURL, listPostJson);
    }

    private void postListSucess(String response){
        Log.d("postListsucess", response);
        SearchResponse searchResponse = new Gson().fromJson(response, SearchResponse.class);
        if(searchResponse != null) {
            listaProduto = searchResponse.getListaProduto();
            ListAdapter adapter = new SimpleAdapter(ListDonationActivity.this,
                    searchResponse.getMapProduto(),
                    R.layout.item_list,
                    new String[]{searchResponse.KEY_TITULO, searchResponse.KEY_CATEGORIA},
                    new int[]{R.id.titulo, R.id.tipocategoria});
            listDonationView.setListAdapter(adapter);
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
                Toast.makeText(ListDonationActivity.this,error.toString(),Toast.LENGTH_LONG).show();
            }
        };
    }
}
