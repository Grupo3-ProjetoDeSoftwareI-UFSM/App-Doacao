package br.ufsm.projetosoftware.appdoacao.network;

/**
 * Created by Felipe on 04/06/2017.
 */

public class LoginResponse {
    int loginStatus;
    String authToken;
    int uid;

    public LoginResponse() {
    }

    public LoginResponse(int loginStatus, String authToken) {
        this.loginStatus = loginStatus;
        this.authToken = authToken;
    }

    public int getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(int loginStatus) {
        this.loginStatus = loginStatus;
    }

    public String getauthToken() {
        return authToken;
    }

    public void setauthToken(String authToken) {
        this.authToken = authToken;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
