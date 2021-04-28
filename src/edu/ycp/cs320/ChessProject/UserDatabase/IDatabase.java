package edu.ycp.cs320.ChessProject.UserDatabase;

import java.util.List;

import edu.ycp.cs320.ChessProject.Chess.Game;
import edu.ycp.cs320.ChessProject.Chess.Move;

//import edu.ycp.cs320.booksdb.model.Author;


public interface IDatabase {

	public User insertNewUser(String username, String password, String question, String answer, String SALT);
	public User changeRank(String username, int Rank);
	public Integer checkIfUserExists(String username);
	public User getUserInfo(String username);
	public User updatePassword(String username, String Password);
	public Game insertNewMove(int id, String move);
	List<Pair<User, Game>> findAllGamesForUser(String user);
	public Integer insertNewGame(int player1);
	public Integer insertSecondPlayer(int player2, int game_id);
	public List<String> getMoveList(int game_id);
}

