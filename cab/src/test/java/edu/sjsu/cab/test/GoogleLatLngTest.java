package edu.sjsu.cab.test;

import org.junit.Before;
import org.junit.Test;

import com.google.maps.model.LatLng;

import edu.sjsu.cab.google.MapDistance;

public class GoogleLatLngTest {

    MapDistance googleMapDistance;

    @Before
    public void setUp()  {
        /*-
         * GeoApiContext context = new
         * GeoApiContext().setApiKey("AIzaSyBYJcvZ6k8x8eAQdPQv-wCUgfd6A_MocyI");
         * GeocodingResult[] results = GeocodingApi.geocode(context,
         * "1600 Amphitheatre Parkway Mountain View, CA 94043").await();
         * System.out.println(results[0].formattedAddress);
         */
        googleMapDistance = new MapDistance();
    }

    @Test
    public void getDistanceTest() throws Exception{
        LatLng origin = googleMapDistance.randomLL();
        LatLng dest = googleMapDistance.randomLL();
        String url = googleMapDistance.getDirectionsUrl(origin, dest);
        String theData = googleMapDistance.askGoogle(url);
        double theDistance = googleMapDistance.getDistance(theData);
        System.out.println(theDistance + " miles");
    }
    
    @Test
    public void getLatLngByAddress() throws Exception{
        String url2 = googleMapDistance.getLatLngUrl("shoreline blvd and U.S. 101");
        System.out.println(url2);
        String theData2 = googleMapDistance.askGoogle(url2);
        double[] blah = googleMapDistance.getLatLng(theData2);
        System.out.println(blah[0] + " by " + blah[1]);
    }
}
