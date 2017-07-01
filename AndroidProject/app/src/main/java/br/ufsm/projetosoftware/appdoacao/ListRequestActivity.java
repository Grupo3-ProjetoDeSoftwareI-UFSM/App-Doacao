package br.ufsm.projetosoftware.appdoacao;

import android.content.Context;
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

import br.ufsm.projetosoftware.appdoacao.models.StatusEnum;
import br.ufsm.projetosoftware.appdoacao.network.IResultString;
import br.ufsm.projetosoftware.appdoacao.network.ListPost;
import br.ufsm.projetosoftware.appdoacao.network.SearchResponse;
import br.ufsm.projetosoftware.appdoacao.network.VolleyServiceString;
import br.ufsm.projetosoftware.appdoacao.view.ListDonationView;
import br.ufsm.projetosoftware.appdoacao.view.ListDonationViewImpl;
import br.ufsm.projetosoftware.appdoacao.view.ListRequestView;
import br.ufsm.projetosoftware.appdoacao.view.ListRequestViewImpl;

public class ListRequestActivity extends AppCompatActivity implements ListRequestView.SelectListListener {

    private ListRequestView listRequestView;
    private IResultString resultCallback = null;
    private VolleyServiceString volleyService;
    private final String POSTLIST = "POSTLIST";
    private String listURL;
    private int uid;
    private String authToken;
    private SharedPreferences loginSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_request);
        listRequestView = new ListRequestViewImpl(getWindow().getDecorView().getRootView());
        listRequestView.setSelectListListener(this);
        listURL = getString(R.string.listRequestURL);
        initCallback();
        volleyService = new VolleyServiceString(resultCallback, this);
        loginSettings = getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
        authToken = loginSettings.getString("authToken", null);
        uid = loginSettings.getInt("uid", 0);
        getDonationList();
    }

    @Override
    public void onSelectList(int id) {

    }

    private void getDonationList(){
        ListPost listPost = new ListPost();
        listPost.setUid(uid);
        listPost.setAuthToken(authToken);
        String listPostJson = new Gson().toJson(listPost, ListPost.class);
        Log.d("listPostJson", listPostJson + listURL);
        volleyService.postDataVolley(POSTLIST, listURL, listPostJson);
    }

    private void postListSucess(String response){
        Log.d("postListsucess", response);
        SearchResponse searchResponse = new Gson().fromJson(response, SearchResponse.class);
        if(searchResponse != null) {
            ListAdapter adapter = new SimpleAdapter(ListRequestActivity.this,
                    searchResponse.getMapProduto(),
                    R.layout.item_list,
                    new String[]{searchResponse.KEY_TITULO, searchResponse.KEY_CATEGORIA},
                    new int[]{R.id.titulo, R.id.tipocategoria});
            listRequestView.setListAdapter(adapter);
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
                Toast.makeText(ListRequestActivity.this,error.toString(),Toast.LENGTH_LONG).show();
            }
        };
    }
}
