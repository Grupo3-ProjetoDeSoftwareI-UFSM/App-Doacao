package br.ufsm.projetosoftware.appdoacao.view;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import br.ufsm.projetosoftware.appdoacao.R;

/**
 * Created by Felipe on 01/07/2017.
 */

public class SolicitacoesViewImpl implements SolicitacoesView {

    View rootView;
    SolicitacaoListClick solicitacaoListClick;
    ListView lvSolicitantes;

    public SolicitacoesViewImpl(View view){
        rootView = view;
        initialize();
        lvSolicitantes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(solicitacaoListClick != null){
                    solicitacaoListClick.onSolicitacaoClick(position);
                }
            }
        });
    }

    private void initialize() {
        lvSolicitantes = (ListView) rootView.findViewById(R.id.lvSolicitantes);
    }


    @Override
    public void setSolicitacaoListListener(SolicitacaoListClick listener) {
        solicitacaoListClick = listener;
    }

    @Override
    public void setListAdapter(ListAdapter adapter) {
        lvSolicitantes.setAdapter(adapter);
    }
}
