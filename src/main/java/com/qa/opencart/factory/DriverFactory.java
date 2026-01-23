package com.qa.opencart.factory;

import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.FrameworkException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DriverFactory {
    WebDriver driver;
    Properties prop;
    public static String highlightEle;
    public static final ThreadLocal<WebDriver> tLDriver = new ThreadLocal<WebDriver>();
    public static final Logger log = LogManager.getLogger(DriverFactory.class);

    public WebDriver initDriver(Properties prop) {
        String browserName = prop.getProperty("browser");
        highlightEle = prop.getProperty("highlight");
        log.info(browserName + " browser launched");

        switch (browserName.trim().toLowerCase()) {
            case "chrome":
                //driver=new ChromeDriver();
                tLDriver.set(new ChromeDriver());
                break;
            case "firefox":
                //driver=new FirefoxDriver();
                tLDriver.set(new FirefoxDriver());
                break;
            case "edge":
                //driver=new EdgeDriver();
                tLDriver.set(new EdgeDriver());
                break;
            case "safari":
                //driver=new SafariDriver();
                tLDriver.set(new SafariDriver());
                break;
            default:
                log.error(AppError.INVALID_BROWSER_MESG + " " +browserName);
                //System.out.println(AppError.INVALID_BROWSER_MESG);
                throw new FrameworkException("----INVALID BROWSER NAME----");
        }
        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();
        getDriver().get(prop.getProperty("url"));
        return getDriver();
    }

    public static WebDriver getDriver() {
        return tLDriver.get();
    }

    public Properties initProp() {
        prop = new Properties();
        try {
            FileInputStream ip = new FileInputStream("./src/main/resources/config/config.properties");
            prop.load(ip);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return prop;
    }

    /* Screenshot Methods */
    public static File getScreenshotFile() {
        File ssFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        return ssFile;
    }
    public static byte[] getScreenshotByte() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
    }
    public static String getScreenshotBase64() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);
    }
}