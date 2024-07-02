package com.sparta.lib.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductsPage extends WebPage{
    public ProductsPage(WebDriver webDriver) {
        super(webDriver);
    }

    private WebElement getInventoryItem(String productName) {
        String xpath = String.format("//div[@class=\"inventory_item\" and .//div[@class=\"inventory_item_name\" and text()=\"%s\"]]", productName);
        By selector = By.xpath(xpath);
        return webDriver.findElement(selector);
    }

    public void addToCart(String productName) {
        var inventoryItem = getInventoryItem(productName);
        var addToCart = inventoryItem.findElement(By.className("btn_primary"));
        addToCart.click();
    }

    public void removeFromCart(String productName) {
        var inventoryItem = getInventoryItem(productName);
        var addToCart = inventoryItem.findElement(By.className("btn_secondary"));
        addToCart.click();
    }

    public String getProductButtonText(String productName) {
        var inventoryItem = getInventoryItem(productName);
        var productButton = inventoryItem.findElement(By.className("btn_inventory"));
        return productButton.getText();
    }

    public int getCartIconCount() {
        By selector = By.className("shopping_cart_badge");
        var wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(selector));
            var cartCount = webDriver.findElement(selector);
            return Integer.parseInt(cartCount.getText());
        } catch (TimeoutException e) {
            return 0;
        }
    }
}
