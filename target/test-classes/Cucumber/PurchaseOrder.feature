
@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file

 Background:
 Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Purchase Order Successful submission
    Given I logged with <Username> and <Password>
    When I add the <ProductName> to Cart
    And I Checkout <ProductName> and Submit the Order
    Then "THANKYOU FOR THE ORDER." message is displayed on the Confirmation Page

    Examples: 
      | Username               | Password    | ProductName     |
      | gowthampsg98@gmail.com | Selenium@24 | ADIDAS ORIGINAL |
     
     
     
     
     
     
     
     
     