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
		User user = new User();
		String enc_password = user.encryptThisString("password123");
		String enc_answer = user.encryptThisString("Mr. Dan");
		
		assertTrue(model.getUser().equals("user123"));
		assertTrue(model.getPassword().equals(enc_password));
		assertTrue(model.getSecurityAnswer().equals(enc_answer));
	}
	
	@Test
	public void testCheckIfUserExists() {
		assertTrue(model.checkIfUserExists("user123"));
		assertFalse(model.checkIfUserExists("NotAUser"));
	}
	
	@Test
	public void testCheckUserSecurityAnswer() {
		assertTrue(model.checkUserSecurityAnswer("user123", "Mr. Dan"));
		assertFalse(model.checkUserSecurityAnswer("user123", "not Mr. Dan"));
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
