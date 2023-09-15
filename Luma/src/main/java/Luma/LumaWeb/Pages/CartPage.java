package Luma.LumaWeb.Pages;

import Luma.LumaWeb.Component.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends AbstractComponent {
    WebDriver driver;
    public CartPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }


}
