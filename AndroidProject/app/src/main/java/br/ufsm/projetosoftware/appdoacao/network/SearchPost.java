package br.ufsm.projetosoftware.appdoacao.network;

/**
 * Created by Felipe on 23/06/2017.
 */

public class SearchPost {

    String busca;
    String tipo;
    String categoria;

    public SearchPost() {
    }

    public SearchPost(String busca, String tipo, String categoria) {
        this.busca = busca;
        this.tipo = tipo;
        this.categoria = categoria;
    }

    public String getBusca() {
        return busca;
    }

    public void setBusca(String busca) {
        this.busca = busca;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
