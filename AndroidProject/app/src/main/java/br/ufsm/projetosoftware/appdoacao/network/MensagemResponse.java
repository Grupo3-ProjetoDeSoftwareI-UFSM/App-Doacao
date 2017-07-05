package br.ufsm.projetosoftware.appdoacao.network;

/**
 * Resposta do servidor ao enviar uma mensagem no chat
 */

public class MensagemResponse {

    int success;

    public MensagemResponse() {
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }
}
