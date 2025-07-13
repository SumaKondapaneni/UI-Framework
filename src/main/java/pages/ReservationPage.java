package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.PageBase;

/**
 * @author Suma Kondapaneni
 * @created 13 Jul 2025
 */

public class ReservationPage extends PageBase{

	public ReservationPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	// Locators
    @FindBy(xpath = "//button[@id='doReservation']")
    private WebElement reserveNow;
	
    
    
    public BookingPage clickReserve() {
    	reserveNow.click();
        return new BookingPage(driver);
    }

}


