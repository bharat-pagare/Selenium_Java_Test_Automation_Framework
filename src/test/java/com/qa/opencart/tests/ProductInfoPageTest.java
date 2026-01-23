package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Map;

public class ProductInfoPageTest extends BaseTest {
    @BeforeClass
    public void productInfoPageSetup() {
        accPage = lp.doLogin(prop.getProperty("username"),prop.getProperty("password"));
    }

    @DataProvider
    public Object[][] getProducts(){
        return new Object [][]{
            {"Macbook","MacBook Pro"},
            {"samsung","Samsung SyncMaster 941BW"},
            {"imac","iMac"},
            {"canon","Canon EOS 5D"}
        };
    }
    @Test (dataProvider = "getProducts")
    public void productHeaderTest(String searchKey, String productName){
        searchResultsPage = accPage.doSearch(searchKey);
        productInfoPage = searchResultsPage.selectProduct(productName);
        String actProductHeader = productInfoPage.getProductHeader();
        Assert.assertEquals(actProductHeader, productName);
    }
    @DataProvider
    public Object[][] getProductImages(){
        return new Object[][]{
                {"Macbook","MacBook Pro",4},
                {"samsung","Samsung SyncMaster 941BW",1},
                {"canon","Canon EOS 5D",3}
        };
    }
    @Test(dataProvider = "getProductImages")
    public void productImagesCountTest(String searchKey, String productName, int imgCount){
        searchResultsPage = accPage.doSearch(searchKey);
        productInfoPage = searchResultsPage.selectProduct(productName);
        int actProductImagesCount = productInfoPage.getProductImages();
        Assert.assertEquals(actProductImagesCount,imgCount);
    }
    @Test
    public void productInfoPageTest(){
        searchResultsPage = accPage.doSearch("macbook");
        productInfoPage = searchResultsPage.selectProduct("MacBook");
        Map<String, String> productData = productInfoPage.getProductData();
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(productData.get("Brand"),"Apple");
        softAssert.assertEquals(productData.get("Availability"),"In Stock");
        softAssert.assertEquals(productData.get("Product Price"),"$602.00");
        softAssert.assertEquals(productData.get("Ex Tax"),"$500.00");
        softAssert.assertEquals(productData.get("Product Code"),"Product 16");
        softAssert.assertEquals(productData.get("Reward Points"),"600");
        softAssert.assertEquals(productData.get("Product Name"),"MacBook");
        softAssert.assertEquals(productData.get("Product Images Count"),"5");
        softAssert.assertAll();
    }
}