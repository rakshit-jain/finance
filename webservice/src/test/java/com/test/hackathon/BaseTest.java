package com.test.hackathon;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:zircon-parent-config.xml" })
public class BaseTest {
	
	static{
		System.setProperty("catalina.home", "C:/Users/puneet.behl/Desktop/PuneetStuff/HCentive/Hackathon/Mario/webservice/src/main/resources/");
	}
	
	@Before
	public void setup() {
	
	}
	

	@After
	public void tearDown() {

	}

}