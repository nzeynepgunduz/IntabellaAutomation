package com.intabella.stepdefinitions;

import com.intabella.pages.LoginPage;
import com.intabella.utilities.BrowserUtils;
import com.intabella.utilities.Driver;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class forgotPasswordDef {

    @Then("the user lands on Forgot Password page after clicking Forgot your password? link")
    public void the_user_lands_on_Forgot_Password_page_after_clicking_Forgot_your_password_link() {
        LoginPage login= new LoginPage();
        login.forgotPassword.click();
        BrowserUtils.waitFor(2);
        String actualUrl = Driver.get().getCurrentUrl();
        String expectedUrl = "https://qa.intabella.com/user/reset-request";
        Assert.assertEquals(expectedUrl,actualUrl);
    }


    @Then("the user can change their password by clicking REQUEST button.")
    public void the_user_can_change_their_password_by_clicking_REQUEST_button() {
        LoginPage login = new LoginPage();
        login.userName.sendKeys("user10");
        WebElement requestButton = Driver.get().findElement(By.xpath("//button"));
        requestButton.click();
        BrowserUtils.waitFor(2);
        WebElement warningMessage = Driver.get().findElement(By.cssSelector(".alert"));
        Assert.assertEquals("Email successfully sent",warningMessage.getText());

    }
}
