package com.sauce.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryPage {

    WebDriver driver;

    @FindBy(className = "title")
    WebElement pageTitle;

    @FindBy(className = "inventory_item")
    List<WebElement> products;

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    WebElement backpack;

    @FindBy(id = "remove-sauce-labs-backpack")
    WebElement removeBackpack;

    @FindBy(className = "shopping_cart_badge")
    WebElement cartBadge;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getPageTitle() {
        return pageTitle.getText();
    }

    public int getProductCount() {
        return products.size();
    }

    public void addBackpack() {
        backpack.click();
    }

    public void removeBackpack() {
        removeBackpack.click();
    }

    public String getCartCount() {
        return cartBadge.getText();
    }
}