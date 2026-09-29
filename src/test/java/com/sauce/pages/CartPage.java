package com.sauce.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

    WebDriver driver;

    @FindBy(className = "cart_item")
    List<WebElement> cartItems;

    @FindBy(className = "inventory_item_name")
    WebElement productName;

    @FindBy(className = "inventory_item_price")
    WebElement productPrice;

    @FindBy(id = "remove-sauce-labs-backpack")
    WebElement removeBackpack;

    @FindBy(id = "checkout")
    WebElement checkout;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public int getCartItemCount() {

        System.out.println("Current URL: " + driver.getCurrentUrl());

        System.out.println(
            "Cart items found: " +
            driver.findElements(By.className("cart_item")).size()
        );

        System.out.println(
            "Backpack button count: " +
            driver.findElements(By.id("remove-sauce-labs-backpack")).size()
        );

        return driver.findElements(By.className("cart_item")).size();
    }

    public String getProductName() {
        return productName.getText();
    }

    public String getProductPrice() {
        return productPrice.getText();
    }

    public void removeBackpack() {
        removeBackpack.click();
    }

    public void clickCheckout() {

        System.out.println("Before clicking checkout");
        System.out.println("Current URL: " + driver.getCurrentUrl());

        int checkoutCount = driver.findElements(By.id("checkout")).size();

        System.out.println("Checkout button count: " + checkoutCount);

        if (checkoutCount > 0) {
            System.out.println("Checkout button found");
            driver.findElement(By.id("checkout")).click();
            System.out.println("Checkout button clicked");
        } else {
            System.out.println("Checkout button NOT found");
        }
    }
}
