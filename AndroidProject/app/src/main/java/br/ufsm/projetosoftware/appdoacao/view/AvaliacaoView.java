package br.ufsm.projetosoftware.appdoacao.view;

/**
 * Created by Felipe on 03/07/2017.
 */

public interface AvaliacaoView {

    interface ConfirmarButtonListener{
        void onConfirmarButtonClick();
    }

    void setConfirmarButtonListener(ConfirmarButtonListener listener);

    float getAvaliacao();

    String getComentario();

}
