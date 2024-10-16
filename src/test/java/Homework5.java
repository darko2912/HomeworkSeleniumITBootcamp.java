import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class Homework5 {
    /*
    Using annotations log in on demoqa (https://demoqa.com/ -> Book Store Application) with cookies-a
     */

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    @BeforeClass
    public void setUp() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        js = (JavascriptExecutor) driver;
    }

    @BeforeMethod
    public void setUpPage(){
        driver.navigate().to("https://demoqa.com/");
    }

    @Test (priority = 10)
    public void userCanLoginWithValidCredentials(){
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
        List<WebElement> bookStoreApplication = driver.findElements(By.className("card-body"));
        bookStoreApplication.get(5).click();

        js.executeScript("window.scrollBy(0,250)");
        List<WebElement> loginLink = driver.findElements(By.id("item-0"));
        loginLink.get(5).click();

        String loginPage = driver.getCurrentUrl();

        js.executeScript("window.scrollBy(0,250)");

        WebElement usernameField = driver.findElement(By.id("userName"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login"));
        String validUsername = "Darko.777";
        String validPassword = "Man#777.";
        usernameField.clear();
        usernameField.sendKeys(validUsername);
        passwordField.clear();
        passwordField.sendKeys(validPassword);
        loginButton.click();


        wait.until(ExpectedConditions.urlToBe("https://demoqa.com/profile"));
        Assert.assertNotEquals(driver.getCurrentUrl(), loginPage);

        List<WebElement> listSubmit = driver.findElements(By.id("submit"));
        Assert.assertEquals(listSubmit.get(0).getText(), "Log out");
        Assert.assertTrue(listSubmit.get(0).isDisplayed());

        WebElement usernameValue = driver.findElement(By.id("userName-value"));
        Assert.assertEquals(usernameValue.getText(), validUsername);
    }

    @Test (priority = 20)
    public void loginWithCookeis(){
        Cookie cookie1 = new Cookie("userName","Darko.777");
        driver.manage().addCookie(cookie1);

        Cookie cookie2 = new Cookie("userID","9e6ea059-aa9c-434b-9c06-f9f4d2d7fe14");
        driver.manage().addCookie(cookie2);

        Cookie cookie3 = new Cookie("token","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6IkRhcmtvLjc3NyIsInBhc3N3b3JkIjoiTWFuIzc3Ny4iLCJpYXQiOjE3MjkwOTY0NjB9.D-hW94sAnYrg84XQaH38JOvM5rb6ADARKSCzDwtVMj4");
        driver.manage().addCookie(cookie3);

        Cookie cookie4 = new Cookie("expires","2024-10-23T16%3A37%3A09.764Z");
        driver.manage().addCookie(cookie4);

        driver.navigate().refresh();

        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
        List<WebElement> bookStoreApplication = driver.findElements(By.className("card-body"));
        bookStoreApplication.get(5).click();

        List<WebElement> listSubmit = driver.findElements(By.id("submit"));
        Assert.assertEquals(listSubmit.get(0).getText(), "Log out");
        Assert.assertTrue(listSubmit.get(0).isDisplayed());

        WebElement usernameValue = driver.findElement(By.id("userName-value"));
        Assert.assertEquals(usernameValue.getText(), "Darko.777");

    }

    @AfterClass
    public void tearDown(){
//        driver.quit();
    }
}
