package Luma.LumaWeb.Modules;

import Luma.LumaWeb.Component.BaseTest;
import Luma.LumaWeb.Pages.CategoryProductsPage;
import Luma.LumaWeb.Pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;


public class ProductFilteringSorting extends BaseTest {
    @Test(testName = "Validate user can search for specific category from search bar",groups = {"RegressionTest"})
    public void ValidSearchTC02001() {
        CategoryProductsPage categoryProductsPage = homePage.searchForProduct("pan", "pants");
        Assert.assertEquals(categoryProductsPage.getSearchResultMsg(), "Search results for: 'pants'");
    }

    @Test(testName = "Validate user can sort search result by price",groups = {"RegressionTest"})
    public void ValidSortTC02002() {
        CategoryProductsPage categoryProductsPage = homePage.searchForProduct("jac", "jacket");
        Assert.assertEquals(categoryProductsPage.selectSortBy("Price"), "Price");
    }

    @Test(testName = "Validate result sort descending by price",groups = {"RegressionTest"})
    public void ValidSortTC02003() {
        CategoryProductsPage categoryProductsPage = homePage.searchForProduct("jac", "jacket");
        categoryProductsPage.selectSortBy("Price");
        Assert.assertTrue(categoryProductsPage.orderProductPricesDes());
    }

    @Test(testName = "Validate result sort ascending by price",groups = {"RegressionTest"})
    public void ValidSortTC02004() {
        CategoryProductsPage categoryProductsPage = homePage.searchForProduct("jac", "jacket");
        categoryProductsPage.selectSortBy("Price");
        Assert.assertTrue(categoryProductsPage.orderProductPricesAsc());
    }

    @Test(testName = "Validate user can go to Hoodies category from category bar",groups = {"RegressionTest"})
    public void ValidSortTC02005() {
        CategoryProductsPage categoryProductsPage = homePage.goToMenTopsHoodies();
        Assert.assertEquals(categoryProductsPage.getCategoryNameTitle(), "Hoodies & Sweatshirts");
    }

    @Test(testName = "Validate result sort ascending by productName",groups = {"RegressionTest"})
    public void ValidSortTC02006() {
        CategoryProductsPage categoryProductsPage = homePage.goToMenTopsHoodies();
        categoryProductsPage.selectSortBy("Product Name");
        categoryProductsPage.orderProductNameAsc();
    }

    @Test(testName = "Validate user can filter product by color",groups = {"RegressionTest"})
    public void ValidSortTC02007() {
        CategoryProductsPage categoryProductsPage = homePage.goToMenTopsHoodies();
        categoryProductsPage.selectShoppingOption("COLOR");
        Assert.assertTrue(categoryProductsPage.colorCheck("Green"));
    }

    @Test(testName = "Validate user can filter product by size",groups = {"RegressionTest"})
    public void ValidSortTC02008() {
        CategoryProductsPage categoryProductsPage = homePage.goToMenTopsHoodies();
        categoryProductsPage.selectShoppingOption("SIZE");
        Assert.assertTrue(categoryProductsPage.sizeCheck("S"));
    }

    @Test(testName = "Validate user can filter product by price",groups = {"RegressionTest"})
    public void ValidSortTC02009() {
        CategoryProductsPage categoryProductsPage = homePage.goToWatches();
        categoryProductsPage.selectShoppingOption("PRICE");
        Assert.assertTrue(categoryProductsPage.priceCheck());
    }

    @Test(testName = "Validate user can filter product by material",groups = {"RegressionTest"})
    public void ValidSortTC02010() {
        CategoryProductsPage categoryProductsPage = homePage.goToWomenBottomPants();
        categoryProductsPage.selectShoppingOption("MATERIAL");
        ProductPage productPage = categoryProductsPage.materialCheck();
        Assert.assertTrue(productPage.getMoreInformationMaterial());
    }

    @Test(testName = "Validate user can filter product by two options",groups = {"RegressionTest"})
    public void ValidSortTC02011() {
        CategoryProductsPage categoryProductsPage = homePage.goToMenTopsHoodies();
        categoryProductsPage.selectShoppingOption("COLOR");
        categoryProductsPage.colorCheck("Red");
        categoryProductsPage.selectShoppingOption("SALE");
        Assert.assertEquals(categoryProductsPage.saleCheck(),2);
    }

}
