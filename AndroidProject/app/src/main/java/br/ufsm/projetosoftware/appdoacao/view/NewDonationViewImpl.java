package br.ufsm.projetosoftware.appdoacao.view;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import br.ufsm.projetosoftware.appdoacao.R;

/**
 * Created by Felipe on 20/05/2017.
 */

public class NewDonationViewImpl implements NewDonationView {

    private View rootView;
    private SelectImageListener selectImageListener;
    private RegisterDonationListener registerDonationListener;
    private Spinner spType;
    private Spinner spCategory;
    private EditText etTitle;
    private EditText etDescription;
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
    }

    private void initialize(){
        spType = (Spinner) rootView.findViewById(R.id.spType);
        spCategory = (Spinner) rootView.findViewById(R.id.spCategory);
        etTitle = (EditText) rootView.findViewById(R.id.etTitle);
        etDescription = (EditText) rootView.findViewById(R.id.etDescription);
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

    @Override
    public void setImage(Bitmap image) {
        ivImage.setImageBitmap(image);
        ivImage.setVisibility(View.VISIBLE);
    }

    @Override
    public void setSelectImageListener(SelectImageListener listener) {
        selectImageListener = listener;
    }

    @Override
    public void setRegisterDonationListener(RegisterDonationListener listener) {
        registerDonationListener = listener;
    }
}