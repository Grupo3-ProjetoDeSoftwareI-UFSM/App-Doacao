package br.ufsm.projetosoftware.appdoacao;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

/**
 * Activity para efetuar cadastro do usuário.
 *
 * @author Felipe
 * @version 2017.04.23
 */
public class RegisterActivityOld extends AppCompatActivity {
    private ProgressDialog pDialog;
    private static String REGISTER_URL = "http://www.appdoacao.esy.es/userRegister.php";
    private static final String TAG_SUCCESS = "success";
    private JSONObject dadosEndereco;
    EditText etName;
    EditText etEmail;
    EditText etCpfcnpj;
    EditText etPassword;
    EditText etCep;
    EditText etAdress;
    EditText etAdressNumber;
    EditText etComplement;
    EditText etDistrict;
    EditText etCity;
    EditText etState;
    Button btCreate;
    String name;
    String email;
    String cpfcnpj;
    String password;
    String cep;
    String adress;
    String adressnumber;
    String complement;
    String district;
    String city;
    String state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //Edit text
        etName = (EditText) findViewById(R.id.input_name);
        etEmail = (EditText) findViewById(R.id.input_email);
        etCpfcnpj = (EditText) findViewById(R.id.input_cpfcnpj);
        etPassword = (EditText) findViewById(R.id.input_password);
        etCep = (EditText) findViewById(R.id.input_cep);
        etAdress = (EditText) findViewById(R.id.input_adress);
        etAdressNumber = (EditText) findViewById(R.id.input_adressnumber);
        etComplement = (EditText) findViewById(R.id.input_complement);
        etDistrict = (EditText) findViewById(R.id.input_district);
        etCity = (EditText) findViewById(R.id.input_city);
        etState = (EditText) findViewById(R.id.input_state);
        //Monitora alterações no campo de CEP para buscar dados do endereço
        etCep.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() == 8){
                    verificaCep(s.toString());
                    if(dadosEndereco != null){
                        try {
                            if(dadosEndereco.getString("erro") == "true"){
                                etCep.setError("CEP inválido");
                            }
                            else if(dadosEndereco.getString("erroconexao") == "true"){
                                etCep.setError("Sem internet");
                            }
                            else{
                                etAdress.setText(dadosEndereco.getString("logradouro"));
                                etDistrict.setText(dadosEndereco.getString("bairro"));
                                etCity.setText(dadosEndereco.getString("localidade"));
                                etState.setText(dadosEndereco.getString("uf"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        //Button
        btCreate = (Button) findViewById(R.id.button_createAccount);
        btCreate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                registerUser();
            }
        });
    }

    /**
     * Efetua o registro do usuário no banco de dados.
     */
    private void registerUser(){
        name = etName.getText().toString();
        email = etEmail.getText().toString();
        cpfcnpj = etCpfcnpj.getText().toString();
        password = etPassword.getText().toString();
        cep = etCep.getText().toString();
        adress = etAdress.getText().toString();
        adressnumber = etAdressNumber.getText().toString();
        complement = etComplement.getText().toString();
        district = etDistrict.getText().toString();
        city = etCity.getText().toString();
        state = etState.getText().toString();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response", response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(RegisterActivityOld.this, jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                            if(jsonObject.getInt("sucess") == 1){
                                Intent i = new Intent(RegisterActivityOld.this, LoginActivityOld.class);
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
                        Toast.makeText(RegisterActivityOld.this,error.toString(),Toast.LENGTH_LONG).show();
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
     * Verifica CEP utilizando o webservice VIACEP
     * Retorna JSONObject com dados do endereço se CEP for valido
     * Retorna JSONObject com key Erro == true caso CEP seja inválido
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
                                etCep.setError("CEP inválido");
                            }
                            else {
                                etAdress.setText(dadosEndereco.getString("logradouro"));
                                etDistrict.setText(dadosEndereco.getString("bairro"));
                                etCity.setText(dadosEndereco.getString("localidade"));
                                etState.setText(dadosEndereco.getString("uf"));
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
                            etCep.setError("Sem internet");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(RegisterActivityOld.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
