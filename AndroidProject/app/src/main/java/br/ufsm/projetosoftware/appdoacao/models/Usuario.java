package br.ufsm.projetosoftware.appdoacao.models;

/**
 * Classe do Usuário;
 * Created on 24/06/2017.
 */

public class Usuario {

    Integer uid;
    String nome;
    String email;
    String cpfcnpj;
    Character tipo;
    String senha;
    String cep;
    String endereco;
    String enderecoNumero;
    String complemento;
    String bairro;
    String cidade;
    String estado;
    Float mediaNota;

    public Usuario() {

    }

    public Usuario(Integer uid, String nome, String email, String cpfcnpj, Character tipo,
                   String senha, String cep, String endereco, String enderecoNumero,
                   String complemento, String bairro, String cidade, String estado, Float mediaNota) {
        this.uid = uid;
        this.nome = nome;
        this.email = email;
        this.cpfcnpj = cpfcnpj;
        this.tipo = tipo;
        this.senha = senha;
        this.cep = cep;
        this.endereco = endereco;
        this.enderecoNumero = enderecoNumero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.mediaNota = mediaNota;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpfcnpj() {
        return cpfcnpj;
    }

    public void setCpfcnpj(String cpfcnpj) {
        this.cpfcnpj = cpfcnpj;
        if(cpfcnpj.length() == 11){
            tipo = 'f';
        }
        if(getCpfcnpj().length() == 14){
            tipo = 'j';
        }
    }

    public Character getTipo() {
        return tipo;
    }

    public void setTipo(Character tipo) {
        this.tipo = tipo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEnderecoNumero() {
        return enderecoNumero;
    }

    public void setEnderecoNumero(String enderecoNumero) {
        this.enderecoNumero = enderecoNumero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Float getMediaNota() {
        return mediaNota;
    }

    public void setMediaNota(Float mediaNota) {
        this.mediaNota = mediaNota;
    }
}
