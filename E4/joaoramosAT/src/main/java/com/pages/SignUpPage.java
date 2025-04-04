package com.pages;


import java.time.format.TextStyle;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.config.Routes;
import com.models.Address;
import com.models.EPronouns;
import com.models.User;

public class SignUpPage extends BasePage {

    public static final String URL = Routes.SIGNUP_URL;

    private WebElement titleMr;
    private WebElement titleMrs;
    private WebElement Name;
    private WebElement Email;
    private WebElement Password;
    private Select DayOfBirth;
    private Select MonthOfBirth;
    private Select YearOfBirth;
    
    private WebElement AddressFirstName;
    private WebElement AddressLastName;
    private WebElement Company;
    private WebElement Address;
    private WebElement Address2;
    private Select Country;
    private WebElement State;
    private WebElement City;
    private WebElement Zipcode;
    private WebElement Mobile;
    private WebElement CreateAcountButton;


    // A pagina não pode ser acessada diretamente
    // public SignUpPage(){  
    //     super();
    //     Init( URL );
    //     initElements();
    // }

    public SignUpPage(WebDriver driver){
        super(driver);
        initElements();
    }


    public void loadUserData(User user) {
        if( user.getPronoun() == EPronouns.Mr) titleMr.click();
        else titleMrs.click();
        
        Name.clear();
        Name.sendKeys(user.getName());

        // Email -> Not interactable in this page through this page

        Password.sendKeys(user.getPassword());

        DayOfBirth.selectByVisibleText( user.getBirth().getDayOfMonth() + "");
        MonthOfBirth.selectByVisibleText( user.getBirth().getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + "");
        YearOfBirth.selectByVisibleText( user.getBirth().getYear() + "");
        
        Address add = user.getAddress();

        AddressFirstName.sendKeys(add.getFirstName());
        AddressLastName.sendKeys(add.getLastName());
        Company.sendKeys(add.getCompany());
        Address.sendKeys(add.getAddress());
        if(add.getAddress2() != null) {Address2.sendKeys(add.getAddress2()); }
        Country.selectByVisibleText( add.getCountry() + "");
        State.sendKeys(add.getState());
        City.sendKeys(add.getCity());
        Zipcode.sendKeys(add.getZipcode());
        Mobile.sendKeys(add.getMobile());
    }

    public boolean createUser(){
        CreateAcountButton.click();

        try {
            WebElement continueButton = driver.findElement(By.className("btn-primary"));
            continueButton.click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void initElements(){
        titleMr = driver.findElement(By.id("id_gender1"));
        titleMrs = driver.findElement(By.id("id_gender2"));
        Name = driver.findElement(By.id("name"));
        Email = driver.findElement(By.id("email"));
        Password = driver.findElement(By.id("password"));
        DayOfBirth = new Select( driver.findElement(By.id("days")) );
        MonthOfBirth = new Select( driver.findElement(By.id("months")) );
        YearOfBirth = new Select( driver.findElement(By.id("years")) );
        
        AddressFirstName = driver.findElement(By.id("first_name"));
        AddressLastName = driver.findElement(By.id("last_name"));
        Company = driver.findElement(By.id("company"));
        Address = driver.findElement(By.id("address1"));
        Address2 = driver.findElement(By.id("address2"));
        Country = new Select( driver.findElement(By.id("country")) );
        State = driver.findElement(By.id("state"));
        City = driver.findElement(By.id("city"));
        Zipcode = driver.findElement(By.id("zipcode"));
        Mobile = driver.findElement(By.id("mobile_number"));
        CreateAcountButton = driver.findElement(By.className("btn-default"));
    }
}
