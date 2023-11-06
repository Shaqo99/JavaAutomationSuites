package com.planittesting.automation.halloweenTraining.model.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.planittesting.automation.halloweenTraining.model.components.ui.Table;

public class CartPage extends BasePage<CartPage> {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public double getPrice(String title) {
        var priceAsString = getTable().getCell("Item", title, "Price").getText().replace("$", "");
        return Double.parseDouble(priceAsString);
    }


    public int getQuantity(String title) {
        var quantityCell = getTable().getCell("Item", title, "Quantity");
        var quantityValueAsString = quantityCell.findElement(By.tagName("input")).getAttribute("value");
        return Integer.parseInt(quantityValueAsString);
    }

    public double getSubtotal(String title) {
        var subtotalAsString = getTable().getCell("Item", title, "Subtotal").getText().replace("$", "");
        return Double.parseDouble(subtotalAsString);
    }

    public double getTotal() {
        var totalAsString = driver.findElement(By.className("total")).getText().replace("Total: ", "");
        return Double.parseDouble(totalAsString);
    }

    private Table getTable() {
        return new Table(driver.findElement(By.className("cart-items")));
    }

    

   


    
}
