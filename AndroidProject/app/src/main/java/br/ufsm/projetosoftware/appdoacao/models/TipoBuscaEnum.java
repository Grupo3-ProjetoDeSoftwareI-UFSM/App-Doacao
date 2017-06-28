package br.ufsm.projetosoftware.appdoacao.models;

/**
 * Tipos para a Activity da Busca
 * Created on 24/06/2017.
 */

public enum TipoBuscaEnum {
    TIPO("Todos"),
    TIPO1("Material de contrução"),
    TIPO2("Roupa");

    private String tipo;

    TipoBuscaEnum(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return tipo;
    }
}
