package com.qa.opencart.pages;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchResultsPage {
    private WebDriver driver;
    ElementUtil eleUtil;
    public SearchResultsPage(WebDriver driver){
        this.driver = driver;
        eleUtil = new ElementUtil(driver);
    }
    private final By searchResults = By.cssSelector("div.product-thumb");
    private final By resultsHeader = By.cssSelector("div#content h1");
    public int getSearchResultsCount(){
        List<WebElement> resultCount = eleUtil.waitForElementsPresence(searchResults, AppConstants.DEFAULT_MEDIUM_WAIT);
        System.out.println("Results count --> "+resultCount.size());
        return resultCount.size();
    }
    public String getHeaderValue(){
        String header = eleUtil.doElementGetText(resultsHeader);
        System.out.println("Results Header --> "+header);
        return header;
    }
    public ProductInfoPage selectProduct(String productName){
        System.out.println("Product selected -->"+productName);
        eleUtil.doClick(By.linkText(productName));
        return new ProductInfoPage(driver);
    }
}