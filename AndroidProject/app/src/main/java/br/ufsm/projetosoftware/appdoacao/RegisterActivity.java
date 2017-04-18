package br.ufsm.projetosoftware.appdoacao;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {
    private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    private static String url_create_user = "http://localhost/android_connect/create_user.php";
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
                name = etName.getText().toString();
                email = etName.getText().toString();
                cpfcnpj = etName.getText().toString();
                password = etName.getText().toString();
                cep = etName.getText().toString();
                adress = etName.getText().toString();
                adressnumber = etName.getText().toString();
                complement = etName.getText().toString();
                district = etName.getText().toString();
                city = etName.getText().toString();
                state = etName.getText().toString();
                new CreateNewUser().execute();
            }
        });
    }

    class CreateNewUser extends AsyncTask<String, String, String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(RegisterActivity.this);
            pDialog.setMessage("Registering User..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        protected String doInBackground(String... args) {

            // Building Parameters
            /*
            ContentValues params = new ContentValues();
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
            */
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("name", name));
            params.add(new BasicNameValuePair("email", email));
            params.add(new BasicNameValuePair("cpfcnpj", cpfcnpj));
            params.add(new BasicNameValuePair("tipo", "f"));
            params.add(new BasicNameValuePair("password", password));
            params.add(new BasicNameValuePair("cep", cep));
            params.add(new BasicNameValuePair("endereco", adress));
            params.add(new BasicNameValuePair("numero", adressnumber));
            params.add(new BasicNameValuePair("complemento", complement));
            params.add(new BasicNameValuePair("bairro", district));
            params.add(new BasicNameValuePair("cidade", city));
            params.add(new BasicNameValuePair("estado", state));
            // getting JSON Object
            // Note that create product url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_create_user,
                    "POST", params);


            // check log cat fro response
            Log.d("Create Response", json.toString());

            // check for success tag
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // successfully created product
                    Context context = getApplicationContext();
                    CharSequence text = "Conta criada com sucesso!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    // closing this screen
                    finish();
                } else {
                    // failed to create product
                    Context context = getApplicationContext();
                    CharSequence text = "Erro na criação da conta!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

    }
}
