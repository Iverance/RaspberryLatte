/**
 * 
 */
package edu.sjsu.cab;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.maps.model.LatLng;

import edu.sjsu.cab.object.*;

/**
 * @author Ashley
 *
 */
public class TaxiRequestTest {

	/**
	 * 
	 */
	public TaxiRequestTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//variable declarations
		try{
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy hh:mm");
			Date passenger1LeaveTime = formatter.parse("4/5/15 8:15");
			LatLng sjsuAddy = new LatLng(43.091018,-73.498425);
			LatLng postOfficeAddy = new LatLng(37.290732,-121.912717);
			PassengerRequest passenger1 = new PassengerRequest("1 Washington Square, San Jose, CA 95192",
															   "1759 Meridian Ave, San Jose, CA 95192", sjsuAddy, postOfficeAddy,
															   1,passenger1LeaveTime,passenger1LeaveTime,false);			
		}catch(ParseException e){
			//how to handle exception case from user?
		}
		
		System.out.println("Hey");

	}

}
