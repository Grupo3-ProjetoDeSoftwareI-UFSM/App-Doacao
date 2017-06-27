package br.ufsm.projetosoftware.appdoacao.view;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import br.ufsm.projetosoftware.appdoacao.R;

/**
 * Created by Felipe on 27/06/2017.
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
    }

    private void initialize(){
        spStatus = (Spinner) rootView.findViewById(R.id.spStatus);
        lvDoacoes = (ListView) rootView.findViewById(R.id.lvDoacoes);
    }

    @Override
    public String getStatus() {
        return spStatus.getSelectedItem().toString();
    }

    @Override
    public void setSpinnerStatus(ArrayAdapter adapter) {
        spStatus.setAdapter(adapter);
    }

    @Override
    public void setSelectSpinnerListener(SelectSpinnerListener listener) {
        selectSpinnerListener = listener;
    }

    @Override
    public void setSelectListListener(SelectListListener listener) {
        selectListListener = listener;
    }
}
