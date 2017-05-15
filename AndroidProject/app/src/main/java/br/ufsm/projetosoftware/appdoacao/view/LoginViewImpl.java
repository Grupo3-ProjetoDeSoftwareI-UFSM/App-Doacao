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

    private void initialize(){
        emailSignInButton = (Button) rootView.findViewById(R.id.email_sign_in_button);
        accountRegisterLink = (TextView) rootView.findViewById(R.id.link_signup);
        emailView = (AutoCompleteTextView) rootView.findViewById(R.id.email);
        passwordView = (EditText) rootView.findViewById(R.id.password);
        loginFormView = rootView.findViewById(R.id.login_form);
        progressView = rootView.findViewById(R.id.login_progress);
        focusView = null;
    }



    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public Bundle getViewState() {
        return null;
    }

    @Override
    public String getEmail() {
        String email = emailView.getText().toString();
        return email;
    }

    @Override
    public String getPassword() {
        String password = passwordView.getText().toString();
        return password;
    }

    @Override
    public void setErrorEmail(String error) {
        emailView.setError(error);
        focusView  = emailView;
    }

    @Override
    public void setErrorPassword(String error) {
        passwordView.setError(error);
        focusView = passwordView;
    }
    
    @Override
    public void setErrorFocus(){
        focusView.requestFocus();
    }

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

    @Override
    public void setLoginListener(LoginButtonListener listener) {
        loginListener = listener;
    }

    @Override
    public void setRegisterListener(RegisterButtonListener listener) {
        registerListener = listener;
    }
}
