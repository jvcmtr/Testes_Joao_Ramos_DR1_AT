package com.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

abstract class BasePage extends BaseDriverFunctionality {
    
    private By navbar = By.className("navbar-nav");
    private By logoutButton = By.cssSelector("a[href='/logout']");
    private By deleteButton = By.cssSelector("a[href='/delete_account']");

    public BasePage(){
        super();
    }
    public BasePage(WebDriver driver){
        super(driver);
    }
    
    public boolean isLogedIn(){
        try {
            getNav(logoutButton);
            return true;
        } catch (Exception e) {
            return  false;
        }
    }

    public boolean logout(){
        if(!isLogedIn()) return false; 

        getNav(logoutButton).click();
        return true;
    }

    public boolean deleteAccount(){
        if(!isLogedIn()) return false; 

        getNav(deleteButton).click();
        return true;
    }

    private WebElement getNav(By comp){
        return driver.findElement(navbar).findElement(comp);
    }
}
