package br.ufsm.projetosoftware.appdoacao.view;

import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;

import br.ufsm.projetosoftware.appdoacao.R;

/**
 * Implementação da interface da tela de lista de avaliações
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

    /**
     * Configura adapter na listView para exibição dos dados
     * @param adapter
     */
    @Override
    public void setListAdapter(ListAdapter adapter) {
        lvAvaliacoes.setAdapter(adapter);
    }

    /**
     * Retorna view RatingBar
     * @return
     */
    @Override
    public RatingBar getRatingBar() {
        return (RatingBar)rootView.findViewById(R.id.rbListAvaliacao);
    }
}
