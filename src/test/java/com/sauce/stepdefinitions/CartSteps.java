package com.sauce.stepdefinitions;

import java.time.Duration;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.sauce.base.Base;
import com.sauce.pages.CartPage;
import com.sauce.pages.InventoryPage;
import com.sauce.pages.LoginPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CartSteps extends Base {

    LoginPage loginPage;
    CartPage cartPage;
    
    @When("user opens the cart")
    public void user_opens_the_cart() {
    	cartPage = new CartPage(getDriver());
        cartPage.clickShoppingCart();
    }
    
    @Then("cart should contain one product")
    public void cart_should_contain_one_product() {

        Assert.assertEquals(cartPage.getCartItemCount(), 1,"Cart should contain one product");
    }

    @Then("product name should be {string}")
    public void product_name_should_be(String expectedName) {

        Assert.assertEquals(cartPage.getProductName(),expectedName);
    }

    @Then("product price should be {string}")
    public void product_price_should_be(String expectedPrice) {

        Assert.assertEquals(cartPage.getProductPrice(),expectedPrice);
    }

    @Then("cart should be empty")
    public void cart_should_be_empty() {

        Assert.assertEquals( cartPage.getCartItemCount(), 0, "Cart is not empty");
    }

    @When("user proceeds to checkout")
    public void user_proceeds_to_checkout() {

        cartPage.clickCheckout();
    }

    @Then("checkout page should be displayed")
    public void checkout_page_should_be_displayed() throws InterruptedException {
    	
        Assert.assertTrue( getDriver().getCurrentUrl().contains("checkout-step-one.html"),"Checkout page is not displayed");
    }
    
    @Then("user removes the backpack")
    public void user_removes_the_backpack() {
    	cartPage.removeBackpack();
    }
}
