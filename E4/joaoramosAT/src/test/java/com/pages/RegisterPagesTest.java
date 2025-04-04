package com.pages;

import java.time.LocalDate;

import static org.junit.Assert.fail;
import org.junit.Test;

import com.models.Address;
import com.models.ECountries;
import com.models.EPronouns;
import com.models.User;

public class RegisterPagesTest {

    @Test
    public void CadastroLoginValido() { 
        User user = getValidUser();
        
        // cadastra o usuario
        SignUpPage signup = new LoginPage().goToSignUpPage(user);
        signup.loadUserData(user);
        signup.createUser();
        if(!signup.isLogedIn()){
            signup.Screenshot();
            signup.Close();
            fail("Cadastrar usuario");
            return;
        }
        signup.logout();
        signup.Close();

        // loga o usuario
        LoginPage login = new LoginPage();
        login.login(user);
        if(!login.isLogedIn()){
            login.Screenshot();
            login.Close();
            fail("Logar usuario cadastrado");
            return;
        }
        login.deleteAccount();
        login.Close();
        
    }

    @Test
    public void LoginUsuarioNaoCadastrado() { 
        User user = getValidUser();

        // tent loga o usuario
        LoginPage login = new LoginPage();
        login.login(user);

        if(!login.isLogedIn()){ // a condicional aqui deveria estar invertida 'login.isLogedIn()'. Mantenho deste jeito para gerar o Screenshot
            login.Screenshot();
            login.Close();
            fail("Login invalido");
            return;
        }

        login.Close();
    }

    private User getValidUser(){
        return new User(
            "Joao Cicero",
            "joao.ramos@al.infnet.edu.br",
            "12345678",
            LocalDate.of(2001, 01, 01),
            new Address(
                "Joao",
                "Ramos",
                "infnet",
                "R. São José, 90",
                null,
                ECountries.India, 
                "RJ", 
                "Rio de Janeiro",
                "20001",
                "99999999"
            ),
            EPronouns.Mr
        );
    }
}
