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
        List<Request> users = dao.findUserByRequest();
        for(Request user:users) {
            System.out.print(user.getUserId()+'\n');
            Assert.notNull(user.getUserId());
        }
    }

    
    
}
