package br.ufsm.projetosoftware.appdoacao.models;

/**
 * Categorias de materias de construção para activity de cadastro
 * Created on 24/06/2017.
 */

public enum CategoriaMaterialConstrucaoEnum {
    CAT1("Portas e janelas"),
    CAT2("Pisos e revestimentos"),
    CAT3("Pintura"),
    CAT4("Banheiro"),
    CAT5("Material hidráulico"),
    CAT6("Material elétrico"),
    CAT7("Material bruto"),
    CAT8("Ferragens");

    private String categoria;

    CategoriaMaterialConstrucaoEnum(String categoria){
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return categoria;
    }
}
