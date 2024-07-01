Feature: Checkout

As a user, I want to be able to checkout out of the website I want so that I can purchase the products.

Scenario: Testing using valid credentials
Given I am on the cart page
And There are items in my basket
When I click on the checkout button
And I input valid credentials for delivery
And I click on the finish button
Then A successful message for the purchase should be displayed on the screen.

Scenario: Testing using invalid credentials
Given I am on the cart page
And There are items in my basket
When I click on the checkout button
And I input invalid credentials for delivery (i.e invalid postcode)
And I click on the finish button
Then An error message should be displayed on the screen

Scenario:  Testing using no items in basket
Given I am on the cart page
And There are no items in my basket
When I click on the checkout button
And I input valid credentials for delivery
And I click on the finish button
Then An error message should be displayed