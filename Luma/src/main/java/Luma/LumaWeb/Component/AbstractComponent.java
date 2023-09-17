package Luma.LumaWeb.Component;

import Luma.LumaWeb.Pages.CartPage;
import Luma.LumaWeb.Pages.CategoryProductsPage;
import Luma.LumaWeb.Pages.LoginPage;
import Luma.LumaWeb.Pages.SignUpPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class AbstractComponent {
    WebDriver driver;

    public AbstractComponent(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "Create an Account")
    private WebElement createAccountPageBtn;
    @FindBy(linkText = "Sign In")
    private WebElement signInPageBtn;
    @FindBy(partialLinkText = "Sign O")
    private WebElement signOutBtn;
    @FindBy(css = ".panel.header .logged-in")
    private WebElement greetingMsg;
    @FindBy(css = ".panel.header button.action.switch")
    private WebElement accountArrowDropdown;
    @FindBy(id = "ui-id-4")
    private WebElement womenTab;
    @FindBy(id = "ui-id-10")
    private WebElement womenBottomTab;
    @FindBy(id = "ui-id-15")
    private WebElement womenBottomPantsTab;
    @FindBy(id = "ui-id-5")
    private WebElement menTab;
    @FindBy(id = "ui-id-17")
    private WebElement menTopTab;
    @FindBy(id = "ui-id-20")
    private WebElement menTopHoodieTab;
    @FindBy(id = "ui-id-6")
    private WebElement gearTab;
    @FindBy(id = "ui-id-27")
    private WebElement watchesTab;
    @FindBy(id = "search")
    private WebElement searchBar;
    @FindBy(css = ".qs-option-name")
    private List<WebElement> searchResultList;
    @FindBy(css = ".showcart")
    private WebElement cartIcon;
    @FindBy(css = ".counter-number")
    private WebElement cartItemCount;
    @FindBy(css = ".subtitle.empty")
    private WebElement cartEmptyMsg;
    @FindBy(css = ".viewcart")
    private WebElement cartView;
    @FindBy(id = "ui-id-1")
    private WebElement cartDropdownDialog;
    @FindBy(css = ".item.product.product-item[data-role='product-item']")
    private List<WebElement> productListCartDialog;
    @FindBy(css = ".action.delete")
    private WebElement deleteIconCartDialog;
    @FindBy(css = ".action-primary.action-accept")
    private WebElement okBtnRemoveItemCart;


    By greetingMsgBy = By.cssSelector(".panel.header .logged-in");
    By greetingMsgNotLoggedBy = By.cssSelector(".not-logged-in");


    // waits

    public void waitToAppearElement(WebElement webElement) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public void waitToDisAppearElement(WebElement webElement) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        webDriverWait.until(ExpectedConditions.invisibilityOf(webElement));
    }

    public void waitToAppearElements(List<WebElement> list) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(list));
    }

    public void waitTextToBe(By locator, String text) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        webDriverWait.until(ExpectedConditions.textToBe(locator, text));
    }

    public void waitToEnableElement(WebElement webElement) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        webDriverWait.until(p -> webElement.isEnabled());
    }

    public void waitAttributeToContains(WebElement webElement, String attribute, String value) {
        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
        w.until(ExpectedConditions.attributeContains(webElement, attribute, value));
    }

    // actions

    public void moveToElement(WebElement webElement) {
        Actions a = new Actions(driver);
        a.moveToElement(webElement).build().perform();
    }

    public void moveToElementAndClick(WebElement webElement) {
        Actions a = new Actions(driver);
        a.moveToElement(webElement).click().build().perform();
    }

    // utilities

    public int getRandomNumber(int bound) {
        Random random = new Random();
        return random.nextInt(bound);
    }

    //  sign up

    public SignUpPage goToSignUpPage() {
        SignUpPage signUpPage = new SignUpPage(driver);
        createAccountPageBtn.click();
        return signUpPage;
    }

    //  logIn & logOut

    public LoginPage goToLoginPage() {
        LoginPage loginPage = new LoginPage(driver);
        signInPageBtn.click();
        return loginPage;
    }

    public void signOut() {
        waitTextToBe(greetingMsgBy, "Welcome, ahmed labib!");
        accountArrowDropdown.click();
        waitToAppearElement(signOutBtn);
        signOutBtn.click();
    }

    public String getGreetingMsg() {
        waitTextToBe(greetingMsgBy, "Welcome, ahmed labib!");
        return greetingMsg.getText();
    }

    // search

    public CategoryProductsPage searchForProduct(String word, String result) {
        CategoryProductsPage categoryProductsPage = new CategoryProductsPage(driver);
        waitTextToBe(greetingMsgNotLoggedBy, "Default welcome msg!");
        searchBar.sendKeys(word);
        waitToAppearElements(searchResultList);
        WebElement product = searchResultList.stream().filter(p -> p.getText().equals(result)).findFirst().orElse(null);
        product.click();
        return categoryProductsPage;
    }

    // product bar

    public CategoryProductsPage goToMenTopsHoodies() {
        CategoryProductsPage categoryProductsPage = new CategoryProductsPage(driver);
        moveToElement(menTab);
        waitToAppearElement(menTopTab);
        moveToElement(menTopTab);
        waitToAppearElement(menTopHoodieTab);
        moveToElementAndClick(menTopHoodieTab);
        return categoryProductsPage;
    }

    public CategoryProductsPage goToWatches() {
        CategoryProductsPage categoryProductsPage = new CategoryProductsPage(driver);
        moveToElement(gearTab);
        waitToAppearElement(watchesTab);
        moveToElementAndClick(watchesTab);
        return categoryProductsPage;
    }

    public CategoryProductsPage goToWomenBottomPants() {
        CategoryProductsPage categoryProductsPage = new CategoryProductsPage(driver);
        moveToElement(womenTab);
        waitToAppearElement(womenBottomTab);
        moveToElement(womenBottomTab);
        waitToAppearElement(womenBottomPantsTab);
        moveToElementAndClick(womenBottomPantsTab);
        return categoryProductsPage;
    }

    // cart

    public void openCartDialog() {
        waitToAppearElement(cartIcon);
        cartIcon.click();
    }

    public CartPage goToCartPage() {
        CartPage cartPage = new CartPage(driver);
        openCartDialog();
        waitToAppearElement(cartDropdownDialog);
        cartView.click();
        return cartPage;
    }

    public String getCartItemCount() {
        return cartItemCount.getText();
    }

    public String getCartEmptyMsg() {
        return cartEmptyMsg.getText();
    }

    public void removeProductFromCartDialog() {
        waitToAppearElement(cartDropdownDialog);
        long count = productListCartDialog.stream().count();
        int x = 0;
        for (int i = 0; i < count; i++) {
            x++;
            deleteIconCartDialog.click();
            waitToAppearElement(okBtnRemoveItemCart);
            okBtnRemoveItemCart.click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions
                    .numberOfElementsToBe(By.cssSelector(".item.product.product-item[data-role='product-item']")
                            , (int) count - x));
        }
    }

    public List getItemsCartDialog() {
        waitToEnableElement(cartItemCount);
        openCartDialog();
        List<String> itemNames = productListCartDialog.stream()
                .map(ele -> ele.findElement(By.cssSelector(".product-item-name")).getText()).collect(Collectors.toList());

        return itemNames;
    }

    public boolean checkExistItemInCartByName(String itemName) {
        List itemNames=getItemsCartDialog();
        boolean check=false;
        for (int i=0; i<itemNames.size();i++) {
            if (itemNames.get(i).equals(itemName)) {
                check= true;
            }
        }
        return  check;
    }
}
