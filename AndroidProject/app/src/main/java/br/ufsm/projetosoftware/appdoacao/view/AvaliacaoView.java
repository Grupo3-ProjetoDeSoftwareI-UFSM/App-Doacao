package br.ufsm.projetosoftware.appdoacao.view;

/**
 * Interface da tela de avaliação
 */

public interface AvaliacaoView {

    interface ConfirmarButtonListener{
        void onConfirmarButtonClick();
    }

    void setConfirmarButtonListener(ConfirmarButtonListener listener);

    float getAvaliacao();

    String getComentario();

}
