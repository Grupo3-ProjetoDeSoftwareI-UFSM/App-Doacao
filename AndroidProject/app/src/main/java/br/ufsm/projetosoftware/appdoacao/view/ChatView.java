package br.ufsm.projetosoftware.appdoacao.view;

import android.widget.ListAdapter;

/**
 * Created by Felipe on 02/07/2017.
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

    void setDoarButtonListener(DoarButtonListener listener);

    void setDoarButtonVisible();

    void setChatAdapter(ListAdapter adapter);

}
