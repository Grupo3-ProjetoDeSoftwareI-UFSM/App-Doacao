package br.ufsm.projetosoftware.appdoacao.network;

/**
 * Created by Felipe on 24/06/2017.
 */

public class RequestResult {
    int success;

    public RequestResult() {
    }

    public RequestResult(int success) {
        this.success = success;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }
}
