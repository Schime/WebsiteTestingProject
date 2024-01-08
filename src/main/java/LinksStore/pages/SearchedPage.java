package LinksStore.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchedPage {
    private final WebDriver driver;
    private final By addToBasketButtonLocator = By.cssSelector("button.button-2.product-box-add-to-cart-button[title='Dodaj']");
    private final By basketButtonLocator = By.cssSelector("div.articleNum span.cart-qty");

    public SearchedPage(WebDriver driver){
        this.driver = driver;
    }
    public void clickAddToBasketButton(){
        WebDriverWait wait = new WebDriverWait ( driver, 5 );
        try{
            Thread.sleep ( 2000 );
            WebElement addToBasketButton = wait.until(ExpectedConditions.elementToBeClickable ( addToBasketButtonLocator ));
            WebElement acceptCookiesButton = driver.findElement(By.id("onetrust-accept-btn-handler"));
            if (acceptCookiesButton.isDisplayed() && acceptCookiesButton.isEnabled()) {
                acceptCookiesButton.click();
            }

            scrollToElement(driver,basketButtonLocator);
            addToBasketButton.click ();
        }catch ( TimeoutException e ){
            e.printStackTrace ();
        } catch ( InterruptedException e ) {
            throw new RuntimeException ( e );
        }
    }
    public void goToBasket(){
        WebDriverWait wait = new WebDriverWait ( driver, 5 );
        try{
            WebElement basketButton = wait.until ( ExpectedConditions.presenceOfElementLocated ( basketButtonLocator ));
            basketButton.click ();
        }catch ( TimeoutException e ){
            e.printStackTrace ();
        }
    }

    private static void scrollToElement(WebDriver driver, By locator) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

}
