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
 * Implementação da interface da tela de visualização de doação
 */

public class DonationViewImpl implements DonationView{

    private View rootView;
    private ImageButtonListener imageButtonListener;
    private SolicitarButtonListener solicitarButtonListener;
    private SolicitacoesButtonListener solicitacoesButtonListener;
    private TextView tvTitulo;
    private TextView tvCategoria;
    private TextView tvDescricao;
    private Button btImagem;
    private ImageView ivImagem;
    private Button btSolicitar;
    private Button btSolicitacoes;

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
        btSolicitacoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(solicitacoesButtonListener != null){
                    solicitacoesButtonListener.onSolicitacoesClick();
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
        btSolicitacoes = (Button) rootView.findViewById(R.id.bt_solicitacoes);
    }

    /**
     * Exibe titulo no campo de titulo
     * @param titulo
     */
    @Override
    public void setTitulo(String titulo) {
        tvTitulo.setText(titulo);
    }

    /**
     * Exibe categoria
     * @param categoria
     */
    @Override
    public void setCategoria(String categoria) {
        tvCategoria.setText(categoria);
    }

    /**
     * Exibe descrição
     * @param descricao
     */
    @Override
    public void setDescricao(String descricao) {
        tvDescricao.setText(descricao);
    }

    /**
     * Exibe imagem no imageView
     * @param imagem
     */
    @Override
    public void setImagem(Bitmap imagem) {
        ivImagem.setVisibility(View.VISIBLE);
        ivImagem.setImageBitmap(imagem);
    }

    /**
     * Configura listener do botão de exibir imagem
     * @param listener
     */
    @Override
    public void setImageListener(ImageButtonListener listener) {
        imageButtonListener = listener;
    }

    /**
     * Configura listener do botão de solicitar
     * @param listener
     */
    @Override
    public void setSolicitarListener(SolicitarButtonListener listener) {
        solicitarButtonListener = listener;
    }

    /**
     * Configura listener do botão de solicitações
     * @param listener
     */
    @Override
    public void setSolicitacoesListener(SolicitacoesButtonListener listener) {
        solicitacoesButtonListener = listener;
    }

    /**
     * Desabilita botão de solicitar
     */
    @Override
    public void disableSolicitarButton() {
        btSolicitar.setEnabled(false);
    }

    /**
     * Altera texto do botão
     * @param text
     */
    @Override
    public void setBottomButtonText(String text) {
        btSolicitar.setText(text);
    }

    /**
     * Altera texto do botão
     * @param text
     */
    @Override
    public void setTopButtonText(String text) {
        btSolicitacoes.setText(text);
    }

    /**
     * Altera visibilidade do botão
     * @param visibility
     */
    @Override
    public void visibilityTopButton(boolean visibility) {
        if(visibility){
            btSolicitacoes.setVisibility(View.VISIBLE);
        }
        else{
            btSolicitacoes.setVisibility(View.INVISIBLE);
        }
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
