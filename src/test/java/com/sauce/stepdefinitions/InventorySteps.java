package com.sauce.stepdefinitions;

import org.testng.Assert;

import com.sauce.base.Base;
import com.sauce.pages.InventoryPage;
import com.sauce.pages.LoginPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class InventorySteps extends Base {

    LoginPage loginPage;
    InventoryPage inventoryPage;

    @Given("user is logged in successfully")
    public void user_is_logged_in_successfully() {

        loginPage = new LoginPage(driver);

        loginPage.userNameField(prop.getProperty("app.username"));
        loginPage.passwordField(prop.getProperty("app.password"));
        loginPage.loginButton();

        inventoryPage = new InventoryPage(driver);
    }

    @Then("inventory page should be displayed")
    public void inventory_page_should_be_displayed() {

        Assert.assertTrue(
            driver.getCurrentUrl().contains("inventory.html"),
            "Inventory page is not displayed"
        );
    }

    @Then("products should be displayed")
    public void products_should_be_displayed() {

        Assert.assertTrue(
            inventoryPage.getProductCount() > 0,
            "No products are displayed"
        );
    }

    @When("user adds backpack to cart")
    public void user_adds_backpack_to_cart() {
        inventoryPage.addBackpack();
    }

    @Then("cart count should be {string}")
    public void cart_count_should_be(String expectedCount) {

        Assert.assertEquals(
            inventoryPage.getCartCount(),
            expectedCount
        );
    }

    @When("user removes backpack from cart")
    public void user_removes_backpack_from_cart() {
        inventoryPage.removeBackpack();
    }
    
    @Then("cart badge should not be displayed")
    public void cart_badge_should_not_be_displayed() {

        Assert.assertTrue(
            driver.findElements(
                org.openqa.selenium.By.className("shopping_cart_badge")
            ).isEmpty(),
            "Cart badge is still displayed"
        );
    }

}