package api;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.Room;
import model.createResponse;

/**
 * @author Suma Kondapaneni
 * @created 12 Jul 2025
 */

public class RoomAPI {
	
	public void createRoom(Room room, String token) {

		Response response = RestAssured.given().log().all() // logs request
				// .header("Authorization", "Bearer " + token)
				.cookie("token", token)
				.header("Content-Type", "application/json")
				.body(room).when()
				.post("/api/room");

		// System.out.println("response -"+response.asString());

		assertEquals(response.getStatusCode(), 200);

		createResponse res = response.as(createResponse.class);

		assertTrue(res.isSuccess(), "create Room is not successful");
		
		

        
    }

    public static void deleteRoom(int roomId,String token) {

		RestAssured.given()
		.cookie("token",token)
		.when()
		.delete("/api/room/" + roomId)
		.then()
		.statusCode(200);
    }
    
	public Room getRoomByName(String roomName) {
		Response response = RestAssured.given().when().get("/api/room").then().statusCode(200).extract().response();

		List<Room> rooms = response.jsonPath().getList("rooms", Room.class);

		return rooms.stream().filter(r -> r.getRoomName().equalsIgnoreCase(roomName)).findFirst()
				.orElseThrow(() -> new RuntimeException("Room not found: " + roomName));
	}
	
	
	
	
	public Response getRooms(String token) {
		

		Response response = RestAssured.given().log().all().cookie("token", token).get("/api/room");

		assertEquals(response.statusCode(), 200);
		assertTrue(response.asString().contains("roomid"));
		return response;
	}

}


