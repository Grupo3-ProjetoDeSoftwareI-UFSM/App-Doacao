package br.ufsm.projetosoftware.appdoacao.network;

/**
 * Dados a serem enviados ao servidor para busca de solicitações de uma doação
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
