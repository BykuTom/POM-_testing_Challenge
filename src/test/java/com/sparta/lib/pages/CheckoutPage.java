package com.sparta.lib.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends WebPage{
    public CheckoutPage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean productIsInCart(String productName) {
        String xpath = String.format("//div[@class=\"inventory_item_name\" and text()=\"%s\"]", productName);
        By selector = By.xpath(xpath);
        try {
            webDriver.findElement(selector);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
