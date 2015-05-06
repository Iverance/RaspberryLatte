package edu.sjsu.cab.algorithm;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.sjsu.cab.object.Marker;
import edu.sjsu.cab.storage.CabStorageService;
import edu.sjsu.cab.storage.Request;

@Component
public class AlgorithmProcessor {

    @Autowired
    CabStorageService dataLayer;

    public List<Marker> getMarkers() {
        List<Marker> markers = new ArrayList<Marker>();
        //TODO: make it is not hard coded
        List<Request> users = dataLayer.findUserByRequest(-122.40880966186523,37.78821704497664);
        ClarkeWrightMethod cwm = new ClarkeWrightMethod(MatrixLoader.getMatrixByUsers(users));
        String[] bestRoute = cwm.getBestRoute();
        for (String index : bestRoute) {
            markers.add(this.convertUserToMarker(users.get(Integer.valueOf(index))));
        }
        Marker marker = new Marker();
        marker.setLatitude((double) users.get(Integer.valueOf(bestRoute[bestRoute.length-1])).getPickupLocationLat());
        marker.setLongitude((double) users.get(Integer.valueOf(bestRoute[bestRoute.length-1])).getPickupLocationLong());
        marker.setMessage("destination");
        marker.setUsername(users.get(Integer.valueOf(bestRoute[bestRoute.length-1])).getFirstName());
        markers.add(marker);
        return markers;
    }

    private Marker convertUserToMarker(Request user) {
        Marker marker = new Marker();
        marker.setLatitude((double) user.getPickupLocationLat());
        marker.setLongitude((double) user.getPickupLocationLong());
        marker.setMessage("passenger");
        marker.setUsername(user.getFirstName());
        return marker;
    }

}
