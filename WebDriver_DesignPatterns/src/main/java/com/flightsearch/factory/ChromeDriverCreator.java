package com.flightsearch.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ChromeDriverCreator implements IBrowserCreator {
    private static final int IMPLICIT_TIMEOUT = 10000;
    private static final int PAGE_LOAD_TIMEOUT = 30000;
    private String chromeDriverPath;

    public ChromeDriverCreator(String driverPath) {
        this.chromeDriverPath = driverPath;
    }

    public WebDriver create() {
        System.setProperty("webdriver.chrome.driver", this.chromeDriverPath);
        WebDriver driver =  new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(IMPLICIT_TIMEOUT, TimeUnit.MILLISECONDS);
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.MILLISECONDS);
        return driver;
    }
}

