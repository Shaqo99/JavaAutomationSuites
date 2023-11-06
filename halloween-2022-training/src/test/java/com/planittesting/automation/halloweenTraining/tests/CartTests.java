package com.planittesting.automation.halloweenTraining.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.planittesting.automation.halloweenTraining.model.pages.HomePage;

public class CartTests extends BaseTests {
    @Test
    void validateCartPrice() {
        var product = open(HomePage.class)
            .clickShopMenu()
            .getProduct(p -> p.getTitle().equals("poodle"));
        var price = product.getPrice();
        var cartPrice = product
            .clickBuyButton(3)
            .clickCartMenu()
            .getPrice("poodle");
        assertEquals(price, cartPrice);
    }

    @Test 
    void validateSubtotal(){
        var product = open(HomePage.class)
            .clickShopMenu()
            .getProduct(p -> p.getTitle().equals("Cheerful Capybara"));
  
        var cartPage = product
            .clickBuyButton(4)
            .clickCartMenu();

        var prodPrice = cartPage
            .getPrice("Cheerful Capybara");
        var quantity = cartPage
            .getQuantity("Cheerful Capybara");
        var subTotal = prodPrice * quantity;
        assertEquals(cartPage.getSubtotal("Cheerful Capybara"), subTotal);
    }

    @Test 
    void validateTotalSubtotal(){
        var shopPage = open(HomePage.class)
            .clickShopMenu()
            .getProduct(p -> p.getTitle().equals("poodle"))
            .clickBuyButton(3)
            .getProduct(p -> p.getTitle().equals("Cheerful Capybara"))
            .clickBuyButton(4)
            .getProduct(p -> p.getTitle().equals("Fluffy Cat"))
            .clickBuyButton(5);
        var cartPage = shopPage
            .clickCartMenu();
        
        var dollSubtotal = cartPage
            .getSubtotal("poodle");
        var bunnySubtotal = cartPage
            .getSubtotal("Cheerful Capybara");
        var bearSubtotal = cartPage
            .getSubtotal("Fluffy Cat");

        var totalAmount = dollSubtotal + bunnySubtotal + bearSubtotal;
        assertEquals(cartPage.getTotal(), totalAmount);
    }

}
