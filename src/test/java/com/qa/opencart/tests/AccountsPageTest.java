package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class AccountsPageTest extends BaseTest {

    @BeforeClass
    public void accPageSetup(){
        accPage = lp.doLogin(prop.getProperty("username"),prop.getProperty("password"));
    }

    @Test
    public void logOutLinkTest(){
        Assert.assertTrue(accPage.isLogOutLinkExists());
    }

    @Test
    public void searchTest(){
        accPage.doSearch("Open Cart");
    }
    @Test
    public void accPageHeadersTest(){
        List<String> accountsPageHeaders = accPage.getAccountsPageHeaders();
        Assert.assertEquals(accountsPageHeaders.size(),AppConstants.ACC_PAGE_HEADERS_COUNT);
        Assert.assertEquals(accountsPageHeaders, AppConstants.expectedHeadersList);
    }
}
