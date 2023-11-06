package com.planittesting.automation.support.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxFactory extends DriverFactory {

    @Override
    public WebDriver getDriver() {
        return new FirefoxDriver(getCapabilities());
    }

    @Override
    public FirefoxOptions getCapabilities() {
        var options = new FirefoxOptions();
        options.setHeadless(isHeadless);
        return options;
    }
    
}
