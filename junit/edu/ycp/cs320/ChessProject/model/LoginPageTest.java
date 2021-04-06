package edu.ycp.cs320.ChessProject.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;



public class LoginPageTest {
	private LoginPage model;
	
	@Before
	public void setUp() {
		model = new LoginPage();
		
		
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
