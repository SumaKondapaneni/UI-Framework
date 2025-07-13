package model;

import java.util.List;

/**
 * @author Suma Kondapaneni
 * @created 12 Jul 2025
 */

public class Room {
		
		private int roomid;
	    private String roomName;
	    private String type;
	    private boolean accessible;
	    private String image;
	    private String description;
	    private List<String> features;
	    private double roomPrice;

	    // Getters and Setters
	    public int getRoomid() { return roomid; }
	    public void setRoomid(int roomid) { this.roomid = roomid; }
	    public String getRoomName() { return roomName; }
	    public void setRoomName(String roomName) { this.roomName = roomName; }

	    public String getType() { return type; }
	    public void setType(String type) { this.type = type; }

	    public boolean isAccessible() { return accessible; }
	    public void setAccessible(boolean accessible) { this.accessible = accessible; }

	    public String getImage() { return image; }
	    public void setImage(String image) { this.image = image; }

	    public String getDescription() { return description; }
	    public void setDescription(String description) { this.description = description; }

	    public List<String> getFeatures() { return features; }
	    public void setFeatures(List<String> features) { this.features = features; }

	    public double getRoomPrice() { return roomPrice; }
	    public void setRoomPrice(double roomPrice) { this.roomPrice = roomPrice; }
	
}


