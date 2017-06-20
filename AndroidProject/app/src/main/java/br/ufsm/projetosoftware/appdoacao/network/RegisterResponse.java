package br.ufsm.projetosoftware.appdoacao.network;

/**
 * Created by Felipe on 04/06/2017.
 */

public class RegisterResponse {

    Integer success;
    String message;

    public RegisterResponse() {
    }

    public RegisterResponse(Integer success, String message) {
        this.success = success;
        this.message = message;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
