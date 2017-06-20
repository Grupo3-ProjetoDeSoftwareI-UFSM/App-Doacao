package br.ufsm.projetosoftware.appdoacao;
import org.junit.Assert;
import org.junit.Test;

import br.ufsm.projetosoftware.appdoacao.utils.UserDataUtil;

import static org.junit.Assert.*;
/**
 * Created by Kipper on 13/06/2017.
 */

public class UserDataUtilTest {

    /*Teste Email se é válido*/
    @Test
    public void testeEMail() {

//diferencia letra maiscula de minuscula
            UserDataUtil testmail = new UserDataUtil();
            assertEquals("São Iguais,","michelep@inf.ufsm.br","MICHELEP@INF.UFSM.BR");
    }

    // Testa se a senha é maior que 4

    @Test
    public  void isPasswordValidTest1(){
        String password = "1234";

        UserDataUtil testSenha = new UserDataUtil();
        assertTrue("A senha é maior que 4",password.length() > 4);
    }

    @Test
    public  void isPasswordValidTest2(){
        UserDataUtil testSenha = new UserDataUtil();
        String password = "12wsuh";
        assertTrue("A senha é maior que 4",password.length() > 4);
        }

    //Test tamanho digito Cep
    @Test
    public void isCEPValidTest() {
        UserDataUtil Cep = new UserDataUtil();
        String cep = "9289189";
        assertTrue("O cep tem tamanho de 8 digitos",cep.length() == 8);
    }
    @Test
    public void isCEPValidTest1() {
        UserDataUtil Cep = new UserDataUtil();
        String cep = "92891896";
        assertTrue("O cep tem tamanho de 8 digitos",cep.length() == 8);
    }
}

