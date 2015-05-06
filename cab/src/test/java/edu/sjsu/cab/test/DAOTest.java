package edu.sjsu.cab.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.Assert;

import edu.sjsu.cab.storage.CabStorageService;
import edu.sjsu.cab.storage.Request;

public class DAOTest extends BaseTest{

    @Autowired
    CabStorageService dao;

    @Test
    public void getUsers() {
        List<Request> users = dao.findUserByRequest(-122.40880966186523,37.78821704497664);
        for(Request user:users) {
            System.out.print(user.getRequestId()+'\n');
            Assert.notNull(user.getRequestId());
        }
    }

    
    
}
