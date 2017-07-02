package br.ufsm.projetosoftware.appdoacao.view;

import android.widget.ListAdapter;

/**
 * Created by Felipe on 01/07/2017.
 */

public interface SolicitacoesView {

    interface SolicitacaoListClick{
        void onSolicitacaoClick(int id);
    }

    void setSolicitacaoListListener(SolicitacaoListClick listener);

    void setListAdapter(ListAdapter adapter);

}
