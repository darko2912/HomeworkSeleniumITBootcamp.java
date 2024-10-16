import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class Homework7 {
        /*
        https://imgflip.com/memegenerator
        Create a meme
         */
        WebDriver driver;
        WebDriverWait wait;

    @BeforeClass
            public void setUp(){
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    @BeforeMethod
            public void pageSetUp(){
        driver.navigate().to("https://imgflip.com/memegenerator");
    }

    @Test
    public void inputImage() {
        WebElement uploadImageButton = driver.findElement(By.id("mm-show-upload"));
        uploadImageButton.click();

        WebElement inputImage = driver.findElement(By.id("mm-upload-file"));
        String relativePath = "src\\test\\resources\\meme.jpg";
        File file = new File(relativePath);
        String absolutePath = file.getAbsolutePath();
        inputImage.sendKeys(absolutePath);

        WebElement uploadButton = driver.findElement(By.id("mm-upload-btn"));
        uploadButton.click();

        List<WebElement> text = driver.findElements(By.className("mm-text"));
        text.get(0).sendKeys("kad\n pokrenem\n test u intellij-ju");
        text.get(1).sendKeys("kad mi test\n pukne na prvoj\n liniji koda");

        List<WebElement> settings = driver.findElements(By.className("gear-icon"));
        List<WebElement> align = driver.findElements(By.className("mm-text-align"));
        List<WebElement> rightAlign = driver.findElements(By.cssSelector("option[value='right']"));

        settings.get(0).click();
        align.get(0).click();
        rightAlign.get(0).click();
        settings.get(0).click();

        settings.get(1).click();
        align.get(1).click();
        rightAlign.get(1).click();
        settings.get(1).click();

    }

    @AfterMethod
    public void method(){

    }

    @AfterClass
    public void tearDown(){
        //driver.quit();
    }





}
