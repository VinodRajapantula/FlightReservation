package com.flightsearch.tests;


import com.flightsearch.decorator.Browser;
import com.flightsearch.models.FlightBuilder;
import com.flightsearch.models.FlightModel;
import com.flightsearch.pages.FlightSearchPage;
import com.flightsearch.pages.ManageBookingPage;
import org.openqa.selenium.remote.BrowserType;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.remote.BrowserType;


import java.text.ParseException;

public class FlightSearchTest extends BaseTest {
    private static final String MONARCH_SITE_URL = "http://www.monarch.co.uk/";
    private static final String EXP_FLYING_OUT_MESSAGE = "Flying Out";
    private static final String EXP_FLIGHT_DIRECTION = "London (All) to Barcelona";
    //private static final String EXP_FLIGHT_DATE = "Wed, 14 Jun 17";
    private static final String EXP_FLIGHT_DATE = "Wed, 21 Jun 17";
    private static final String EXP_CONSOLE_INFO_ORIGIN = "13:25 London Gatwick";
    private static final String EXP_CONSOLE_INFO_DESTINATION = "16:40 Barcelona";
    private static final String EXP_PAGE_TITLE = "Monarch";
    private static final String ERROR_MESSAGE_UNABLE_TO_OPEN_MONARCH_SITE = "Unable to open Monarch site";
    private static final String ERROR_MESSAGE_FLYING_OUT_PAGE_NOT_OPENED = "Flying Out page not opened";
    private static final String ERROR_MESSAGE_DIRECTION_IS_NOT_CORRECT = "Direction is not correct";
    private static final String ERROR_MESSAGE_FLIGHT_DATE_IS_NOT_CORRECT = "Flight date is not correct";
    private static final String ERROR_MESSAGE_FLIGHT_PRICE_NOT_IN_UK_CURRENCY_FORMAT = "Flight Price not in UK currency format";
    private static final String ERROR_MESSAGE_ORIGIN_CONSOLE_INFO_IS_NOT_CORRECT = "Origin information in Console is not correct";
    private static final String ERROR_MESSAGE_DESTINATION_CONSOLE_INFO_IS_NOT_CORRECT = "Destination information in Console is not correct";
    //Input values
    private static final String INPUT_ORIGIN_CITY = "London (All)";
    private static final String INPUT_DESTINATION_CITY = "Barcelona";
    //private static final String INPUT_TRIP_TYPE = "One-Way";
    private static final String INPUT_TRAVEL_DATE = "21";
    private static final String INPUT_NUMBER_OF_ADULTS = "2";
    private static final String INPUT_NUMBER_OF_CHILDREN = "2";
    private static final String INPUT_NUMBER_OF_INFANTS = "1";

    FlightModel flightModel = FlightBuilder.buildOneWayFlight();



    private FlightSearchPage flightSearchPage;
    private ManageBookingPage manageBookingPage;
    //private SoftAssert s_assert;

    @Test(description = "Opening firefox browser and accessing Monarch site")
    public void openMonarchSite() throws InterruptedException {
        String actPageTitle;
        //s_assert = new SoftAssert();
        flightSearchPage = new FlightSearchPage();
        flightSearchPage.openMonarchSite(MONARCH_SITE_URL);
        actPageTitle = flightSearchPage.getPageTitle();
        Assert.assertTrue(actPageTitle.contains(EXP_PAGE_TITLE), ERROR_MESSAGE_UNABLE_TO_OPEN_MONARCH_SITE);
    }

    @Test(description = "Entering Origin and Destination", dependsOnMethods = "openMonarchSite")
    public void enterTravelSourceAndDestination() {
        //flightSearchPage.selectOriginAndDestination(INPUT_ORIGIN_CITY, INPUT_DESTINATION_CITY);
        flightSearchPage.selectOriginAndDestination(INPUT_ORIGIN_CITY, INPUT_DESTINATION_CITY);
    }

    @Test(description = "Entering Trip type and Travel date", dependsOnMethods = "enterTravelSourceAndDestination")
    public void enterTripTypeAndDateOfTravel() throws InterruptedException {
        //flightSearchPage.selectTripType(INPUT_TRIP_TYPE);
        flightSearchPage.selectTripType(flightModel.getType().getValue());
        flightSearchPage.selectDateOfTravel(INPUT_TRAVEL_DATE);
    }

    @Test(description = "Eenter number of Adults, children and infants", dependsOnMethods = "enterTripTypeAndDateOfTravel")
    public void passengerDetails() {
        flightSearchPage.selectNumberOfAdultsChildsAndInfants(INPUT_NUMBER_OF_ADULTS, INPUT_NUMBER_OF_CHILDREN, INPUT_NUMBER_OF_INFANTS);
    }

    @Test(description = "Validate Flying Out text", dependsOnMethods = "passengerDetails")
    public void searchForFlight() throws InterruptedException {
        manageBookingPage = flightSearchPage.findFlight();
    }

    @Test(description = "Validate Flying Out text", dependsOnMethods = "searchForFlight")
    public void validateFlyingOut() {
        Assert.assertTrue(manageBookingPage.isPageContainsFlyingOut(EXP_FLYING_OUT_MESSAGE), ERROR_MESSAGE_FLYING_OUT_PAGE_NOT_OPENED);
    }

    @Test(description = "Validate Flight Direction text", dependsOnMethods = "validateFlyingOut")
    public void validateFlightDirection() {
        Assert.assertTrue(manageBookingPage.isDirectionCorrect(EXP_FLIGHT_DIRECTION), ERROR_MESSAGE_DIRECTION_IS_NOT_CORRECT);
    }

    @Test(description = "Validate Flight date", dependsOnMethods = "validateFlightDirection")
    public void validateFlightDate() {
        Assert.assertTrue(manageBookingPage.isFlightDateCorrect(EXP_FLIGHT_DATE), ERROR_MESSAGE_FLIGHT_DATE_IS_NOT_CORRECT);
    }

    @Test(description = "Validate Flight price is in UK currency format", dependsOnMethods = "validateFlightDate")
    public void validateFlightPrice() throws ParseException {
        Assert.assertTrue(manageBookingPage.isFlightPriceCorrect(), ERROR_MESSAGE_FLIGHT_PRICE_NOT_IN_UK_CURRENCY_FORMAT);
    }

    @Test(description = "Validate console text", dependsOnMethods = "validateFlightPrice")
    public void validateConsoleTextforOrigin() {
        Assert.assertTrue(manageBookingPage.isDepartureArrivalConsoleCorrect(EXP_CONSOLE_INFO_ORIGIN), ERROR_MESSAGE_ORIGIN_CONSOLE_INFO_IS_NOT_CORRECT);
    }

    @Test(description = "Validate console text", dependsOnMethods = "validateConsoleTextforOrigin")
    public void validateConsoleTextforDestination() {
        Assert.assertTrue(manageBookingPage.isDepartureArrivalConsoleCorrect(EXP_CONSOLE_INFO_DESTINATION), ERROR_MESSAGE_DESTINATION_CONSOLE_INFO_IS_NOT_CORRECT);
    }




}
