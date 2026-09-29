Feature: SauceDemo Inventory

  Scenario: Verify inventory page and products
    Given user is logged in successfully
    Then inventory page should be displayed
    And products should be displayed

  Scenario: Add product to cart
    Given user is logged in successfully
    When user adds backpack to cart
    Then cart count should be "1"

  Scenario: Remove product from cart
    Given user is logged in successfully
    When user adds backpack to cart
    And user removes backpack from cart
    Then cart badge should not be displayed
