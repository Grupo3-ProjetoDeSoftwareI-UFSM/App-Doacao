package br.ufsm.projetosoftware.appdoacao.models;

/**
 * Categorias de materias de construção para activity de busca
 * Created on 24/06/2017.
 */

public enum CategoriaMaterialConstrucaoBuscaEnum {
    CAT("Todos"),
    CAT1("Portas e janelas"),
    CAT2("Pisos e revestimentos"),
    CAT3("Pintura"),
    CAT4("Banheiro"),
    CAT5("Material hidráulico"),
    CAT6("Material elétrico"),
    CAT7("Material bruto"),
    CAT8("Ferragens");

    private String categoria;

    CategoriaMaterialConstrucaoBuscaEnum(String categoria){
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return categoria;
    }
}
