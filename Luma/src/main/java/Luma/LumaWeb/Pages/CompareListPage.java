package Luma.LumaWeb.Pages;

import Luma.LumaWeb.Component.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Arrays;
import java.util.List;

public class CompareListPage extends AbstractComponent {
    WebDriver driver;

    public CompareListPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(css = ".cell.product.info")
    private List<WebElement> productList;


    public boolean checkExistingProduct(String[] names) {
        List<String> itemNames = Arrays.asList(names);
        boolean check = false;
        for (int x = 0; x < productList.size(); x++) {
            String productName = productList.get(x).findElement(By.cssSelector(".product-item-name")).getText();
            if (itemNames.contains(productName)) check = true; else check=false;
        }
        return check;
    }
}
