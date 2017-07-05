package br.ufsm.projetosoftware.appdoacao.view;

import android.widget.ListAdapter;

/**
 * Interface da tela de solicitações de uma doação
 */

public interface SolicitacoesView {

    interface SolicitacaoListClick{
        void onSolicitacaoClick(int id);
    }

    void setSolicitacaoListListener(SolicitacaoListClick listener);

    void setListAdapter(ListAdapter adapter);

}
