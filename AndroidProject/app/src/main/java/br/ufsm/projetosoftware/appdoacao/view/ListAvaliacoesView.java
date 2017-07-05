package br.ufsm.projetosoftware.appdoacao.view;

import android.widget.ListAdapter;
import android.widget.RatingBar;

/**
 * Interface da tela de lista de avaliações
 */

public interface ListAvaliacoesView {

    void setListAdapter(ListAdapter adapter);

    RatingBar getRatingBar();
}
