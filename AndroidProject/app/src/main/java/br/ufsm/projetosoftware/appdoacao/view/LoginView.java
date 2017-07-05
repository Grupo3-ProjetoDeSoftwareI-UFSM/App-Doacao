package br.ufsm.projetosoftware.appdoacao.view;

/**
 * Interface da tela de login
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
