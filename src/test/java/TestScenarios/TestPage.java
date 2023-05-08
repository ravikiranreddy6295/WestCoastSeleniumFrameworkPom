package TestScenarios;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PageClass.FlightsPage;
import PageClass.HomePage;
import PageClass.YourFlightToNewYorkPage;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import io.github.bonigarcia.wdm.WebDriverManager;

public class TestPage {

	WebDriver driver;
	
    @BeforeTest
    public void setup(){

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://flights.booking.com/");
    }
    
    @Test
    public void flightBooking() {

    	HomePage home=new HomePage(driver);
        //selecting London as source
    	home.source();
    	//Selecting New york as destination
    	home.destination();
        //Selecting Start date and end date
    	home.startEndDate();
    	//search
    	home.search();
    	
    	FlightsPage flight=new FlightsPage(driver);
    	//select fastest
    	flight.fastest();
    	//select see flight button
    	flight.seeFlight();
    	
    	YourFlightToNewYorkPage yourFlight=new YourFlightToNewYorkPage(driver);
    	//verify start date
    	yourFlight.verifyStartDate();
    	//verify return date
    	yourFlight.verifyReturnDate();
    	//verify flight time
    	yourFlight.verifyFlightTime();
    	//verify flight price
    	yourFlight.verifyPrice();
    	//select
    	yourFlight.select();
    	//verify price in London to newyork page
    	yourFlight.verifyPriceLondonToNewyork();

    }
    
    @AfterTest
    public void tearDown() {
    	driver.quit();
    }
}
