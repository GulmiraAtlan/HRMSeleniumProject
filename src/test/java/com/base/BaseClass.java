package com.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class BaseClass {
    protected WebDriver driver;

    // Method to initialize WebDriver based on the browser type
    public void setUp(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");;
            options.addArguments("--disable-lazy-loading");
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", "path/to/geckodriver");
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            System.setProperty("webdriver.edge.driver", "path/to/msedgedriver");
            driver = new EdgeDriver();
        } else {
            throw new IllegalArgumentException("Browser " + browser + " is not supported.");
        }

        // Set implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    // Method to close the browser

    public void tearDown() {
        if (driver != null) {
            try {
                getDriver().quit(); // Properly close the browser
            } catch (Exception e) {
                System.err.println("Error while closing the browser: " + e.getMessage());
            }
        }
    }

    // Getter for WebDriver
    public WebDriver getDriver() {
        return driver;
    }
}


