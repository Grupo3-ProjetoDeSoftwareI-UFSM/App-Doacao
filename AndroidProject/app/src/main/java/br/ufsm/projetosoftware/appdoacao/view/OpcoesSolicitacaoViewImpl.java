package br.ufsm.projetosoftware.appdoacao.view;

import android.view.View;
import android.widget.Button;

import br.ufsm.projetosoftware.appdoacao.R;

/**
 * Created by Felipe on 04/07/2017.
 */

public class OpcoesSolicitacaoViewImpl implements OpcoesSolicitacaoView{

    View rootView;
    AvaliacoesButtonListener avaliacoesButtonListener;
    ChatButtonListener chatButtonListener;
    ConcluiDoacaoButtonListener concluiDoacaoButtonListener;
    Button btAvaliacoes;
    Button btChat;
    Button btConcluiDoacao;

    public OpcoesSolicitacaoViewImpl(View view) {
        rootView = view;
        initialize();
        btAvaliacoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(avaliacoesButtonListener != null){
                    avaliacoesButtonListener.onAvaliacoesButtonClick();
                }
            }
        });
        btChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chatButtonListener != null){
                    chatButtonListener.onChatButtonClick();
                }
            }
        });
        btConcluiDoacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(concluiDoacaoButtonListener != null){
                    concluiDoacaoButtonListener.onConcluirDoacaoButtonClick();
                }
            }
        });
    }

    private void initialize(){
        btAvaliacoes = (Button) rootView.findViewById(R.id.btAvaliacoes);
        btChat = (Button) rootView.findViewById(R.id.btChat);
        btConcluiDoacao = (Button) rootView.findViewById(R.id.btConcluiDoacao);
    }

    @Override
    public void setAvaliacoesListener(AvaliacoesButtonListener listener) {
        avaliacoesButtonListener = listener;
    }

    @Override
    public void setChatListener(ChatButtonListener listener) {
        chatButtonListener = listener;
    }

    @Override
    public void setConcluiDoacaoListener(ConcluiDoacaoButtonListener listener) {
        concluiDoacaoButtonListener = listener;
    }
}
