package br.ufsm.projetosoftware.appdoacao.models;

import android.graphics.Bitmap;

/**
 * Created by Felipe on 03/06/2017.
 */

public class Produto {

    Integer pid;
    String titulo;
    String descricao;
    String tipo;
    String categoria;
    Bitmap imagem;
    Character status;
    Integer doadorID;

    public Produto() {
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public Bitmap getImagem() {
        return imagem;
    }

    public void setImagem(Bitmap imagem) {
        this.imagem = imagem;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    public Integer getDoadorID() {
        return doadorID;
    }

    public void setDoadorID(Integer doadorID) {
        this.doadorID = doadorID;
    }
}
