package com.gmail.bragar.olga.tests;

import com.gmail.bragar.olga.RunnerListener.SeleniumRunner;
import com.gmail.bragar.olga.pages.LoginPage;
import com.gmail.bragar.olga.pages.MailBoxPage;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SeleniumRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PostServiceTest {
    public static WebDriver driver;
    public static LoginPage loginPage;
    public static MailBoxPage mailBoxPage1;
    public static MailBoxPage mailBoxPage2;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/olgab/Driver/chromedriver.exe");
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        mailBoxPage1 = new MailBoxPage(driver);
        mailBoxPage2 = new MailBoxPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://mail.ukr.net/desktop/login");
    }

    @Test
    public void t001Login()throws Exception{
        System.out.println("Verification login page");
        assertEquals("Пошта @ ukr.net - українська електронна пошта • Створи емейл", driver.getTitle());
    }

    @Test
    public void t002MailBox() throws Exception{
        System.out.println("Verification mail box1 page");
        loginPage.logIn("TestingBox1","testingbox1pass" );
        String mailUser = mailBoxPage1.getUserMail();
        Assert.assertEquals("testingbox1@ukr.net",mailUser);
    }

    @Test
    public void t003SendLetter() throws Exception{
        System.out.println("Sending letter is testing");
        mailBoxPage1.clickWriteLetterButton();
        mailBoxPage1.inputAddress("TestingBox2@ukr.net");
        mailBoxPage1.inputSubject("Test letter");
        mailBoxPage1.findFrame();
        mailBoxPage1.inputLetterText("HelOOOOOOO worldDDDDDDDD");
        mailBoxPage1.closeFrame();
        mailBoxPage1.clickSendLetterButton();
        assertTrue(mailBoxPage1.isMessageSentLetterPresent());
        mailBoxPage1.logOut();
    }

    @Test
    public  void t004ReturnToLogin() throws Exception{
        System.out.println("Verification login page ");
        assertEquals("Почта @ ukr.net - украинская электронная почта • Создать емейл", driver.getTitle());
    }

    @Test
    public void t005MailBox2() throws Exception{
        System.out.println("Verification Mail box2 page");
        loginPage.logIn("TestingBox2", "testingbox2pass");
        String mailUser = mailBoxPage2.getUserMail();
        Assert.assertEquals("testingbox2@ukr.net",mailUser);
    }

    @Test
    public  void t006EqualsReceiveLetter() throws Exception{
        System.out.println("Receive letter is testing");
        mailBoxPage2.waitLetter("Ева Иванова");
    }

    @Test
    public void t007EqualsSubjectAndText() throws Exception{
        System.out.println("Checking subject and text letter");
        mailBoxPage2.clickNameSender();
        String subject = mailBoxPage2.getSubjectLetter();
        String content = mailBoxPage2.getContentLetter();
        Assert.assertEquals("Test letter",subject);
        Assert.assertEquals("HelOOOOOOO worldDDDDDDDD",content);
        mailBoxPage2.logOut();
    }
    @Test
    public  void t008ReturnToLogin() throws Exception{
        System.out.println("Verification login page ");
        assertEquals("Почта @ ukr.net - украинская электронная почта • Создать емейл", driver.getTitle());
    }


    @AfterClass
    public static void tearDown(){
        driver.close();
        driver.quit();
    }

}
