package com.sparta.stepdefs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

public interface StepDefable {

    ChromeDriverService service = null;
    String DRIVER_LOCATION = "src/test/resources/chromedriver.exe";
    WebDriver webDriver = null;

    default ChromeOptions getChromeOptions(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        //options.addArguments("--headless");
        options.addArguments("--remote-allow-origins=*");
        return options;
    }
}