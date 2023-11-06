package com.planittesting.automation.support.driver;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.reflections.Reflections;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;

public abstract class DriverFactory {
    protected boolean isHeadless = true;
    private Optional<URL> gridUrl = Optional.ofNullable(null);

    public abstract WebDriver getDriver();
    public abstract Capabilities getCapabilities();

    public DriverFactory withHeadless(boolean headless) {
        this.isHeadless = headless;
        return this;
    }

    public DriverFactory withGridUrl(String gridUrl) throws Exception {
        URL url = gridUrl != null && !gridUrl.isEmpty()? new URL(gridUrl) : null;
        this.gridUrl = Optional.ofNullable(url);
        return this;
    }

    public WebDriver build() throws Exception {
        var driver = gridUrl
            .map(url -> (WebDriver)new RemoteWebDriver(url, getCapabilities()))
            .orElseGet(() -> getDriver());
        return new Augmenter().augment(driver);
    }

    public static DriverFactory getFactory(String browser) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, RuntimeException {
        var driverClasses = new ArrayList<>(
                new Reflections(DriverFactory.class.getPackageName()).getSubTypesOf(DriverFactory.class));
        return driverClasses.stream()
            .filter(driverClass -> driverClass.getSimpleName().toLowerCase().contains(browser.toLowerCase()))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Browser: '" + browser + "'' is not a supported browser"))
            .getConstructor()
            .newInstance();
    }

    public static void setupDriverManager(String browser) {
        Stream.of(DriverManagerType.values())
            .filter(driverManagerType -> driverManagerType.getBrowserName().equalsIgnoreCase(browser))
            .findFirst()
            .ifPresent(driverManagerType -> {
                try{WebDriverManager.getInstance(driverManagerType).setup();} catch (Exception e){}
            });
    }
}
