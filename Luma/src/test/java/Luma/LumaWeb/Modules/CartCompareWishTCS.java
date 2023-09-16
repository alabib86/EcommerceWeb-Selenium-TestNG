package Luma.LumaWeb.Modules;

import Luma.LumaWeb.Component.BaseTest;
import Luma.LumaWeb.Component.Retry;
import Luma.LumaWeb.Pages.CategoryProductsPage;
import Luma.LumaWeb.Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class CartCompareWishTCS extends BaseTest {
    @Test(testName = "Validate user get proper message for empty cart when open cart dialog", dataProvider = "getSignUpData")
    public void ValidCartTC03001(HashMap<String, String> input) {
        LoginPage loginPage = homePage.goToLoginPage();
        loginPage.login(input.get("email"), input.get("password"));
        CategoryProductsPage categoryProductsPage = homePage.goToMenTopsHoodies();
        categoryProductsPage.getCategoryNameTitle();
        categoryProductsPage.openCartDialog();
        Assert.assertEquals(categoryProductsPage.getCartEmptyMsg(), "You have no items in your shopping cart.");
    }

    @Test(testName = "Validate user can add items to the cart from products categories",
            dataProvider = "getSignUpData")
    public void ValidCartTC03002(HashMap<String, String> input) {
        LoginPage loginPage = homePage.goToLoginPage();
        loginPage.login(input.get("email"), input.get("password"));
        CategoryProductsPage categoryProductsPage = homePage.goToMenTopsHoodies();
        categoryProductsPage.getCategoryNameTitle();
        categoryProductsPage.addItemsToCart();
    }

    @Test(testName = "Validate user can remove all item from cart dialog",
            dataProvider = "getSignUpData", dependsOnMethods = "ValidCartTC03002", retryAnalyzer = Retry.class)
    public void ValidCartTC03003(HashMap<String, String> input) {
        LoginPage loginPage = homePage.goToLoginPage();
        loginPage.login(input.get("email"), input.get("password"));
        homePage.getGreetingMsg();
        homePage.openCartDialog();
        homePage.removeProductFromCartDialog();
        Assert.assertEquals(homePage.getCartEmptyMsg(), "You have no items in your shopping cart.");
    }

}
