package br.ufsm.projetosoftware.appdoacao.models;

/**
 * Created by Felipe on 10/06/2017.
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
