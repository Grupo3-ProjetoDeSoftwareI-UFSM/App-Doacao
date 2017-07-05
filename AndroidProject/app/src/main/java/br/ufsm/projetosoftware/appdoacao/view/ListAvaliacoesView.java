package br.ufsm.projetosoftware.appdoacao.view;

import android.widget.ListAdapter;
import android.widget.RatingBar;

/**
 * Created by Felipe on 04/07/2017.
 */

public interface ListAvaliacoesView {

    void setListAdapter(ListAdapter adapter);

    RatingBar getRatingBar();
}
