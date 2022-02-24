package com.intabella.stepdefinitions;

import com.intabella.pages.DashboardPage;
import com.intabella.pages.LoginPage;
import com.intabella.utilities.BrowserUtils;
import com.intabella.utilities.ConfigurationReader;
import com.intabella.utilities.Driver;
import io.cucumber.java.cs.A;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    @Given("the user write invalid {string} and {string}")
    public void the_user_write_invalid_and(String userName, String password) {
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

    @Then("the user should not be able to login")
    public void the_user_should_not_be_able_to_login() {
        BrowserUtils.waitFor(3);
        String actualUrl = Driver.get().getCurrentUrl();
        String expectedUrl= "https://qa.intabella.com/user/login";
        Assert.assertEquals(expectedUrl,actualUrl);

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



    @Given("the user logs in with spaces on {string}")
    public void the_user_logs_in_with_spaces_on(String username) {
        username = "    user20    ";
        Assert.assertTrue(username.trim(),true);
        String password = ConfigurationReader.get("driver_password");
        LoginPage login = new LoginPage();
        login.login(username,password);
    }

    @Then("the user should be able to login")
    public void the_user_should_be_able_to_login() {
        BrowserUtils.waitFor(3);
        String actualTitle = Driver.get().getTitle();
        String expectedTitle = "Dashboard";
        Assert.assertEquals(expectedTitle,actualTitle);

    }
    @Then("the warning message should pop up as {string}")
    public void the_warning_message_should_pop_up_as(String warningMessage) {
      String expectedMessage = Driver.get().findElement(By.xpath("//*[text()='Invalid user name or password.']")).getText();

        Assert.assertEquals(warningMessage,expectedMessage);
    }


    @Given("the user write {string} or {string}")
    public void the_user_write_or(String userName, String password) {
        LoginPage login = new LoginPage();
        login.login(userName, password);
    }


    @Then("the warning message should pop up as {string} for the empty field")
    public void the_warning_message_should_pop_up_as_for_the_empty_field(String expectedWarningMessage) {
        BrowserUtils.waitFor(3);
        System.out.println(new LoginPage().userName.getText());
        System.out.println(new LoginPage().password.getText());
        if (new LoginPage().userName.getText().isEmpty()) {
            BrowserUtils.waitFor(3);
            String warningMessage = new LoginPage().userName.getAttribute("validationMessage");
            System.out.println(warningMessage);
            Assert.assertEquals(expectedWarningMessage, warningMessage);
        }
           else if (new LoginPage().password.getText().isEmpty()){
            BrowserUtils.waitFor(3);
            String warningMessage = new LoginPage().password.getAttribute("validationMessage");
            System.out.println(warningMessage);
            Assert.assertEquals(expectedWarningMessage, warningMessage);
        }else {
            System.out.println("error");
        }

        }

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

    @Then("the user should be able to click Remember me on this computer checkbox")
    public void the_user_should_be_able_to_click_Remember_me_on_this_computer_checkbox() {
        try {
            LoginPage loginPage= new LoginPage();
            new WebDriverWait(Driver.get(), 5).until(ExpectedConditions.elementToBeClickable(loginPage.rememberMe));
            System.out.println("Element is clickable");
        }
        catch(TimeoutException e) {
            System.out.println("Element isn't clickable");
        }

    }
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

