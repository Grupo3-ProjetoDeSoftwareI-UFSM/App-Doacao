package br.ufsm.projetosoftware.appdoacao;
import org.junit.Assert;
import org.junit.Test;
import java.util.Queue;

import br.ufsm.projetosoftware.appdoacao.utils.UserDataUtil;

import static org.junit.Assert.*;

public class UserDataUtilTest {


    //Teste Email

    @Test
    public void testeEMail() {
        boolean actual = UserDataUtil.isEmailValid("michelep@inf.ufsm.br");
        Assert.assertEquals(true, actual);
    }

    // Testa senha
    @Test
    public void isPasswordValidTest1() {
        boolean actual = UserDataUtil.isPasswordValid("2017lá!");
        Assert.assertEquals(true, actual);
    }

    @Test
    public void isPasswordValidTest2() {
        boolean actual = UserDataUtil.isPasswordValid("1234");
        Assert.assertEquals(false, actual);
    }

    @Test
    public void isCEPValidTest1() {
        boolean actual = UserDataUtil.isCEPValid("970323120");
        Assert.assertEquals(false, actual);
    }

    @Test
    public void isCEPValidTest2() {
        boolean actual = UserDataUtil.isCEPValid("97032120");
        Assert.assertEquals(true, actual);
    }

    //Test Cpf
    @Test
    public void testIsCpfValido() {
        boolean actual = UserDataUtil.isCPFValid("00933070711");
        Assert.assertEquals(false, actual);
    }

    @Test
    public void testIsCpfValido_1() {
        boolean actual = UserDataUtil.isCPFValid("00933070711");
        Assert.assertEquals(false, actual);
    }

    @Test
    public void testIsCpfValido_2() {
        boolean actual = UserDataUtil.isCPFValid("57249755307");
        Assert.assertEquals(true, actual);
    }

    @Test
    public void testIsCpfValido_3() {
        boolean actual = UserDataUtil.isCPFValid("14909973232");
        Assert.assertEquals(false, actual);
    }

    //Teste CNPJ
    @Test
    public void testIsCNPJ() {
        boolean actual = UserDataUtil.isCNPJValid("21.155.071/0001-33");
        Assert.assertEquals(false, actual);
    }

}





