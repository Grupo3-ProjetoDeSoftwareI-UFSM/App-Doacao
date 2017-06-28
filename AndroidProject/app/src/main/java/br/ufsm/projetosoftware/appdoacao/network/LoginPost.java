package br.ufsm.projetosoftware.appdoacao.network;

/**
 * Parâmetros a ser enviado para servidor, ao fazer login
 * Created on 24/06/2017.
 */

public class LoginPost {
    String email;
    String password;

    public LoginPost() {
    }

    public LoginPost(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
