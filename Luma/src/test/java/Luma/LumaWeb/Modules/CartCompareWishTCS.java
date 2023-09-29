package Luma.LumaWeb.Modules;

import Luma.LumaWeb.Component.BaseTest;
import Luma.LumaWeb.Component.Retry;
import Luma.LumaWeb.Pages.*;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;


public class CartCompareWishTCS extends BaseTest {
    @Test(testName = "Validate user get proper message for empty cart when open cart dialog",
            dataProvider = "getSignUpData",groups = {"RegressionTest"})
    public void ValidCartTC03001(HashMap<String, String> input) {
        LoginPage loginPage = homePage.goToLoginPage();
        loginPage.login(input.get("email"), input.get("password"));
        CategoryProductsPage categoryProductsPage = homePage.goToMenTopsHoodies();
        categoryProductsPage.getCategoryNameTitle();
        categoryProductsPage.openCartDialog();
        Assert.assertEquals(categoryProductsPage.getCartEmptyMsg(), "You have no items in your shopping cart.");
    }

    @Test(testName = "Validate user can add items to the cart from products categories",
            dataProvider = "getSignUpData",groups = {"RegressionTest"})
    public void ValidCartTC03002(HashMap<String, String> input) {
        LoginPage loginPage = homePage.goToLoginPage();
        loginPage.login(input.get("email"), input.get("password"));
        CategoryProductsPage categoryProductsPage = homePage.goToMenTopsHoodies();
        categoryProductsPage.getCategoryNameTitle();
        categoryProductsPage.addItemsToCart();
    }

    @Test(testName = "Validate user can remove all item from cart dialog",
            dataProvider = "getSignUpData", dependsOnMethods = {"ValidCartTC03002"}, retryAnalyzer = Retry.class
            ,groups = {"RegressionTest"})
    public void ValidCartTC03003(HashMap<String, String> input) {
        LoginPage loginPage = homePage.goToLoginPage();
        loginPage.login(input.get("email"), input.get("password"));
        homePage.getGreetingMsg();
        homePage.openCartDialog();
        homePage.removeProductFromCartDialog();
        Assert.assertEquals(homePage.getCartEmptyMsg(), "You have no items in your shopping cart.");
    }

    @Parameters({"email", "password"})
    @Test(testName = "Validate user can see the item added in cart dialog when added",groups = {"RegressionTest"})
    public void ValidCartTC03004(String e, String p) {
        LoginPage loginPage = homePage.goToLoginPage();
        loginPage.login(e, p);
        homePage.getGreetingMsg();
        CategoryProductsPage categoryProductsPage = homePage.goToWatches();
        categoryProductsPage.addItemToCart("Cruise Dual Analog Watch", "", "");
        Assert.assertTrue(categoryProductsPage.checkExistItemInCartByName("Cruise Dual Analog Watch"));

    }

    @Parameters({"email", "password"})
    @Test(testName = "Validate subtotal of each item ",groups = {"RegressionTest"})
    public void ValidCartTC03005(String e, String p) {
        LoginPage loginPage = homePage.goToLoginPage();
        loginPage.login(e, p);
        homePage.getGreetingMsg();
        CartPage cartPage = homePage.goToCartPage();
        Assert.assertTrue(cartPage.checkItemSubtotal());
    }

    @Parameters({"email", "password"})
    @Test(testName = "Validate subtotal of all order  ",groups = {"RegressionTest"})
    public void ValidCartTC03006(String e, String p) {
        LoginPage loginPage = homePage.goToLoginPage();
        loginPage.login(e, p);
        homePage.getGreetingMsg();
        CartPage cartPage = homePage.goToCartPage();
        Assert.assertTrue(cartPage.checkOrderSubtotal());
    }

    @Parameters({"email", "password"})
    @Test(testName = "Validate order Total calculations  ",groups = {"RegressionTest"})
    public void ValidCartTC03007(String e, String p) {
        LoginPage loginPage = homePage.goToLoginPage();
        loginPage.login(e, p);
        homePage.getGreetingMsg();
        CartPage cartPage = homePage.goToCartPage();
        Assert.assertTrue(cartPage.checkOrderTotal());
    }

    @Parameters({"email", "password"})
    @Test(testName = "Validate user can add item to wishlist  ",groups = {"RegressionTest"})
    public void ValidCartTC03008(String e, String p) {
        LoginPage loginPage = homePage.goToLoginPage();
        loginPage.login(e, p);
        homePage.getGreetingMsg();
        CategoryProductsPage categoryProductsPage = homePage.goToWatches();
        WishListPage wishListPage = categoryProductsPage.addItemToWishList("Dash Digital Watch");
        Assert.assertTrue(wishListPage.getPageMessage().contains("Dash Digital Watch"));
    }

    @Parameters({"email", "password"})
    @Test(testName = "Validate user can delete item from wishlist  ", dependsOnMethods = {"ValidCartTC03008"}
            ,groups = {"RegressionTest"})
    public void ValidCartTC03009(String e, String p) {
        LoginPage loginPage = homePage.goToLoginPage();
        loginPage.login(e, p);
        homePage.getGreetingMsg();
        WishListPage wishListPage = homePage.goToWishList();
        wishListPage.deleteItemFromWishList("Dash Digital Watch");
        Assert.assertTrue(wishListPage.getPageMessage().contains("Dash Digital Watch has been removed from your Wish List."));
    }

    @Parameters({"email", "password"})
    @Test(testName = "Validate user can add item from compare list  ",groups = {"RegressionTest"})
    public void ValidCartTC03010(String e, String p) {
        SoftAssert sf = new SoftAssert();
        LoginPage loginPage = homePage.goToLoginPage();
        loginPage.login(e, p);
        homePage.getGreetingMsg();
        CategoryProductsPage categoryProductsPage = homePage.goToWatches();
        sf.assertEquals(categoryProductsPage
                .addItemToCompareList("Summit Watch"), "You added product Summit Watch to the ");
        sf.assertEquals(categoryProductsPage
                .addItemToCompareList("Luma Analog Watch"), "You added product Luma Analog Watch to the ");
        CompareListPage compareListPage = categoryProductsPage.goToCompareList();
        String[] names = {"Luma Analog Watch", "Summit Watch"};
        Assert.assertTrue(compareListPage.checkExistingProduct(names));
    }


}
