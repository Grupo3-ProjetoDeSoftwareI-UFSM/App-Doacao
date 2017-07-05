package br.ufsm.projetosoftware.appdoacao.view;

import android.view.View;
import android.widget.Button;

import br.ufsm.projetosoftware.appdoacao.R;

/**
 * Implementação da interface da tela de opções da solicitação
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

    /**
     * Configura listener do botão de avaliações
     * @param listener
     */
    @Override
    public void setAvaliacoesListener(AvaliacoesButtonListener listener) {
        avaliacoesButtonListener = listener;
    }

    /**
     * Configura listener do botão de iniciar chat
     * @param listener
     */
    @Override
    public void setChatListener(ChatButtonListener listener) {
        chatButtonListener = listener;
    }

    /**
     * Configura listener do botão de concluir doação
     * @param listener
     */
    @Override
    public void setConcluiDoacaoListener(ConcluiDoacaoButtonListener listener) {
        concluiDoacaoButtonListener = listener;
    }
}
