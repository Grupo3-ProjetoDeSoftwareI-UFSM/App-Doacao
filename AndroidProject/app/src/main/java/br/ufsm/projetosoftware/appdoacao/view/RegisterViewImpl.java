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
    private EditText etNome;
    private EditText etEmail;
    private EditText etCpfcnpj;
    private EditText etSenha;
    private EditText etCep;
    private EditText etEndereco;
    private EditText etEnderecoNumero;
    private EditText etComplemento;
    private EditText etBairro;
    private EditText etCidade;
    private EditText etEstado;
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
        etNome = (EditText) rootView.findViewById(R.id.input_nome);
        etEmail = (EditText) rootView.findViewById(R.id.input_email);
        etCpfcnpj = (EditText) rootView.findViewById(R.id.input_cpfcnpj);
        etSenha = (EditText) rootView.findViewById(R.id.input_senha);
        etCep = (EditText) rootView.findViewById(R.id.input_cep);
        etEndereco = (EditText) rootView.findViewById(R.id.input_endereco);
        etEnderecoNumero = (EditText) rootView.findViewById(R.id.input_endereconumero);
        etComplemento = (EditText) rootView.findViewById(R.id.input_complemento);
        etBairro = (EditText) rootView.findViewById(R.id.input_bairro);
        etCidade = (EditText) rootView.findViewById(R.id.input_cidade);
        etEstado = (EditText) rootView.findViewById(R.id.input_estado);
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
    public void setEndereco(String endereco) {
        etEndereco.setText(endereco);
    }

    @Override
    public void setBairro(String bairro) {
        etBairro.setText(bairro);
    }

    @Override
    public void setCidade(String cidade) {
        etCidade.setText(cidade);
    }

    @Override
    public void setEstado(String estado) {
        etEstado.setText(estado);
    }

    @Override
    public String getNome() {
        return etNome.getText().toString();
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
    public String getSenha() {
        return etSenha.getText().toString();
    }

    @Override
    public String getCep() {
        return etCep.getText().toString().replaceAll("-", "");
    }

    @Override
    public String getEndereco() {
        return etEndereco.getText().toString();
    }

    @Override
    public String getEnderecoNumero() {
        return etEnderecoNumero.getText().toString();
    }

    @Override
    public String getComplemento() {
        return etComplemento.getText().toString();
    }

    @Override
    public String getBairro() {
        return etBairro.getText().toString();
    }

    @Override
    public String getCidade() {
        return etCidade.getText().toString();
    }

    @Override
    public String getEstado() {
        return etEstado.getText().toString();
    }

    @Override
    public void setErrorCEP(String error) {
        etCep.setError(error);
    }

    @Override
    public void setErrorEmail(String error) {
        etEmail.setError(error);
    }

    @Override
    public void setErrorCpfCnpj(String error) {
        etCpfcnpj.setError(error);
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
