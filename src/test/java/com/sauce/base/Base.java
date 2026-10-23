package com.sauce.base;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/** Shared scenario browser and configuration support. */
public class Base {

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();
    private static final Properties PROPERTIES = loadProperties();

    protected WebDriver getDriver() {
        WebDriver currentDriver = DRIVER.get();
        if (currentDriver == null) {
            throw new IllegalStateException("WebDriver has not been started for this scenario.");
        }
        return currentDriver;
    }

    protected String getProperty(String key) {
        String value = System.getProperty(key);
        if (value == null || value.isBlank()) {
            value = PROPERTIES.getProperty(key);
        }
        if (value == null || value.isBlank()) {
            throw new IllegalStateException("Required configuration property is missing: " + key);
        }
        return value.trim();
    }

    public void setup() {
        if (DRIVER.get() != null) {
            throw new IllegalStateException("A WebDriver is already active for this scenario.");
        }

        String browser = getProperty("browser").toLowerCase(Locale.ROOT);
        boolean headless = Boolean.parseBoolean(getProperty("headless"));
        WebDriver newDriver;
        try {
            newDriver = createDriver(browser, headless);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("Could not start browser '" + browser
                    + "'. Check that the browser is installed and supported by Selenium.", exception);
        }
        DRIVER.set(newDriver);

        try {
            newDriver.manage().window().maximize();
            newDriver.get(getProperty("app.url"));
        } catch (RuntimeException exception) {
            quitDriver();
            throw new IllegalStateException("Could not open the application using browser '" + browser + "'.", exception);
        }
    }

    public void quitDriver() {
        WebDriver currentDriver = DRIVER.get();
        try {
            if (currentDriver != null) {
                currentDriver.quit();
            }
        } finally {
            DRIVER.remove();
        }
    }

    private static WebDriver createDriver(String browser, boolean headless) {
        switch (browser) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                if (headless) {
                    chromeOptions.addArguments("--headless=new");
                }
                return new ChromeDriver(chromeOptions);
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (headless) {
                    firefoxOptions.addArguments("-headless");
                }
                return new FirefoxDriver(firefoxOptions);
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                if (headless) {
                    edgeOptions.addArguments("--headless=new");
                }
                return new EdgeDriver(edgeOptions);
            default:
                throw new IllegalArgumentException("Unsupported browser '" + browser + "'. Use chrome, firefox, or edge.");
        }
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream input = Base.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new IllegalStateException("config.properties was not found on the test classpath.");
            }
            properties.load(input);
            return properties;
        } catch (IOException exception) {
            throw new ExceptionInInitializerError(exception);
        }
    }
}
