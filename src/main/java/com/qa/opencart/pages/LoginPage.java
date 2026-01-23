package com.qa.opencart.pages;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.utils.ElementUtil;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;
    private ElementUtil eleUtil;
    //private locators/objects in the page
    private final By emailID = By.id("input-email");
    private final By pwd = By.id("input-password");
    private final By loginButton = By.xpath("//input[@value='Login']");
    private final By forgotPwd = By.linkText("Forgotten Password");
    private final By header = By.tagName("h2");
    private final By registerPage = By.linkText("Register");
    public static final Logger log = LogManager.getLogger(DriverFactory.class);

    //public constructor to assign the value of driver
    public LoginPage (WebDriver driver){
        this.driver = driver;
        eleUtil = new ElementUtil(driver);
    }

    //public page methods
    @Step("getting login page title....")
    public String getLoginPageTitle(){
        String title = eleUtil.waitForTitleContains(AppConstants.LOGIN_PAGE_TITLE, AppConstants.DEFAULT_SHORT_WAIT);
        //System.out.println("Login Page Title is "+title);
        log.info("Login Page Title is "+title);
        return title;
    }
    @Step("getting login url title....")
    public String getLoginPageURL(){
        String url = eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_FRACTION_URL,AppConstants.DEFAULT_SHORT_WAIT);
        //System.out.println("Login Page URL is "+url);
        log.info("Login Page URL is "+url);
        return url;
    }
    @Step("forgot pwd link exist...")
    public boolean isForgotPwdLinkExists(){
        return eleUtil.isElementDisplayed(forgotPwd);
    }
    @Step("page header exist...")
    public boolean isHeaderExists(){
        return eleUtil.isElementDisplayed(header);
    }
    @Step("login with correct username: {0} and password: {1}")
    public AccountsPage doLogin (String appUsername,String appPwd){
        eleUtil.waitForElementVisible(emailID,5).sendKeys(appUsername);
        eleUtil.doSendKeys(pwd,appPwd);
        eleUtil.doClick(loginButton);
        return new AccountsPage(driver);
    }
    @Step("navigating to register page...")
    public RegisterPage navigateToRegisterPage(){
        eleUtil.waitForElementVisible(registerPage,AppConstants.DEFAULT_SHORT_WAIT).click();
        return new RegisterPage(driver);
    }
}