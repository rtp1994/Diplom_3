import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class HomePageTests{
    private WebDriver driver;
    private HomePage homePage;

    @Before
    @Step("Open")
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://stellarburgers.nomoreparties.site/");
    }
    @Test
    @Step("Click buns")
    @DisplayName("Testing constructor of buns")
    public void clickBunSection() {
        homePage = new HomePage(driver);
        homePage.clickSaucesSection();
        homePage.clickBunSauces();
        homePage.activityCheckCurrentBunSection();
    }
    @Test
    @Step("Click sauces")
    @DisplayName("Testing constructor of sauces")
    public void clickSaucesSection() {
        homePage = new HomePage(driver);
        homePage.clickSaucesSection();
        homePage.activityCheckCurrentSauceSection();
    }
    @Test
    @Step("Click stuffing")
    @DisplayName("Testing constructor of stuffing")
    public void clickStuffingSection() {
        homePage = new HomePage(driver);
        homePage.clickStuffingSection();
        homePage.activityCheckStuffingBunSection();
    }
    @After
    public void tearDown() {
        driver.quit();
    }
}