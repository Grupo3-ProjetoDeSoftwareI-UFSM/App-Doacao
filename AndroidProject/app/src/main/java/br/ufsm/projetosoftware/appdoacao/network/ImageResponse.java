package br.ufsm.projetosoftware.appdoacao.network;

/**
 * Created by Felipe on 17/06/2017.
 */

public class ImageResponse {
    String image;
    int returnCode;
    String message;

    public ImageResponse() {
    }

    public ImageResponse(String image, int returnCode, String message) {
        this.image = image;
        this.returnCode = returnCode;
        this.message = message;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
