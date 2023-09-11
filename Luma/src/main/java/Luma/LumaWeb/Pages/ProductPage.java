package Luma.LumaWeb.Pages;

import Luma.LumaWeb.Component.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductPage extends AbstractComponent {
    WebDriver driver;

    public ProductPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "product-addtocart-button")
    private WebElement addToCartBtn;
    @FindBy(xpath = "[aria-label='XS']")
    private WebElement sizeXSBtn;
    @FindBy(xpath = "[aria-label='S']")
    private WebElement sizeSBtn;
    @FindBy(xpath = "[aria-label='M']")
    private WebElement sizeMBtn;
    @FindBy(xpath = "[aria-label='L']")
    private WebElement sizeLBtn;
    @FindBy(xpath = "[aria-label='XL']")
    private WebElement sizeXLBtn;
    @FindBy(id = "qty")
    private WebElement quantityPlaceholder;
    @FindBy(css = ".swatch-option.color")
    private List<WebElement> colorSelection;
    @FindBy(css = "[option-label='Black']")
    private WebElement colorBlack;
    @FindBy(css = "[option-label='Blue']")
    private WebElement colorBlue;
    @FindBy(css = "[option-label='Green']")
    private WebElement colorGreen;
    @FindBy(css = "[option-label='Gray']")
    private WebElement colorGray;
    @FindBy(css = "[option-label='Orange']")
    private WebElement colorOrange;
    @FindBy(css = "[option-label='Red']")
    private WebElement colorRed;
    @FindBy(css = ".towishlist")
    private WebElement addToWishList;
    @FindBy(css = ".tocompare")
    private WebElement addToCompare;
    @FindBy(css = ".base")
    private WebElement productNameTitle;
    @FindBy(css = ".add")
    private WebElement addReviewLink;
    @FindBy(css = ".price")
    private WebElement productPrice;
    @FindBy(css = ".available")
    private WebElement productAvailability;
    @FindBy(css = ".fotorama__arr.fotorama__arr--next")
    private WebElement rightArrow;
    @FindBy(css = ".fotorama__arr.fotorama__arr--prev")
    private WebElement leftArrow;


}
