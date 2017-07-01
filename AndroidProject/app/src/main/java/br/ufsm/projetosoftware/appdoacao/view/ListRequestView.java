package br.ufsm.projetosoftware.appdoacao.view;

import android.widget.ListAdapter;

/**
 * Created by Felipe on 30/06/2017.
 */

public interface ListRequestView {

    interface SelectListListener{
        void onSelectList(int id);
    }

    void setSelectListListener(SelectListListener listener);

    void setListAdapter(ListAdapter adapter);

}
