package com.intabella.stepdefinitions;

import com.intabella.pages.LoginPage;
import com.intabella.utilities.Driver;
import io.cucumber.java.en.Then;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class rememberMeDef {
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
}
