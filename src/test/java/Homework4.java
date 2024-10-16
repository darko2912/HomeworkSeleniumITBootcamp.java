import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class Homework4 {
    public static void main(String[] args) {
        /*
        https://www.amazon.com/
Test adding a book to the cart and whether the book is deleted when you clear the cookies
         */

        WebDriverManager.edgedriver().setup();
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        driver.navigate().to("https://www.amazon.com/");

        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        WebElement searchButton = driver.findElement(By.id("nav-search-submit-text"));
        String book = "Selenium Framework Design in Data-Driven Testing: Build data-driven test frameworks using Selenium WebDriver, AppiumDriver, Java, and TestNG";
        searchBox.sendKeys(book);
        searchButton.click();

        List<WebElement> results = driver.findElements(By.cssSelector(".a-size-mini.a-spacing-none.a-color-base.s-line-clamp-2"));
        for (int i=0; i< results.size(); i++){
            if (results.get(i).getText().equals(book)){
                results.get(i).click();
            }
        }

        WebElement cartAmountBeforeAdding = driver.findElement(By.id("nav-cart-count"));
        Assert.assertEquals(cartAmountBeforeAdding.getText(), "0");

        WebElement cart = driver.findElement(By.id("nav-cart"));
        cart.click();

        WebElement emptyCart = driver.findElement(By.id("sc-empty-cart"));
        Assert.assertTrue(emptyCart.isDisplayed());

        driver.navigate().back();

        WebElement bookName = driver.findElement(By.id("productTitle"));
        String bookTitleToBeAdded = bookName.getText();

        WebElement addButton = driver.findElement(By.id("add-to-cart-button"));
        addButton.click();

        WebElement successfulMessage = driver.findElement(By.id("NATC_SMART_WAGON_CONF_MSG_SUCCESS"));
        Assert.assertTrue(successfulMessage.isDisplayed());
        String message = successfulMessage.getText();
        Assert.assertEquals(message, "Added to cart");

        WebElement cartAmountAfterAdding = driver.findElement(By.id("nav-cart-count-container"));
        String books = cartAmountAfterAdding.getText();
        Assert.assertEquals(books, "1");

        WebElement bookInCart = driver.findElement(By.className("a-truncate-cut"));
        String bookTitleInCart = bookInCart.getText();

        Assert.assertEquals(bookTitleToBeAdded, bookTitleInCart);

        boolean isPresent = false;
        try {
            isPresent = driver.findElement(By.cssSelector(".a-size-medium-plus.a-color-base.sw-atc-text.a-text-bold")).isDisplayed();
        } catch (Exception e){

        }

        driver.manage().deleteAllCookies();
        driver.navigate().refresh();

        WebElement cartAmountAfterRemoving = driver.findElement(By.id("nav-cart-count"));
        Assert.assertEquals(cartAmountAfterRemoving.getText(), "0");

        emptyCart = driver.findElement(By.id("sc-empty-cart"));
        Assert.assertTrue(emptyCart.isDisplayed());
        Assert.assertFalse(isPresent);
    }
}
