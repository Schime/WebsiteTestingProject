package LinksStore.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class MainPage {

    private final WebDriver driver;
    private final By logoLocator = By.xpath("//img[@class='pnglogo' and @title='Links']");
    private final By registerLinkLocator = By.xpath("//div[@class='selector user-registration']//a[@class='header-small-menu user-register' and @title='Registrirajte se']");
    private final By loginLinkLocator = By.xpath("//div[@class='selector user-auth']//a[@class='header-small-menu user-auth user-login' and @title='Prijava']");
    private final By searchBoxLocator = By.cssSelector("span.search-box-text-holder input.search-box-text");
    private final By searchButtonLocator = By.cssSelector("input[type='submit'].button-1.search-box-button");
    private final By productTitleLocator = By.cssSelector("div.search-results div.product-grid h2.product-title a");
    public MainPage(WebDriver driver){
        this.driver = driver;
    }


    public Boolean isLogoPresent(){
        WebDriverWait wait = new WebDriverWait ( driver, 5 );
        try{
            wait.until ( ExpectedConditions.presenceOfElementLocated ( logoLocator ) );
            return true;
        }catch ( TimeoutException e ){
            return false;
        }
    }
    public void performSearch(String keyword){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        try{
            wait.until(ExpectedConditions.presenceOfElementLocated(searchBoxLocator));
            WebElement searchInput = driver.findElement(searchBoxLocator);
            searchInput.clear();
            searchInput.sendKeys(keyword);
            driver.findElement(searchButtonLocator).click();
        }catch ( TimeoutException e ){
            e.printStackTrace ();
        }
    }

    public String searchResult() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        try {
            WebElement item = wait.until(ExpectedConditions.presenceOfElementLocated(productTitleLocator));
            return item.getText();
        } catch (TimeoutException e) {
            e.printStackTrace ();
            return null;
        }
    }

    public void clickRegisterButton(){
        WebDriverWait wait = new WebDriverWait ( driver, 5 );
        try{
            WebElement registerButton = wait.until(ExpectedConditions.elementToBeClickable ( registerLinkLocator ));
            registerButton.click ();
        }catch ( TimeoutException e ){
            e.printStackTrace ();
        }
    }

    public void clickLogInButton(){
        WebDriverWait wait = new WebDriverWait ( driver, 5 );
        try{
            WebElement registerButton = wait.until(ExpectedConditions.elementToBeClickable ( loginLinkLocator ));
            registerButton.click ();
        }catch ( TimeoutException e ){
            e.printStackTrace ();
        }
    }
}
