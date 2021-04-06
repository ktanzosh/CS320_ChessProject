package edu.ycp.cs320.ChessProject.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.ycp.cs320.ChessProject.UserDatabase.User;
import edu.ycp.cs320.ChessProject.UserDatabase.UsersList;

public class LoginPage {
	private String user, password;
	//private boolean info;
	
	public LoginPage() {
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getUser() {
		return user;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	/*
	public void setInfo(boolean info) {
		this.info = info;
	}
	
	public boolean getInfo() {
		return info;
	}
	*/
	public boolean checkInfo(String User, String Password) {
		
		List<User> usersList;
		usersList = new ArrayList<User>();
		usersList.addAll(UsersList.createUsersList());
		for (User user : usersList) {
			if (user.getUser().equals(User)) {
				if(user.getPassword().equals(Password))
					return true;
			}
		}
		return false;
		
	}
	

	
}
