package br.ufsm.projetosoftware.appdoacao.view;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.ufsm.projetosoftware.appdoacao.R;

/**
 * Created by Felipe on 29/06/2017.
 */

public class RequestViewImpl implements RequestView{

    View rootView;
    SolicitarButtonListener solicitarButtonListener;
    TextView tvTitulo;
    EditText etJustificativa;
    Button btSolicitar;

    public RequestViewImpl(View view){
        rootView = view;
        initialize();
        btSolicitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(solicitarButtonListener != null){
                    solicitarButtonListener.onSolicitarButton();
                }
            }
        });
    }

    private void initialize(){
        tvTitulo = (TextView) rootView.findViewById(R.id.tvTitulo);
        etJustificativa = (EditText) rootView.findViewById(R.id.etJustificativa);
        btSolicitar = (Button) rootView.findViewById(R.id.btConfirmaSolicitacao);
    }

    @Override
    public void setSolicitarButtonListener(SolicitarButtonListener listener) {
        solicitarButtonListener = listener;
    }

    @Override
    public void setTitulo(String titulo) {
        tvTitulo.setText(titulo);
    }

    @Override
    public String getJustificativa() {
        return etJustificativa.getText().toString();
    }
}
