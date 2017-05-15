package br.ufsm.projetosoftware.appdoacao.view;

/**
 * Created by Felipe on 13/05/2017.
 */

public interface LoginView extends ViewMvc {

    interface LoginButtonListener{
        void onLoginClick();
    }

    interface RegisterButtonListener{
        void onRegisterClick();
    }

    String getEmail();

    String getPassword();

    void setErrorEmail(String error);

    void setErrorPassword(String error);

    void setErrorFocus();

    void showProgress(final boolean show);

    void setLoginListener(LoginButtonListener listener);

    void setRegisterListener(RegisterButtonListener listener);
}
