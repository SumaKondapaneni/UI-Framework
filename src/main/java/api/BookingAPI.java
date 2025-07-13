package api;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.Booking;

/**
 * @author Suma Kondapaneni
 * @created 12 Jul 2025
 */

public class BookingAPI {
	
	public static void deleteBooking(int bookingId,String token) {
		// TODO Auto-generated method stub

		RestAssured.given()
		.cookie("token",token)

		.when().delete("/api/booking/" + bookingId).then().log().all().statusCode(200);

	}
	
	public List<Integer> getBooking(int roomId , String token) {
		
		List<Integer> bookingIds = new ArrayList<>();

		// Send booking request
		Response getResponse = RestAssured.given()
				.cookie("token", token)
				.header("Content-Type", "application/json")
				.queryParam("roomid", roomId).when().get("/api/booking/").then()
				.log().all()
				.statusCode(200).extract().response();

		// Deserialize response

		List<Booking> bookings = getResponse.jsonPath()
				.getList("bookings", Booking.class);

		if (bookings.isEmpty()) {
			System.out.println("No bookings found for roomId " + roomId);

		}
		for (Booking booking : bookings) {

		int bookingId = booking.getBookingid();
		
		bookingIds.add(bookingId);
		}
		return bookingIds;

	}
	
	
	public Response getBookings() {
		
		Response getResponse =RestAssured.given().when().get("/api/booking");
		return getResponse;
		
	}
	
	public List<Booking> createBooking(Booking bookingRequest) {
		
		Response response = RestAssured.given().log().all() // logs request
				.contentType(ContentType.JSON).body(bookingRequest).when().post("/api/booking").then().log().all() // Logs
				// response
				.statusCode(200).extract().response();

		
		return response.jsonPath().getList("", Booking.class);
	}

}


