package br.ufsm.projetosoftware.appdoacao.network;

/**
 * Created by Felipe on 04/06/2017.
 */

public class RegisterResponse {

    Integer sucess;
    String message;

    public RegisterResponse() {
    }

    public RegisterResponse(Integer sucess, String message) {
        this.sucess = sucess;
        this.message = message;
    }

    public Integer getSucess() {
        return sucess;
    }

    public void setSucess(Integer sucess) {
        this.sucess = sucess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
