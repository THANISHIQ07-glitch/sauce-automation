Feature: SauceDemo Checkout

  Scenario: Complete checkout successfully
    Given user is logged in successfully
    When user adds backpack to cart
    And user opens the cart
    And user proceeds to checkout
    Then checkout page should be displayed
    When user enters checkout details
    And user continues to checkout overview
    And user completes the order
    Then order confirmation should be displayed