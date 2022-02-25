package com.intabella.stepdefinitions;

import com.intabella.pages.LoginPage;
import com.intabella.utilities.BrowserUtils;
import com.intabella.utilities.Driver;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;

public class warningMessageDef {
    @Then("the warning message should pop up as {string}")
    public void the_warning_message_should_pop_up_as(String warningMessage) {
        String expectedMessage = Driver.get().findElement(By.xpath("//*[text()='Invalid user name or password.']")).getText();

        Assert.assertEquals(warningMessage,expectedMessage);
    }

    @Then("the warning message should pop up as {string} for the empty field")
    public void the_warning_message_should_pop_up_as_for_the_empty_field(String expectedWarningMessage) {
        BrowserUtils.waitFor(3);
        String warningMessage="";
        if (new LoginPage().userName.getAttribute("value").equals("")) {
            warningMessage = new LoginPage().userName.getAttribute("validationMessage");

        } else if (new LoginPage().password.getAttribute("value").equals("")){
            warningMessage = new LoginPage().password.getAttribute("validationMessage");

        }else {
            System.out.println("error");
        }

        Assert.assertEquals(expectedWarningMessage, warningMessage);
    }
}
