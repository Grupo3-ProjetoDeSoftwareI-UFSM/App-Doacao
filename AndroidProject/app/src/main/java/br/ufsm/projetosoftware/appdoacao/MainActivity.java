package br.ufsm.projetosoftware.appdoacao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.ufsm.projetosoftware.appdoacao.view.MainView;
import br.ufsm.projetosoftware.appdoacao.view.MainViewImpl;

public class MainActivity extends AppCompatActivity implements MainView.NewDonationButtonListener,
        MainView.SearchDonationButtonListener, MainView.ListDonationButtonListener{

    private MainView mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainView = new MainViewImpl(getWindow().getDecorView().getRootView());
        mainView.setNewDonationListener(this);
        mainView.setSearchDonationListener(this);
        mainView.setListDonationListener(this);
    }

    @Override
    public void onNewDonationClick() {
        Intent toNewDonation = new Intent(MainActivity.this, NewDonationActivity.class);
        startActivity(toNewDonation);
    }

    @Override
    public void onSearchDonationClick() {
        Intent toSearchDonation = new Intent(MainActivity.this, SearchDonationActivity.class);
        startActivity(toSearchDonation);
    }

    @Override
    public void onListDonationClick() {
        Intent toListDonation = new Intent(MainActivity.this, ListDonationActivity.class);
        startActivity(toListDonation);
    }
}
