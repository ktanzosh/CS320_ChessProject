package edu.ycp.cs320.ChessProject.UserDatabase;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.ChessProject.Chess.Game;

public class User {
	private String user, password, sec_question, sec_answer, SALT;
	private int userID;
	private List<Game> gameList = new ArrayList<Game>();
	
	//private User blankUser = new User();
	//private List<User> usersList;
	//private ArrayList<User> usersList = new ArrayList<User>();
	//private usersList.addAll(UsersList.createUsersList());
	
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public int getUserID() {
		return userID;
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
	
	public void setSecurityQuestion(String sec_question) {
		this.sec_question = sec_question;
	}
	
	public String getSecurityQuestion() {
		return sec_question;
	}
	
	public void setSecurityAnswer(String sec_answer) {
		this.sec_answer = sec_answer;
	}
	
	public String getSecurityAnswer() {
		return sec_answer;
	}
	
	public void setSALT(String SALT) {
		this.SALT = SALT;
	}
	
	public String getSALT() {
		return SALT;
	}

	public User getUserInfo(String User) {
		InitDatabase.init();
		IDatabase db = DatabaseProvider.getInstance();
		User user = db.getUserInfo(User);
		return user;
		
	}

	public boolean checkIfUserExists(String User) {
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
	
	public boolean checkUserSecurityAnswer(String User, String Answer, String SALT) {
		User user = getUserInfo(User);
		
		Answer = decryptThisString(Answer, SALT);
		
		if(user.getSecurityAnswer().equals(Answer)) {
			return true;
		}
		else {
			return false;	
		}
	}

	public void setNewPassword(String User, String newpass, String SALT) {
		InitDatabase.init();
		IDatabase db = DatabaseProvider.getInstance();
		String enc_pass = decryptThisString(newpass, SALT);
		db.updatePassword(User, enc_pass);
		
	}
	
	public boolean checkInfo(String User, String Password) {
		if(checkIfUserExists(User) == false) {
			return false;
		}
		else {
			User user = getUserInfo(User);
			String SALT = user.getSALT();
			Password = decryptThisString(Password, SALT);
			
			if(user.getPassword().equals(Password)) {
				return true;
			}
			else {
				return false;	
			}
		}
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
	public static String[] encryptThisString(String input) {
	     try {
	         // getInstance() method is called with algorithm SHA-512
	         MessageDigest md = MessageDigest.getInstance("SHA-512");

	         SecureRandom rand = new SecureRandom();
	         int salt = rand.nextInt(9999);
	         String SALT = Integer.toString(salt);
	         input = SALT + input;
	         
	         
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
	         String[] result = new String[2];
	         result[0] = hashtext;
	         result[1] = SALT;
	         
	         //System.out.println("enc:" + hashtext);
	         // return the HashText
	         return result;
	     }
	     // For specifying wrong message digest algorithms
	     catch (NoSuchAlgorithmException e) {
	         throw new RuntimeException(e);
	     }
	}
	     
     public static String decryptThisString(String input, String SALT) {
	     try {
	         // getInstance() method is called with algorithm SHA-512
	         MessageDigest md = MessageDigest.getInstance("SHA-512");

	         input = SALT + input;
	         
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
	         
	         //System.out.println("dec:" + hashtext);
	         // return the HashText
	         return hashtext;
	     }

	     // For specifying wrong message digest algorithms
	     catch (NoSuchAlgorithmException e) {
	         throw new RuntimeException(e);
	     }
	 }
		
}
