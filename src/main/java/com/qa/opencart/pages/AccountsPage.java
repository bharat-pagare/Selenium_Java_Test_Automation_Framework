package com.qa.opencart.pages;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class AccountsPage {
    private WebDriver driver;
    private ElementUtil eleUtil;
    private final By headers = By.cssSelector("div#content h2");
    private final By logoutLink = By.linkText("Logout");
    private final By search = By.name("search");
    private final By searchIcon = By.cssSelector("div#search button");
    public AccountsPage(WebDriver driver) {
        this.driver = driver;
        eleUtil= new ElementUtil(driver);
    }

    @Step("getting Accounts page header")
    public List<String> getAccountsPageHeaders(){
        List<WebElement> headersList = eleUtil.waitForElementsPresence(headers, AppConstants.DEFAULT_SHORT_WAIT);
        System.out.println("Total no. of Headers "+headersList.size());
        List<String> headersValList = new ArrayList<String>();
        for (WebElement e : headersList){
            String text = e.getText();
            headersValList.add(text);
        }
        return headersValList;
    }

    @Step("logout link exist...")
    public boolean isLogOutLinkExists(){
        return eleUtil.isElementDisplayed(logoutLink);
    }

    @Step("performing search action for {0}")
    public SearchResultsPage doSearch(String searchKey){
        System.out.println("Product searched --> "+searchKey);
        WebElement searchEle = eleUtil.waitForElementVisible(search, AppConstants.DEFAULT_SHORT_WAIT);
        searchEle.clear();
        searchEle.sendKeys(searchKey);
        eleUtil.doClick(searchIcon);
        return new SearchResultsPage(driver);
    }
}