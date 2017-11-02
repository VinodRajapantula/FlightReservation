package com.flightsearch.tests;

import com.flightsearch.decorator.Browser;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 * Created by Vinod_Rajapantula on 6/15/2017.
 */
public class BaseTest {

    @BeforeClass
    public void launchBrowser() throws InterruptedException {
        Browser.createBrowser(BrowserType.CHROME);
    }

    @AfterClass
    public void quitBrowser() {
        if (Browser.getCurrent() != null) {
            Browser.getCurrent().quit();
        }

    }
}
