package br.ufsm.projetosoftware.appdoacao.network;

/**
 * Created by Felipe on 24/06/2017.
 */

public class RequestPost {
    int doacao;
    int interessado;

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
}
