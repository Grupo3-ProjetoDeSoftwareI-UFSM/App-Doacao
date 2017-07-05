package br.ufsm.projetosoftware.appdoacao.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.ufsm.projetosoftware.appdoacao.R;

/**
 * Implementação da interface da tela de registro de usuário
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

    /**
     * Exibe endereço no edittext
     * @param endereco
     */
    @Override
    public void setEndereco(String endereco) {
        etEndereco.setText(endereco);
    }

    /**
     * Exibe bairro
     * @param bairro
     */
    @Override
    public void setBairro(String bairro) {
        etBairro.setText(bairro);
    }

    /**
     * Exibe cidade
     * @param cidade
     */
    @Override
    public void setCidade(String cidade) {
        etCidade.setText(cidade);
    }

    /**
     * Exibe estado
     * @param estado
     */
    @Override
    public void setEstado(String estado) {
        etEstado.setText(estado);
    }

    /**
     * Retorna nome
     * @return String nome
     */
    @Override
    public String getNome() {
        return etNome.getText().toString();
    }

    /**
     * Retorna email
     * @return String email
     */
    @Override
    public String getEmail() {
        return etEmail.getText().toString();
    }

    /**
     * Retorna cpf/cnpj
     * @return cpf e cnpj
     */
    @Override
    public String getCpfCnpj() {
        return etCpfcnpj.getText().toString();
    }

    /**
     * Retorna senha
     * @return String senha
     */
    @Override
    public String getSenha() {
        return etSenha.getText().toString();
    }

    /**
     * Retorna cep
     * @return cep
     */
    @Override
    public String getCep() {
        return etCep.getText().toString().replaceAll("-", "");
    }

    /**
     * Retorna endereço
     * @return String endereço
     */
    @Override
    public String getEndereco() {
        return etEndereco.getText().toString();
    }

    /**
     * Retorna número do endereço
     * @return String endereço
     */
    @Override
    public String getEnderecoNumero() {
        return etEnderecoNumero.getText().toString();
    }

    /**
     * Retorna complemento
     * @return String complemento
     */
    @Override
    public String getComplemento() {
        return etComplemento.getText().toString();
    }

    /**
     * Retorna bairro
     * @return bairro
     */
    @Override
    public String getBairro() {
        return etBairro.getText().toString();
    }

    /**
     * Retorna cidade
     * @return
     */
    @Override
    public String getCidade() {
        return etCidade.getText().toString();
    }

    /**
     * Retorna estado
     * @return estado
     */
    @Override
    public String getEstado() {
        return etEstado.getText().toString();
    }

    /**
     * Exibe erro no campo de cep
     * @param error
     */
    @Override
    public void setErrorCEP(String error) {
        etCep.setError(error);
    }

    /**
     * Exibe erro no campo de email
     * @param error
     */
    @Override
    public void setErrorEmail(String error) {
        etEmail.setError(error);
    }

    /**
     * Exibe erro no campo de cpf
     * @param error
     */
    @Override
    public void setErrorCpfCnpj(String error) {
        etCpfcnpj.setError(error);
    }

    /**
     * Configura listener do botão de concluir cadastro
     * @param listener
     */
    @Override
    public void setRegisterListener(RegisterButtonListener listener) {
        registerButtonListener = listener;
    }

    /**
     * Configura listener do campo de cep
     * @param listener
     */
    @Override
    public void setCepListener(CepListener listener) {
        cepListener = listener;
    }
}
