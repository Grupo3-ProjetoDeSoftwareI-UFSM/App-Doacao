package br.ufsm.projetosoftware.appdoacao.view;

/**
 * Created by Felipe on 20/05/2017.
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

    void setNewDonationListener(NewDonationButtonListener listener);

    void setSearchDonationListener(SearchDonationButtonListener listener);

    void setListDonationListener(ListDonationButtonListener listener);

}
