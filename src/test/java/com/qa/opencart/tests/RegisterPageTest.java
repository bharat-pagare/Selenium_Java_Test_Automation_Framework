package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.CsvUtil;
import com.qa.opencart.utils.ExcelUtil;
import com.qa.opencart.utils.StringUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RegisterPageTest extends BaseTest {
    @BeforeClass
    public void goToRegisterPage(){
        registerPage = lp.navigateToRegisterPage();
    }

    @DataProvider
    public Object[][] getRegData(){
        return new Object[][]{
            {"Mark","Henry","1234567809","MH@test","No"},
            {"Mark2","Henry2","1234567822","MH2@test","Yes"},
            {"Mark3","Henry3","1234567833","MH3@test","No"}
        };
    }

    @DataProvider
    public Object[][] getExcelData(){
       return ExcelUtil.getExcelData("Register");
    }

    @DataProvider
    public Object[][] getRegCSVData(){
        return CsvUtil.getCSVData("RegPageTestDataCSV");
    }

    @Test (dataProvider = "getRegCSVData")
    public void registerTest(String firstname, String lastname, String telephone, String password, String subscribe){
       Assert.assertTrue(registerPage.userRegister(firstname,lastname, StringUtil.getRandomEmail(),telephone,password,subscribe));
    }
}