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

    String getTipo();

    String getCategoria();

    String getTitulo();

    String getDescricao();

    Bitmap getImage();

    void setImage(Bitmap image);

    void setSelectImageListener(SelectImageListener listener);

    void setRegisterDonationListener(RegisterDonationListener listener);
}
