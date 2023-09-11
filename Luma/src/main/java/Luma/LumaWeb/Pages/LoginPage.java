package Luma.LumaWeb.Pages;

import Luma.LumaWeb.Component.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage extends AbstractComponent {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "email")
    private WebElement emailPlaceholder;
    @FindBy(id = "email-error")
    private WebElement emailErrorMsg;

    @FindBy(css = "input[name='login[password]']")
    private WebElement passPlaceholder;
    @FindBy(id = "pass-error")
    private WebElement passErrorMsg;
    @FindBy(css = ".action.login.primary")
    private WebElement signInBtn;

    @FindBy(css = ".action.remind")
    private WebElement forgotPassLink;

    @FindBy(css = ".action.create.primary")
    private WebElement createAccountBtn;

    @FindBy(xpath = "//div[contains(text(),'The account sign-in was incorrect or your account ')]")
    private WebElement errorLoginMsg;

    @FindBy(css = ".action.action-login.secondary")
    private WebElement signInBtnSecondary;

    public void login(String email,String password){
        waitToAppearElement(emailPlaceholder);
        emailPlaceholder.sendKeys(email);
        passPlaceholder.sendKeys(password);
        waitToEnableElement(signInBtnSecondary);
        signInBtn.click();
    }
    public SignUpPage goToCreateAccountPage(){
        SignUpPage signUpPage=new SignUpPage(driver);
        waitToAppearElement(createAccountBtn);
        createAccountBtn.click();
        return signUpPage;
    }

    public String getErrorEmailMsgLogin(){return emailErrorMsg.getText();}
    public String getErrorPasswordMsgLogin(){return passErrorMsg.getText();}
    public String getErrorLoginMsg(){return errorLoginMsg.getText();}


}
