package edu.sjsu.cab.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.sjsu.cab.algorithm.AlgorithmProcessor;
import edu.sjsu.cab.object.Marker;
import edu.sjsu.cab.service.response.RestResponse;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    AlgorithmProcessor algorithmProcessor;

    // @formatter:off
    @RequestMapping(method = RequestMethod.GET, value = "/greeting")
    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "Hi! "+name+" The cab server is running!";
    }

    @RequestMapping(value = "/newRequest")
    public RestResponse newRequest(@RequestParam Map<String,String> requestParams) {
        String destinationAddress=requestParams.get("destAdd");
        //PassengerRequest passengerRequest = new PassengerRequest(originalAddress, destinationAddress, destinationLL, destinationLL, numOfPassengers);
        //TODO: save request to DB 
        return new RestResponse(111,"Server received the request!");
    }

    @RequestMapping(value = "/getRoute")
    public List<Marker> getRoute(@RequestParam Map<String,String> requestParams) {
        return algorithmProcessor.getMarkers();
    }

    
//    http://localhost:8080/newRequest?originAdd=20,destAdd=ff,pssgrNum=2
}
