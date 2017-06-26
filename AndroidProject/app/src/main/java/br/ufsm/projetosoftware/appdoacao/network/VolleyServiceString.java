package br.ufsm.projetosoftware.appdoacao.network;

import android.content.Context;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Felipe on 04/06/2017.
 */

public class VolleyServiceString {
    IResultString mResultCallback = null;
    Context mContext;

    public VolleyServiceString(IResultString resultCallback, Context context){
        mResultCallback = resultCallback;
        mContext = context;
    }

    /**
     * Recebe um objeto com os dados da solicitação e envia para o servidor
     * @param requestType
     * @param url
     * @param sendObj
     */
    public void postDataVolley(final String requestType, String url, final String sendObj){
        try {
            RequestQueue queue = Volley.newRequestQueue(mContext);

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if(mResultCallback != null)
                        mResultCallback.notifySuccess(requestType,response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if(mResultCallback != null)
                        mResultCallback.notifyError(requestType,error);
                }
            }){
                @Override
                protected Map<String, String> getParams() {
                    Map<String,String> params;
                    params = new Gson().fromJson(sendObj, new TypeToken<HashMap<String, String>>(){}.getType());
                    return params;
                }
            };

            queue.add(stringRequest);

        }catch(Exception e){

        }
    }

    /**
     * Acessa a URL e busca valor
     * @param requestType
     * @param url
     */
    public void getDataVolley(final String requestType, String url){
        try {
            RequestQueue queue = Volley.newRequestQueue(mContext);

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if(mResultCallback != null)
                        mResultCallback.notifySuccess(requestType, response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if(mResultCallback != null)
                        mResultCallback.notifyError(requestType, error);
                }
            });

            queue.add(stringRequest);

        }catch(Exception e){

        }
    }
}
