package br.ufsm.projetosoftware.appdoacao.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.ufsm.projetosoftware.appdoacao.R;

/**
 * Implementação da interface da tela principal com opções do aplicativo
 */

public class MainViewImpl implements MainView {

    private View rootView;
    private Button btNewDonation;
    private Button btSearchDonation;
    private Button btListDonation;
    private Button btListRequest;
    private Button btSair;
    private NewDonationButtonListener newDonationButtonListener;
    private SearchDonationButtonListener searchDonationButtonListener;
    private ListDonationButtonListener listDonationButtonListener;
    private ListRequestButtonListener listRequestButtonListener;
    private SairButtonListener sairButtonListener;

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
        btListRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listRequestButtonListener != null){
                    listRequestButtonListener.onListRequestClick();
                }
            }
        });
        btSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sairButtonListener != null){
                    sairButtonListener.onSairClick();
                }
            }
        });
    }

    private void initialize(){
        btNewDonation = (Button) rootView.findViewById(R.id.btNewDonation);
        btSearchDonation = (Button) rootView.findViewById(R.id.btSearchDonation);
        btListDonation = (Button) rootView.findViewById(R.id.btListDonation);
        btListRequest = (Button) rootView.findViewById(R.id.btListRequest);
        btSair = (Button) rootView.findViewById(R.id.btSair);
    }

    @Override
    public View getRootView() {
        return null;
    }

    @Override
    public Bundle getViewState() {
        return null;
    }

    /**
     * Configura listener do botao de nova doação
     * @param listener
     */
    @Override
    public void setNewDonationListener(NewDonationButtonListener listener) {
        newDonationButtonListener = listener;
    }

    /**
     * Configura listener do botão de busca de doação
     * @param listener
     */
    @Override
    public void setSearchDonationListener(SearchDonationButtonListener listener) {
        searchDonationButtonListener = listener;
    }

    /**
     * Configura listener do botão de lista de doações
     * @param listener
     */
    @Override
    public void setListDonationListener(ListDonationButtonListener listener) {
        listDonationButtonListener = listener;
    }

    /**
     * Configura listener do botão de lista de solicitações
     * @param listener
     */
    @Override
    public void setListRequestListener(ListRequestButtonListener listener) {
        listRequestButtonListener = listener;
    }

    /**
     * Configura listener do botão de sair
     * @param listener
     */
    @Override
    public void setSairListener(SairButtonListener listener) {
        sairButtonListener = listener;
    }
}
