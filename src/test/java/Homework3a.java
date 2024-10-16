import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;


public class Homework3a {
    public static void main(String[] args) throws InterruptedException {
        /*
        Verify that user can login with valid credentials
         */

        WebDriverManager.edgedriver().setup();
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();

        driver.navigate().to("https://www.saucedemo.com/v1/index.html");

        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        String loginUrl = driver.getCurrentUrl();
        String username = "standard_user";
        String password = "secret_sauce";

        usernameField.clear();
        usernameField.sendKeys(username);
        passwordField.clear();
        passwordField.sendKeys(password);
        loginButton.click();

        Assert.assertNotEquals(driver.getCurrentUrl(), loginUrl);
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/v1/inventory.html");

        WebElement hamButton = driver.findElement(By.className("bm-burger-button"));
        hamButton.click();
        Thread.sleep(3000);
        WebElement logoutButton = driver.findElement(By.id("logout_sidebar_link"));
        Assert.assertTrue(logoutButton.isDisplayed());
    }
}
