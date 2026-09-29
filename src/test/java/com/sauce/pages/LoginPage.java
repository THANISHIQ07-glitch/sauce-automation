package com.sauce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	@FindBy(id="user-name")
	WebElement userName;
	
	@FindBy(id="password")
	WebElement password;
	
	@FindBy(id="login-button")
	WebElement login;
	
	@FindBy(xpath="//h3")
	WebElement errormessage;
	
	@FindBy(xpath="//h3")
	WebElement lockederrorfield;
	
	public void userNameField(String usernamefield) {
		userName.sendKeys(usernamefield);
	}
	
	public void passwordField(String passworfField) {
		password.sendKeys(passworfField);
	}
	
	public void loginButton() {
		login.click();
	}
	
	public String errormessge() {
		return errormessage.getText();
	}
	
	public String lockederror() {
		return lockederrorfield.getText();
	}
}
