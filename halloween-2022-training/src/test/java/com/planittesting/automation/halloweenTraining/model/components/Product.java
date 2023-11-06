package com.planittesting.automation.halloweenTraining.model.components;

import java.util.stream.IntStream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.planittesting.automation.halloweenTraining.model.pages.ShopPage;

public class Product {
    private WebElement rootElement;
    private ShopPage parent;

    public Product(WebElement rootElement, ShopPage parent) {
        this.rootElement = rootElement;
        this.parent = parent;
    }

    public String getTitle() {
        return rootElement.findElement(By.className("product-title")).getText();
    }

    public double getPrice() {
        var priceAsString = rootElement.findElement(By.className("product-price")).getText().replace("$", "");
        return Double.parseDouble(priceAsString);
    }

    public int getStars() {
        return Integer.parseInt(rootElement.findElement(By.className("star-level")).getText());
    }

    public ShopPage clickBuyButton(int times) {
        var element = rootElement.findElement(By.className("btn"));
        IntStream.range(0, times).forEach(i -> element.click());
        return parent;
    }

    public ShopPage clickBuyButton() {
        return clickBuyButton(1);
    }
}
