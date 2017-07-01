package br.ufsm.projetosoftware.appdoacao.view;

/**
 * Created by Felipe on 29/06/2017.
 */

public interface RequestView {

    interface SolicitarButtonListener{
        void onSolicitarButton();
    }

    void setSolicitarButtonListener(SolicitarButtonListener listener);

    void setTitulo(String titulo);

    String getJustificativa();

}
