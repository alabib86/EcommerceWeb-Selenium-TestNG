package Luma.LumaWeb.Component;

import Luma.LumaWeb.Pages.LoginPage;
import Luma.LumaWeb.Pages.SignUpPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

public class AbstractComponent {
    WebDriver driver;
    @FindBy(linkText = "Create an Account")
    private WebElement createAccountPageBtn;
    @FindBy(linkText = "Sign In")
    private WebElement signInPageBtn;

    @FindBy(id = "ui-id-3")
    private WebElement whatsNewTab;
    @FindBy(id = "ui-id-4")
    private WebElement womenTab;
    @FindBy(id = "ui-id-5")
    private WebElement menTab;
    @FindBy(id = "ui-id-6")
    private WebElement gearTab;
    @FindBy(id = "ui-id-7")
    private WebElement trainingTab;
    @FindBy(id = "ui-id-8")
    private WebElement saleTab;
    @FindBy(id = "search")
    private WebElement searchBar;
    @FindBy(css = ".showcart")
    private WebElement cartIcon;
    @FindBy(css = ".panel.header button.action.switch")
    private WebElement accountArrowDropdown;
    @FindBy(partialLinkText = "Sign O")
    private WebElement signOutBtn;
    @FindBy(css = ".panel.header .logged-in")
    private WebElement greetingMsg;
    By greetingMsgBy = By.cssSelector(".panel.header .logged-in");
    public AbstractComponent(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public SignUpPage goToSignUpPage() {
        SignUpPage signUpPage = new SignUpPage(driver);
        createAccountPageBtn.click();
        return signUpPage;
    }

    public LoginPage goToLoginPage() {
        LoginPage loginPage = new LoginPage(driver);
        signInPageBtn.click();
        return loginPage;
    }
    public String getGreetingMsg()   {
       waitTextToBe(greetingMsgBy,"Welcome, ahmed labib!");
        return greetingMsg.getText();
    }
    public void signOut()  {
        waitTextToBe(greetingMsgBy,"Welcome, ahmed labib!");
        accountArrowDropdown.click();
        waitToAppearElement(signOutBtn);
        signOutBtn.click();
    }

    public void waitToAppearElement(WebElement webElement) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public void waitTextToBe(By locator,String text) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        webDriverWait.until(ExpectedConditions.textToBe(locator,text));
    }

    public void waitToEnableElement(WebElement webElement) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        webDriverWait.until(p -> webElement.isEnabled());
    }

    public int getRandomNumber() {
        Random random = new Random();
        return random.nextInt(1000);
    }


}
