package br.ufsm.projetosoftware.appdoacao.view;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import br.ufsm.projetosoftware.appdoacao.R;

/**
 * Implementação da interface da tela de nova doação
 */

public class NewDonationViewImpl implements NewDonationView {

    private View rootView;
    private SelectImageListener selectImageListener;
    private RegisterDonationListener registerDonationListener;
    private SelectTipoListener selectTipoListener;
    private Spinner spTipo;
    private Spinner spCategoria;
    private EditText etTitulo;
    private EditText etDescricao;
    private Button btSelectImage;
    private ImageView ivImage;
    private Button btRegisterDonation;

    public NewDonationViewImpl(View view){
        rootView = view;
        initialize();
        btSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectImageListener != null){
                    selectImageListener.onSelectImageClick();
                }
            }
        });
        btRegisterDonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(registerDonationListener != null){
                    registerDonationListener.onRegisterDonationClick();
                }
            }
        });
        spTipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(selectTipoListener != null){
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
        spTipo = (Spinner) rootView.findViewById(R.id.spType);
        spCategoria = (Spinner) rootView.findViewById(R.id.spCategory);
        etTitulo = (EditText) rootView.findViewById(R.id.etTitle);
        etDescricao = (EditText) rootView.findViewById(R.id.etDescription);
        btSelectImage = (Button) rootView.findViewById(R.id.btSelectImage);
        ivImage = (ImageView) rootView.findViewById(R.id.ivImage);
        btRegisterDonation = (Button) rootView.findViewById(R.id.btRegisterDonation);
    }


    @Override
    public View getRootView() {
        return null;
    }

    @Override
    public Bundle getViewState() {
        return null;
    }

    /**
     * Retorna tipo selecionado no spinner
     * @return String tipo
     */
    @Override
    public String getTipo() {
        return spTipo.getSelectedItem().toString();
    }

    /**
     * Retorna categoria selecionada no spinner
     * @return String categoria
     */
    @Override
    public String getCategoria() {
        return spCategoria.getSelectedItem().toString();
    }

    /**
     * Retorna titulo
     * @return String titulo
     */
    @Override
    public String getTitulo() {
        return etTitulo.getText().toString();
    }

    /**
     * Retorna descrição
     * @return String descrição
     */
    @Override
    public String getDescricao() {
        return etDescricao.getText().toString();
    }

    /**
     * Retorna imagem do imageview
     * @return
     */
    @Override
    public Bitmap getImage() {
        return null;
    }

    /**
     * Exibi imagem no imageView
     * @param image
     */
    @Override
    public void setImage(Bitmap image) {
        ivImage.setImageBitmap(image);
        ivImage.setVisibility(View.VISIBLE);
    }

    /**
     * Configura listener do botão de escolher imagem
     * @param listener
     */
    @Override
    public void setSelectImageListener(SelectImageListener listener) {
        selectImageListener = listener;
    }

    /**
     * Configura listener do botão de cadastro de doação
     * @param listener
     */
    @Override
    public void setRegisterDonationListener(RegisterDonationListener listener) {
        registerDonationListener = listener;
    }

    /**
     * Configura listener do spinner de tipo
     * @param listener
     */
    @Override
    public void setSelectTipoListener(SelectTipoListener listener) {
        selectTipoListener = listener;
    }

    /**
     * Configura valores do spinner de tipo
     * @param adapter
     */
    @Override
    public void setTipoValues(ArrayAdapter adapter) {
        spTipo.setAdapter(adapter);
    }

    /**
     * Configura valores do spinner de categoria
     * @param adapter
     */
    @Override
    public void setCategoriaValues(ArrayAdapter adapter) {
        spCategoria.setAdapter(adapter);
    }
}
