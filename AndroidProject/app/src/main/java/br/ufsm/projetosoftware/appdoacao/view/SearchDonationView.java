package br.ufsm.projetosoftware.appdoacao.view;

import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

/**
 * Interface da tela de busca de doações
 */

public interface SearchDonationView extends ViewMvc{

    interface BuscaListener{
        void onBuscaClick();
    }

    interface ListListener{
        void onListItemClick(int id);
    }

    interface SelectTipoListener{
        void onSelectTipo(long id);
    }

    String getTipo();

    String getCategoria();

    String getBusca();

    void setBuscaListener(BuscaListener listener);

    void setListListener(ListListener listener);

    void setListAdapter(ListAdapter adapter);

    void setSelectTipoListener(SelectTipoListener listener);

    void setTipoValues(ArrayAdapter adapter);

    void setCategoriaValues(ArrayAdapter adapter);
}
