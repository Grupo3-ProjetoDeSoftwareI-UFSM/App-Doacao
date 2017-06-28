package br.ufsm.projetosoftware.appdoacao.models;

/**
 * Define os tipos para Activity de cadastro;
 * Created on 24/06/2017.
 */

public enum TipoEnum {
    TIPO1("Material de contrução"),
    TIPO2("Roupa");

    private String tipo;

    TipoEnum(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return tipo;
    }
}
