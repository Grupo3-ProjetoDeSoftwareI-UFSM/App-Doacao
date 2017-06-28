package br.ufsm.projetosoftware.appdoacao.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

/**
 * Created by Felipe on 03/06/2017.
 */

public class Produto {

    Integer pid;
    String titulo;
    String descricao;
    String tipo;
    String categoria;
    String imagem;
    String imageId;
    String datahora;
    String status;
    Integer doador;
    String authToken;


    public Produto() {
        status = StatusEnum.STATUS1.toString();
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
        Bitmap bitmapImage = null;
        if(imagem != null){
            byte[] decodedString = Base64.decode(imagem, Base64.DEFAULT);
            bitmapImage = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        }
        return bitmapImage;
    }

    public void setImagem(Bitmap bitmapImage) {
        ByteArrayOutputStream byteArrayBitmapStream = new ByteArrayOutputStream();
        bitmapImage.compress(Bitmap.CompressFormat.PNG, 100,
                byteArrayBitmapStream);
        byte[] b = byteArrayBitmapStream.toByteArray();
        imagem = Base64.encodeToString(b, Base64.DEFAULT);
    }

    public void setImagem(String bitmapEncodedImage){
        imagem = bitmapEncodedImage;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getDatahora() {
        return datahora;
    }

    public void setDatahora(String datahora) {
        this.datahora = datahora;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getDoador() {
        return doador;
    }

    public void setDoador(Integer doador) {
        this.doador = doador;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getTipoCategoria(){
        return tipo + " > " + categoria;
    }
}
