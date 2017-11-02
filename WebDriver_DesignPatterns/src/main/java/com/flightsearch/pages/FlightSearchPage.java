package com.flightsearch.pages;

import com.flightsearch.decorator.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class FlightSearchPage {
    private static final int MIN_TIMEOUT = 2000;
    private static final int MAX_TIMEOUT = 5000;
    private static final String XPATH_ORIGIN_LIST = "//div[@class='location-select-holder clearfix departure-airport']//a[@data-name='OriginAirport']";
    private static final String XPATH_ORIGIN_CITY = "(//div[@class='location-select-holder clearfix departure-airport'])[1]//ul";
    private static final String XPATH_DESTINATION_LIST = "//a[@data-name='DestinationAirport']";
    private static final String XPATH_DESTINATION_CITY = "(//div[@class='location-select-holder clearfix destination-airport'])[1]//ul";
    private static final String XPATH_TRIP_TYPE = "//div[@class='return-flight btn-group btn-group-justified']";
    private static final String XPATH_DATE_PICKER = "//div[@class='date-holder departure-date']/button[@class='ui-datepicker-trigger']";
    private static final String XPATH_TRAVEL_DATE = "//span[text()='June']/../../following-sibling::table/tbody";
    private static final String XPATH_NUMBER_OF_ADULTS = "//div[@class='adults clearfix']";
    private static final String XPATH_CHILD_LIST = "//a[@data-name='Children']";
    private static final String XPATH_NUMBER_OF_CHILDREN = "//div[@class='half children']//ul";
    private static final String XPATH_INFANT_LIST = "//a[@data-name='Infants']";
    private static final String XPATH_NUMBER_OF_INFANTS = "//div[@class='half infants']//ul";
    private static final String XPATH_FIND_FLIGHT = "//div[@id='flights-container']//a[@class='button search-module-submit']";

    public FlightSearchPage() {
        PageFactory.initElements(Browser.getCurrent(), this);
    }

    @FindBy(xpath = XPATH_ORIGIN_LIST)
    private WebElement originList;
    @FindBy(xpath = XPATH_ORIGIN_CITY)
    private WebElement originCity;
    @FindBy(xpath = XPATH_DESTINATION_LIST)
    private WebElement destinationList;
    @FindBy(xpath = XPATH_DESTINATION_CITY)
    private WebElement destinationCity;
    @FindBy(xpath = XPATH_TRIP_TYPE)
    private WebElement tripType;
    @FindBy(xpath = XPATH_DATE_PICKER)
    private WebElement datePicker;
    @FindBy(xpath = XPATH_TRAVEL_DATE)
    private WebElement travelDate;
    @FindBy(xpath = XPATH_NUMBER_OF_ADULTS)
    private WebElement numberOfAdults;
    @FindBy(xpath = XPATH_CHILD_LIST)
    private WebElement childList;
    @FindBy(xpath = XPATH_NUMBER_OF_CHILDREN)
    private WebElement numberOfChildren;
    @FindBy(xpath = XPATH_INFANT_LIST)
    private WebElement infantList;
    @FindBy(xpath = XPATH_NUMBER_OF_INFANTS)
    private WebElement numberOfInfant;
    @FindBy(xpath = XPATH_FIND_FLIGHT)
    private WebElement btnFindFlights;

    public void openMonarchSite(String monarchSiteURL) {
        System.out.println("Start of openMonarchSite");
        Browser.getCurrent().manage().window().maximize();
        System.out.println("URL: " + monarchSiteURL);
        Browser.getCurrent().get(monarchSiteURL);
        System.out.println("Title:" + Browser.getCurrent().getTitle());
    }

    public String getPageTitle() {
        return Browser.getCurrent().getTitle();
    }

    public void selectOriginAndDestination(String cityOfOrigin, String cityOfDestination) {
        originList.click();
        originCity.findElement(By.linkText(cityOfOrigin)).click();
        destinationList.click();
        destinationCity.findElement(By.linkText(cityOfDestination)).click();
    }

    public void selectTripType(String typeOfTrip) {
        tripType.findElement(By.linkText(typeOfTrip)).click();
    }

    public void selectDateOfTravel(String dateOfTravel) throws InterruptedException {

        datePicker.click();
        WebElement travelDateObj = travelDate.findElement(By.linkText(dateOfTravel));
        Browser.getCurrent().waitForElementClickable(travelDateObj);
        travelDateObj.click();
        Browser.getCurrent().waitForElementNotVisible(travelDate);
        //Thread.sleep(MIN_TIMEOUT);
    }

    public void selectNumberOfAdultsChildsAndInfants(String adultCount, String childCount, String infantCount) {
        numberOfAdults.findElement(By.linkText(adultCount)).click();
        childList.click();
        numberOfChildren.findElement(By.linkText(childCount)).click();
        infantList.click();
        numberOfInfant.findElement(By.linkText(infantCount)).click();
    }

    public ManageBookingPage findFlight() throws InterruptedException {
        btnFindFlights.click();
        //Thread.sleep(MIN_TIMEOUT);
        //return PageFactory.initElements(browser, ManageBookingPage.class);
        return new ManageBookingPage(Browser.getCurrent());

    }
}
