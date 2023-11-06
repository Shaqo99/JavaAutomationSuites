package com.planittesting.automation.halloweenTraining.tests;

import java.lang.reflect.InvocationTargetException;
import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.WebDriver;

import com.planittesting.automation.support.driver.DriverFactory;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * Everything that is commong to all tests goes here
 */
@Execution(ExecutionMode.CONCURRENT)
public class BaseTests {
    private static Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
    protected WebDriver driver;

    @BeforeAll
    public static void setupDriverManager() {
        DriverFactory.setupDriverManager(dotenv.get("SELENIUM_BROWSER"));
    }

    @BeforeEach
    public void setup() throws Exception {
        var browser = dotenv.get("SELENIUM_BROWSER");
        var headless = Boolean.parseBoolean(dotenv.get("SELENIUM_HEADLESS", "true"));
        var gridUrl = dotenv.get("SELENIUM_GRID_URL");

        driver = DriverFactory.getFactory(browser).withHeadless(headless).withGridUrl(gridUrl).build();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(dotenv.get("SELENIUM_WAIT"))));
        driver.manage().window().maximize();
        driver.get(dotenv.get("SELENIUM_URL"));
    }

    @AfterEach
    public void shutdown() {
        driver.quit();
    }

    protected <T> T open(Class<T> page) {
        try {
            return page.getConstructor(WebDriver.class).newInstance(driver);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }
}
