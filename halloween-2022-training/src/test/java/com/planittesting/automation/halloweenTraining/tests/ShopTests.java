package com.planittesting.automation.halloweenTraining.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.planittesting.automation.halloweenTraining.model.pages.HomePage;

public class ShopTests extends BaseTests {
    @Test
    void validateProductPrice() {
        var price = open(HomePage.class)
            .clickShopMenu()
            .getProduct(p -> p.getTitle().equals("poodle"))
            .getPrice();
        assertEquals(30, price);
    }

    @Test
    void validateProductTitle() {
        var title = open(HomePage.class)
            .clickShopMenu()
            .getProduct(p -> p.getPrice()==13.99)
            .getTitle();
        assertEquals("Smiley Bear", title); 
    }

    @Test
    void validateProductStars() {
        var title = open(HomePage.class)
            .clickShopMenu()
            .getProduct(p -> p.getStars()==5)
            .getTitle();
        assertEquals("Stuffed Frog", title);
    }

   
}
