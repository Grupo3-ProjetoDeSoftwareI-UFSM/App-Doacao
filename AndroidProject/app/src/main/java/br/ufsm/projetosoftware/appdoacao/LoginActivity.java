package br.ufsm.projetosoftware.appdoacao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import br.ufsm.projetosoftware.appdoacao.view.LoginView;
import br.ufsm.projetosoftware.appdoacao.view.LoginViewImpl;

/**
 * Activity da tela de login do usuário.
 * Created by Felipe on 13/05/2017.
 */
public class LoginActivity extends AppCompatActivity
        implements LoginView.LoginButtonListener, LoginView.RegisterButtonListener {

    private LoginView loginView;
    private String sessionToken = null;
    private String LOGIN_URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginView = new LoginViewImpl(getWindow().getDecorView().getRootView());
        loginView.setLoginListener(this);
        loginView.setRegisterListener(this);
        LOGIN_URL = getString(R.string.loginURL);
    }

    /**
     * Método executado quando botão de login é pressionado.
     */
    @Override
    public void onLoginClick() {
        attemptLogin();

    }

    /**
     * Método executado quando botão de registro é pressionado.
     */
    @Override
    public void onRegisterClick() {
        toSignUp();
    }

    /**
     * Tenta fazer login verificando dados.
     */
    private void attemptLogin(){
        loginView.setErrorEmail(null);
        loginView.setErrorPassword(null);
        String email = loginView.getEmail();
        String password = loginView.getPassword();
        boolean cancel = false;
        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !UserDataUtil.isPasswordValid(password)) {
            loginView.setErrorPassword(getString(R.string.error_invalid_password));
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            loginView.setErrorEmail(getString(R.string.error_field_required));
            cancel = true;
        } else if (!UserDataUtil.isEmailValid(email)) {
            loginView.setErrorEmail(getString(R.string.error_invalid_email));
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            loginView.setErrorFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            loginView.showProgress(true);
            userLogin(email, password);
            //mAuthTask = new UserLoginTask(email, password);
            //mAuthTask.execute((Void) null);
        }
    }

    /**
     * Faz login do usuário verificando email e senha com o servidor.
     * @param email
     * @param password
     */
    private void userLogin(String email, String password){
        final String mEmail =  email;
        final String mPassword = password;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loginView.showProgress(false);
                        Log.d("Response", response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int loginStatus = jsonObject.getInt("loginStatus");
                            switch(loginStatus){
                                //Retorna 1 caso o login tenha sido efetuado com sucesso
                                case 1:
                                    sessionToken = jsonObject.getString("sessionToken");
                                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                    i.putExtra("sessionToken", sessionToken);
                                    startActivity(i);
                                    break;
                                //retorna 2 se o email não estiver cadastrado
                                case 2:
                                    loginView.setErrorEmail("Email não registrado");
                                    break;
                                //retorna 3 se a senha estiver incorreta
                                case 3:
                                    loginView.setErrorPassword("Senha incorreta");
                                    break;
                                case 4:
                                    Toast.makeText(LoginActivity.this, "Erro ao efetuar login, tente novamente", Toast.LENGTH_LONG).show();
                                    break;
                                case 5:
                                    loginView.setErrorEmail("Campo não preenchido.");
                                    break;
                                default:
                                    Toast.makeText(LoginActivity.this, response, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Erro na conexao", error.toString());
                        loginView.showProgress(false);
                        Toast.makeText(LoginActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("email", mEmail);
                params.put("password", mPassword);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    /**
     * Inicia activity de registro do usuário.
     */
    private void toSignUp(){
        Intent i;
        i = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(i);
    }
}
