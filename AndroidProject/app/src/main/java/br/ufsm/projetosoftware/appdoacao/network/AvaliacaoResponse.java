package br.ufsm.projetosoftware.appdoacao.network;

/**
 * Resposta do servidor ao cadastrar uma avaliação
 */

public class AvaliacaoResponse {

    Integer success;

    public AvaliacaoResponse() {
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }
}

