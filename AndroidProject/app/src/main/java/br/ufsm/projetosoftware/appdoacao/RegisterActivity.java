package br.ufsm.projetosoftware.appdoacao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import br.ufsm.projetosoftware.appdoacao.view.RegisterView;
import br.ufsm.projetosoftware.appdoacao.view.RegisterViewImpl;

/**
 * Activity da tela de registro de usuário.
 * Created by Felipe on 15/05/2017.
 */
public class RegisterActivity extends AppCompatActivity implements RegisterView.RegisterButtonListener, RegisterView.CepListener{

    private RegisterView registerView;
    private String REGISTER_URL;
    private JSONObject dadosEndereco;
    private String name;
    private String email;
    private String cpfcnpj;
    private String password;
    private String cep;
    private String adress;
    private String adressnumber;
    private String complement;
    private String district;
    private String city;
    private String state;
    private boolean cepValido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerView = new RegisterViewImpl(getWindow().getDecorView().getRootView());
        registerView.setRegisterListener(this);
        registerView.setCepListener(this);
        REGISTER_URL = getString(R.string.registerURL);
        cepValido = false;
    }


    /**
     * Método executado quando há alteração no texto do campo de CEP.
     * Executa método verificaCep quando o CEP for digitado.
     */
    @Override
    public void onCepTyped() {
        String CEP =  registerView.getCep();
        if(CEP.length() == 8){
            verificaCep(CEP.toString());
            /*if(dadosEndereco != null){
                try {
                    if(dadosEndereco.getString("erro") == "true"){
                        registerView.setErrorCEP("CEP inválido");
                    }
                    else if(dadosEndereco.getString("erroconexao") == "true"){
                        registerView.setErrorCEP("Sem internet");
                    }
                    else{
                        registerView.setAdress(dadosEndereco.getString("logradouro"));
                        registerView.setDistrict(dadosEndereco.getString("bairro"));
                        registerView.setCity(dadosEndereco.getString("localidade"));
                        registerView.setState(dadosEndereco.getString("uf"));
                        cepValido = true;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }*/
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
        name = registerView.getName();
        email = registerView.getEmail();
        cpfcnpj = registerView.getCpfCnpj();
        password = registerView.getPassword();
        cep = registerView.getCep();
        adress = registerView.getAdress();
        adressnumber = registerView.getAdressNumber();
        complement = registerView.getComplement();
        district = registerView.getDistrict();
        city = registerView.getCity();
        state = registerView.getState();
        if(!UserDataUtil.isEmailValid(email)){
            registerView.setErrorEmail("Email inválido");
            return;
        }
        else if(!UserDataUtil.isCEPValid(cep)){
            registerView.setErrorCEP("Cep inválido");
            return;
        }
        else if(cpfcnpj.length() != 11 && cpfcnpj.length() != 14){
            registerView.setErrorCpfCnpj("CPF ou CNPJ inválido");
            return;
        }
        else if(cpfcnpj.length() == 11){
            if(!UserDataUtil.isCPFValid(cpfcnpj)) {
                registerView.setErrorCpfCnpj("CPF inválido");
                return;
            }
        }
        else if(cpfcnpj.length() == 14){
            if(!UserDataUtil.isCNPJValid(cpfcnpj)) {
                registerView.setErrorCpfCnpj("CNPJ inválido");
                return;
            }
        }
        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response", response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(RegisterActivity.this, jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                            if(jsonObject.getInt("success") == 1){
                                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(i);
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
                        Toast.makeText(RegisterActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("nome", name);
                params.put("email", email);
                params.put("cpfcnpj", cpfcnpj);
                params.put("tipo", "f");
                params.put("password", password);
                params.put("cep", cep);
                params.put("endereco", adress);
                params.put("numero", adressnumber);
                params.put("complemento", complement);
                params.put("bairro", district);
                params.put("cidade", city);
                params.put("estado", state);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    /**
     * Verifica cep e preenche dados de enreço automaticamente conforme cep.
     * @param cep
     */
    private void verificaCep(String cep){
        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        final String erroConexao = "{\"erroconexao\": \"true\"}";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response", response);
                        try {
                            dadosEndereco = new JSONObject(response);
                            if(dadosEndereco.has("erro")){
                                registerView.setErrorCEP("CEP inválido");
                                cepValido = false;
                            }
                            else {
                                registerView.setAdress(dadosEndereco.getString("logradouro"));
                                registerView.setDistrict(dadosEndereco.getString("bairro"));
                                registerView.setCity(dadosEndereco.getString("localidade"));
                                registerView.setState(dadosEndereco.getString("uf"));
                                cepValido = true;
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
                        try {
                            dadosEndereco = new JSONObject(erroConexao);
                            registerView.setErrorCEP("Sem internet");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(RegisterActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
