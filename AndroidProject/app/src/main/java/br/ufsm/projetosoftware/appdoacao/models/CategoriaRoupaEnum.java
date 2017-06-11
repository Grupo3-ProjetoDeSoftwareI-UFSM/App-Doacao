package br.ufsm.projetosoftware.appdoacao.models;

/**
 * Created by Felipe on 10/06/2017.
 */

public enum CategoriaRoupaEnum {
    CAT1("Masculino"),
    CAT2("Feminino"),
    CAT3("Infantil");

    String categoria;

    CategoriaRoupaEnum(String categoria){
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return categoria;
    }
}
