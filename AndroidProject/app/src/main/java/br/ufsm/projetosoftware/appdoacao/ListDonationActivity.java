package br.ufsm.projetosoftware.appdoacao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.android.volley.VolleyError;

import br.ufsm.projetosoftware.appdoacao.models.StatusEnum;
import br.ufsm.projetosoftware.appdoacao.network.IResultString;
import br.ufsm.projetosoftware.appdoacao.network.VolleyServiceString;
import br.ufsm.projetosoftware.appdoacao.view.ListDonationView;
import br.ufsm.projetosoftware.appdoacao.view.ListDonationViewImpl;

public class ListDonationActivity extends AppCompatActivity
        implements ListDonationView.SelectSpinnerListener, ListDonationView.SelectListListener{

    ListDonationView listDonationView;
    private IResultString resultCallback = null;
    private VolleyServiceString volleyService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_donation);
        listDonationView = new ListDonationViewImpl(getWindow().getDecorView().getRootView());
        listDonationView.setSelectListListener(this);
        listDonationView.setSelectListListener(this);
        listDonationView.setSpinnerStatus(new ArrayAdapter<StatusEnum>(this, android.R.layout.simple_list_item_1, StatusEnum.values()));
        initCallback();
        volleyService = new VolleyServiceString(resultCallback, this);
    }

    @Override
    public void onSelectSpinner(int pos) {
        getDonationList(listDonationView.getStatus());
    }

    @Override
    public void onSelectList(int id) {

    }

    private void getDonationList(String status){

    }

    private void initCallback(){
        resultCallback = new IResultString() {
            @Override
            public void notifySuccess(String requestType, String response) {

            }

            @Override
            public void notifyError(String requestType, VolleyError error) {

            }
        };
    }
}
