package br.ufsm.projetosoftware.appdoacao.network;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.ufsm.projetosoftware.appdoacao.models.Produto;
import br.ufsm.projetosoftware.appdoacao.models.Solicitacao;

/**
 * Solicitações de uma doação retornadas pelo servidor
 */

public class SolicitacoesResponse {

    private List<Solicitacao> listaSolicitacao = new ArrayList<>();
    public final static String KEY_NOME = "nome";
    public final static String KEY_JUSTIFICATIVA = "justificativa";

    public SolicitacoesResponse() {
    }

    public List<Solicitacao> getListaSolicitacao() {
        return listaSolicitacao;
    }

    public void setListaSolicitacao(List<Solicitacao> listaSolicitacao) {
        this.listaSolicitacao = listaSolicitacao;
    }

    public List<HashMap<String, String>> getMapSolicitacao(){
        List<HashMap<String, String>> mapList = new ArrayList<>();
        for(Solicitacao s : listaSolicitacao){
            HashMap<String, String> map = new HashMap<>();
            map.put(KEY_NOME, s.getNome());
            map.put(KEY_JUSTIFICATIVA, s.getJustificativa());
            mapList.add(map);
        }
        return mapList;
    }
}
