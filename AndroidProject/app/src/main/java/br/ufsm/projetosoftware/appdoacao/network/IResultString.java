package br.ufsm.projetosoftware.appdoacao.network;

import com.android.volley.VolleyError;

/**
 * Interface de envio dos dados ao servidor
 * Created on 24/06/2017.
 */

public interface IResultString {
    public void notifySuccess(String requestType,String response);
    public void notifyError(String requestType,VolleyError error);
}
