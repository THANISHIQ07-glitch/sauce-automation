Feature: SauceDemo Login

  Scenario: Login with valid credentials
    Given user is on the SauceDemo login page
    When user enters valid username and password
    And user clicks on the login button
    Then user should be logged in successfully

  Scenario: Login with locked out user
    Given user is on the SauceDemo login page
    When user enters locked out username and valid password
    And user clicks on the login button
    Then user should see login error message

  Scenario: Verify login for all users from Excel
    Given user is on the SauceDemo login page
    When user verifies login for all Excel users