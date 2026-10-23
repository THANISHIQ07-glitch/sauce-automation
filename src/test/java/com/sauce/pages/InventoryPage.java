package com.sauce.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InventoryPage {

    WebDriver driver;
   WebDriverWait wait;

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
        this.wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    
    public void waitForPageLoad() {
        wait.until(ExpectedConditions.urlContains("inventory.html"));
        wait.until(ExpectedConditions.visibilityOf(pageTitle));
    }

    public String getPageTitle() {
        return pageTitle.getText();
    }

    public int getProductCount() {
        return products.size();
    }

    public void addBackpack() {
       wait.until(ExpectedConditions.elementToBeClickable(backpack)).click();
    }

    public void removeBackpack() {
        wait.until(ExpectedConditions.elementToBeClickable(removeBackpack)).click();
    }

    public String getCartCount() {
        return cartBadge.getText();
    }
}