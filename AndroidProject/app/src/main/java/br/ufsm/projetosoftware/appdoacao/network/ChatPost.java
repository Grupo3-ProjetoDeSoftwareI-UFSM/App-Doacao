package br.ufsm.projetosoftware.appdoacao.network;

/**
 * Created by Felipe on 02/07/2017.
 */

public class ChatPost {

    int idSolicitacao;
    int lastMessage;

    public ChatPost() {
    }

    public int getIdSolicitacao() {
        return idSolicitacao;
    }

    public void setIdSolicitacao(int idSolicitacao) {
        this.idSolicitacao = idSolicitacao;
    }

    public int getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(int lastMessage) {
        this.lastMessage = lastMessage;
    }
}
