package com.sauce.stepdefinitions;

import org.testng.Assert;

import com.sauce.base.Base;
import com.sauce.pages.LoginPage;
import com.sauce.utiliese.ExcelUtils;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps extends Base {
	
	LoginPage page;
		
	@Given("user is on the SauceDemo login page")
	public void user_is_on_the_sauce_demo_login_page() {
	    page=new LoginPage(driver);
	}

	@When("user enters valid username and password")
    public void user_enters_valid_username_and_password() {
    	page.userNameField(prop.getProperty("app.username"));
    	page.passwordField(prop.getProperty("app.password"));
	}

	@Then("user should be logged in successfully")
	public void user_should_be_logged_in_successfully() {
		Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"), "Login was not successful");

	}
	
	@When("user enters locked out username and valid password")
	public void user_enters_locked_out_username_and_valid_password() {
	    page.userNameField(prop.getProperty("app.lockeruser"));
	    page.passwordField(prop.getProperty("app.password"));
	}
	
	@Then("user should see login error message")
	public void user_should_see_login_error_message() {
	  Assert.assertEquals("Epic sadface: Sorry, this user has been locked out.", page.errormessge());
	}
	
	@When("login with locker user")
	public void login_with_locked_user() {
		page.userNameField(prop.getProperty("app.lockeruser"));
		page.passwordField(prop.getProperty("app.password"));
	}
	
	@Then("locked error message")
	public void locker_error_message() {
		Assert.assertEquals(page.lockederror(), "Epic sadface: Sorry, this user has been locked out.");
	}
	
	@And("user clicks on the login button")
	public void user_clicks_on_the_login_button() {
	    page.loginButton();
	}
	
	@Then("login result should be verified")
	public void login_result_should_be_verified() {
	    Assert.assertTrue(
	        driver.getCurrentUrl().contains("inventory.html"),
	        "Login was not successful"
	    );
	}
	
	@When("user verifies login for all Excel users")
	public void user_verifies_login_for_all_excel_users() {
	    int rowCount = ExcelUtils.getRowCount();

	    for (int row = 1; row <= rowCount; row++) {

	        String username = ExcelUtils.getCellData(row, "UserName");
	        String password = ExcelUtils.getCellData(row, "Password");

	        driver.get(prop.getProperty("app.url"));

	        page = new LoginPage(driver);

	        page.userNameField(username);
	        page.passwordField(password);
	        page.loginButton();

	        if (driver.getCurrentUrl().contains("inventory.html")) {
	            System.out.println("Login successful: " + username);
	        } else {
	            System.out.println("Login rejected: " + username);
	        }
	    }
	}
}
