package com.sauce.stepdefinitions;

import java.time.Duration;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.sauce.base.Base;
import com.sauce.pages.CartPage;
import com.sauce.pages.InventoryPage;
import com.sauce.pages.LoginPage;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CartSteps extends Base {

    LoginPage loginPage;
    InventoryPage inventoryPage;
    CartPage cartPage;

    @When("user opens the cart")
    public void user_opens_the_cart() {

        driver.findElement(
                org.openqa.selenium.By.className("shopping_cart_link")
        ).click();

        cartPage = new CartPage(driver);
    }

    @Then("cart should contain one product")
    public void cart_should_contain_one_product() {

        Assert.assertEquals(
                cartPage.getCartItemCount(),
                1,
                "Cart should contain one product"
        );
    }

    @Then("product name should be {string}")
    public void product_name_should_be(String expectedName) {

        Assert.assertEquals(
                cartPage.getProductName(),
                expectedName
        );
    }

    @Then("product price should be {string}")
    public void product_price_should_be(String expectedPrice) {

        Assert.assertEquals(
                cartPage.getProductPrice(),
                expectedPrice
        );
    }

    @When("user removes the backpack")
    public void user_removes_the_backpack() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        cartPage.removeBackpack();
    }

    @Then("cart should be empty")
    public void cart_should_be_empty() {

        Assert.assertEquals(
                cartPage.getCartItemCount(),
                0,
                "Cart is not empty"
        );
    }

    @When("user proceeds to checkout")
    public void user_proceeds_to_checkout() {

        cartPage.clickCheckout();
    }

    @Then("checkout page should be displayed")
    public void checkout_page_should_be_displayed() {

        Assert.assertTrue(
                driver.getCurrentUrl().contains("checkout-step-one.html"),
                "Checkout page is not displayed"
        );
    }
}