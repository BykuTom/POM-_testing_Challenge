Feature: User Login

  As a registered user
  I want to log in to the application
  So that I can access my personalized dashboard and use the application’s features

Scenario: Successful login with valid credentials
Given the user is on the Login page
When the user enters a valid username and password
And clicks the login button
Then the user should be redirected to the Product page


  Scenario: Unsuccessful login with invalid credentials
    Given the user is on the Login page
    When the user enters an invalid username or password
    And clicks the login button
    Then the user should see an error message "Epic sadface: Username and password do not match any user in this service"
    And the user should remain on the Login page

  Scenario: Unsuccessful login with only password entered
    Given the user is on the Login page
    When the user leaves the username field empty and enters a valid password
    And clicks the login button
    Then the user should see an error message "Epic sadface: Username is required"
    And the user should remain on the Login page