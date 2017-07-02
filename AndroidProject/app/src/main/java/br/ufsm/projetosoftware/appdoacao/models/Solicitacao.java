package br.ufsm.projetosoftware.appdoacao.models;

/**
 * Created by Felipe on 01/07/2017.
 */

public class Solicitacao {

    private String nome;
    private String justificativa;
    private int interessado;

    public Solicitacao() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public int getInteressado() {
        return interessado;
    }

    public void setInteressado(int interessado) {
        this.interessado = interessado;
    }
}
