package edu.sjsu.cab.google;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.maps.model.LatLng;

public class MapDistance {

	/*
	 * Form query url for getting directions from origin to destination
	 * @param origin LatLng of origin point
	 * @param dest LatLng of destination point
	 * @return Url String
	 */
	public String getDirectionsUrl(LatLng origin, LatLng dest) {

		// Origin of route
		String str_origin = "origin=" + origin.lat + "," + origin.lng;

		// Destination of route
		String str_dest = "destination=" + dest.lat + "," + dest.lng;

		// Building the parameters to the web service
		String parameters = str_origin + "&" + str_dest + "&"
				+ "key=AIzaSyBYJcvZ6k8x8eAQdPQv-wCUgfd6A_MocyI";

		// Output format
		String output = "json";

		// Building the url to the web service
		String url = "https://maps.googleapis.com/maps/api/directions/"
				+ output + "?" + parameters;

		return url;
	}

	/*
	 * Form query url for getting latLng for an address
	 * @param address string of full address
	 * @return url of query
	 */
	public String getLatLngUrl(String address) {

		// Address
		String addressParam = address.replaceAll(" ", "+");

		// Sensor enabled
		String sensor = "sensor=false";

		// Building the parameters to the web service
		String parameters = "address=" + addressParam + "&"
				+ "key=AIzaSyBYJcvZ6k8x8eAQdPQv-wCUgfd6A_MocyI";

		// Output format
		String output = "json";

		// Building the url to the web service
		String url = "https://maps.googleapis.com/maps/api/geocode/" + output
				+ "?" + parameters;

		return url;
	}

	/*
	 * Query Google using url
	 * @param strUrl url string to be used to query google
	 * @return string of json response
	 */
	public String askGoogle(String strUrl) throws IOException {
		String data = "";
		InputStream iStream = null;
		HttpURLConnection urlConnection = null;
		try {
			URL url = new URL(strUrl);

			// Creating an http connection to communicate with url
			urlConnection = (HttpURLConnection) url.openConnection();

			// Connecting to url
			urlConnection.connect();

			// Reading data from url
			iStream = urlConnection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(
					iStream));
			StringBuffer sb = new StringBuffer();

			String line = "";
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			data = sb.toString();
			br.close();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			iStream.close();
			urlConnection.disconnect();
		}
		return data;
	}

	/*
	 * Randomly generate a LatLng
	 * @return random LatLng
	 */
	public LatLng randomLL() {
		Random r = new Random();
		int Low = 37;
		int High = 38;
		double R = r.nextInt(High - Low) + Low + r.nextDouble();

		int low2 = -121;
		int high2 = -120;

		double R2 = r.nextInt(high2 - low2) + low2 + r.nextDouble();

		LatLng random = new LatLng(R, R2);

		return random;
	}

	/*
	 * Use string from askGoogle to parse through JSONObject to get distance
	 * @param theData json string from askGoogle query.
	 * @return distance between two points
	 */
	public double getDistance(String theData) {

		final JSONObject obj = new JSONObject(theData);
		JSONObject res = obj.getJSONArray("routes").getJSONObject(0);
	//	System.out.println(res);
		JSONArray loc = res.getJSONArray("legs");
	//	System.out.println(loc);

		String dist = loc.getJSONObject(0).getJSONObject("distance")
				.getString("text");
		String distCut = dist.substring(0, dist.indexOf(" "));
	//	System.out.println(distCut);
		double theDistance = Double.parseDouble(distCut);

		return theDistance;

	}

	/*
	 * method returns array: first elemnt is Lat, second element is Lng
	 * @param theData JSON string from askGoogle query
	 * @return array with [lat,lng] of address
	 */
	public double[] getLatLng(String theData) {

		double lat;
		double lng;
		double[] latAndLng = new double[2];

		final JSONObject obj = new JSONObject(theData);
		JSONObject res = obj.getJSONArray("results").getJSONObject(0);
		System.out.println(res.getString("formatted_address"));
		JSONObject loc = res.getJSONObject("geometry")
				.getJSONObject("location");

		lat = loc.getDouble("lat");
		lng = loc.getDouble("lng");
		// System.out.println("lat: " + loc.getDouble("lat") + ", lng: " +
		// loc.getDouble("lng"));

		latAndLng[0] = lat;
		latAndLng[1] = lng;

		return latAndLng;

	}

}