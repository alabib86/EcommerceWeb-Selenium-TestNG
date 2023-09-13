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
    @FindBy(css = ".filter-options-item")
    private List<WebElement> optionsList;
    @FindBy(css = ".filter-options-title")
    private WebElement firstOption;
    @FindBy(css = "#sorter")
    private WebElement sortByDropDown;
    @FindBy(css = ".action.sorter-action.sort-desc")
    private WebElement descBtn;
    @FindBy(css = ".action.sorter-action.sort-asc")
    private WebElement ascBtn;
    private By productPriceBy = By.cssSelector("span.price");
    private By productNameLinkBy = By.cssSelector("product-item-link");

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
        List pricesOriginal = productListResult.stream().map(p -> p.findElement(productPriceBy).
                getText()).collect(Collectors.toList());
        List pricesSorted = productListResult.stream().map(p -> p.findElement(productPriceBy).
                getText()).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        return pricesOriginal.equals(pricesSorted);
    }

    public boolean orderProductPricesAsc() {
        moveToElementAndClick(descBtn);
        waitToAppearElement(ascBtn);
        waitToAppearElements(productListResult);
        List pricesOriginal = productListResult.stream().map(p -> p.findElement(productPriceBy).
                getText()).collect(Collectors.toList());
        List pricesSorted = productListResult.stream().map(p -> p.findElement(productPriceBy).
                getText()).sorted().collect(Collectors.toList());
        return pricesOriginal.equals(pricesSorted);
    }

    public boolean orderProductNameAsc() {
        waitToAppearElement(descBtn);
        waitToAppearElements(productListResult);
        List namesOriginal = productListResult.stream().map(p -> p.findElement(productNameLinkBy).
                getText()).collect(Collectors.toList());
        List namesSorted = productListResult.stream().map(p -> p.findElement(productNameLinkBy).
                getText()).sorted().collect(Collectors.toList());
        return namesOriginal.equals(namesSorted);
    }

    public void selectShoppingOption(String name) {
        waitAttributeToContains(firstOption, "aria-selected", "false");
        WebElement option = optionsList.stream().filter(o -> o.getText().equals(name)).findFirst().orElse(null);
        option.click();
    }

    public boolean colorCheck(String color) {
        waitToAppearElement(driver.findElement(By.cssSelector(".swatch-option.color[tabindex='-1'][option-label='" + color + "']")));
        driver.findElement(By.cssSelector(".swatch-option.color[tabindex='-1'][option-label='"+color+"']")).click();
        waitToAppearElements(productListResult);
        List<WebElement> colorLabel = driver.findElements(By.cssSelector(".swatch-option.color[tabindex='0'][option-label='" + color + "']"));
        boolean checked = colorLabel.stream().allMatch(w -> w.getAttribute("aria-checked").equals("true"));
        return checked;
    }

    public boolean sizeCheck(String size) {
        waitToAppearElement(driver.findElement(By.cssSelector(".swatch-option.text[tabindex='-1'][option-label='" + size + "']")));
        driver.findElement(By.cssSelector(".swatch-option.text[tabindex='-1'][option-label='" + size + "']")).click();
        waitToAppearElements(productListResult);
        List<WebElement> sizeLabel = driver.findElements(By.cssSelector(".swatch-option.text[tabindex='0'][option-label='" + size + "']"));
        boolean checked = sizeLabel.stream().allMatch(w -> w.getAttribute("aria-checked").equals("true"));
        return checked;
    }

    public boolean priceCheck() {
        waitToAppearElement(driver.findElement(By.xpath("//span[contains(text(),'$59.99')]")));
        driver.findElement(By.xpath("//span[contains(text(),'$59.99')]")).click();
        waitToAppearElements(productListResult);
        List<WebElement> priceLabel = driver.findElements(By.cssSelector(".price-wrapper "));
        boolean checked = priceLabel.stream().allMatch(w -> Double.parseDouble(w.getText().split("\\$")[1]) < 59.99);
        return checked;
    }

    public ProductPage materialCheck() {
        ProductPage productPage = new ProductPage(driver);
        waitToAppearElement(driver.findElement(By.partialLinkText("Organic Cott")));
        driver.findElement(By.partialLinkText("Organic Cott")).click();
        waitToAppearElements(productListResult);
        waitToAppearElement(driver.findElement(By.cssSelector(".filter-value")));
        driver.findElement(By.cssSelector(".product-item-info")).click();
        return productPage;
    }
    public int saleCheck(){
        driver.findElement(By.partialLinkText("Yes")).click();
        List<WebElement> items =driver.findElements(By.cssSelector("ol.items:nth-child(2) .item"));
        return items.size();
    }
}
