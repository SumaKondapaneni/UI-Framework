package pages;

/**
 * @author Suma Kondapaneni
 * @created 13 Jul 2025
 */


import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import base.PageBase;

import java.time.Duration;

public class BookingConfirmationPage extends PageBase {

    @FindBy(xpath = "//h2[contains(text(), 'Booking Confirmed')]")
    private WebElement confirmationHeader;

    @FindBy(xpath = "//p[contains(text(),'Your booking has been confirmed for the following dates')]")
    private WebElement confirmationMessage;

    @FindBy(xpath = "//a[contains(text(), 'Return home')]")
    private WebElement returnHomeButton;

    public BookingConfirmationPage(WebDriver driver) {
        super(driver);
     
    }

    public boolean isBookingConfirmed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            wait.until(ExpectedConditions.visibilityOf(confirmationHeader));
            return confirmationMessage.isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    public HomePage clickReturnHome() {
        returnHomeButton.click();
        return new HomePage(driver);
    }
}

