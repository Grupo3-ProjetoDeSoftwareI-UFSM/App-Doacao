package br.ufsm.projetosoftware.appdoacao.view;

/**
 * Created by Felipe on 04/07/2017.
 */

public interface OpcoesSolicitacaoView {

    interface AvaliacoesButtonListener{
        void onAvaliacoesButtonClick();
    }

    interface ChatButtonListener{
        void onChatButtonClick();
    }

    interface ConcluiDoacaoButtonListener{
        void onConcluirDoacaoButtonClick();
    }

    void setAvaliacoesListener(AvaliacoesButtonListener listener);

    void setChatListener(ChatButtonListener listener);

    void setConcluiDoacaoListener(ConcluiDoacaoButtonListener listener);

}
