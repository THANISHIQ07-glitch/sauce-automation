package com.sauce.stepdefinitions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.sauce.base.Base;
import com.sauce.pages.CheckoutPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CheckoutSteps extends Base {

    CheckoutPage checkoutPage;

    @When("user enters checkout details")
    public void user_enters_checkout_details() {

        checkoutPage = new CheckoutPage(driver);

        checkoutPage.enterFirstName("Thanish");
        checkoutPage.enterLastName("Tester");
        checkoutPage.enterPostalCode("636001");
    }

    @And("user continues to checkout overview")
    public void user_continues_to_checkout_overview() {

        checkoutPage.clickContinue();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("checkout-step-two.html"),
                "Checkout overview page is not displayed"
        );
    }

    @And("user completes the order")
    public void user_completes_the_order() {

        checkoutPage.clickFinish();
    }

    public String getCompleteMessage() {

        System.out.println("Current URL: " + driver.getCurrentUrl());

        System.out.println("Page title: " + driver.getTitle());

        System.out.println(
            "Complete header count: " +
            driver.findElements(By.className("complete-header")).size()
        );

        return driver.findElement(By.className("complete-header")).getText();
    }
  }
