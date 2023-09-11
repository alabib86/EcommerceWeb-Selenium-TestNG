package Luma.LumaWeb.Pages;

import Luma.LumaWeb.Component.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage extends AbstractComponent {
    WebDriver driver;
    public AccountPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//div[contains(text(),'Thank you for registering with Main Website Store.')]")
    private WebElement alertConfirmation;

    public String getAlertConfirmationMsg(){
        return alertConfirmation.getText();
    }
}


