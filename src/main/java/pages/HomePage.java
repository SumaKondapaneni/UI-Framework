package pages;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PageBase;
/**
 * @author Suma Kondapaneni
 * @created 12 Jul 2025
 */

public class HomePage extends PageBase {

    // Locators
    @FindBy(id = "name")
    private WebElement nameInput;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "phone")
    private WebElement phoneInput;

    @FindBy(css = "[type='submit']")
    private WebElement submitBtn;

    @FindBy(xpath = "//*[@id='navbarNav']/ul/li//a[text()='Admin']")
    private WebElement adminLink;

    @FindBy(xpath = "//*[@id='booking']//label[contains(text(),'Check In')]/following-sibling::div//input")
    private WebElement checkinInput;

    @FindBy(xpath = "//*[@id='booking']//label[contains(text(),'Check Out')]/following-sibling::div//input")
    private WebElement checkoutInput;

    @FindBy(xpath = "//button[contains(text(), 'Check Availability')]")
    private WebElement checkAvailabilityButton;

    public HomePage(WebDriver driver) {
        super(driver);
       
    }

    By roomCards = By.xpath("//*[@id='rooms']//div[contains(@class,'room-card')]");
    By roomLinks = By.xpath("//*[@id='rooms']//div[contains(@class,'room-card')]//a");

    public void checkAvailability(String checkinDate, String checkoutDate) throws ParseException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");

        String checkin = outputFormat.format(inputFormat.parse(checkinDate));
        String checkout = outputFormat.format(inputFormat.parse(checkoutDate));

        setInputDate(checkinInput, checkin);
        setInputDate(checkoutInput, checkout);

        checkAvailabilityButton.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(roomCards));
    }

    public ReservationPage clickFirstRoomImage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(roomLinks));

        List<WebElement> rooms = driver.findElements(roomLinks);

        if (!rooms.isEmpty()) {
            WebElement firstRoom = rooms.get(0);
            try {
                scrollToElement(firstRoom);
                wait.until(ExpectedConditions.elementToBeClickable(firstRoom));
                firstRoom.click();
            } catch (ElementClickInterceptedException | StaleElementReferenceException e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstRoom);
            }
        } else {
            throw new NoSuchElementException("No room image links found.");
        }
		return new ReservationPage(driver);
    }

    public void fillContactForm(String name, String email, String phone) {
        nameInput.clear();
        nameInput.sendKeys(name);

        emailInput.clear();
        emailInput.sendKeys(email);

        phoneInput.clear();
        phoneInput.sendKeys(phone);

        submitBtn.click();
    }

    public AdminLoginPage clickAdmin() {
        adminLink.click();
        return new AdminLoginPage(driver);
    }

    public void setInputDate(WebElement element, String date) {
        try {
            element.click();
            element.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
            element.sendKeys(date);
            element.sendKeys(Keys.TAB);

            if (!date.equals(element.getAttribute("value"))) {
                throw new Exception("Date not updated properly");
            }
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + date + "';", element);
        }
    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center', behavior: 'instant'});", element);
    }

    public boolean isNoRoomsAvailable() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(), 'No rooms available') or contains(text(),'No availability')]")
            ));
            return message.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

	public boolean isSearchStatePreserved() {
		// TODO Auto-generated method stub
		return false;
	}
}