package br.ufsm.projetosoftware.appdoacao.view;

import android.graphics.Bitmap;

/**
 * Created by Felipe on 20/05/2017.
 */

public interface NewDonationView extends ViewMvc{

    interface SelectImageListener{
        void onSelectImageClick();
    }

    interface RegisterDonationListener{
        void onRegisterDonationClick();
    }

    void setImage(Bitmap image);

    void setSelectImageListener(SelectImageListener listener);

    void setRegisterDonationListener(RegisterDonationListener listener);
}
