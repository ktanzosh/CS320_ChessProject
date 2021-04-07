package edu.ycp.cs320.ChessProject.UserDatabase;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String user, password, sec_question, sec_answer;
	//private User blankUser = new User();
	//private List<User> usersList;
	//private ArrayList<User> usersList = new ArrayList<User>();
	//private usersList.addAll(UsersList.createUsersList());
	
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
	
	public void setSecurityQuestion(String sec_question) {
		this.sec_question = sec_question;
	}
	
	public String getSecurtyQuestion() {
		return sec_question;
	}
	
	public void setSecurityAnswer(String sec_answer) {
		this.sec_answer = sec_answer;
	}
	
	public String getSecurityAnswer() {
		return sec_answer;
	}

	public User getUserInfo(String User) {
		User blankUser = new User();
		List<User> usersList;
		usersList = new ArrayList<User>();
		usersList.addAll(UsersList.createUsersList());
		for (User user : usersList) {
			if (user.getUser().equals(User)) {
				return user;
			}
		}
		return blankUser;
		
	}

	public boolean checkIfUserExsists(String User) {
		List<User> usersList;
		usersList = new ArrayList<User>();
		usersList.addAll(UsersList.createUsersList());
		for (User user : usersList) {
			if (user.getUser().equals(User)) {
				return true;
			}
		}
		return false;	
	}
	
	public boolean checkUserSecurityAnswer(String User, String Answer) {
		List<User> usersList;
		usersList = new ArrayList<User>();
		usersList.addAll(UsersList.createUsersList());
		for (User user : usersList) {
			if (user.getUser().equals(User)) {
				if (user.getSecurityAnswer().equals(Answer)) {
					return true;
				}
			}
		}
		return false;	
	}

	public void setNewPassord(String User, String newpass) {
		List<User> usersList;
		usersList = new ArrayList<User>();
		usersList.addAll(UsersList.createUsersList());
		for (User user : usersList) {
			if (user.getUser().equals(User)) {
				user.setPassword(newpass);			
			}
		}
		
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
