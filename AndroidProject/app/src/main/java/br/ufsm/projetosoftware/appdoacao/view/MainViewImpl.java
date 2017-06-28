package br.ufsm.projetosoftware.appdoacao.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.ufsm.projetosoftware.appdoacao.R;

/**
 * Created by Felipe on 20/05/2017.
 */

public class MainViewImpl implements MainView {

    private View rootView;
    private Button btNewDonation;
    private Button btSearchDonation;
    private Button btListDonation;
    private NewDonationButtonListener newDonationButtonListener;
    private SearchDonationButtonListener searchDonationButtonListener;
    private ListDonationButtonListener listDonationButtonListener;

    public MainViewImpl(View view){
        rootView = view;
        initialize();
        btNewDonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (newDonationButtonListener != null) {
                    newDonationButtonListener.onNewDonationClick();
                }
            }
        });
        btSearchDonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchDonationButtonListener != null){
                    searchDonationButtonListener.onSearchDonationClick();
                }
            }
        });
        btListDonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listDonationButtonListener != null){
                    listDonationButtonListener.onListDonationClick();
                }
            }
        });
    }

    private void initialize(){
        btNewDonation = (Button) rootView.findViewById(R.id.btNewDonation);
        btSearchDonation = (Button) rootView.findViewById(R.id.btSearchDonation);
        btListDonation = (Button) rootView.findViewById(R.id.btListDonation);
    }

    @Override
    public View getRootView() {
        return null;
    }

    @Override
    public Bundle getViewState() {
        return null;
    }

    @Override
    public void setNewDonationListener(NewDonationButtonListener listener) {
        newDonationButtonListener = listener;
    }

    @Override
    public void setSearchDonationListener(SearchDonationButtonListener listener) {
        searchDonationButtonListener = listener;
    }

    @Override
    public void setListDonationListener(ListDonationButtonListener listener) {
        listDonationButtonListener = listener;
    }
}
