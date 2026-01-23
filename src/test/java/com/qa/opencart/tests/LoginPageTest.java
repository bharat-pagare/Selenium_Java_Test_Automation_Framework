package com.qa.opencart.tests;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("EP-100: Design the Open Cart App Login Page")
@Feature("F-101: design open cart login feature")
@Story("US-50: develop login core features: title, url, user is able to login")
public class LoginPageTest extends BaseTest {

    @Description("login page title test.....")
    @Link("")
    @Owner("Naveen Automation Labs")
    @Severity(SeverityLevel.MINOR)
    @Test
    public void loginPageTitleTest(){
        String actTitle = lp.getLoginPageTitle();
        ChainTestListener.log("Login Page title is: "+actTitle);
        Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE);
    }

    @Description("login page url test....")
    @Owner("Naveen Automation Labs")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void loginPageURLTest(){
        String actURL = lp.getLoginPageURL();
        Assert.assertTrue(actURL.contains(AppConstants.LOGIN_PAGE_FRACTION_URL));
    }

    @Description("forgot password link exist test....")
    @Owner("Naveen Automation Labs")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void isForgotPwdLinkExistTest(){
        Assert.assertTrue(lp.isForgotPwdLinkExists());
    }

    @Description("login page header test....")
    @Owner("Naveen Automation Labs")
    @Severity(SeverityLevel.MINOR)
    @Test
    public void isHeaderExists(){
        Assert.assertTrue(lp.isHeaderExists());
    }

    @Description("user is able to login to app with the correct credentials....")
    @Owner("Naveen Automation Labs")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    public void loginTest(){
        accPage = lp.doLogin(prop.getProperty("username"),prop.getProperty("password"));
        Assert.assertTrue(accPage.isLogOutLinkExists());
    }
}