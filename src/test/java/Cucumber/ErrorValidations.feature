
@tag
Feature: Title of your feature
  I want to use this template for my feature file

  
  @ErrorValidation
  Scenario Outline: Title of your scenario outline
    Given I landed on Ecommerce Page
    When I logged with <username> and <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | username               | password    | 
      | gowthampsg98@gmail.com | Selenium24  | 
      
