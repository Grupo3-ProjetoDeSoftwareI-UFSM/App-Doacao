package br.ufsm.projetosoftware.appdoacao.network;

import java.util.List;

import br.ufsm.projetosoftware.appdoacao.models.Mensagem;

/**
 * Created by Felipe on 02/07/2017.
 */

public class ChatResponse {

    List<Mensagem> mensagemList;

    public ChatResponse() {
    }

    public List<Mensagem> getMensagemList() {
        return mensagemList;
    }

    public void setMensagemList(List<Mensagem> mensagemList) {
        this.mensagemList = mensagemList;
    }
}
