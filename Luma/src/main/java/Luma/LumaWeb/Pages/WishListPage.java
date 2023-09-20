package Luma.LumaWeb.Pages;

import Luma.LumaWeb.Component.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class WishListPage extends AbstractComponent {
    WebDriver driver;
    public WishListPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".page.messages")
    private WebElement pageMessage;

    @FindBy(css = ".wishlist .product-item")
    private List<WebElement> productsList;

    public String getPageMessage(){
        waitToAppearElement(pageMessage);
        return pageMessage.getText();
    }

    public void deleteItemFromWishList(String itemName){
        waitToAppearElements(productsList);
        for (WebElement webElement:productsList){
            if (webElement.findElement(By.cssSelector(".product-item-link")).getText().equals(itemName)){
                moveToElement(webElement);
                webElement.findElement(By.cssSelector(".btn-remove")).click();
            }
        }
    }
}

