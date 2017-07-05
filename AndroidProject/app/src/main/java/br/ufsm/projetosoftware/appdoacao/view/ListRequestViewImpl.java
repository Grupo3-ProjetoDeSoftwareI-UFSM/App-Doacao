package br.ufsm.projetosoftware.appdoacao.view;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import br.ufsm.projetosoftware.appdoacao.R;

/**
 * Implementação da interface da tela de lista de solicitações
 */

public class ListRequestViewImpl implements ListRequestView{

    private View rootView;
    private SelectListListener selectListListener;
    private ListView lvSolicitacoes;

    public ListRequestViewImpl(View view){
        rootView = view;
        initialize();
        lvSolicitacoes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(selectListListener != null){
                    selectListListener.onSelectList(position);
                }
            }
        });
    }

    private void initialize(){
        lvSolicitacoes = (ListView) rootView.findViewById(R.id.lvSolicitacoes);
    }

    /**
     * Configura listener da lista
     * @param listener
     */
    @Override
    public void setSelectListListener(SelectListListener listener) {
        selectListListener = listener;
    }

    /**
     * Configura adapter da lista para exibir dados
     * @param adapter
     */
    @Override
    public void setListAdapter(ListAdapter adapter) {
        lvSolicitacoes.setAdapter(adapter);
    }
}
