package br.ufsm.projetosoftware.appdoacao.models;

/**
 * Categorias de roupas para activity de busca
 * Created on 24/06/2017.
 */

public enum CategoriaRoupaBuscaEnum {
    CAT("Todos"),
    CAT1("Masculino"),
    CAT2("Feminino"),
    CAT3("Infantil");

    String categoria;

    CategoriaRoupaBuscaEnum(String categoria){
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return categoria;
    }
}
