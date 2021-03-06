package br.ufsm.projetosoftware.appdoacao;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import br.ufsm.projetosoftware.appdoacao.network.IResultString;
import br.ufsm.projetosoftware.appdoacao.network.LoginPost;
import br.ufsm.projetosoftware.appdoacao.network.LoginResponse;
import br.ufsm.projetosoftware.appdoacao.network.VolleyServiceString;
import br.ufsm.projetosoftware.appdoacao.utils.UserDataUtil;
import br.ufsm.projetosoftware.appdoacao.view.LoginView;
import br.ufsm.projetosoftware.appdoacao.view.LoginViewImpl;

/**
 * Activity da tela de login do usuário.
 * Created on 13/05/2017.
 */
public class LoginActivity extends AppCompatActivity
        implements LoginView.LoginButtonListener, LoginView.RegisterButtonListener {

    private LoginView loginView;
    private String authToken = null;
    private String LOGIN_URL;
    private IResultString resultCallback = null;
    private VolleyServiceString volleyService;
    private final String POSTLOGIN = "POSTLOGIN";
    private SharedPreferences loginSettings;

    /**
     * Inicializa a Activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * Verifa se existe uma chave de autenticação
         */
        loginSettings = getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
        /**
         * Se existe, vai para o Método toMainActivity()
         */
        if(loginSettings.contains("authToken")){
            toMainActivity();
        }
        /**
         * Inicializa o controle da view
         */
        setContentView(R.layout.activity_login);
        loginView = new LoginViewImpl(getWindow().getDecorView().getRootView());
        loginView.setLoginListener(this);
        loginView.setRegisterListener(this);
        /**
         * Configura a conexão com o servidor
         */
        LOGIN_URL = getString(R.string.loginURL);
        initCallback();
        /**
         * Inicializa a classe que faz conexão com o servidor
         */
        volleyService = new VolleyServiceString(resultCallback, this);
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
        /**
         * Cria uma classe com os parametros a serem enviados pro servidor
         */
        LoginPost loginPost = new LoginPost(email, password);
        /**
         * Converte o objeto com os parametros para uma string JSon, pela classe da Google: Gson
         */
        String loginJSON = new Gson().toJson(loginPost);

        /**
         * Passa a string Json(valores a ser enviados)
         */
        volleyService.postDataVolley(POSTLOGIN, LOGIN_URL, loginJSON);
    }

    /**
     * Inicia activity de registro do usuário.
     * Troca de Activity(Login para Register)
     */
    private void toSignUp(){
        Intent i;

        i = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(i);
    }

    /**
     * Troca de Activity(Login para Main)
     */
    private void toMainActivity(){
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i);
    }

    /**
     * Recebe a resposta do servidor
     * @param response
     */
    private void postLoginSucess(String response){
        System.out.println(response);
        LoginResponse loginResponse = new Gson().fromJson(response, LoginResponse.class);
        loginView.showProgress(false);
        switch(loginResponse.getLoginStatus()){
            //Retorna 1 caso o login tenha sido efetuado com sucesso
            case 1:
                authToken = loginResponse.getauthToken();
                System.out.println(authToken);
                loginSettings.edit().putString("authToken", authToken).commit();
                loginSettings.edit().putInt("uid", loginResponse.getUid()).commit();
                toMainActivity();
                break;
            //retorna 2 se o email não estiver cadastrado
            case 2:
                loginView.setErrorEmail("Email não registrado");
                break;
            //retorna 3 se a senha estiver incorreta
            case 3:
                loginView.setErrorPassword("Senha incorreta");
                break;
            //Erro de consulta
            case 4:
                Toast.makeText(LoginActivity.this, "Erro ao efetuar login, tente novamente", Toast.LENGTH_LONG).show();
                break;
            case 5:
                loginView.setErrorEmail("Campo não preenchido.");
                break;
            //Email não foi ativado
            case 6:
                loginView.setErrorEmail("Conta não ativada, verifique seu email.");
                break;

            default:
                Toast.makeText(LoginActivity.this, response, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Receber uma resposta do servidor
     */
    private void initCallback() {
        resultCallback = new IResultString() {
            /**
             * Sucesso na conexão com o servidor
             * @param requestType
             * @param response
             */
            @Override
            public void notifySuccess(String requestType, String response) {
                if(requestType.equals(POSTLOGIN)){
                    postLoginSucess(response);
                }
            }

            /**
             * Erro na conexão com o servidor(Ex: Sem internet)
             * @param requestType
             * @param error
             */
            @Override
            public void notifyError(String requestType, VolleyError error) {
                Log.d("Erro na conexao", error.toString());
                loginView.showProgress(false);
                Toast.makeText(LoginActivity.this,error.toString(),Toast.LENGTH_LONG).show();
            }
        };
    }
}
