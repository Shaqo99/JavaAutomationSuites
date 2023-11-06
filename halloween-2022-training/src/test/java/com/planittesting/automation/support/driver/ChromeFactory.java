package com.planittesting.automation.support.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeFactory extends DriverFactory {

    @Override
    public WebDriver getDriver() {
        return new ChromeDriver(getCapabilities());
    }

    @Override
    public ChromeOptions getCapabilities() {
        var options = new ChromeOptions();
        options.setHeadless(isHeadless);
        options.addArguments("--disable-gpu",
                "--window-size=1920,1200",
                "--ignore-certificate-errors");
        return options;
    }
    
}
