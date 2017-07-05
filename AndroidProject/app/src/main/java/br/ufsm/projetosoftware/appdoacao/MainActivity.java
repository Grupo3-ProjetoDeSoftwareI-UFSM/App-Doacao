package br.ufsm.projetosoftware.appdoacao;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.ufsm.projetosoftware.appdoacao.view.MainView;
import br.ufsm.projetosoftware.appdoacao.view.MainViewImpl;

/**
 * Activity da tela principal com opções do aplicativo
 */
public class MainActivity
        extends AppCompatActivity
        implements MainView.NewDonationButtonListener,
        MainView.SearchDonationButtonListener,
        MainView.ListDonationButtonListener,
        MainView.ListRequestButtonListener,
        MainView.SairButtonListener{

    private MainView mainView;
    private SharedPreferences loginSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainView = new MainViewImpl(getWindow().getDecorView().getRootView());
        mainView.setNewDonationListener(this);
        mainView.setSearchDonationListener(this);
        mainView.setListDonationListener(this);
        mainView.setListRequestListener(this);
        mainView.setSairListener(this);
        loginSettings = getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
    }

    /**
     * Inicia activity de cadastrar doação
     */
    @Override
    public void onNewDonationClick() {
        Intent toNewDonation = new Intent(MainActivity.this, NewDonationActivity.class);
        startActivity(toNewDonation);
    }

    /**
     * Inicia activity de procurar doação
     */
    @Override
    public void onSearchDonationClick() {
        Intent toSearchDonation = new Intent(MainActivity.this, SearchDonationActivity.class);
        startActivity(toSearchDonation);
    }

    /**
     * Inicia activity de lista doações do usuário
     */
    @Override
    public void onListDonationClick() {
        Intent toListDonation = new Intent(MainActivity.this, ListDonationActivity.class);
        startActivity(toListDonation);
    }

    /**
     * Inicia activity de listar solicitações do usuário
     */
    @Override
    public void onListRequestClick() {
        Intent toListRequest = new Intent(MainActivity.this, ListRequestActivity.class);
        startActivity(toListRequest);
    }

    /**
     * Encerra sessão do usuário no aplicativo
     */
    @Override
    public void onSairClick() {
        loginSettings.edit().clear();
        Intent toLogin = new Intent(MainActivity.this, LoginActivity.class);
        toLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(toLogin);
    }
}
