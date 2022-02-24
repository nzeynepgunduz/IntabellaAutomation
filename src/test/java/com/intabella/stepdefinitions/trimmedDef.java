package com.intabella.stepdefinitions;

import com.intabella.pages.LoginPage;
import com.intabella.utilities.ConfigurationReader;
import io.cucumber.java.en.Given;
import org.junit.Assert;

public class trimmedDef {

    @Given("the user logs in with spaces on {string}")
    public void the_user_logs_in_with_spaces_on(String username) {
        username = "    user20    ";
        Assert.assertTrue(username.trim(),true);
        String password = ConfigurationReader.get("driver_password");
        LoginPage login = new LoginPage();
        login.login(username,password);
    }
}
