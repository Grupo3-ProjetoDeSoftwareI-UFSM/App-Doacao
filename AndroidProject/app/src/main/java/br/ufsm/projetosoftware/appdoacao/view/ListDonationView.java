package br.ufsm.projetosoftware.appdoacao.view;

import android.widget.ArrayAdapter;

/**
 * Created by Felipe on 27/06/2017.
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
}
