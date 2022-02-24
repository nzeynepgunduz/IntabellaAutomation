package com.intabella.stepdefinitions;

import com.intabella.pages.LoginPage;
import com.intabella.utilities.BrowserUtils;
import com.intabella.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class invalidCredentialsDef {

    @Given("the user write invalid {string} and {string}")
    public void the_user_write_invalid_and(String userName, String password) {
        new LoginPage().login(userName,password);

    }
    @Then("the user should not be able to login")
    public void the_user_should_not_be_able_to_login() {
        BrowserUtils.waitFor(3);
        String actualUrl = Driver.get().getCurrentUrl();
        String expectedUrl= "https://qa.intabella.com/user/login";
        Assert.assertEquals(expectedUrl,actualUrl);

    }
}
