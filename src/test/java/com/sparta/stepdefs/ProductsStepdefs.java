package com.sparta.stepdefs;

import com.sparta.lib.pages.CheckoutPage;
import com.sparta.lib.pages.LoginPage;
import com.sparta.lib.pages.ProductsPage;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class ProductsStepdefs implements StepDefable{
    private String[] productNames;
    private LoginPage loginPage;
    private ProductsPage productsPage;
    private CheckoutPage checkoutPage;

    static ChromeDriverService service;
    WebDriver webDriver;

    @BeforeAll
    public static void beforeAll() throws IOException {
        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(DRIVER_LOCATION))
                .usingAnyFreePort()
                .build();
        service.start();
    }

    @Before
    public void setUp(){
        webDriver = new RemoteWebDriver(service.getUrl(), getChromeOptions());
    }

    @After
    public void afterEach(){
        webDriver.quit();
    }

    @AfterAll
    public static void afterAll() {
        service.stop();
    }

    @Given("I am logged in as a {string}")
    public void iAmLoggedInAsA(String userType) {
        webDriver.get("https://www.saucedemo.com/v1/");
        loginPage = new LoginPage(webDriver);
        loginPage.enterUsername(userType);
        loginPage.enterPassword("secret_sauce");
        loginPage.clickSignIn();
        productsPage = new ProductsPage(webDriver);
    }

    @When("I click add to cart button for the products {string}")
    public void iClickAddToCartButtonForTheProducts(String productList) {

        productNames = productList.split(",");
        for (String name : productNames) {
            productsPage.addToCart(name);
        }
    }

    @Then("the buttons text switches to REMOVE")
    public void theButtonsTextSwitchesToREMOVE() {
        for (String name : productNames) {
            String buttonText = productsPage.getProductButtonText(name);
            assertThat(buttonText, is("REMOVE"));
        }
    }

    @And("the cart icon count becomes {int}")
    public void theCartIconCountBecomes(int count) {
        int cartCount = productsPage.getCartIconCount();
        assertThat(cartCount, is(count));
    }

    @And("the products appear on the cart page")
    public void theProductsAppearOnTheCartPage() {
        webDriver.get("https://www.saucedemo.com/v1/cart.html");
        checkoutPage = new CheckoutPage(webDriver);
        for (String name : productNames) {
            assertThat(checkoutPage.productIsInCart(name), is(true));
        }
    }
}
