package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.config.Routes;
import com.models.User;


public class LoginPage extends BasePage {
    
    public static final String URL = Routes.LOGIN_URL;

    private WebElement LoginForm;
    private By Email = By.name("email") ;
    private By Password = By.name("password");
    private By LoginButton = By.tagName("button");
    private By ErrorMesage = By.cssSelector("[style='color: red']");
    
    private WebElement SignUpForm;
    private By NameSignup = By.name("name") ;
    private By EmailSignup = By.name("email");
    private By SignUpButton = By.tagName("button");

    public LoginPage(){
        Init( URL );
        initElements();
    }


    public String login(User user) {
        WebElement email = LoginForm.findElement(Email);
        WebElement password = LoginForm.findElement(Password);
        WebElement sumbit = LoginForm.findElement(LoginButton);

        email.sendKeys(user.getEmail());
        password.sendKeys(user.getPassword());
        sumbit.click();

        try {
            WebElement error = LoginForm.findElement(ErrorMesage);
            return error.getText();
        } catch (Exception e) {
            return "";
        }
    };

    public SignUpPage goToSignUpPage(User user){
        WebElement name = SignUpForm.findElement(NameSignup);
        WebElement email = SignUpForm.findElement(EmailSignup);
        WebElement sumbit = SignUpForm.findElement(SignUpButton);

        email.sendKeys(user.getEmail());
        name.sendKeys(user.getName());
        sumbit.click();

        return new SignUpPage(driver);
        // try {
        //     WebElement signupHeader = driver.findElement(By.id("id_gender1"));
        // } catch (Exception e) {
        //     return null;
        // }
    }

    private void initElements(){
        LoginForm = driver.findElement( By.className("login-form"));
        SignUpForm = driver.findElement( By.className("signup-form"));
    }
}
    