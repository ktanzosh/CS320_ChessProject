package edu.ycp.cs320.ChessProject.controller;
import edu.ycp.cs320.ChessProject.model.LoginPage;

public class LoginPageController {
	
	private LoginPage model;
	private String theUsername = "user123";
	private String thePassword = "password123";
	
	public void setModel(LoginPage model) {
		this.model = model;
	}
	
	public boolean checkInfo(String User, String Password) {
		if((User.equals(theUsername)) && (Password.equals(thePassword))) {
			return true;
		}
		
		else {
			return false;
		}
	}
	
}