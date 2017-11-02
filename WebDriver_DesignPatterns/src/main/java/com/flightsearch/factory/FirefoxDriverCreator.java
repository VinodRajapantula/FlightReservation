package com.flightsearch.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;


public class FirefoxDriverCreator implements IBrowserCreator {
    private static final int IMPLICIT_TIMEOUT = 10000;
    private static final int PAGE_LOAD_TIMEOUT = 30000;
    private String firefoxDriverPath;

    public FirefoxDriverCreator(String driverPath) {
        this.firefoxDriverPath = driverPath;
    }

    public WebDriver create() {
        System.setProperty("webdriver.gecho.driver", firefoxDriverPath);
        WebDriver driver =  new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(IMPLICIT_TIMEOUT, TimeUnit.MILLISECONDS);
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.MILLISECONDS);
        return driver;
    }
}
