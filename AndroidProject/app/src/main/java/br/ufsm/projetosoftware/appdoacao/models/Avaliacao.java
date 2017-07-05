package br.ufsm.projetosoftware.appdoacao.models;

/**
 * Classe com dados de uma avaliação de usuário
 */

public class Avaliacao {

    Integer aid;
    Integer idSolicitacao;
    Integer idAvaliado;
    Integer idAvaliador;
    Float avaliacao;
    String comentario;

    public Avaliacao() {
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getIdSolicitacao() {
        return idSolicitacao;
    }

    public void setIdSolicitacao(Integer idSolicitacao) {
        this.idSolicitacao = idSolicitacao;
    }

    public Integer getIdAvaliado() {
        return idAvaliado;
    }

    public void setIdAvaliado(Integer idAvaliado) {
        this.idAvaliado = idAvaliado;
    }

    public Integer getIdAvaliador() {
        return idAvaliador;
    }

    public void setIdAvaliador(Integer idAvaliador) {
        this.idAvaliador = idAvaliador;
    }

    public Float getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Float avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
