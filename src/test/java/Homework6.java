import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class Homework6 {
    /*
https://practicetestautomation.com/
Test log in functionality using annotations and different test cases in same class
     */

    WebDriver driver;
    WebDriverWait wait;
    @BeforeClass
    public void setUp(){
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @BeforeMethod
    public void pageSetUp(){
        driver.navigate().to("https://practicetestautomation.com/");
    }

    @Test (priority = 10)
    public void userCanLoginWithValidCredentials(){
        WebElement practiceButton = driver.findElement(By.id("menu-item-20"));
        practiceButton.click();
        WebElement loginPageLink = driver.findElement(By.linkText("Test Login Page"));
        loginPageLink.click();
        String loginPage = driver.getCurrentUrl();
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("submit"));
        String validUsername = "student";
        String validPassword = "Password123";
        usernameField.clear();
        usernameField.sendKeys(validUsername);
        passwordField.clear();
        passwordField.sendKeys(validPassword);
        submitButton.click();
        Assert.assertNotEquals(driver.getCurrentUrl(), loginPage);
        WebElement logOutButton = driver.findElement(By.linkText("Log out"));
        Assert.assertTrue(logOutButton.isDisplayed());
    }

    @Test(priority = 20)
    public void userCannotLoginWithInvalidUsername(){
        WebElement practiceButton = driver.findElement(By.id("menu-item-20"));
        practiceButton.click();
        WebElement loginPageLink = driver.findElement(By.linkText("Test Login Page"));
        loginPageLink.click();
        String loginPage = driver.getCurrentUrl();
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("submit"));
        String invalidUsername = "student123";
        String validPassword = "Password123";
        usernameField.clear();
        usernameField.sendKeys(invalidUsername);
        passwordField.clear();
        passwordField.sendKeys(validPassword);
        submitButton.click();
        Assert.assertEquals(driver.getCurrentUrl(), loginPage);
        WebElement error = driver.findElement(By.id("error"));
        Assert.assertTrue(error.isDisplayed());
        Assert.assertEquals(error.getText(), "Your username is invalid!");
    }

    @Test(priority = 30)
    public void userCannotLoginWithInvalidPassword(){
        WebElement practiceButton = driver.findElement(By.id("menu-item-20"));
        practiceButton.click();
        WebElement loginPageLink = driver.findElement(By.linkText("Test Login Page"));
        loginPageLink.click();
        String loginPage = driver.getCurrentUrl();
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("submit"));
        String validUsername = "student";
        String invalidPasword = "123456";
        usernameField.clear();
        usernameField.sendKeys(validUsername);
        passwordField.clear();
        passwordField.sendKeys(invalidPasword);
        submitButton.click();
        Assert.assertEquals(driver.getCurrentUrl(), loginPage);
        WebElement error = driver.findElement(By.id("error"));
        Assert.assertTrue(error.isDisplayed());
        Assert.assertEquals(error.getText(), "Your password is invalid!");
    }

    @AfterMethod
    public void method(){

    }

    @AfterClass
    public void teatDown(){
        driver.quit();
    }
}
