package GameEngine;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class jUnitUser {
	
	private User userOne;
	private User userTwo;
	private User userThree;
	private User userFour;

	@Before
	public void setUp() throws Exception {
		
		userOne = new User();
		userOne.logIn("ssimmons", "password");
		
		userTwo = new User();
		userTwo.logIn("test2", "testpw");
		
		userThree = new User();
		userThree.logIn("test3", "testpw");
		
		userFour = new User();
		userFour.logIn("test1", "testpw");
	}

	@Test
	public void getUsername() {
		assertEquals("ssimmons", userOne.getUsername());
	}
	
	@Test
	public void getEmail() {
		assertEquals("sebesmos@iastate.edu", userOne.getEmail());
	}
	
	@Test
	public void getPassword() {
		assertEquals("password", userOne.getPassword());
	}
	
	@Test
	public void isBannedTrue() {
		assertEquals(true, userTwo.isBanned());
	}
	
	@Test
	public void isBannedFalse() {
		assertEquals(false, userOne.isBanned());
	}
	
	@Test
	public void isAdminTrue() {
		assertEquals(true, userOne.isAdmin());
	}
	
	@Test
	public void isAdminFalse() {
		assertEquals(false, userTwo.isAdmin());
	}
	
	@Test
	public void isModeratorTrue() {
		assertEquals(true, userFour.isModerator());
	}
	
	@Test
	public void isModeratorFalse() {
		assertEquals(false, userThree.isModerator());
	}
	
	@Test
	public void Ban() throws Exception {
		userThree.Ban();
		userThree.saveUser();
		assertEquals(true, userThree.isBanned());
	}
	
	@Test
	public void resetPassword() throws Exception {
		userThree.resetPassword();
		userThree.saveUser();
		assertEquals("", userThree.getPassword());
	}
	
	//Successfully creates a new user
	/*@Test
	public void createNewUser() throws Exception {
		User userNew = new User();
		userNew.saveUser("test4", "test4@test.com", "password");
	}*/

}
