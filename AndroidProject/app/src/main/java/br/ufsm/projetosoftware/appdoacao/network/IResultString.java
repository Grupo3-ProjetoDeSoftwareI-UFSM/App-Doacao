package br.ufsm.projetosoftware.appdoacao.network;

import com.android.volley.VolleyError;

/**
 * Created by Felipe on 04/06/2017.
 */

public interface IResultString {
    public void notifySuccess(String requestType,String response);
    public void notifyError(String requestType,VolleyError error);
}
