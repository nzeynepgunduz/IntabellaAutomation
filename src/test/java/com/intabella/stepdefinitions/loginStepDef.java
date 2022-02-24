package com.intabella.stepdefinitions;
import com.intabella.pages.DashboardPage;
import com.intabella.pages.LoginPage;
import com.intabella.utilities.BrowserUtils;
import com.intabella.utilities.ConfigurationReader;
import com.intabella.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import java.util.ArrayList;

public class loginStepDef {

    String copiedUrl = null;

    @When("the {string} is on the login page")
    public void the_is_on_the_login_page(String userType) {
        Driver.get().get(ConfigurationReader.get("url"));
        BrowserUtils.waitFor(3);

    }
    @Given("the user write {string} and {string}")
    public void the_user_write_and(String userName, String password) {
        new LoginPage().login(userName,password);

    }

    @Then("the user should be able to login and the title should be {string}")
    public void the_user_should_be_able_to_login_and_the_title_should_be(String pageTitle) {
        BrowserUtils.waitFor(3);
        String actualTitle = Driver.get().findElement(By.cssSelector(".oro-subtitle")).getText();
        Assert.assertEquals(pageTitle,actualTitle);
    }

    @When("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        Driver.get().get(ConfigurationReader.get("url"));
        BrowserUtils.waitFor(3);
    }

    @Given("the user logs in with {string} and {string}")
    public void the_user_logs_in_with_and(String username, String password) {
        username= ConfigurationReader.get("driver_username");
        password = ConfigurationReader.get("driver_password");
        new LoginPage().login(username,password);
    }

    @Given("the user copies the url and logs out")
    public void the_user_copies_the_url_and_logs_out() {
        copiedUrl = Driver.get().getCurrentUrl();
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.logOut();
    }

    @Then("the user should not be able to login with pasting the url")
    public void the_user_should_not_be_able_to_login_with_pasting_the_url() {

        Driver.get().navigate().to(copiedUrl);
        String expectedTitle = "Login";
        String actualTitle = Driver.get().getTitle();
        Assert.assertEquals(expectedTitle,actualTitle);
    }



    @Then("the user should be able to login by copying url without logging out, opening a new tab and pasting the url.")
    public void the_user_should_be_able_to_login_by_copying_url_without_logging_out_opening_a_new_tab_and_pasting_the_url() {
            String firstPage = Driver.get().getCurrentUrl();
            System.out.println("firstPage = " + firstPage);

            JavascriptExecutor jse = (JavascriptExecutor)Driver.get();
            jse.executeScript("window.open()");
            BrowserUtils.waitFor(3);

            ArrayList<String> tabs = new ArrayList<String>(Driver.get().getWindowHandles());
            Driver.get().switchTo().window(tabs.get(0)).close();
            BrowserUtils.waitFor(3);
            Driver.get().switchTo().window(tabs.get(1));
            Driver.get().get(firstPage);
            BrowserUtils.waitFor(3);

            String newTab = Driver.get().getCurrentUrl();
            System.out.println("newTab = " + newTab);
            BrowserUtils.waitFor(3);

            Assert.assertEquals(firstPage,newTab);
            BrowserUtils.waitFor(3);
        }

    @Then("the user should be able to login")
    public void the_user_should_be_able_to_login() {
        BrowserUtils.waitFor(3);
        String actualTitle = Driver.get().getTitle();
        String expectedTitle = "Dashboard";
        Assert.assertEquals(expectedTitle,actualTitle);

    }

    @Given("the user write {string} or {string}")
    public void the_user_write_or(String userName, String password) {
        LoginPage login = new LoginPage();
        login.login(userName, password);
    }










}

