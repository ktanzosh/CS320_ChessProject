package edu.ycp.cs320.ChessProject.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.ycp.cs320.ChessProject.UserDatabase.User;
import edu.ycp.cs320.ChessProject.UserDatabase.UsersList;

// model class for Numbers
// only the controller should be allowed to call the set methods
// the JSP will call the "get" and "is" methods implicitly
// when the JSP specifies game.min, that gets converted to
//    a call to model.getMin()
// when the JSP specifies if(game.done), that gets converted to
//    a call to model.isDone()
public class LoginPage {
	private String user, password;
	private boolean info;
	
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

	public void setInfo(boolean info) {
		this.info = info;
	}
	
	public boolean getInfo() {
		return info;
	}
	
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
