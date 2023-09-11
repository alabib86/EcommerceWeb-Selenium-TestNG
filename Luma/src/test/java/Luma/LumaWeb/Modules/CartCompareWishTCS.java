package Luma.LumaWeb.Modules;

import Luma.LumaWeb.Component.BaseTest;
import Luma.LumaWeb.Pages.LoginPage;
import org.testng.annotations.Test;

import java.util.HashMap;

public class CartCompareWishTCS extends BaseTest {
    @Test(testName = "Validate user can log out from his account",dataProvider = "getSignUpData")
    public void ValidLoginTC02001(HashMap<String,String> input)   {
        LoginPage loginPage = homePage.goToLoginPage();
        loginPage.login(input.get("email"), input.get("password"));
        homePage.signOut();
    }
}
