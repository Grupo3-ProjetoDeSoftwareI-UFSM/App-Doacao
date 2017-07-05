package br.ufsm.projetosoftware.appdoacao.view;

import android.widget.ListAdapter;

/**
 * Interface da tela de chat
 */

public interface ChatView {

    interface SendButtonListener{
        void onSendButtonClick();
    }

    interface DoarButtonListener{
        void onDoarButtonClick();
    }

    String getMessage();

    void setSendButtonListener(SendButtonListener listener);

    void setChatAdapter(ListAdapter adapter);

}
