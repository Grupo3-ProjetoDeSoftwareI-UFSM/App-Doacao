package br.ufsm.projetosoftware.appdoacao.view;

/**
 *
 * Interface da tela principal com opções do aplicativo
 */

public interface MainView extends ViewMvc {
    interface NewDonationButtonListener{
        void onNewDonationClick();
    }

    interface SearchDonationButtonListener{
        void onSearchDonationClick();
    }

    interface ListDonationButtonListener{
        void onListDonationClick();
    }

    interface ListRequestButtonListener{
        void onListRequestClick();
    }

    interface SairButtonListener{
        void onSairClick();
    }

    void setNewDonationListener(NewDonationButtonListener listener);

    void setSearchDonationListener(SearchDonationButtonListener listener);

    void setListDonationListener(ListDonationButtonListener listener);

    void setListRequestListener(ListRequestButtonListener listener);

    void setSairListener(SairButtonListener listener);

}
