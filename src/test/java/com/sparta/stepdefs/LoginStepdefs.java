package com.sparta.stepdefs;

import com.sparta.lib.pages.LoginPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginStepdefs implements StepDefable {
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
    public void setUp() {
        webDriver = new RemoteWebDriver(service.getUrl(), getChromeOptions());
    }

    @After
    public void afterEach() {
        webDriver.quit();
    }

    @AfterAll
    public static void afterAll() {
        service.stop();
    }


    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() {
        webDriver.get("https://www.saucedemo.com/v1/index.html");
    }


    @When("the user enters a valid username and password")
    public void theUserEntersAValidUsernameAndPassword(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        Map<String, String> row = data.getFirst();

        LoginPage page = new LoginPage(webDriver);

        page.enterUsername(row.get("username"));
        page.enterPassword(row.get("password"));
    }

    @And("clicks the login button")
    public void clicksTheLoginButton() {
        LoginPage page = new LoginPage(webDriver);
        page.clickSignIn();
    }

    @Then("the user should be redirected to the Product page")
    public void theUserShouldBeRedirectedToTheProductPage() {
        assertEquals("https://www.saucedemo.com/v1/inventory.html", webDriver.getCurrentUrl());
    }


    @When("the user enters an invalid username or password")
    public void theUserEntersAnInvalidUsernameOrPassword(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        Map<String, String> row = data.getFirst();

        LoginPage page = new LoginPage(webDriver);

        page.enterUsername(row.get("username"));
        page.enterPassword(row.get("password"));
    }

    @Then("the user should see an error message {string}")
    public void theUserShouldSeeAnErrorMessage(String arg0) {
        LoginPage page = new LoginPage(webDriver);
        assertTrue(webDriver.findElement(By.cssSelector("*[data-test=\"error\"]")).getText().contains("Epic sadface"));

    }


    @When("the user leaves the username field empty and enters a valid password")
    public void theUserLeavesTheUsernameFieldEmptyAndEntersAValidPassword() {
        LoginPage page = new LoginPage(webDriver);
        page.enterPassword("secret_sauce");
    }
}
