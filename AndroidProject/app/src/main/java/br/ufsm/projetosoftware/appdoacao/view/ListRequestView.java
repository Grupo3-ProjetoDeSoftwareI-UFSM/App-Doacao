package br.ufsm.projetosoftware.appdoacao.view;

import android.widget.ListAdapter;

/**
 * Interface da tela de lista de solicitações
 */

public interface ListRequestView {

    interface SelectListListener{
        void onSelectList(int id);
    }

    void setSelectListListener(SelectListListener listener);

    void setListAdapter(ListAdapter adapter);

}
