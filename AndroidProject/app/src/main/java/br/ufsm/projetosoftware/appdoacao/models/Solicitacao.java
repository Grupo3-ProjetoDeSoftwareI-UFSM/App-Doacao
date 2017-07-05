package br.ufsm.projetosoftware.appdoacao.models;

/**
 * Solicitação de doação
 */

public class Solicitacao {

    private String nome;
    private String justificativa;
    private int interessado;
    private int sid;

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

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }
}
