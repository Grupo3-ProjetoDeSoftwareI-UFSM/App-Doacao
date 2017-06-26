package br.ufsm.projetosoftware.appdoacao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.json.JSONObject;

import br.ufsm.projetosoftware.appdoacao.models.Usuario;
import br.ufsm.projetosoftware.appdoacao.network.CEPResponse;
import br.ufsm.projetosoftware.appdoacao.network.IResultString;
import br.ufsm.projetosoftware.appdoacao.network.RegisterResponse;
import br.ufsm.projetosoftware.appdoacao.network.VolleyServiceString;
import br.ufsm.projetosoftware.appdoacao.utils.UserDataUtil;
import br.ufsm.projetosoftware.appdoacao.view.RegisterView;
import br.ufsm.projetosoftware.appdoacao.view.RegisterViewImpl;

/**
 * Activity da tela de registro de usuário.
 * Created on 15/05/2017.
 */
public class RegisterActivity extends AppCompatActivity implements RegisterView.RegisterButtonListener, RegisterView.CepListener{

    private RegisterView registerView;
    private String REGISTER_URL;
    private JSONObject dadosEndereco;
    private Usuario usuario;
    private IResultString resultCallback = null;
    private VolleyServiceString volleyService;
    private boolean cepValido;
    private final String POSTUSUARIO = "POSTUSUARIO";
    private final String GETCEP = "GETCEP";

    /**
     * Inicializa a Activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerView = new RegisterViewImpl(getWindow().getDecorView().getRootView());
        registerView.setRegisterListener(this);
        registerView.setCepListener(this);
        REGISTER_URL = getString(R.string.registerURL);
        cepValido = false;
        initCallback();
        volleyService = new VolleyServiceString(resultCallback, this);
    }


    /**
     * Método executado quando há alteração no texto do campo de CEP.
     * Executa método verificaCep quando o CEP for digitado.
     */
    @Override
    public void onCepTyped() {
        String CEP =  registerView.getCep();
        if(CEP.length() == 8){
            String url = "https://viacep.com.br/ws/" + CEP + "/json/";
            volleyService.getDataVolley(GETCEP, url);
        }
    }

    /**
     * Método executado quando é pressionado o botão Registrar.
     */
    @Override
    public void onRegisterClick() {
        registerUser();
    }

    /**
     * Faz o registro do usuário.
     */
    private void registerUser(){
        usuario = new Usuario();
        usuario.setNome(registerView.getNome());
        usuario.setEmail(registerView.getEmail());
        usuario.setCpfcnpj(registerView.getCpfCnpj());
        usuario.setSenha(registerView.getSenha());
        usuario.setCep(registerView.getCep());
        usuario.setEndereco(registerView.getEndereco());
        usuario.setEnderecoNumero(registerView.getEnderecoNumero());
        usuario.setComplemento(registerView.getComplemento());
        usuario.setBairro(registerView.getBairro());
        usuario.setCidade(registerView.getCidade());
        usuario.setEstado(registerView.getEstado());
        //Verifica dados
        if(!UserDataUtil.isEmailValid(usuario.getEmail())){
            registerView.setErrorEmail("Email inválido");
            return;
        }
        else if(!UserDataUtil.isCEPValid(usuario.getCep())){
            registerView.setErrorCEP("Cep inválido");
            return;
        }
        else if(usuario.getCpfcnpj().length() != 11 && usuario.getCpfcnpj().length() != 14){
            registerView.setErrorCpfCnpj("CPF ou CNPJ inválido");
            return;
        }
        else if(usuario.getCpfcnpj().length() == 11){
            if(!UserDataUtil.isCPFValid(usuario.getCpfcnpj())) {
                registerView.setErrorCpfCnpj("CPF inválido");
                return;
            }
        }
        else if(usuario.getCpfcnpj().length() == 14){
            if(!UserDataUtil.isCNPJValid(usuario.getCpfcnpj())) {
                registerView.setErrorCpfCnpj("CNPJ inválido");
                return;
            }
        }
        String usuarioJSON = new Gson().toJson(usuario);
        volleyService.postDataVolley(POSTUSUARIO, REGISTER_URL, usuarioJSON);
    }

    /**
     * Recebe a resposta do servidor, referente ao registro de usuário
     * @param response
     */
    private void postRegisterSucess(String response){
        Log.d("Response", response);
        RegisterResponse registerResponse = new Gson().fromJson(response, RegisterResponse.class);
        Toast.makeText(RegisterActivity.this, registerResponse.getMessage(), Toast.LENGTH_LONG).show();
        if(registerResponse.getSuccess() == 1) {
            Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(i);
        }
    }
    /**
     * Verificação do CEP
     */
    private void getCepSucess(String response){
        Log.d("Response", response);
        CEPResponse cepResponse = new Gson().fromJson(response, CEPResponse.class);
        if(cepResponse.getErro() != null){
            registerView.setErrorCEP("CEP inválido");
            cepValido = false;
        }
        else {
            registerView.setEndereco(cepResponse.getLogradouro());
            registerView.setBairro(cepResponse.getBairro());
            registerView.setCidade(cepResponse.getLocalidade());
            registerView.setEstado(cepResponse.getUf());
            cepValido = true;
        }
    }

    private void initCallback() {
        resultCallback = new IResultString() {
            /**
             * Sucesso na conexão com o servidor
             * @param requestType
             * @param response
             */
            @Override
            public void notifySuccess(String requestType, String response) {
                if(requestType.equals(GETCEP)){
                    getCepSucess(response);
                }
                else if(requestType.equals(POSTUSUARIO)){
                    postRegisterSucess(response);
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
                Toast.makeText(RegisterActivity.this,error.toString(),Toast.LENGTH_LONG).show();
            }
        };
    }
}
