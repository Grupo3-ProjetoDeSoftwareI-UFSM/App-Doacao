package br.ufsm.projetosoftware.appdoacao.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.ufsm.projetosoftware.appdoacao.R;

/**
 * Created by Felipe on 13/05/2017.
 */

public class LoginViewImpl implements LoginView {

    private View rootView;
    private LoginButtonListener loginListener;
    private RegisterButtonListener registerListener;

    private AutoCompleteTextView emailView;
    private EditText passwordView;
    private View progressView;
    private View loginFormView;
    private Button emailSignInButton;
    private TextView accountRegisterLink;
    private View focusView;

    /**
     * Construtor da classe
     * @param view
     */
    public LoginViewImpl(View view) {
        //rootView = inflater.inflate(R.layout.activity_login, container, false);
        rootView = view;
        initialize();

        emailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loginListener != null) {
                    loginListener.onLoginClick();
                }
            }
        });

        accountRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(registerListener != null){
                    registerListener.onRegisterClick();
                }
            }
        });
    }

    /**
     * Método para inicializar os elementos da tela
     */
    private void initialize(){
        emailSignInButton = (Button) rootView.findViewById(R.id.email_sign_in_button);
        accountRegisterLink = (TextView) rootView.findViewById(R.id.link_signup);
        emailView = (AutoCompleteTextView) rootView.findViewById(R.id.email);
        passwordView = (EditText) rootView.findViewById(R.id.password);
        loginFormView = rootView.findViewById(R.id.login_form);
        progressView = rootView.findViewById(R.id.login_progress);
        focusView = null;
    }


    /**
     * Retorna a view
     * @return
     */
    @Override
    public View getRootView() {
        return rootView;
    }

    /**
     * Retorna o estado da View
     * @return
     */
    @Override
    public Bundle getViewState() {
        return null;
    }

    /**
     * Retorna o Email do usuário
     * @return email
     */
    @Override
    public String getEmail() {
        String email = emailView.getText().toString();
        return email;
    }

    /**
     * Retorna a senha
     * @return senha
     */
    @Override
    public String getPassword() {
        String password = passwordView.getText().toString();
        return password;
    }

    /**
     * Informa que houve um erro no email digitado
     * @param error
     */
    @Override
    public void setErrorEmail(String error) {
        emailView.setError(error);
        focusView  = emailView;
    }

    /**
     * Informa que houve um erro na senha digitada
     * @param error
     */
    @Override
    public void setErrorPassword(String error) {
        passwordView.setError(error);
        focusView = passwordView;
    }

    /**
     * Redireciona o foco da tela, quando ocorrer erro de digitação
     */
    @Override
    public void setErrorFocus(){
        focusView.requestFocus();
    }

    /**
     * Efeito de carregar: Verifica qual a versão do Android
     * @param show
     */
    @Override
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = rootView.getResources().getInteger(android.R.integer.config_shortAnimTime);

            loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            loginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            progressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    progressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    /**
     * Setar a classe LoginActivity como listener do botão de Loguin
     * @param listener
     */
    @Override
    public void setLoginListener(LoginButtonListener listener) {
        loginListener = listener;
    }

    /**
     * Setar a classe LoginActivity com Listener do botão de registro
     * @param listener
     */
    @Override
    public void setRegisterListener(RegisterButtonListener listener) {
        registerListener = listener;
    }
}
