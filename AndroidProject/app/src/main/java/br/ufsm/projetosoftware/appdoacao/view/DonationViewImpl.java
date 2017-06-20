package br.ufsm.projetosoftware.appdoacao.view;

import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import br.ufsm.projetosoftware.appdoacao.R;

/**
 * Created by Felipe on 17/06/2017.
 */

public class DonationViewImpl implements DonationView{

    private View rootView;
    private ImageButtonListener imageButtonListener;
    private SolicitarButtonListener solicitarButtonListener;
    private TextView tvTitulo;
    private TextView tvCategoria;
    private TextView tvDescricao;
    private Button btImagem;
    private ImageView ivImagem;
    private Button btSolicitar;

    public DonationViewImpl(View view){
        rootView = view;
        initialize();
        btImagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imageButtonListener != null){
                    imageButtonListener.onImageClick();
                }
            }
        });
        btSolicitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(solicitarButtonListener != null){
                    solicitarButtonListener.onSolicitarClick();
                }
            }
        });
    }

    private void initialize(){
        tvTitulo = (TextView)rootView.findViewById(R.id.tv_titulo);
        tvCategoria = (TextView)rootView.findViewById(R.id.tv_categoria);
        tvDescricao = (TextView)rootView.findViewById(R.id.tv_descricao);
        btImagem = (Button) rootView.findViewById(R.id.bt_imagem);
        ivImagem = (ImageView)rootView.findViewById(R.id.iv_imagem);
        btSolicitar = (Button) rootView.findViewById(R.id.bt_solicitar);
    }

    @Override
    public void setTitulo(String titulo) {
        tvTitulo.setText(titulo);
    }

    @Override
    public void setCategoria(String categoria) {
        tvCategoria.setText(categoria);
    }

    @Override
    public void setDescricao(String descricao) {
        tvDescricao.setText(descricao);
    }

    @Override
    public void setImagem(Bitmap imagem) {
        ivImagem.setVisibility(View.VISIBLE);
        ivImagem.setImageBitmap(imagem);
    }

    @Override
    public void setImageListener(ImageButtonListener listener) {
        imageButtonListener = listener;
    }

    @Override
    public void setSolicitarListener(SolicitarButtonListener listener) {
        solicitarButtonListener = listener;
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
