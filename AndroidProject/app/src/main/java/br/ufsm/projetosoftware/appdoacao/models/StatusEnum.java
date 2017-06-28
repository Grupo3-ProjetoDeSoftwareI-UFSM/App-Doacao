package br.ufsm.projetosoftware.appdoacao.models;

/**
 * Status do produto. Se ja foi ou não doado;
 * Created on 24/06/2017.
 */

public enum StatusEnum {
    STATUS1("Disponivel"),
    STATUS2("Doado");

    private String status;

    StatusEnum(String status){
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
}
