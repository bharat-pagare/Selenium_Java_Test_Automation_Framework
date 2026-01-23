package com.qa.opencart.pages;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;

public class ProductInfoPage {
    WebDriver driver;
    ElementUtil eleUtil;
    public ProductInfoPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(driver);
    }
    private final By header = By.tagName("H1");
    private final By productImages = By.cssSelector("ul.thumbnails img");
    private final By productInfo = By.xpath("//div[@id='content']//ul[@class='list-unstyled']/li");
    Map<String, String> productMap;

    public String getProductHeader() {
        String productHeader = eleUtil.waitForElementVisible(header, AppConstants.DEFAULT_SHORT_WAIT).getText();
        System.out.println("Product Header found is --> " + productHeader);
        return productHeader;
    }

    public int getProductImages() {
        int imgCount = eleUtil.waitForElementsVisible(productImages, AppConstants.DEFAULT_MEDIUM_WAIT).size();
        //System.out.println("Total number of Images found --> " + imgCount);
        return imgCount;
    }
    public Map<String, String> getProductData(){
        //productMap = new HashMap<String,String>(); //Data will be stored without maintaining any insertion order
        //productMap = new LinkedHashMap<String,String>(); //Data will be stored maintaining the insertion order
        productMap = new TreeMap<String, String>(); //Data will be stored maintaining the alphabetic order
        productMap.put("Product Name",getProductHeader());
        productMap.put("Product Images Count", String.valueOf(getProductImages()));
        getProductMetaData();
        System.out.println(productMap);
        return productMap;
    }
    private void getProductMetaData(){
        List<WebElement> metaList = eleUtil.waitForElementsVisible(productInfo, AppConstants.DEFAULT_SHORT_WAIT);
        String metaKey, metaValue;

        for(WebElement e : metaList){
            String metaData = e.getText();
            if (metaData.contains(":")) {
                String[] split = metaData.split(":");
                metaKey = split[0].trim();
                metaValue = split[1].trim();
                productMap.put(metaKey, metaValue);
            }
            else{
                metaKey = "Product Price";
                metaValue = metaData;
                productMap.put(metaKey, metaValue);
            }
        }
    }
}