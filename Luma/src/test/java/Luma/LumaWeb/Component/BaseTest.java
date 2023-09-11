package Luma.LumaWeb.Component;

import Luma.LumaWeb.Pages.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {
    public WebDriver driver;
    public HomePage homePage;
    public void initializeDriver(){

        WebDriverManager.firefoxdriver().setup();
        driver=new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public HomePage lunchWebApp(){

        initializeDriver();
        homePage=new HomePage(driver);
        homePage.GoTo();

        return homePage;
    }

//    @AfterMethod
//    public void closeBrowser() {
//        driver.close();
//    }
}
