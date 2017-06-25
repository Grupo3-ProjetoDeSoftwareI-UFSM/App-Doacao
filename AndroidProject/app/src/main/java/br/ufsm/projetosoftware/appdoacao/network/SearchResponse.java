package br.ufsm.projetosoftware.appdoacao.network;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.ufsm.projetosoftware.appdoacao.models.Produto;

/**
 * Created by Felipe on 23/06/2017.
 */

public class SearchResponse {

    private List<Produto> listaProduto = new ArrayList<>();
    public String KEY_TITULO = "titulo";
    public String KEY_CATEGORIA = "tipocategoria";

    public List<Produto> getListaProduto(){
        return listaProduto;
    }

    public List<HashMap<String, String>> getMapProduto(){
        List<HashMap<String, String>> mapList = new ArrayList<>();
        for(Produto p : listaProduto){
            HashMap<String, String> map = new HashMap<>();
            map.put(KEY_TITULO, p.getTitulo());
            map.put(KEY_CATEGORIA, p.getTipoCategoria());
            mapList.add(map);
        }
        return mapList;
    }
 }
