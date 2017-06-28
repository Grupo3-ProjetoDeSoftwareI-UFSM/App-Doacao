package br.ufsm.projetosoftware.appdoacao.models;

/**
 * Categorias de roupas para activity de Cadastro
 * Created on 24/06/2017.
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
