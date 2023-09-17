package Luma.LumaWeb.Pages;

import Luma.LumaWeb.Component.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponent {
    WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".cart.item")
    private List<WebElement> cartItemsList;
    @FindBy(css = ".action-edit")
    private WebElement editItemBtn;
    @FindBy(css = ".action-delete")
    private WebElement deleteItemBtn;
    @FindBy(css = ".product-item-name")
    private WebElement itemName;
    @FindBy(css = ".item-options")
    private WebElement itemOptions;
    @FindBy(css = ".item .primary.checkout")
    private WebElement proceedToCheckoutBtn;
    @FindBy(css = ".totals.sub .price")
    private WebElement subtotalAll;
    @FindBy(css = ".amount[data-th='Discount'] .price")
    private WebElement discountPrice;
    @FindBy(css = ".totals-tax .price")
    private WebElement taxPrice;
    @FindBy(css = "strong .price")
    private WebElement totalPrice;
    @FindBy(css = ".update")
    private WebElement updateShoppingCart;
    private By itemPriceBy = By.cssSelector(".col.price .price");
    private By itemQtyBy = By.cssSelector(".control.qty input");
    private By itemSubtotalPriceBy = By.cssSelector(".col.subtotal .price");

    public boolean checkItemSubtotal() {
        boolean check = false;
        for (int i = 0; i < cartItemsList.size(); i++) {
            String priceStr = cartItemsList.get(i).findElement(itemPriceBy).getText().split("\\$")[1];
            String qtyStr = cartItemsList.get(i).findElement(itemQtyBy).getAttribute("value");
            String subtotalStr = cartItemsList.get(i).findElement(itemSubtotalPriceBy).getText().split("\\$")[1];
            double price = Double.parseDouble(priceStr);
            double subtotal = Double.parseDouble(subtotalStr);
            int quantity = Integer.parseInt(qtyStr);
            if (price * quantity == subtotal) {
                check = true;
            } else {
                check = false;
            }
        }
        return check;
    }

    public boolean checkOrderSubtotal() {
        boolean check = false;
        String subtotalAllStr = subtotalAll.getText().split("\\$")[1];
        double subT = Double.parseDouble(subtotalAllStr);
        Double sum = 0.0;

        for (int i = 0; i < cartItemsList.size(); i++) {
            String subtotalStr = cartItemsList.get(i).findElement(itemSubtotalPriceBy).getText().split("\\$")[1];
            double subtotal = Double.parseDouble(subtotalStr);
            sum += subtotal;
        }
        if (sum == subT) {
            check = true;
        } else {
            check = false;
        }
        return check;
    }

    public boolean checkOrderTotal() {
        boolean check = false;

        String subtotalAllStr = subtotalAll.getText().split("\\$")[1];
        double subT = Double.parseDouble(subtotalAllStr);
        String discountStr = discountPrice.getText().split("\\$")[1];
        double discount = Double.parseDouble(discountStr);
        String taxStr = taxPrice.getText().split("\\$")[1];
        double tax = Double.parseDouble(taxStr);
        String orderTotalStr = totalPrice.getText().split("\\$")[1];
        double orderTotal = Double.parseDouble(orderTotalStr);

        Double sum =subT-discount+tax ;

        if (sum == orderTotal) {
            check = true;
        } else {
            check = false;
        }
        return check;
    }

}
