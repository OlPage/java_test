package com.gmail.bragar.olga.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public  WebDriver driver;

    @FindBy(id = "id-1")
    private WebElement loginField;

    @FindBy(id = "id-2")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    public void inputLogin(String login){
        loginField.sendKeys(login);
    }

    public  void inputPassword(String password){
        passwordField.sendKeys(password);
    }

    public  void clickLoginButton(){
        loginButton.click();
    }

    public void logIn(String login, String password){
        inputLogin(login);
        inputPassword(password);
        clickLoginButton();
    }
}
