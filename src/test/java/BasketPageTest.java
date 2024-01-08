import LinksStore.pages.SearchedPage;
import LinksStore.pages.BasketPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.*;

public class BasketPageTest {

    private WebDriver driver;
    private SearchedPage searchedPage;
    private BasketPage basketPage;
    public String testURL = "https://www.links.hr/hr/search?q=laptop";

    @BeforeMethod
    @Parameters("browser")
    public void setupTest(String browser) {
        if(browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
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
        searchedPage = new SearchedPage ( driver );
        basketPage = new BasketPage ( driver );
    }

    @Test
    public void removeItemFromBasketSequenceTest(){
        searchedPage.clickAddToBasketButton ();
        searchedPage.goToBasket ();

        String currentUrl = driver.getCurrentUrl ();
        String expectedUrl = "https://www.links.hr/hr/cart";

        Assert.assertEquals ( currentUrl, expectedUrl, "Basket Button didn't forward to correct URL" );

        Assert.assertTrue ( basketPage.itemInBasket (), "Added item is not in basket" );

        basketPage.emptyBasket();

        Assert.assertTrue ( basketPage.basketEmpty (), "Basket is not empty after removing all items" );

    }

    @AfterMethod
    public void teardownTest(){

        driver.quit();
    }
}
