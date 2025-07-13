package model;

/**
 * @author Suma Kondapaneni
 * @created 12 Jul 2025
 */

public class Booking {
	private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private boolean depositpaid;
    private int roomid;
	private int bookingid;
    private BookingDates bookingdates;
    public Booking() {
        // Required default constructor for Jackson
    }

    public Booking(String firstname, String lastname, String email, String phone, boolean depositpaid, int roomid, BookingDates bookingdates) {
        this.setFirstname(firstname);
        this.setLastname(lastname);
        this.setEmail(email);
        this.setPhone(phone);
        this.setDepositpaid(depositpaid);
        this.setRoomid(roomid);
        this.setBookingdates(bookingdates);
    }

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isDepositpaid() {
		return depositpaid;
	}

	public void setDepositpaid(boolean depositpaid) {
		this.depositpaid = depositpaid;
	}

	public int getRoomid() {
		return roomid;
	}

	public void setRoomid(int roomid) {
		this.roomid = roomid;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public BookingDates getBookingdates() {
		return bookingdates;
	}

	public void setBookingdates(BookingDates bookingdates) {
		this.bookingdates = bookingdates;
	}

	public int getBookingid() {
		return bookingid;
	}

	public void setBookingid(int bookingid) {
		this.bookingid = bookingid;
	}


}


