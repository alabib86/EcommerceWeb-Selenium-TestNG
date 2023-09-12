package Luma.LumaWeb.Modules;

import Luma.LumaWeb.Component.BaseTest;
import Luma.LumaWeb.Pages.CategoryProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;


public class ProductFilteringSorting extends BaseTest {
    @Test(testName = "Validate user can search for specific category from search bar")
    public void ValidSearchTC02001() {
        CategoryProductsPage categoryProductsPage = homePage.searchForProduct("pan", "pants");
        Assert.assertEquals(categoryProductsPage.getSearchResultMsg(), "Search results for: 'pants'");
    }

    @Test(testName = "Validate user can sort search result by price")
    public void ValidSortTC02002() {
        CategoryProductsPage categoryProductsPage = homePage.searchForProduct("jac", "jacket");
        Assert.assertEquals(categoryProductsPage.selectSortBy("Price"), "Price");
    }

    @Test(testName = "Validate result sort descending by price")
    public void ValidSortTC02003() {
        CategoryProductsPage categoryProductsPage = homePage.searchForProduct("jac", "jacket");
        categoryProductsPage.selectSortBy("Price");
        Assert.assertTrue(categoryProductsPage.orderProductPricesDes());
    }

    @Test(testName = "Validate result sort ascending by price")
    public void ValidSortTC02004() {
        CategoryProductsPage categoryProductsPage = homePage.searchForProduct("jac", "jacket");
        categoryProductsPage.selectSortBy("Price");
        Assert.assertTrue(categoryProductsPage.orderProductPricesAsc());
    }
    @Test(testName = "Validate user can go to Hoodies category from category bar")
    public void ValidSortTC02005() {
        CategoryProductsPage categoryProductsPage = homePage.goToMenTopsHoodies();
        Assert.assertEquals(categoryProductsPage.getCategoryNameTitle(),"Hoodies & Sweatshirts");
    }

}
