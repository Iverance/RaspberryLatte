package edu.sjsu.cab.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import edu.sjsu.cab.bootstrap.CabConfiguration;
import edu.sjsu.cab.storage.CabStorageService;
import edu.sjsu.cab.storage.User;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CabConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class DAOTest {

    @Autowired
    CabStorageService dao;

//    @Before
//    public void setUP() {
//        dao = new CabStorageServiceImplementation();
//    }
    

    @Test
    public void getUsers() {
        List<User> users = dao.findUserByRequest();
        for(User user:users) {
            System.out.print(user.getUserId());
        }
    }

    
    
}
