package br.ufsm.projetosoftware.appdoacao.view;

import android.graphics.Bitmap;

/**
 * Created by Felipe on 17/06/2017.
 */

public interface DonationView extends ViewMvc{

    interface ImageButtonListener{
        void onImageClick();
    }

    interface SolicitarButtonListener{
        void onSolicitarClick();
    }

    interface SolicitacoesButtonListener{
        void onSolicitacoesClick();
    }

    void setTitulo(String titulo);

    void setCategoria(String categoria);

    void setDescricao(String descricao);

    void setImagem(Bitmap imagem);

    void setImageListener(ImageButtonListener listener);

    void setSolicitarListener(SolicitarButtonListener listener);

    void setSolicitacoesListener(SolicitacoesButtonListener listener);

    void disableSolicitarButton();

    void setBottomButtonText(String text);

    void setTopButtonText(String text);

    void visibilityTopButton(boolean visibility);
}
