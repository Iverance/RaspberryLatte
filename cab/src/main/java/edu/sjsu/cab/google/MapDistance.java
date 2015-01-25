package edu.sjsu.cab.google;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

//import org.json.JSONObject;

public class MapDistance {
	
	public static void main(String[] args) throws Exception{
		
		/*GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyBYJcvZ6k8x8eAQdPQv-wCUgfd6A_MocyI");
		GeocodingResult[] results =  GeocodingApi.geocode(context,
		    "1600 Amphitheatre Parkway Mountain View, CA 94043").await();
		System.out.println(results[0].formattedAddress);*/
		MapDistance asdf = new MapDistance();
	//	JSON object;
		
		LatLng origin = asdf.randomLL();
		LatLng dest = asdf.randomLL();
        String url = asdf.getDirectionsUrl(origin, dest);
        
        System.out.println(url);
        String theData = asdf.downloadUrl(url);
        
        double theDistance = asdf.getDistance(theData);
        System.out.println(theDistance+ " miles");
		
	}
	
	private String getDirectionsUrl(LatLng origin,LatLng dest){
		 
        // Origin of route
        String str_origin = "origin="+origin.lat+","+origin.lng;
 
        // Destination of route
        String str_dest = "destination="+dest.lat+","+dest.lng;
 
        // Sensor enabled
        String sensor = "sensor=false";
 
        // Building the parameters to the web service
        String parameters = str_origin+"&"+str_dest+"&"+sensor;
 
        // Output format
        String output = "json";
 
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/"+output+"?"+parameters;
 
        return url;
    }
	
	private String downloadUrl(String strUrl) throws IOException{
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try{
            URL url = new URL(strUrl);
 
            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();
 
            // Reading data from url
            iStream = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));
            StringBuffer sb  = new StringBuffer();
 
            String line = "";
            while( ( line = br.readLine())  != null){
                sb.append(line);
            }
            data = sb.toString();
            br.close();
 
        }catch(Exception e){
            System.out.println(e);
        }finally{
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }
	
	private LatLng randomLL(){
		Random r = new Random();
		int Low = 37;
		int High = 38;
		double R = r.nextInt(High-Low) + Low+ r.nextDouble();
		
		int low2 = -122;
		int high2 = -120;
		
		double R2 = r.nextInt(high2-low2) + low2+ r.nextDouble();
		
		LatLng random = new LatLng(R,R2);
		
		return random;
	}
	
	private double getDistance(String theData){
		
		String word = "text";
        String miles = "mi\",";
        String distanceString = theData.substring(theData.indexOf(word)+9, theData.indexOf(miles)-1);
        
        double theDistance = Double.parseDouble(distanceString);
		return theDistance;
		
	}
	
	
	
	

}
