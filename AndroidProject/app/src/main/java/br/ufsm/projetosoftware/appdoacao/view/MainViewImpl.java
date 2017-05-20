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
    private NewDonationButtonListener newDonationButtonListener;

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
    }

    private void initialize(){
        btNewDonation = (Button) rootView.findViewById(R.id.btNewDonation);
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
}
