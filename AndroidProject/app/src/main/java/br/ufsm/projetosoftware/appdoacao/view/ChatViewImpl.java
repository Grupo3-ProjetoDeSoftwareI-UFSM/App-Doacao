package br.ufsm.projetosoftware.appdoacao.view;

import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import br.ufsm.projetosoftware.appdoacao.R;

/**
 * Created by Felipe on 02/07/2017.
 */

public class ChatViewImpl implements ChatView {

    View rootView;
    SendButtonListener sendButtonListener;
    DoarButtonListener doarButtonListener;
    ListView chat;
    EditText etMessage;
    FloatingActionButton btSend;
    Button btDoar;

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
        btDoar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(doarButtonListener != null){
                    doarButtonListener.onDoarButtonClick();
                }
            }
        });
    }

    private void initialize() {
        chat = (ListView) rootView.findViewById(R.id.lvChat);
        etMessage = (EditText) rootView.findViewById(R.id.etMensagem);
        btSend = (FloatingActionButton) rootView.findViewById(R.id.btSend);
        btDoar = (Button) rootView.findViewById(R.id.btDoar);
    }

    @Override
    public String getMessage() {
        String message = etMessage.getText().toString();
        etMessage.setText("");
        return message;

    }

    @Override
    public void setSendButtonListener(SendButtonListener listener) {
        sendButtonListener = listener;
    }

    @Override
    public void setDoarButtonListener(DoarButtonListener listener) {
        doarButtonListener = listener;
    }

    @Override
    public void setDoarButtonVisible() {
        btDoar.setVisibility(View.VISIBLE);
    }

    @Override
    public void setChatAdapter(ListAdapter adapter) {
        chat.setAdapter(adapter);
    }

}
