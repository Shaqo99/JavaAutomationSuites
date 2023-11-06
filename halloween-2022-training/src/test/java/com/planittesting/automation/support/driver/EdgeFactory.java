package com.planittesting.automation.support.driver;

    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.edge.EdgeDriver;
    import org.openqa.selenium.edge.EdgeOptions;

    public class EdgeFactory extends DriverFactory{

        @Override
        public WebDriver getDriver() {
            return new EdgeDriver(getCapabilities());
        }

        @Override
        public EdgeOptions getCapabilities() {
            var options = new EdgeOptions();
            options.setHeadless(isHeadless);
            options.addArguments("--disable-gpu",
                    "--window-size=1920,1200",
                    "--ignore-certificate-errors");
            return options;
        }
        
    }
