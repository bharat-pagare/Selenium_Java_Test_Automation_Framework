package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {
    @BeforeClass
    public void searchSetup(){
        accPage = lp.doLogin(prop.getProperty("username"),prop.getProperty("password"));
    }
    @DataProvider
    public Object[][] getProduct(){
        return new Object[][]{
                {"Macbook","MacBook Pro"}
        };
    }
    @Test(dataProvider = "getProduct")
    public void searchTest(String searchKey, String productName) {
        searchResultsPage = accPage.doSearch(searchKey);
        productInfoPage = searchResultsPage.selectProduct(productName);
        String actHeader = productInfoPage.getProductHeader();
        Assert.assertEquals(actHeader, productName);
    }
}