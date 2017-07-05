package br.ufsm.projetosoftware.appdoacao.network;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.ufsm.projetosoftware.appdoacao.models.Avaliacao;
import br.ufsm.projetosoftware.appdoacao.models.Solicitacao;

/**
 * Created by Felipe on 04/07/2017.
 */

public class AvaliacoesResponse {
    List<Avaliacao> listaAvaliacao;
    public final static String KEY_AVALIACAO = "avaliacao";
    public final static String KEY_COMENTARIO = "comentario";

    public AvaliacoesResponse() {
    }

    public List<Avaliacao> getListaAvaliacao() {
        return listaAvaliacao;
    }

    public void setListaAvaliacao(List<Avaliacao> listaAvaliacao) {
        this.listaAvaliacao = listaAvaliacao;
    }

    public List<HashMap<String, String>> getMapSolicitacao(){
        List<HashMap<String, String>> mapList = new ArrayList<>();
        for(Avaliacao a : listaAvaliacao){
            HashMap<String, String> map = new HashMap<>();
            map.put(KEY_AVALIACAO, a.getAvaliacao().toString());
            map.put(KEY_COMENTARIO, a.getComentario());
            mapList.add(map);
        }
        return mapList;
    }
}
