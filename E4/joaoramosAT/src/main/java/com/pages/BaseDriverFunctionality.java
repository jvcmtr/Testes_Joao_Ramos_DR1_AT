package com.pages;

import java.io.File;
import java.io.IOException;
import java.time.Instant;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

abstract class BaseDriverFunctionality {
    protected WebDriver driver;

    public BaseDriverFunctionality() {
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
    }

    public BaseDriverFunctionality(WebDriver _driver){
        this.driver = _driver;
    }

    public void Init(String url) {
        driver.get(url);
    }

    public void Close() {
        driver.quit();
    }

    public void Screenshot(){
        if(! (driver instanceof  TakesScreenshot)){
            System.out.println("Driver does not support screenshot capture.");
            return;
        }
  
        TakesScreenshot screenshotDriver = (TakesScreenshot) driver;
        File screenshot = screenshotDriver.getScreenshotAs(OutputType.FILE);
        try {
            File destination = new File("./screenshots/" + Instant.now().toEpochMilli()  + ".png");
            FileUtils.copyFile(screenshot, destination);
            System.out.println("Screenshot saved at: " + destination.getAbsolutePath());
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
