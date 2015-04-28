package edu.sjsu.cab.algorithm;

import java.util.List;

import edu.sjsu.cab.object.Marker;
import edu.sjsu.cab.storage.CabStorageServiceImplementation;
import edu.sjsu.cab.storage.User;

public class AlgorithmProcessor {

    CabStorageServiceImplementation dataLayer = new CabStorageServiceImplementation();
    
    public List<Marker> getMarkers() {
        List<User> users = dataLayer.findUserByRequest();
        ClarkeWrightMethod cwm = new ClarkeWrightMethod(MatrixLoader.getMatrixByUsers(users));
        
        return null;
    }
    
}
