package br.ufsm.projetosoftware.appdoacao.view;

/**
 * Interface da tela de opções de solicitação
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
