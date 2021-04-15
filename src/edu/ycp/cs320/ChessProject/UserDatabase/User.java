package edu.ycp.cs320.ChessProject.UserDatabase;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.ChessProject.Chess.Game;

public class User {
	private String user, password, sec_question, sec_answer;
	private List<Game> gameList = new ArrayList<Game>();
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
		InitDatabase.init();
		IDatabase db = DatabaseProvider.getInstance();
		int result = db.checkIfUserExists(User);
		
		if(result == 1) {
			return true;
		}
		
		else {
			return false;
		}
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
	
	public List<Game> getGameList() {
		return gameList;
	}
	
	public void addGameToGameList(Game g) {
		gameList.add(g);
	}
	
	public void printMoves() {
		for (Game g : gameList) {
				g.printMoveList();
		}
	}
	
	public void getMoves() {
		for (Game g : gameList) {
				g.getMoveList();
		}
	}
	
	//This encryption method was found at: https://www.geeksforgeeks.org/sha-512-hash-in-java/
	public static String encryptThisString(String input) {
	     try {
	         // getInstance() method is called with algorithm SHA-512
	         MessageDigest md = MessageDigest.getInstance("SHA-512");

	         // digest() method is called
	         // to calculate message digest of the input string
	         // returned as array of byte
	         byte[] messageDigest = md.digest(input.getBytes());

	         // Convert byte array into signum representation
	         BigInteger no = new BigInteger(1, messageDigest);

	         // Convert message digest into hex value
	         String hashtext = no.toString(16);

	         // Add preceding 0s to make it 32 bit
	         while (hashtext.length() < 32) {
	             hashtext = "0" + hashtext;
	         }

	         // return the HashText
	         return hashtext;
	     }

	     // For specifying wrong message digest algorithms
	     catch (NoSuchAlgorithmException e) {
	         throw new RuntimeException(e);
	     }
	 }
		
}
