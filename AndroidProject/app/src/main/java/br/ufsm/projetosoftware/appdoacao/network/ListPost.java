package br.ufsm.projetosoftware.appdoacao.network;

/**
 * Classe com dados para solicitação ao servidor de lista de doações
 */

public class ListPost {

    String authToken;
    int uid;
    String status;

    public ListPost() {
    }

    public ListPost(String authToken, int uid, String status) {
        this.authToken = authToken;
        this.uid = uid;
        this.status = status;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
