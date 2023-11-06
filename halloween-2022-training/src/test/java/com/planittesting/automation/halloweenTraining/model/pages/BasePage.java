package com.planittesting.automation.halloweenTraining.model.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.planittesting.automation.halloweenTraining.model.components.dialogs.LoginDialog;
import com.planittesting.automation.halloweenTraining.model.components.dialogs.LogoutDialog;
import com.planittesting.automation.halloweenTraining.model.data.LoginData;

public abstract class BasePage<T> {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public ContactPage clickContactMenu() {
        driver.findElement(By.id("menu-contact")).click();
        return new ContactPage(driver);
    }

    public ShopPage clickShopMenu() {
        driver.findElement(By.id("menu-shop")).click();
        return new ShopPage(driver);
    }

    public CartPage clickCartMenu() {
        driver.findElement(By.id("menu-contact")).click();
        return new CartPage(driver);
    }

    @SuppressWarnings("unchecked")
    public LoginDialog<T> clickLoginMenu() {
        driver.findElement(By.id("menu-login")).click();
        var rootElement = driver.findElement(By.id("login-modal"));
        return new LoginDialog<T>(rootElement, (T)this);
    }

    public LogoutDialog clickLogoutMenu() {
        driver.findElement(By.id("menu-logout")).click();
        var rootElement = driver.findElement(By.id("logout-modal"));
        return new LogoutDialog(rootElement);
    }

    public T login(LoginData data) {
        return clickLoginMenu()
            .setUsername(data.username())
            .setPassword(data.password())
            .clickAgreeTermsCheckbox()
            .clickLoginButton();
    }

    public String getUser() {
        var elements = driver.findElements(By.cssSelector("#login-greeting"));
        if (elements.isEmpty())
            return "";
        return elements.get(0).getText();
         
    }
}
