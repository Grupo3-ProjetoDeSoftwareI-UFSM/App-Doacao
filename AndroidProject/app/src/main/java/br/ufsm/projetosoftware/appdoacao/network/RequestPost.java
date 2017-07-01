package br.ufsm.projetosoftware.appdoacao.network;

/**
 * Ao solicitar produto, envia ao servidor
 * Created on 24/06/2017.
 */

public class RequestPost {
    int doacao;
    int interessado;
    String justificativa;
    String authToken;

    public RequestPost() {
    }

    public RequestPost(int doacao, int interessado) {
        this.doacao = doacao;
        this.interessado = interessado;
    }

    public int getDoacao() {
        return doacao;
    }

    public void setDoacao(int doacao) {
        this.doacao = doacao;
    }

    public int getInteressado() {
        return interessado;
    }

    public void setInteressado(int interessado) {
        this.interessado = interessado;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
