package Luma.LumaWeb.Pages;

import Luma.LumaWeb.Component.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryProductsPage extends AbstractComponent {
    WebDriver driver;

    public CategoryProductsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".item.search")
    private WebElement searchResultMsg;
    @FindBy(css = ".base")
    private WebElement categoryNameTitle;
    @FindBy(css = ".item.product.product-item")
    private List<WebElement> productListResult;
    @FindBy(css = "#sorter")
    private WebElement sortByDropDown;
    @FindBy(css = ".action.sorter-action.sort-desc")
    private WebElement descBtn;
    @FindBy(css = ".action.sorter-action.sort-asc")
    private WebElement ascBtn;

    public String getSearchResultMsg() {
        waitToAppearElement(searchResultMsg);
        return searchResultMsg.getText();
    }
    public String getCategoryNameTitle() {
        waitToAppearElements(productListResult);
        return categoryNameTitle.getText();
    }

    public String selectSortBy(String value) {
        waitToAppearElement(sortByDropDown);
        Select dropDown = new Select(sortByDropDown);
        dropDown.selectByVisibleText(value);
        return dropDown.getFirstSelectedOption().getText();
    }

    public boolean orderProductPricesDes() {
        waitToAppearElements(productListResult);
        List pricesOriginal = productListResult.stream().map(p -> p.findElement(By.cssSelector("span.price")).
                getText()).collect(Collectors.toList());
        List pricesSorted = productListResult.stream().map(p -> p.findElement(By.cssSelector("span.price")).
                getText()).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        return pricesOriginal.equals(pricesSorted);
    }

    public boolean orderProductPricesAsc() {
        moveToElementAndClick(descBtn);
        waitToAppearElement(ascBtn);
        waitToAppearElements(productListResult);
        List pricesOriginal = productListResult.stream().map(p -> p.findElement(By.cssSelector("span.price")).
                getText()).collect(Collectors.toList());
        List pricesSorted = productListResult.stream().map(p -> p.findElement(By.cssSelector("span.price")).
                getText()).sorted().collect(Collectors.toList());
        return pricesOriginal.equals(pricesSorted);
    }
}
