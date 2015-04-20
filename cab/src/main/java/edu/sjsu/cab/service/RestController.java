package edu.sjsu.cab.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.sjsu.cab.object.Freeway;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    
    @RequestMapping(method=RequestMethod.GET,value="/greeting")
    public Freeway greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Freeway();
    }
    
    

    
}
