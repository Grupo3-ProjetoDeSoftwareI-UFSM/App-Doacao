package br.ufsm.projetosoftware.appdoacao.network;

/**
 * Created by Felipe on 02/07/2017.
 */

public class MensagemPost {

    int idSolicitacao;
    int idRemetente;
    String message;

    public MensagemPost() {
    }

    public int getIdSolicitacao() {
        return idSolicitacao;
    }

    public void setIdSolicitacao(int idSolicitacao) {
        this.idSolicitacao = idSolicitacao;
    }

    public int getIdRemetente() {
        return idRemetente;
    }

    public void setIdRemetente(int idRemetente) {
        this.idRemetente = idRemetente;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
