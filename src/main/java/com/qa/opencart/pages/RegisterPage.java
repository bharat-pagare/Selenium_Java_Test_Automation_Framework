package com.qa.opencart.pages;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    private WebDriver driver;
    private ElementUtil eleUtil;
    public RegisterPage(WebDriver driver){
        this.driver = driver;
        eleUtil = new ElementUtil(driver);
    }

    private By firstName = By.id("input-firstname");
    private final By lastName = By.id("input-lastname");
    private final By email = By.id("input-email");
    private final By telephone = By.id("input-telephone");
    private final By password = By.id("input-password");
    private final By confirmpassword = By.id("input-confirm");
    private final By subscribeYes = By. xpath("//input[@name='newsletter' and @value=1]");
    private final By subscribeNo = By. xpath("//input[@name='newsletter' and @value=0]");
    private final By agreeCheckBox = By.name("agree");
    private final By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");
    private final By successMessg = By.cssSelector("div#content h1");
    private final By logoutLink = By.linkText("Logout");
    private final By registerLink = By.linkText("Register");

    public boolean userRegister(String firstName, String lastName, String email, String telephone, String password,
                                String subscribe) {
        eleUtil.waitForElementVisible(this.firstName, AppConstants.DEFAULT_SHORT_WAIT).sendKeys(firstName);
        eleUtil.doSendKeys(this.lastName, lastName);
        eleUtil.doSendKeys(this.email, email);
        eleUtil.doSendKeys(this.telephone, telephone);
        eleUtil.doSendKeys(this.password, password);
        eleUtil.doSendKeys(this.confirmpassword, password);

        if(subscribe.equalsIgnoreCase("Yes")){
            eleUtil.doClick(subscribeYes);}
        else {
            eleUtil.doClick(subscribeNo);
        }
        eleUtil.doClick(agreeCheckBox);
        eleUtil.doClick(continueButton);

        String successMsg = eleUtil.waitForElementVisible(successMessg, AppConstants.DEFAULT_MEDIUM_WAIT).getText();
        System.out.println(successMsg);

        if (successMsg.contains(AppConstants.USER_REGISTER_SUCCESS_MESSG)) {
            eleUtil.doClick(logoutLink);
            eleUtil.doClick(registerLink);
            return true;
        } else {
            return false;
        }
    }
}