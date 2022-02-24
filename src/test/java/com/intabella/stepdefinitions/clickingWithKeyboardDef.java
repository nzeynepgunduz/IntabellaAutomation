package com.intabella.stepdefinitions;

import com.intabella.pages.LoginPage;
import com.intabella.utilities.BrowserUtils;
import com.intabella.utilities.ConfigurationReader;
import com.intabella.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class clickingWithKeyboardDef {

    @Given("the user write a valid {string} and click ENTER")
    public void the_user_write_a_valid_and_click_ENTER(String userName) {
        userName = ConfigurationReader.get("salesmanager_username");
        LoginPage login= new LoginPage();
        login.userName.sendKeys(userName);
        login.userName.sendKeys(Keys.ENTER);
        BrowserUtils.waitFor(2);

    }

    @Then("the user should be on password input box, write a valid {string}")
    public void the_user_should_be_on_password_input_box_write_a_valid(String password) {
        password = ConfigurationReader.get("salesmanager_password");
        LoginPage login = new LoginPage();
        login.password.sendKeys(password);
        login.password.sendKeys(Keys.ENTER);


    }

    @Then("the login button should be clicked by hitting the ENTER button")
    public void the_login_button_should_be_clicked_by_hitting_the_ENTER_button() {
        String pageTitle = "Dashboard";
        String actualTitle = Driver.get().findElement(By.cssSelector(".oro-subtitle")).getText();
        Assert.assertEquals(pageTitle,actualTitle);
    }


    @Given("the user write a valid {string} and click TAB")
    public void the_user_write_a_valid_and_click_TAB(String userName) {
        userName = ConfigurationReader.get("salesmanager_username");
        LoginPage login= new LoginPage();
        login.userName.sendKeys(userName);
        login.userName.sendKeys(Keys.TAB);
        BrowserUtils.waitFor(2);

    }

}
