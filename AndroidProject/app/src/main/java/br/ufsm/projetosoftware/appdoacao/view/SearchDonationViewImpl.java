package br.ufsm.projetosoftware.appdoacao.view;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import br.ufsm.projetosoftware.appdoacao.R;

/**
 * Created by Felipe on 22/06/2017.
 */

public class SearchDonationViewImpl implements SearchDonationView{

    private View rootView;
    private BuscaListener buscaListener;
    private ListListener listListener;
    private SelectTipoListener selectTipoListener;
    private Spinner spTipo;
    private Spinner spCategoria;
    private EditText etBusca;
    private Button btBusca;
    private ListView listBusca;

    public SearchDonationViewImpl(View view){
        rootView = view;
        initialize();
        btBusca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(buscaListener != null){
                    buscaListener.onBuscaClick();
                }
            }
        });
        listBusca.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(listListener != null){
                    listListener.onListItemClick(position);
                }
            }
        });
        spTipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(id == 0){
                    spCategoria.setEnabled(false);
                }
                else if(selectTipoListener != null){
                    spCategoria.setEnabled(true);
                    selectTipoListener.onSelectTipo(id);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectTipoListener.onSelectTipo(0);
            }
        });
    }

    private void initialize(){
        spTipo = (Spinner) rootView.findViewById(R.id.SpBuscaTipo);
        spCategoria = (Spinner) rootView.findViewById(R.id.SpBuscaCategoria);
        etBusca = (EditText) rootView.findViewById(R.id.EtBusca);
        btBusca = (Button) rootView.findViewById(R.id.BtBusca);
        listBusca = (ListView) rootView.findViewById(R.id.ListBusca);

    }

    @Override
    public String getTipo() {
        if(spTipo.getSelectedItemId() == 0)
            return "";
        else
            return spTipo.getSelectedItem().toString();
    }

    @Override
    public String getCategoria() {
        if(spCategoria.isEnabled() == false || spCategoria.getSelectedItemId() == 0)
            return "";
        else
            return spCategoria.getSelectedItem().toString();
    }

    @Override
    public String getBusca() {
        if(etBusca.getText().length() > 0)
            return etBusca.getText().toString();
        else{
            return "";
        }
    }

    @Override
    public void setBuscaListener(BuscaListener listener) {
        buscaListener = listener;
    }

    @Override
    public void setListListener(ListListener listener) {
        listListener = listener;
    }

    @Override
    public void setListAdapter(ListAdapter adapter) {
        listBusca.setAdapter(adapter);
    }

    @Override
    public void setSelectTipoListener(SelectTipoListener listener) {
        selectTipoListener = listener;
    }

    @Override
    public void setTipoValues(ArrayAdapter adapter) {
        spTipo.setAdapter(adapter);
    }

    @Override
    public void setCategoriaValues(ArrayAdapter adapter) {
        spCategoria.setAdapter(adapter);
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public Bundle getViewState() {
        return null;
    }
}
