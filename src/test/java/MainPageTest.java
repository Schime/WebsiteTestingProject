import LinksStore.pages.MainPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.*;

public class MainPageTest {
    private WebDriver driver;
    private MainPage mainPage;
    public String testURL = "https://www.links.hr/hr/";

    @BeforeMethod
    @Parameters("browser")
    public void setupTest(String browser) {
        if(browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless");
            driver = new FirefoxDriver(options);
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        driver.navigate().to(testURL);
        mainPage = new MainPage(driver);
    }

    @Test
    public void logoPresentTest(){
        Assert.assertTrue ( mainPage.isLogoPresent (), "Links logo is not present!" );
    }
    @Test
    public void validSearchInputTest(){
        String searchInput = "laptop";

        mainPage.performSearch ( searchInput );

        String searchOutput = mainPage.searchResult ().toLowerCase ();

        Assert.assertTrue ( searchOutput.contains ( searchInput ), "Search is not displaying correct items" );
    }

    @Test
    public void invalidSearchInputTest(){
        String searchInput = "1234567789";

        mainPage.performSearch ( searchInput );

        String searchOutput = mainPage.searchResult ();

        Assert.assertNull ( searchOutput, "Search gave a result for invalid input" );
    }
    @Test
    public void logInButtonTest(){
        mainPage.clickLogInButton ();

        String expectedLink = "https://www.links.hr/hr/login";

        String currentUrl = driver.getCurrentUrl();

        Assert.assertEquals ( expectedLink,currentUrl, "Button didn't forward user to correct URL" );
    }

    @Test
    public void registerButtonTest(){
        mainPage.clickRegisterButton ();

        String expectedLink = "https://www.links.hr/hr/register";

        String currentUrl = driver.getCurrentUrl ();

        Assert.assertEquals ( expectedLink,currentUrl, "Button didn't forward user to correct URL");
    }
    @AfterMethod
    public void teardownTest(){

        driver.quit();
    }
}
