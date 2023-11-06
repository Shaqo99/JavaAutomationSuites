package com.planittesting.automation.halloweenTraining.model.components.dialogs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsDriver;

import com.planittesting.automation.halloweenTraining.model.pages.HomePage;

public class LogoutDialog {
    private WebElement rootElement;

    public LogoutDialog(WebElement rootElement) {
        this.rootElement = rootElement;
    }

    public HomePage clickLogoutButton() {
        rootElement.findElement(By.id("modal-yes-button")).click();
        var driver = ((WrapsDriver)rootElement).getWrappedDriver();
        return new HomePage(driver);
    }
}
