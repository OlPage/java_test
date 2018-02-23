package com.gmail.bragar.olga.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MailBoxPage {
    public MailBoxPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public WebDriver driver;

    @FindBy(className = "login-button__user")
    private WebElement userProfile;

    @FindBy(css = ".login-button__menu-icon")
    private WebElement userMenu;

    @FindBy(css = "#login__logout>b")
    private WebElement buttonLogout;

    @FindBy(css = "button.default.compose" )
    private WebElement buttonWriteLetter;

    @FindBy(name = "toInput")
    private  WebElement addressField;

    @FindBy(name = "subject")
    private WebElement subjectField;

    @FindBy(css = ".sendmsg__area iframe")
    private WebElement letterFrameWindow;

    @FindBy(css = ".mce-content-body")
    private WebElement letterField;

    @FindBy(css = "button.default.send")
    private WebElement buttonSendLetter;

    @FindBy(xpath = ".//*[@id='page']//table//tbody/tr[1]/td[3]/a")
    private  WebElement nameSender;

    @FindBy(css = ".screen__content h3")
    private  WebElement subject;

    @FindBy(css = ".xfmc1")
    private WebElement content;

    public  By locator = new By.ByCssSelector("div.sendmsg__ads-ready");

    public String getContentLetter(){
        return  content.getText();
    }

    public  String getSubjectLetter(){
        return subject.getText();
    }

    public void clickNameSender(){
        nameSender.click();
    }

    public void clickSendLetterButton(){
        buttonSendLetter.click();
    }

    public  void findFrame(){
        driver.switchTo().frame(letterFrameWindow);
    }

    public  void closeFrame(){
        driver.switchTo().defaultContent();
    }

    public void inputLetterText(String text){
        letterField.sendKeys(text);
    }

    public void inputSubject(String subject){
        subjectField.sendKeys(subject);
    }

    public void inputAddress(String address){
        addressField.sendKeys(address);
    }

    public void clickWriteLetterButton(){
        buttonWriteLetter.click();
    }

    public void waitLetter(String name ){
        int i=0;
        while (!(name).equals(nameSender.getText())){
            i++;
            if(i==4000)
                break;
        }
    }

    public String getUserMail(){
        String userMail = userProfile.getText();
        return userMail;
    }

    public boolean isMessageSentLetterPresent() {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public  void entryUserMenu(){
        userMenu.click();
    }

    public  void userLogout(){
        buttonLogout.click();
    }
    public void logOut(){
        entryUserMenu();
        userLogout();
    }
}
