package br.ufsm.projetosoftware.appdoacao.view;

import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

/**
 * Interface da tela de lsita de doações
 */

public interface ListDonationView {

    interface SelectSpinnerListener{
        void onSelectSpinner(int pos);
    }

    interface SelectListListener{
        void onSelectList(int id);
    }

    String getStatus();

    void setSpinnerStatus(ArrayAdapter adapter);

    void setSelectSpinnerListener(SelectSpinnerListener listener);

    void setSelectListListener(SelectListListener listener);

    void setListAdapter(ListAdapter adapter);
}
