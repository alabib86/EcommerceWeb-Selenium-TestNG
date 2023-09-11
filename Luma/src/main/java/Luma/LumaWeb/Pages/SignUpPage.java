package Luma.LumaWeb.Pages;

import Luma.LumaWeb.Component.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage extends AbstractComponent {
    WebDriver driver;

    public SignUpPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "firstname")
    private WebElement firstNamePlaceHolder;
    @FindBy(id = "lastname")
    private WebElement lastNamePlaceHolder;
    @FindBy(id = "email_address")
    private WebElement emailPlaceHolder;
    @FindBy(id = "password")
    private WebElement passwordPlaceHolder;
    @FindBy(id = "password-confirmation")
    private WebElement confirmPasswordPlaceHolder;
    @FindBy(css = ".action.submit.primary")
    private WebElement createAccountBtn;
    @FindBy(id = "firstname-error")
    private WebElement errorFirstNameMsg;
    @FindBy(id = "lastname-error")
    private WebElement errorLastNameMsg;
    @FindBy(id = "email_address-error")
    private WebElement errorEmailMsg;
    @FindBy(id = "password-error")
    private WebElement errorPassMsg;
    @FindBy(id = "password-confirmation-error")
    private WebElement errorConfirmPassMsg;
    @FindBy(css = ".base")
    private WebElement pageTitleLbl;
@FindBy(css = ".action.action-register.primary")
    private WebElement submitFinal;

    public AccountPage createNewAccount(String firstName, String lastName, String email, String password, String confirmPass) {
        AccountPage accountPage = new AccountPage(driver);

        waitToAppearElement(firstNamePlaceHolder);
        firstNamePlaceHolder.sendKeys(firstName);
        lastNamePlaceHolder.sendKeys(lastName);
        emailPlaceHolder.sendKeys(email);
        passwordPlaceHolder.sendKeys(password);
        confirmPasswordPlaceHolder.sendKeys(confirmPass);
        waitToEnableElement(submitFinal);
        createAccountBtn.click();

        return accountPage;
    }

    public String getEmailErrorMsgSignUp() {
        return errorEmailMsg.getText();
    }

    public String getFirstNameErrorMsg() {
        return errorFirstNameMsg.getText();
    }

    public String getLastNameErrorMsg() {
        return errorLastNameMsg.getText();
    }

    public String getPasswordErrorMsgSignUp() {
        return errorPassMsg.getText();
    }

    public String getConfirmPassErrorMsg() {
        return errorConfirmPassMsg.getText();
    }

    public String getPageTitleLbl() {
        return pageTitleLbl.getText();
    }
}
