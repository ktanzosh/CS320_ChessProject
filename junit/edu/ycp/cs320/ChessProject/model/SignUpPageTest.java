package edu.ycp.cs320.ChessProject.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;



public class SignUpPageTest {
	private SignUpPage model;
	
	@Before
	public void setUp() {
		model = new SignUpPage();
		
		
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
	 
}
