package br.ufsm.projetosoftware.appdoacao;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
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
public class RegisterActivity extends AppCompatActivity {
    private ProgressDialog pDialog;
    private static String REGISTER_URL = "http://www.appdoacao.esy.es/userRegister.php";
    private static final String TAG_SUCCESS = "success";
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
                            Toast.makeText(RegisterActivity.this, jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                            if(jsonObject.getInt("sucess") == 1){
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

}
