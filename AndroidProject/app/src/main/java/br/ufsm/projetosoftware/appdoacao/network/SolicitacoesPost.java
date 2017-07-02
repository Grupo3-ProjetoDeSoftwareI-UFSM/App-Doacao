package br.ufsm.projetosoftware.appdoacao.network;

/**
 * Created by Felipe on 01/07/2017.
 */

public class SolicitacoesPost {
    private int doacao;
    private String authToken;

    public SolicitacoesPost() {
    }

    public int getDoacao() {
        return doacao;
    }

    public void setDoacao(int doacao) {
        this.doacao = doacao;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
