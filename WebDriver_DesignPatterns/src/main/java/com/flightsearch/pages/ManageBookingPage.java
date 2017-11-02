package com.flightsearch.pages;

import com.flightsearch.decorator.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;


public class ManageBookingPage {

    private static final String XPATH_FLYING_OUT = "//*[@class='flight-direction']/*[@class='icon-flight-out']";
    private static final String XPATH_FLIGHT_PRICE = "//*[text()='Flight Only']/ancestor::li/*[@class='this-flight-price']";
    private static final String XPATH_FLIGHT_DATE = "//*[@class='flight']/ancestor::div/h3";
    private static final String XPATH_DEPARTURE_ARRIVAL_CONSOLE = "//*[text()='Flight Only']/ancestor::label/following-sibling::div";
    private final Browser browser;

    public ManageBookingPage(WebDriver browser) {
        this.browser = (Browser) browser;
        PageFactory.initElements(browser, this);
    }

    @FindBy(xpath = XPATH_FLYING_OUT)
    private WebElement flyingOut;
    @FindBy(xpath = XPATH_FLIGHT_PRICE)
    private WebElement flightPrice;
    @FindBy(xpath = XPATH_FLIGHT_DATE)
    private WebElement flightDate;
    @FindBy(xpath = XPATH_DEPARTURE_ARRIVAL_CONSOLE)
    private WebElement departureArrivalConsole;

    public boolean isPageContainsFlyingOut(String expString) {
        String flyingOutText = flyingOut.getText();
        System.out.println("flyingOutText: " + flyingOutText);
        return flyingOutText.contains(expString);
    }

    public boolean isDirectionCorrect(String expDirectioin) {
        String directioinText = flyingOut.getText();
        return directioinText.contains(expDirectioin);
    }

    public boolean isFlightDateCorrect(String expFlightDate) {
        String flightDateText = flightDate.getText();
        System.out.println("flightDateText: " + flightDateText);
        return flightDateText.contains(expFlightDate);
    }

    public boolean isFlightPriceCorrect() throws ParseException {
        Number number = null;
        boolean priceMatch;
        String flightPriceText = flightPrice.getText();
        System.out.println("flightPriceText: " + flightPriceText);
        number = NumberFormat.getCurrencyInstance(Locale.UK).parse(flightPriceText);
        priceMatch = number != null;
        System.out.println("Price match : " + priceMatch);
        return priceMatch;
    }

    public boolean isDepartureArrivalConsoleCorrect(String expInfo) {
        String consoleText = departureArrivalConsole.getText();
        System.out.println("DepartureArrivalConsoleText: " + consoleText);
        return consoleText.contains(expInfo);
    }

}
