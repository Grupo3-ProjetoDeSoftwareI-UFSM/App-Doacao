package br.ufsm.projetosoftware.appdoacao.view;

import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;

import br.ufsm.projetosoftware.appdoacao.R;

/**
 * Created by Felipe on 04/07/2017.
 */

public class ListAvaliacoesViewImpl implements ListAvaliacoesView{

    View rootView;
    ListView lvAvaliacoes;

    public ListAvaliacoesViewImpl(View view) {
        rootView = view;
        initialize();
    }

    private void initialize() {
        lvAvaliacoes = (ListView) rootView.findViewById(R.id.lvAvaliacoes);
    }

    @Override
    public void setListAdapter(ListAdapter adapter) {
        lvAvaliacoes.setAdapter(adapter);
    }

    @Override
    public RatingBar getRatingBar() {
        return (RatingBar)rootView.findViewById(R.id.rbListAvaliacao);
    }
}
