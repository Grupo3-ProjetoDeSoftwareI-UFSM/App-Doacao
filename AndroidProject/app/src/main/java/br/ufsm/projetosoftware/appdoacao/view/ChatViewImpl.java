package br.ufsm.projetosoftware.appdoacao.view;

import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import br.ufsm.projetosoftware.appdoacao.R;

/**
 * Implementação da interface da tela de chat
 */

public class ChatViewImpl implements ChatView {

    View rootView;
    SendButtonListener sendButtonListener;
    ListView chat;
    EditText etMessage;
    FloatingActionButton btSend;

    public ChatViewImpl(View view) {
        rootView = view;
        initialize();
        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sendButtonListener != null){
                    sendButtonListener.onSendButtonClick();
                }
            }
        });
    }

    private void initialize() {
        chat = (ListView) rootView.findViewById(R.id.lvChat);
        etMessage = (EditText) rootView.findViewById(R.id.etMensagem);
        btSend = (FloatingActionButton) rootView.findViewById(R.id.btSend);
    }

    /**
     * Retorna mensagem digitada pelo usuário
     * @return
     */
    @Override
    public String getMessage() {
        String message = etMessage.getText().toString();
        etMessage.setText("");
        return message;

    }

    /**
     * Configura listener do botão de envio de mensagem
     * @param listener
     */
    @Override
    public void setSendButtonListener(SendButtonListener listener) {
        sendButtonListener = listener;
    }

    /**
     * Configura ListAdapter no listView para exibir dados
     * @param adapter
     */
    @Override
    public void setChatAdapter(ListAdapter adapter) {
        chat.setAdapter(adapter);
    }

}
