Feature: Customers

Background: Below are the common steps for each scenario
	Given User launch chrome browser
  When User opens URL "http://admin-demo.nopcommerce.com/login"
  And User enters Email as "admin@yourstore.com" and Password as "admin"
  And Click on Login
  Then User can view Dashboard

@sanity
Scenario: Add a new Customer
    When User clicks on Customers menu
    And click on customers Menu Item
    And click on Add new button
    Then User can view Add new customer page
    When user enter customer info
    And Click on save button
    Then user can view confirmation message "The new customer has been added successfully."
    And close browser

@regression       
Scenario: Search Customer by EmailID
    When User clicks on Customers menu
    And click on customers Menu Item
    And Enter search details as email "" first name "Victoria" last name "Terces"
    When Click on search button
    Then User should found details in the Search table
    When User click on Log out link
    Then Page Title should be "Your store. Login"
    And close browser