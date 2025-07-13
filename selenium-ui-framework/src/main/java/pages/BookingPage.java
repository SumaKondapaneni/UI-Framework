package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PageBase;

/**
 * @author Suma Kondapaneni
 * @created 13 Jul 2025
 */



public class BookingPage extends PageBase {


	

    public BookingPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath="//input[@name='firstname']")
    WebElement firstName;

    @FindBy(xpath="//input[@name='lastname']")
    WebElement lastName;

    @FindBy(xpath="//input[@name='email']")
    WebElement email;

    @FindBy(xpath="//input[@name='phone']")
    WebElement phone;


    @FindBy(xpath="//Button[contains(text(),'Reserve Now')]")
    WebElement reserveNow;

    @FindBy(css = ".row .col-sm-4")
    List<WebElement> bookings;

  

    public HomePage createBooking(String fname, String lname, String mail, String phoneNum) {
        firstName.sendKeys(fname);
        lastName.sendKeys(lname);
        email.sendKeys(mail);
        phone.sendKeys(phoneNum);
        reserveNow.click();
        BookingConfirmationPage confirmpage  = new BookingConfirmationPage(driver);
        if(confirmpage.isBookingConfirmed()) {
        	confirmpage.clickReturnHome();
        	
        }
		return new HomePage(driver);
        
    }

    public boolean isBookingPresent(String name) {
        return bookings.stream().anyMatch(e -> e.getText().contains(name));
    }
    
    public boolean isValidationMessageDisplayed() {
        try {
            // Try catching common validation message elements (e.g., spans or divs near input fields)
            List<WebElement> validationMessages = driver.findElements(By.cssSelector(".invalid-feedback, .error, .validation-message"));

            for (WebElement message : validationMessages) {
                if (message.isDisplayed()) {
                    return true;
                }
            }

 
        } catch (Exception e) {
            return false;
        }
		return false;
    }

    public void cancelBooking() {
        WebElement cancelBtn = driver.findElement(By.xpath("//button[contains(text(),'Cancel')]"));
        
        // Scroll if necessary
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cancelBtn);

        cancelBtn.click();

    }



    public boolean isValidationMessageDisplayed(String messageText) {
        try {
            WebElement validation = driver.findElement(By.xpath("//div[contains(@class,'alert')]//li[contains(text(),'" + messageText + "')]"));
            return validation.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void clickSubmitWithoutFilling() {
    	reserveNow.click(); 
    }
}


