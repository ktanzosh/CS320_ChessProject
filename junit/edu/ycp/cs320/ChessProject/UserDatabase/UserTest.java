package edu.ycp.cs320.ChessProject.UserDatabase;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;



public class UserTest {
	private User model;
	
	@Before
	public void setUp() {
		model = new User();
	}
	
	@Test
	public void testSetUser() {
		model.setUser("username");
		assertEquals(model.getUser(), "username");
		assertFalse(model.getUser().equals("notUsername"));
	}
	
	@Test
	public void testSetPassword() {
		model.setPassword("password");
		assertEquals(model.getPassword(), "password");
		assertFalse(model.getPassword().equals("notPassword"));
	}
	
	@Test
	public void testGetUserInfo() {
		model = model.getUserInfo("user123");
		assertTrue(model.getUser().equals("user123"));
		assertTrue(model.getPassword().equals("password123"));
		assertTrue(model.getSecurityAnswer().equals("Bread"));
	}
	
	@Test
	public void testCheckIfUserExists() {
		assertTrue(model.checkIfUserExsists("user123"));
		assertFalse(model.checkIfUserExsists("NotAUser"));
	}
	
	@Test
	public void testCheckUserSecurityAnswer() {
		assertTrue(model.checkUserSecurityAnswer("user123", "Bread"));
		assertFalse(model.checkUserSecurityAnswer("user123", "notBread"));
	}
	
	@Test
	public void testCheckInfo() {
		String realUser = "user123";
		String realPassword = "password123";
		String fakeUser = "notAUser";
		String fakePassword = "wrongPassword";
		assertTrue(model.checkInfo(realUser, realPassword));
		assertFalse(model.checkInfo(fakeUser, realPassword));
		assertFalse(model.checkInfo(realUser, fakePassword));
	}
	
	 
}
