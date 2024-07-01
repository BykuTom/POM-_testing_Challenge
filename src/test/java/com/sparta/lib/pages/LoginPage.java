package com.sparta.lib.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends WebPage{

    private final By signInButton = new By.ById("login-button");
    private final By usernameInput = new By.ByName("user-name");
    private final By passwordInput = new By.ByName("password");



    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void enterUsername(String username){
        WebElement userInput = webDriver.findElement(usernameInput);
        userInput.sendKeys(username);
    }

    public void enterPassword(String password){
        WebElement passInput = webDriver.findElement(passwordInput);
        passInput.sendKeys(password);
    }

    public void clickSignIn(){
        WebElement signIn = webDriver.findElement(signInButton);
        signIn.click();
    }
}
