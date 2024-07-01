package com.sparta.lib.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public abstract class WebPage {
    protected WebDriver webDriver;
    protected Actions actions;

    public WebPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.actions = new Actions(this.webDriver);
    }

    public String getTitle() {
        return webDriver.getTitle();
    }

    public String getUrl() {
        return webDriver.getCurrentUrl();
    }

    public void refresh(){
        webDriver.navigate().refresh();
    }

    public void waitFor(int timeoutInSeconds) throws InterruptedException {
        Thread.sleep(timeoutInSeconds * 1000);
    }
    
}
