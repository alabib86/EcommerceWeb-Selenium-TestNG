package Luma.LumaWeb.Pages;

import Luma.LumaWeb.Component.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage extends AbstractComponent {
    WebDriver driver;
    public CheckOutPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

}
