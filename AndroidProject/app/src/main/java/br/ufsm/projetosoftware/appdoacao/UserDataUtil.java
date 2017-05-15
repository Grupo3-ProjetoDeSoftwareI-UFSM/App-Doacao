package br.ufsm.projetosoftware.appdoacao;

/**
 * Created by Felipe on 14/05/2017.
 */

public final class UserDataUtil {

    private UserDataUtil(){}

    public static boolean isEmailValid(String email) {
        return email.contains("@");
    }

    public static boolean isPasswordValid(String password){
        return password.length() > 4;
    }
}
