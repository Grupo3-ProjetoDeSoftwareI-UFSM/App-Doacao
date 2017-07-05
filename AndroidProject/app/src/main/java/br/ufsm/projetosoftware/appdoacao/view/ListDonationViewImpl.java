package br.ufsm.projetosoftware.appdoacao.view;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import br.ufsm.projetosoftware.appdoacao.R;

/**
 * Implementação da interface da tela de lista de doações
 */

public class ListDonationViewImpl implements ListDonationView{

    private View rootView;
    private SelectSpinnerListener selectSpinnerListener;
    private SelectListListener selectListListener;
    private Spinner spStatus;
    private ListView lvDoacoes;

    public ListDonationViewImpl(View view){
        rootView = view;
        initialize();
        spStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(selectSpinnerListener != null){
                    selectSpinnerListener.onSelectSpinner(position);                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                if(selectSpinnerListener != null){
                    selectSpinnerListener.onSelectSpinner(0);
                }
            }
        });
        lvDoacoes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(selectListListener != null){
                    selectListListener.onSelectList(position);
                }
            }
        });
    }

    private void initialize(){
        spStatus = (Spinner) rootView.findViewById(R.id.spStatus);
        lvDoacoes = (ListView) rootView.findViewById(R.id.lvDoacoes);
    }

    /**
     * Retorna status selecionado no spinner
     * @return
     */
    @Override
    public String getStatus() {
        return spStatus.getSelectedItem().toString();
    }

    /**
     * Configura opções do spinner de status
     * @param adapter
     */
    @Override
    public void setSpinnerStatus(ArrayAdapter adapter) {
        spStatus.setAdapter(adapter);
    }

    /**
     * Configura listener do spinner de status
     * @param listener
     */
    @Override
    public void setSelectSpinnerListener(SelectSpinnerListener listener) {
        selectSpinnerListener = listener;
    }

    /**
     * Configura listener da lista de doações
     * @param listener
     */
    @Override
    public void setSelectListListener(SelectListListener listener) {
        selectListListener = listener;
    }

    /**
     * Configura adapter na lista para exibição dos dados
     * @param adapter
     */
    @Override
    public void setListAdapter(ListAdapter adapter) {
        lvDoacoes.setAdapter(adapter);
    }
}
