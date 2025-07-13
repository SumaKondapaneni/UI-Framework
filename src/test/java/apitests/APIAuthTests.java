package apitests;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import api.BookingAPI;
import api.RoomAPI;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.RestAssured;

import model.Booking;
import model.BookingDates;
import model.Room;
import utils.ConfigReader;
import utils.ExcelReader;

import static org.testng.Assert.assertNotNull;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Suma Kondapaneni
 * @created 12 Jul 2025
 */

@Epic("Automation In Testing API")
@Feature("Automation In Testing - Room Booking")
public class APIAuthTests {

	private WebDriver driver;

	private String token;
	 private List<Integer> createdRoomIds = new ArrayList<>();
	    private List<Integer> createdBookingIds = new ArrayList<>();


	@DataProvider(name = "roomData")
	public Object[][] roomDataProvider() throws Exception {
		return ExcelReader.readRoomData("src/test/resources/roomData.xlsx", "Rooms");
	}
	
	
	//@DataProvider(name = "bookingData")
	public Object[][] bookingDataProvider() throws Exception {
		return ExcelReader.readBookingData("src/test/resources/roomData.xlsx", "BookingInfo");
	}

	/*
	 * @BeforeClass public void setUp() { RestAssured.baseURI
	 * =ConfigReader.get("api.baseUrl");
	 * 
	 * 
	 * 
	 * String userid= ConfigReader.get("api.user"); String pass=
	 * ConfigReader.get("api.password");
	 * 
	 * String authPayload = """ { "username": "", "password": "" } """;
	 * 
	 * Response response = RestAssured .given() .contentType(ContentType.JSON)
	 * .body(authPayload) .post("/auth");
	 * 
	 * assertEquals(response.getStatusCode(), 200, "Auth failed");
	 * 
	 * token = response.getCookie("token"); assertNotNull(token, "Token is null");
	 * 
	 * 
	 * 
	 * }
	 */

	@BeforeClass
	public void loginAndExtractToken() {

		RestAssured.baseURI = ConfigReader.get("api.baseUrl");

		String url = ConfigReader.get("app.url");

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.get(url);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Wait until the "Admin" link is clickable and click it
		WebElement adminLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Admin")));
		adminLink.click();

		System.out.println("Clicked the Admin link successfully.");

		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("password")).sendKeys("password");
		driver.findElement(By.id("doLogin")).click();

		// Wait briefly for cookie to be set
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Extract 'token' cookie
		Cookie tokenCookie = driver.manage().getCookieNamed("token");
		assertNotNull(tokenCookie, "Token cookie should not be null");

		token = tokenCookie.getValue();
		System.out.println("Extracted token: " + token);

	}



	
	
	
	@Test(dataProvider="roomData")
	@Severity(SeverityLevel.CRITICAL)
	@Story("Create Rooms with Multiple Bookings")
    public void createMultipleRoomsAndBookings(String roomName, String roomType, boolean accessible, int price, String description,
			String[] features) {

		Object[][] bookings = null;
		try {
			bookings = this.bookingDataProvider();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
           

            // --- Create room
            Room roomPayload = new Room();
    		roomPayload.setRoomName(roomName);
    		roomPayload.setType(roomType);
    		roomPayload.setAccessible(accessible);
    		// roomPayload.setImage("https://via.placeholder.com/150"); optional
    		roomPayload.setDescription(description);
    		roomPayload.setFeatures(Arrays.asList(features));
    		roomPayload.setRoomPrice(price);
    		RoomAPI api = new RoomAPI();
    		api.createRoom(roomPayload, token); 
    		Room createdRoom =api.getRoomByName(roomName);
            int roomId = createdRoom.getRoomid();
            createdRoomIds.add(roomId);
            System.out.println("Created Room ID: " + createdRoomIds);

            // --- Create booking
            for (Object[] data : bookings ) {
            	
            	String firstName = (String) data[0];
                String lastName = (String) data[1];
                String email = (String) data[2];
                String phone = (String) data[3];
                boolean depositPaid = (Boolean) data[4];
                String checkin = (String) data[5];
                String checkout = (String) data[6];

            Booking bookingPayload = new Booking(firstName, lastName, email, phone, depositPaid, roomId,
                    new BookingDates(checkin, checkout));
            BookingAPI bookingApi = new BookingAPI();
            bookingApi.createBooking(bookingPayload);
            createdBookingIds =bookingApi.getBooking(roomId, token);
            
            System.out.println("Booking created for Room ID " + roomId + ", Booking ID: " + createdBookingIds);
            
            }
        
            
            System.out.println("Bookings created for " + roomId + ": " +createdBookingIds);
    }

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@SuppressWarnings("static-access")
	@AfterClass
	public void tearDown() {
		if (driver != null) {
			driver.quit(); // Only quit after all tests finish
		}
		for (int bookingId : createdBookingIds) {
            new BookingAPI().deleteBooking(bookingId, token);
            System.out.println("Deleted Booking ID: " + bookingId);
        }
        

        for (int roomId : createdRoomIds) {
            new RoomAPI().deleteRoom(roomId, token);
            System.out.println("Deleted Room ID: " + roomId);
        }
	}

}
