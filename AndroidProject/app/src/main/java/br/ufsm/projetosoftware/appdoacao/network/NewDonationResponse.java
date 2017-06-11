package br.ufsm.projetosoftware.appdoacao.network;

/**
 * Created by Felipe on 06/06/2017.
 */

public class NewDonationResponse {

    int returnCode;
    String message;

    public NewDonationResponse() {
    }

    public NewDonationResponse(int returnCode, String message) {
        this.returnCode = returnCode;
        this.message = message;
    }

    public int getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
