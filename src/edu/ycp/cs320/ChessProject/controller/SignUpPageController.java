package edu.ycp.cs320.ChessProject.controller;
import edu.ycp.cs320.ChessProject.model.SignUpPage;

public class SignUpPageController {
	
	private SignUpPage model;
	//private String theUsername, thePassword, passwordConfirm, theSecurityQuestion, theSecurityAnswer;
	private String takenUsername = "user123";
	
	public void setModel(SignUpPage model) {
		this.model = model;
	}
	
	public boolean storeInfo(String User, String Password, String SecurityA) {
		if (User.equals(takenUsername)) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public boolean checkPasswordMatch(String Password, String confirmPassword) {
		if (Password.equals(confirmPassword)) {
			return true;
		}
		else {
			return false;
		}
	}
}