package br.ufsm.projetosoftware.appdoacao.view;

/**
 * Interface da tela de solicitação de doação
 */

public interface RequestView {

    interface SolicitarButtonListener{
        void onSolicitarButton();
    }

    void setSolicitarButtonListener(SolicitarButtonListener listener);

    void setTitulo(String titulo);

    String getJustificativa();

}
