package model;

/**
 * @author Suma Kondapaneni
 * @created 12 Jul 2025
 */

public class BookingDates {

	
	    private String checkin;
	    private String checkout;
	    
	    public BookingDates() {
	        //default constructor
	    }

	    public BookingDates(String checkin, String checkout) {
	        this.checkin = checkin;
	        this.checkout = checkout;
	    }

	    public String getCheckin() {
	        return checkin;
	    }

	    public String getCheckout() {
	        return checkout;
	    }
	

}


