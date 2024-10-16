import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;

public class Homework3e {
    public static void main(String[] args) throws InterruptedException {
        /*
        Verify that locked out user cannot login with valid credentials
         */
        WebDriverManager.edgedriver().setup();
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();

        driver.navigate().to("https://www.saucedemo.com/v1/index.html");

        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        String loginUrl = driver.getCurrentUrl();
        String username = "locked_out_user";
        String password = "secret_sauce";

        usernameField.clear();
        usernameField.sendKeys(username);
        passwordField.clear();
        passwordField.sendKeys(password);
        loginButton.click();

        Assert.assertEquals(driver.getCurrentUrl(), loginUrl);

        WebElement error = driver.findElement(By.cssSelector("h3[data-test='error']"));
        String message = error.getText();
        Assert.assertEquals(message, "Epic sadface: Sorry, this user has been locked out.");

    }
}
