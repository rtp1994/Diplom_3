import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class LoginTests {
    private WebDriver driver;
    private LoginPage loginPage;
    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:/cygwin64/home/Basek/Diplom/Diplom3/drivers/chromedriver_win32/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
    @Test
    @Step("Open site and login")
    @DisplayName("Login HomePage")
    public void loginAcrossHomePage() {
        driver.get("https://stellarburgers.nomoreparties.site");
        HomePage homePage = new HomePage(driver);
        homePage.clickButtonPersonalAccount();

        loginPage = new LoginPage(driver);
        loginPage.login("aaa@ya.ru", "aaaaaa");
    }
    @Test
    @Step("Open site and login")
    @DisplayName("Login PersonalAccountPage")
    public void loginAcrossPersonalAccountPage() {
        driver.get("https://stellarburgers.nomoreparties.site");
        HomePage homePage = new HomePage(driver);
        homePage.clickButtonPersonalAccount();
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);

        loginPage = new LoginPage(driver);
        loginPage.login("aaa@ya.ru", "aaaaaa");
    }
    @Test
    @Step("Open site and login")
    @DisplayName("Login across RegistrationPage")
    public void loginAcrossRegistrationPage(){
        driver.get("https://stellarburgers.nomoreparties.site/register");
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.clickButtonEnter();

        loginPage = new LoginPage(driver);
        loginPage.login("aaa@ya.ru", "aaaaaa");
    }
    @Test
    @Step("Open site and login")
    @DisplayName("Login RecoveryPassword")
    public void loginAcrossRecoveryPassword(){
        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");
        PasswordRecoveryPage passwordRecoveryPage = new PasswordRecoveryPage(driver);
        passwordRecoveryPage.clickEnterPasswordRecoveryPage();

        loginPage = new LoginPage(driver);
        loginPage.login("aaa@ya.ru", "aaaaaa");
    }
    @After
    public void tearDown() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isDisplayedCheckoutButton());
        driver.quit();
    }
}