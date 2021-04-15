package edu.ycp.cs320.ChessProject.UserDatabase;

import java.util.List;

//import edu.ycp.cs320.booksdb.model.Author;


public interface IDatabase {

	public User InsertNewUser(String username, String password, String question, String answer);
	public Integer checkIfUserExists(String username);
}
