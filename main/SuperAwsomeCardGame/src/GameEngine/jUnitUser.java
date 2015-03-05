package GameEngine;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class jUnitUser {
	
	private User userOne;

	@Before
	public void setUp() throws Exception {
		
		userOne = new User();
		userOne.logIn("ssimmons", "password");
	}

	@Test
	public void getUsername() {
		assertEquals("ssimmons", userOne.getUsername());
	}
	
	@Test
	public void test() {
		assertEquals("sebesmos@iastate.edu", userOne.getEmail());
	}
	
	

}
