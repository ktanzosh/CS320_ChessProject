package edu.ycp.cs320.ChessProject.UserDatabase;

import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.ChessProject.Chess.Game;
import edu.ycp.cs320.ChessProject.Chess.Move;

//import edu.ycp.cs320.booksdb.model.Author;


public interface IDatabase {

	public User insertNewUser(String username, String password, String question, String answer, String SALT);
	public User changeRank(String username, int Rank);
	public Integer checkIfUserExists(String username);
	public User getUserInfo(String username);
	public User getUserInfoByID(int user_id);
	public User updatePassword(String username, String Password);
	public Game insertNewMove(int id, String move, int piece_id);
	ArrayList<Pair<ArrayList<String>, ArrayList<String>>> findAllGamesForUser(int id);
	public Integer insertNewGame(int player1);
	public Boolean insertSecondPlayer(int player2, int game_id);
	public User getSecondPlayerInfo(int game_id);
	public ArrayList<String> getMoveList(int game_id);
	public ArrayList<Integer> getMoveListbyPieceID(int game_id);
	public ArrayList<String> getMoveListIncludingPieceID(int game_id);
	public Integer insertGameEnd(int game_id, String finish, int winner);
}

