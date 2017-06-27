package br.ufsm.projetosoftware.appdoacao.models;

/**
 * Created by Felipe on 27/06/2017.
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
