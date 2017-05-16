package br.ufsm.projetosoftware.appdoacao.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.ufsm.projetosoftware.appdoacao.R;

/**
 * Created by Felipe on 14/05/2017.
 */

public class RegisterViewImpl implements RegisterView{

    private View rootView;
    private EditText etName;
    private EditText etEmail;
    private EditText etCpfcnpj;
    private EditText etPassword;
    private EditText etCep;
    private EditText etAdress;
    private EditText etAdressNumber;
    private EditText etComplement;
    private EditText etDistrict;
    private EditText etCity;
    private EditText etState;
    private Button btCreate;
    private RegisterButtonListener registerButtonListener;
    private CepListener cepListener;

    
    public RegisterViewImpl(View view){
        rootView = view;
        initialize();
        btCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (registerButtonListener != null) {
                    registerButtonListener.onRegisterClick();
                }
            }
        });
        etCep.addTextChangedListener(new TextWatcher() {

            boolean isUpdating;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str;
                if(isUpdating){
                    isUpdating = false;
                }else if(s.length() > 5 && !s.toString().contains("-")){
                    str = new StringBuilder(s.toString()).insert(5, "-").toString();
                    isUpdating = true;
                    etCep.setText(str);
                    etCep.setSelection(str.length());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                cepListener.onCepTyped();
            }
        });
    }
    
    private void initialize(){
        etName = (EditText) rootView.findViewById(R.id.input_name);
        etEmail = (EditText) rootView.findViewById(R.id.input_email);
        etCpfcnpj = (EditText) rootView.findViewById(R.id.input_cpfcnpj);
        etPassword = (EditText) rootView.findViewById(R.id.input_password);
        etCep = (EditText) rootView.findViewById(R.id.input_cep);
        etAdress = (EditText) rootView.findViewById(R.id.input_adress);
        etAdressNumber = (EditText) rootView.findViewById(R.id.input_adressnumber);
        etComplement = (EditText) rootView.findViewById(R.id.input_complement);
        etDistrict = (EditText) rootView.findViewById(R.id.input_district);
        etCity = (EditText) rootView.findViewById(R.id.input_city);
        etState = (EditText) rootView.findViewById(R.id.input_state);
        btCreate = (Button) rootView.findViewById(R.id.button_createAccount);
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
    public void setAdress(String adress) {
        etAdress.setText(adress);
    }

    @Override
    public void setDistrict(String district) {
        etDistrict.setText(district);
    }

    @Override
    public void setCity(String city) {
        etCity.setText(city);
    }

    @Override
    public void setState(String state) {
        etState.setText(state);
    }

    @Override
    public String getName() {
        return etName.getText().toString();
    }

    @Override
    public String getEmail() {
        return etEmail.getText().toString();
    }

    @Override
    public String getCpfCnpj() {
        return etCpfcnpj.getText().toString();
    }

    @Override
    public String getPassword() {
        return etPassword.getText().toString();
    }

    @Override
    public String getCep() {
        return etCep.getText().toString().replaceAll("-", "");
    }

    @Override
    public String getAdress() {
        return etAdress.getText().toString();
    }

    @Override
    public String getAdressNumber() {
        return etAdressNumber.getText().toString();
    }

    @Override
    public String getComplement() {
        return etComplement.getText().toString();
    }

    @Override
    public String getDistrict() {
        return etDistrict.getText().toString();
    }

    @Override
    public String getCity() {
        return etCity.getText().toString();
    }

    @Override
    public String getState() {
        return etState.getText().toString();
    }

    @Override
    public void setErrorCEP(String error) {
        etCep.setError(error);
    }

    @Override
    public void setRegisterListener(RegisterButtonListener listener) {
        registerButtonListener = listener;
    }

    @Override
    public void setCepListener(CepListener listener) {
        cepListener = listener;
    }
}
