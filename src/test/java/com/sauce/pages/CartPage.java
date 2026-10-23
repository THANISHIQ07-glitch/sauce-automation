package com.sauce.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

    WebDriver driver;
    
    @FindBy(className = "shopping_cart_link")
    WebElement shppingCart;

    @FindBy(className = "cart_item")
    List<WebElement> cartItems;
    
    @FindBy(id="add-to-cart-sauce-labs-backpack")
    WebElement addBackpack;

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
    
    public void clickShoppingCart() {
    	shppingCart.click();
    }

    public int getCartItemCount() {
        return cartItems.size();
    }

    public String getProductName() {
        return productName.getText();
    }

    public String getProductPrice() {
        return productPrice.getText();
    }
    
    public void addBackpack() {
    	addBackpack.click();
    }

    public void removeBackpack() {
        removeBackpack.click();
    }

    public void clickCheckout() {
        checkout.click();
    }
}
