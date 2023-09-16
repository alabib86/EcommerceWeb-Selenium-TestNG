package Luma.LumaWeb.Component;

import Luma.LumaWeb.Pages.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import java.time.Duration;
import java.util.HashMap;

public class BaseTest {
    public WebDriver driver;
    public HomePage homePage;
    public void initializeDriver(){


        String browserName = System.getProperty("browser") != null ?
                System.getProperty("browser") :"chrome";

        if (browserName.contains("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            WebDriverManager.firefoxdriver().setup();

            if (browserName.contains("headless")) {
                options.addArguments("headless");
            }
            driver = new FirefoxDriver(options);

        } else if (browserName.contains("chrome")) {
            ChromeOptions options = new ChromeOptions();
            WebDriverManager.chromedriver().setup();
            if (browserName.contains("headless")) {
                options.addArguments("headless");
            }
            driver = new ChromeDriver(options);

        } else if (browserName.contains("edge")) {
            EdgeOptions options = new EdgeOptions();
            WebDriverManager.edgedriver().setup();
            if (browserName.contains("headless")) {
                options.addArguments("headless");
            }
            driver = new EdgeDriver(options);
        }
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

    @AfterMethod
    public void closeBrowser() {
        driver.close();
    }

    @DataProvider
    public Object[][] getSignUpData() {

        HashMap<String, String> map = new HashMap<>();
        map.put("firstName", "ahmed");
        map.put("lastName", "labib");
        map.put("email", "testerjob86@gmail.com");
        map.put("password", "Ahmed@123");
        map.put("confirmPass", "Ahmed@123");

        Object[][] objects = new Object[][]{{map},};


        return objects;
    }
}
