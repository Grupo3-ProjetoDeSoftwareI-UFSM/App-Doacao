package br.ufsm.projetosoftware.appdoacao;

/**
 * Classe com métodos de verificação de dados do usuário.
 * Created by Felipe on 14/05/2017.
 */
public final class UserDataUtil {

    private UserDataUtil(){}

    /**
     * Verifica se email é válido.
     * @param email
     * @return Verdadeiro se email válido
     */
    public static boolean isEmailValid(String email) {
        return email.contains("@");
    }

    /**
     * Verifica se senha é valida.
     * @param password
     * @return Verdadeiro se senha válida
     */
    public static boolean isPasswordValid(String password){
        return password.length() > 4;
    }

    /**
     * Verifica se CEP é valido
     * @param cep
     * @return Verdadeiro se CEP for válido
     */
    public static boolean isCEPValid(String cep){
        return cep.length() == 8;
    }

    private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    /**
     * Calcula digito verificador do CPF ou CNPJ
     * @param str
     * @param peso
     * @return Digito verificador
     */
    private static int calcularDigito(String str, int[] peso) {
        int soma = 0;
        for (int indice=str.length()-1, digito; indice >= 0; indice-- ) {
            digito = Integer.parseInt(str.substring(indice,indice+1));
            soma += digito*peso[peso.length-str.length()+indice];
        }
        soma = 11 - soma % 11;
        return soma > 9 ? 0 : soma;
    }

    /**
     * Verifica se número de CPF é válido
     * @param cpf
     * @return Verdadeiro se CPF válido
     */
    public static boolean isCPFValid(String cpf) {
        if ((cpf==null) || (cpf.length()!=11)) return false;

        Integer digito1 = calcularDigito(cpf.substring(0,9), pesoCPF);
        Integer digito2 = calcularDigito(cpf.substring(0,9) + digito1, pesoCPF);
        return cpf.equals(cpf.substring(0,9) + digito1.toString() + digito2.toString());
    }

    /**
     * Verifica se número de CNPJ é válido
     * @param cnpj
     * @return Verdadeiro se CNPJ válido
     */
    public static boolean isCNPJValid(String cnpj) {
        if ((cnpj==null)||(cnpj.length()!=14)) return false;

        Integer digito1 = calcularDigito(cnpj.substring(0,12), pesoCNPJ);
        Integer digito2 = calcularDigito(cnpj.substring(0,12) + digito1, pesoCNPJ);
        return cnpj.equals(cnpj.substring(0,12) + digito1.toString() + digito2.toString());
    }
}
