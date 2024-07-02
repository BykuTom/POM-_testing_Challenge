Feature: Adding Products to cart

As a user I need to be able to add products to the cart so I can save them for later purchase

Scenario: Adding products to cart as a standard user

Given I am logged in as a "standard_user"
When I click add to cart button for the products "Sauce Labs Backpack"
Then the buttons text switches to REMOVE
And the cart icon count becomes 1
And the products appear on the cart page

Scenario: Removing products from the cart

Given I am logged in as a [USER_TYPE]
And the products [PRODUCT_NAME_LIST] are in the cart
When I click the remove button for [REMOVED_PRODUCTS]
Then [REMOVED_PRODUCTS] are no longer present in cart
And the cart icon count becomes [NUMBER_OF_PRODUCTS]