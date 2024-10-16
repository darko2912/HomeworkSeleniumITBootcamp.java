import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.ArrayList;

public class Homework2 {
    public static void main(String[] args) {
        /*
Open browser and 5 more tabs. On each tab open some URL wich you want. Close all tabs except where is open Google.
         */

        WebDriverManager.edgedriver().setup();
        WebDriver driver=new EdgeDriver();
        driver.manage().window().maximize();

        JavascriptExecutor js=(JavascriptExecutor) driver;
        for (int i=0; i<5; i++){
            js.executeScript("window.open()");
        }

        ArrayList<String> lista=new ArrayList<>(driver.getWindowHandles());
        driver.navigate().to("https://www.google.com");
        driver.switchTo().window(lista.get(1));
        driver.navigate().to("https://www.facebook.com");
        driver.switchTo().window(lista.get(2));
        driver.navigate().to("https://www.youtube.com");
        driver.switchTo().window(lista.get(3));
        driver.navigate().to("https://www.linkedin.com");
        driver.switchTo().window(lista.get(4));
        driver.navigate().to("https://www.joberty.com/sr/");
        driver.switchTo().window(lista.get(5));
        driver.navigate().to("https://stackoverflow.com/");

        for (int i=0; i<lista.size(); i++){
            driver.switchTo().window(lista.get(i));
            if (!driver.getCurrentUrl().equals("https://www.google.com/")){
                driver.close();
            }
        }

    }
}
