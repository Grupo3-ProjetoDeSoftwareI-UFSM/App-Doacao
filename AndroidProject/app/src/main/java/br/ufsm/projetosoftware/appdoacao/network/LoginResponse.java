package br.ufsm.projetosoftware.appdoacao.network;

/**
 * Created by Felipe on 04/06/2017.
 */

public class LoginResponse {
    int loginStatus;
    String sessionToken;

    public LoginResponse() {
    }

    public LoginResponse(int loginStatus, String sessionToken) {
        this.loginStatus = loginStatus;
        this.sessionToken = sessionToken;
    }

    public int getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(int loginStatus) {
        this.loginStatus = loginStatus;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }
}
