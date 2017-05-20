package br.ufsm.projetosoftware.appdoacao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.ufsm.projetosoftware.appdoacao.view.MainView;
import br.ufsm.projetosoftware.appdoacao.view.MainViewImpl;

public class MainActivity extends AppCompatActivity implements MainView.NewDonationButtonListener{

    private MainView mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainView = new MainViewImpl(getWindow().getDecorView().getRootView());
        mainView.setNewDonationListener(this);
    }

    @Override
    public void onNewDonationClick() {

    }
}
