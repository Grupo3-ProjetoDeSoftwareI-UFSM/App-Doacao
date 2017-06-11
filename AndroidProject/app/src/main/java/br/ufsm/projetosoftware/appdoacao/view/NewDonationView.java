package br.ufsm.projetosoftware.appdoacao.view;

import android.graphics.Bitmap;
import android.widget.ArrayAdapter;

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

    interface SelectTipoListener{
        void onSelectTipo(long id);
    }

    String getTipo();

    String getCategoria();

    String getTitulo();

    String getDescricao();

    Bitmap getImage();

    void setImage(Bitmap image);

    void setSelectImageListener(SelectImageListener listener);

    void setRegisterDonationListener(RegisterDonationListener listener);

    void setSelectTipoListener(SelectTipoListener listener);

    void setTipoValues(ArrayAdapter adapter);

    void setCategoriaValues(ArrayAdapter adapter);
}
