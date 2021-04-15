package edu.ycp.cs320.ChessProject.UserDatabase;

import java.util.List;

//import edu.ycp.cs320.booksdb.model.Author;


public interface IDatabase {

	public User insertNewUser(String username, String password, String question, String answer);
	public Integer checkIfUserExists(String username);
	public User getUserInfo(String username);
	public User updatePassword(String username, String Password);
}
