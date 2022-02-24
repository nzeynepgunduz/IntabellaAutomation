package com.intabella.stepdefinitions;

import com.intabella.pages.LoginPage;
import com.intabella.utilities.BrowserUtils;
import com.intabella.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.Keys;

public class passwordInputBoxDef {
    @Given("the user write any password")
    public void the_user_write_any_password() {
        LoginPage login = new LoginPage();
        login.password.sendKeys("abcdefg");
    }

    @Then("the password should not be visible as letters")
    public void the_password_should_not_be_visible_as_letters() {
        Assert.assertTrue(new LoginPage().password.getAttribute("type").equals("password"));

    }

    @Then("the password should not be visible in page source")
    public void the_password_should_not_be_visible_in_page_source() {
        String pageSource = Driver.get().getPageSource();
        Assert.assertFalse(pageSource.contains("abcdefg"));

    }
    @Then("the password should not be possible to copy")
    public void the_password_should_not_be_possible_to_copy() {
        LoginPage login = new LoginPage();
        login.password.sendKeys(Keys.CONTROL + "a");
        BrowserUtils.waitFor(3);
        login.password.sendKeys(Keys.CONTROL + "c");
        login.userName.click();
        BrowserUtils.waitFor(3);
        login.userName.sendKeys(Keys.CONTROL+"v");
        BrowserUtils.waitFor(3);
        String actualusername = login.userName.getText();
        Assert.assertTrue(actualusername.isEmpty());

    }


}
