import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.concurrent.TimeUnit;

public class PersonalAccountTests {
    private WebDriver driver;
    private LoginPage loginPage;

    @Before
    @Step("Open site and login")
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        System.setProperty("webdriver.chrome.driver", "C:/cygwin64/home/Basek/Diplom/Diplom3/drivers/chromedriver_win32/chromedriver.exe");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get("https://stellarburgers.nomoreparties.site/");

        HomePage homePage = new HomePage(driver);
        homePage.clickButtonPersonalAccount();

        loginPage = new LoginPage(driver);
        loginPage.login("aaa@ya.ru", "aaaaaa");

        homePage.clickButtonPersonalAccount();
    }
    @Test
    @DisplayName("Logout")
    public void logOutOfAccount() {
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
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