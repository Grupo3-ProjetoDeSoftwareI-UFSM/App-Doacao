package br.ufsm.projetosoftware.appdoacao.view;

/**
 * Created by Felipe on 14/05/2017.
 */

public interface RegisterView extends ViewMvc{

    interface RegisterButtonListener{
        void onRegisterClick();
    }

    interface CepListener{
        void onCepTyped();
    }

    void setEndereco(String endereco);

    void setBairro(String bairro);

    void setCidade(String cidade);

    void setEstado(String estado);

    String getNome();

    String getEmail();

    String getCpfCnpj();

    String getSenha();

    String getCep();

    String getEndereco();

    String getEnderecoNumero();

    String getComplemento();

    String getBairro();

    String getCidade();

    String getEstado();

    void setErrorCEP(String error);

    void setErrorEmail(String error);

    void setErrorCpfCnpj(String error);

    void setRegisterListener(RegisterButtonListener listener);

    void setCepListener(CepListener listener);
}
