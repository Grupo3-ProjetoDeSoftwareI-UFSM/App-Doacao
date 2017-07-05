package br.ufsm.projetosoftware.appdoacao.network;

/**
 * Dados enviados para servidor ao buscar lista de avaliações de um usuário.
 */

public class AvaliacoesPost {

    int idSolicitacao;

    public AvaliacoesPost() {
    }

    public int getIdSolicitacao() {
        return idSolicitacao;
    }

    public void setIdSolicitacao(int idSolicitacao) {
        this.idSolicitacao = idSolicitacao;
    }
}
