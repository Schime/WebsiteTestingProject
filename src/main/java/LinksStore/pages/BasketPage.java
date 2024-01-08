package LinksStore.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BasketPage {

    private final WebDriver driver;
    private final By emptyCartTextLocator = By.xpath("//div[@class='empty-cart']");

    private final By itemImageLocator = By.xpath("//td[@class='product-picture']//img");

    private final By removeAllButtonLocator = By.xpath("//span[@class='button btn-icon btn-text btn-delete' and contains(@onclick, 'shoppingcart_removeAllFromCart')]");

    public BasketPage(WebDriver driver){
        this.driver = driver;
    }

    public Boolean basketEmpty(){
        WebDriverWait wait = new WebDriverWait ( driver, 5 );
        try{
            wait.until(ExpectedConditions.presenceOfElementLocated ( emptyCartTextLocator ));
            return true;
        }catch ( TimeoutException e ){
            e.printStackTrace ();
            return false;
        }
    }

    public Boolean itemInBasket(){
        WebDriverWait wait = new WebDriverWait ( driver, 5 );
        try{
            wait.until ( ExpectedConditions.presenceOfElementLocated ( itemImageLocator ) );
            return true;
        }catch ( TimeoutException e ){
            e.printStackTrace ();
            return false;
        }
    }

    public void emptyBasket(){
        WebDriverWait wait = new WebDriverWait ( driver, 5 );
        try{
            WebElement removeAllButton = wait.until ( ExpectedConditions.presenceOfElementLocated ( removeAllButtonLocator ) );
            removeAllButton.click ();
        }catch ( TimeoutException e ){
            e.printStackTrace ();
        }
    }
}

