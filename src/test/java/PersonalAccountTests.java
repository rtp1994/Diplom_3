import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class PersonalAccountTests {
    private WebDriver driver;
    private LoginPage loginPage;
    private PersonalAccountPage personalAccountPage;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get("https://stellarburgers.nomoreparties.site/");

        HomePage homePage = new HomePage(driver);
        homePage.clickButtonPersonalAccount();

        loginPage = new LoginPage(driver);
        loginPage.login("tasya.morozova@gmail.com", "123456");

        homePage.clickButtonPersonalAccount();
    }

    @Test
    @DisplayName("Logout")
    public void logOutOfAccount() {
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        personalAccountPage.isDisplayedTextInfo();
        personalAccountPage.clickButtonGetOutOfAccount();
        loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isDisplayedTextEnter());
    }

    @Test
    @DisplayName("From Personal account to Constructor by click to \"Constructor\"")
    public void transitionAcrossConstructor(){
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        personalAccountPage.clickHeaderConstructor();
        Assert.assertTrue(personalAccountPage.isDisplayedTextAssembleBurger());
    }
    @Test
    @DisplayName("From Personal account to Constructor by click to logo")
    public void transitionAcrossLogo(){
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        personalAccountPage.clickLogo();
        Assert.assertTrue(personalAccountPage.isDisplayedTextAssembleBurger());
    }
    @After
    public void tearDown() {
        driver.quit();
    }
}