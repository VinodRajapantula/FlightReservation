package com.flightsearch.decorator;

import com.flightsearch.factory.ChromeDriverCreator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

public class Browser implements WebDriver, WrapsDriver {
    private WebDriver driver;
    private static final int MIN_TIME_OUT_IN_SECONDS = 60;
    public static final String PATH_OF_CHROME_WEBDRIVER = "D:\\Vinod\\Training\\Tools\\chromedriver.exe";
    public static final String PATH_OF_FIREFOX_WEBDRIVER = "D:\\Vinod\\Training\\Tools\\geckodriver.exe";
    private static Browser browser;

    public Browser(WebDriver driver) {
        this.driver = driver;
    }

    public static void createBrowser(String bowserType){
        WebDriver driver = null;
        if (BrowserType.CHROME.equals(bowserType)) {
             driver = new  ChromeDriverCreator(PATH_OF_CHROME_WEBDRIVER).create();
        } else if (BrowserType.FIREFOX.equals(bowserType)) {
           driver = new ChromeDriverCreator(PATH_OF_FIREFOX_WEBDRIVER).create();
        }
        browser = new Browser(driver);
    }

    public static Browser getCurrent() {
        if(browser == null) {
            throw new RuntimeException("Browser have not opened");
        }
        return browser;
    }

    public void waitForElementClickable(WebElement object) {
        WebDriverWait wait = new WebDriverWait(driver, MIN_TIME_OUT_IN_SECONDS);
        wait.until(ExpectedConditions.elementToBeClickable(object));
    }

    public void waitForElementNotVisible(WebElement object) {
        WebDriverWait wait = new WebDriverWait(driver, MIN_TIME_OUT_IN_SECONDS);
        wait.until(ExpectedConditions.invisibilityOf(object));
    }

    public void waitForElementVisible(WebElement object) {
        WebDriverWait wait = new WebDriverWait(driver, MIN_TIME_OUT_IN_SECONDS);
        wait.until(ExpectedConditions.visibilityOf(object));
    }

    public void get(String s) {
        System.out.println("Accessing the URL :" + s);
        driver.get(s);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public List<WebElement> findElements(By by) {
        return driver.findElements(by);
    }

    public WebElement findElement(By by) {
        System.out.println("Finding the object");
        return driver.findElement(by);
    }

    public String getPageSource() {
        return driver.getPageSource();
    }

    public void close() {
        driver.close();
    }

    public void quit() {
        System.out.println("Closing the browser");
        driver.quit();
    }

    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    public TargetLocator switchTo() {
        return driver.switchTo();
    }

    public Navigation navigate() {
        return driver.navigate();
    }

    public Options manage() {
        return driver.manage();
    }

    public WebDriver getWrappedDriver() {
        return driver;
    }
}
