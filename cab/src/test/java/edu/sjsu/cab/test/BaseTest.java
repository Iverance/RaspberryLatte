package edu.sjsu.cab.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import edu.sjsu.cab.bootstrap.CabConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CabConfiguration.class, loader = AnnotationConfigContextLoader.class)
public abstract class BaseTest {

         
}
