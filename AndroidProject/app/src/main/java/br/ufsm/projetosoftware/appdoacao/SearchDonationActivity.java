package br.ufsm.projetosoftware.appdoacao;

import android.content.Intent;
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

import br.ufsm.projetosoftware.appdoacao.R;
import br.ufsm.projetosoftware.appdoacao.models.CategoriaMaterialConstrucaoBuscaEnum;
import br.ufsm.projetosoftware.appdoacao.models.CategoriaMaterialConstrucaoEnum;
import br.ufsm.projetosoftware.appdoacao.models.CategoriaRoupaBuscaEnum;
import br.ufsm.projetosoftware.appdoacao.models.CategoriaRoupaEnum;
import br.ufsm.projetosoftware.appdoacao.models.Produto;
import br.ufsm.projetosoftware.appdoacao.models.TipoBuscaEnum;
import br.ufsm.projetosoftware.appdoacao.models.TipoEnum;
import br.ufsm.projetosoftware.appdoacao.network.IResultString;
import br.ufsm.projetosoftware.appdoacao.network.SearchPost;
import br.ufsm.projetosoftware.appdoacao.network.SearchResponse;
import br.ufsm.projetosoftware.appdoacao.network.VolleyServiceString;
import br.ufsm.projetosoftware.appdoacao.view.DonationView;
import br.ufsm.projetosoftware.appdoacao.view.NewDonationView;
import br.ufsm.projetosoftware.appdoacao.view.SearchDonationView;
import br.ufsm.projetosoftware.appdoacao.view.SearchDonationViewImpl;

/**
 * Activity para buscar os produtos
 */
public class SearchDonationActivity extends AppCompatActivity
        implements SearchDonationView.BuscaListener,
        SearchDonationView.ListListener,
        SearchDonationView.SelectTipoListener{

    private SearchDonationView searchDonationView;
    private IResultString resultCallback = null;
    private VolleyServiceString volleyService;
    private final String POSTSEARCH = "POSTSEARCH";
    private String searchURL;
    private List<Produto> searchList;

    /**
     * Inicializa a Activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_donation);
        searchDonationView = new SearchDonationViewImpl(getWindow().getDecorView().getRootView());
        searchDonationView.setBuscaListener(this);
        searchDonationView.setListListener(this);
        searchDonationView.setSelectTipoListener(this);
        searchURL = getString(R.string.searchURL);
        searchDonationView.setTipoValues(new ArrayAdapter<TipoBuscaEnum>(this, android.R.layout.simple_list_item_1, TipoBuscaEnum.values()));
        initCallback();
        volleyService = new VolleyServiceString(resultCallback, this);
    }

    /**
     * Ao clicar no botão 'Buscar', executa o metodo
     */
    @Override
    public void onBuscaClick() {
        SearchPost search = new SearchPost();
        search.setBusca(searchDonationView.getBusca());
        search.setTipo(searchDonationView.getTipo());
        search.setCategoria(searchDonationView.getCategoria());
        String searchJson = new Gson().toJson(search);
        Log.d("searchJson", searchJson);
        volleyService.postDataVolley(POSTSEARCH, searchURL, searchJson);
    }

    /**
     * Ao clicar sobre algum item da lista de produtos
     * @param id
     */
    @Override
    public void onListItemClick(int id) {
        Intent viewItem = new Intent(SearchDonationActivity.this, DonationActivity.class);
        Produto produto = searchList.get(id);
        viewItem.putExtra("Titulo", produto.getTitulo());
        viewItem.putExtra("Categoria", produto.getTipoCategoria());
        viewItem.putExtra("Descricao", produto.getDescricao());
        viewItem.putExtra("ImageId", produto.getImageId());
        viewItem.putExtra("uid", produto.getDoador());
        viewItem.putExtra("doacaoId", produto.getPid());
        viewItem.putExtra("intent", DonationActivity.ISOLICITA);
        startActivity(viewItem);
    }

    /**
     * Ao selecionar o tipo, carrega as categorias pertencentes.
     * @param id
     */
    @Override
    public void onSelectTipo(long id) {
        if(id == 1){
            searchDonationView.setCategoriaValues(new ArrayAdapter<CategoriaMaterialConstrucaoBuscaEnum>(this, android.R.layout.simple_list_item_1, CategoriaMaterialConstrucaoBuscaEnum.values()));
        }
        else if(id == 2){
            searchDonationView.setCategoriaValues(new ArrayAdapter<CategoriaRoupaBuscaEnum>(this, android.R.layout.simple_list_item_1, CategoriaRoupaBuscaEnum.values()));
        }
    }

    /**
     * Se conseguir conectar com o servidor, traz a informação;
     * @param response
     */
    private void postSearchSucess(String response){
        Log.d("respostaSearch", response);
        if(response.length() < 20){
            Toast.makeText(SearchDonationActivity.this,"Nenhum resultado encontrado",Toast.LENGTH_LONG).show();
        }
        else {
            SearchResponse searchResponse = new Gson().fromJson(response, SearchResponse.class);
            searchList = searchResponse.getListaProduto();
            ListAdapter adapter = new SimpleAdapter(SearchDonationActivity.this,
                    searchResponse.getMapProduto(),
                    R.layout.item_list,
                    new String[]{searchResponse.KEY_TITULO, searchResponse.KEY_CATEGORIA},
                    new int[]{R.id.titulo, R.id.tipocategoria});
            searchDonationView.setListAdapter(adapter);
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
                if(requestType.equals(POSTSEARCH)){
                    postSearchSucess(response);
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
                Toast.makeText(SearchDonationActivity.this,error.toString(),Toast.LENGTH_LONG).show();
            }
        };
    }
}
