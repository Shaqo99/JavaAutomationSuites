package com.planittesting.automation.halloweenTraining.model.pages;

import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.planittesting.automation.halloweenTraining.model.components.Product;

public class ShopPage extends BasePage<ShopPage> {

    public ShopPage(WebDriver driver) {
        super(driver);
        
    }

    public Product getProduct(Function<Product, Boolean> condition) {
        var elements = driver.findElements(By.className("product"));
        return elements.stream()
            .map(e -> new Product(e, this))
            .filter(p -> condition.apply(p))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Product not found"));
    }
}
