package br.ufsm.projetosoftware.appdoacao.view;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import br.ufsm.projetosoftware.appdoacao.R;

/**
 * Created by Felipe on 03/07/2017.
 */

public class AvaliacaoViewImpl implements AvaliacaoView{

    View rootView;
    ConfirmarButtonListener confirmarButtonListener;
    RatingBar rbAvaliacao;
    EditText etComentario;
    Button btConfirmar;

    public AvaliacaoViewImpl(View view) {
        rootView = view;
        initialize();
        btConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(confirmarButtonListener != null){
                    confirmarButtonListener.onConfirmarButtonClick();
                }
            }
        });
    }

    private void initialize() {
        rbAvaliacao = (RatingBar) rootView.findViewById(R.id.rbAvaliacao);
        etComentario = (EditText) rootView.findViewById(R.id.etComentario);
        btConfirmar = (Button) rootView.findViewById(R.id.btConfirmaAvaliacao);
    }

    @Override
    public void setConfirmarButtonListener(ConfirmarButtonListener listener) {
        confirmarButtonListener = listener;
    }

    @Override
    public float getAvaliacao() {
        return rbAvaliacao.getRating();
    }

    @Override
    public String getComentario() {
        return etComentario.getText().toString();
    }
}
