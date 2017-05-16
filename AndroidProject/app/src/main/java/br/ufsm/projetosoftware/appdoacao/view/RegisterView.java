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

    void setAdress(String adress);

    void setDistrict(String district);

    void setCity(String city);

    void setState(String state);

    String getName();

    String getEmail();

    String getCpfCnpj();

    String getPassword();

    String getCep();

    String getAdress();

    String getAdressNumber();

    String getComplement();

    String getDistrict();

    String getCity();

    String getState();

    void setErrorCEP(String error);

    void setRegisterListener(RegisterButtonListener listener);

    void setCepListener(CepListener listener);
}
