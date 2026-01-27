package com.qa.opencart.base;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.listeners.TestAllureListener;
import com.qa.opencart.pages.*;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.testng.ITest;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.util.Properties;
//@Listeners(ChainTestListener.class)
//@Listeners({ChainTestListener.class, TestAllureListener.class })
@Listeners(TestAllureListener.class)
public class BaseTest {
    protected WebDriver driver;
    protected Properties prop;
    DriverFactory df;
    protected LoginPage lp;
    protected AccountsPage accPage;
    protected SearchResultsPage searchResultsPage;
    protected ProductInfoPage productInfoPage;
    protected RegisterPage registerPage;

    @Description("launch the browser {0} and url")
    @Parameters({"browser"})
    @BeforeTest
    public void setUp(String browserName){
        df = new DriverFactory();
        prop = df.initProp();
        if(browserName!=null){
            prop.setProperty("browser",browserName);
        }
        driver = df.initDriver(prop);
        lp = new LoginPage(driver);
    }

    @AfterMethod
    public void attachScreenshot(ITestResult result){
        if(!result.isSuccess()){
            ChainTestListener.embed(DriverFactory.getScreenshotFile(),"image/png");
        }
    }

    @Description("closing the browser")
    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}