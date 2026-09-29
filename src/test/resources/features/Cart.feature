Feature: SauceDemo Cart

  Scenario: Verify product in cart
    Given user is logged in successfully
    When user adds backpack to cart
    And user opens the cart
    Then cart should contain one product

  Scenario: Verify product details in cart
    Given user is logged in successfully
    When user adds backpack to cart
    And user opens the cart
    Then product name should be "Sauce Labs Backpack"
    And product price should be "$29.99"

  Scenario: Remove product from cart
    Given user is logged in successfully
    When user adds backpack to cart
    And user opens the cart
    And user removes the backpack
    Then cart should be empty

  Scenario: Proceed to checkout
    Given user is logged in successfully
    When user adds backpack to cart
    And user opens the cart
    And user proceeds to checkout
    Then checkout page should be displayed