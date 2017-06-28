package br.ufsm.projetosoftware.appdoacao.network;

/**
 * Ao cadastrar nova doação, recebe resposta do servidor
 * Created on 24/06/2017.
 */

public class NewDonationResponse {

    String uid;
    String imageId;
    int returnCode;
    String message;

    public NewDonationResponse() {
    }

    public NewDonationResponse(int returnCode, String message) {
        this.returnCode = returnCode;
        this.message = message;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
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
