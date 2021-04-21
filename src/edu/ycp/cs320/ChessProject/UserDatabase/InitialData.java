package edu.ycp.cs320.ChessProject.UserDatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.ycp.cs320.ChessProject.Chess.Game;
import edu.ycp.cs320.ChessProject.Chess.Move;

public class InitialData {

	// reads initial Author data from CSV file and returns a List of Authors
	public static List<User> getUsers() throws IOException {
		List<User> userList = new ArrayList<User>();
		ReadCSV readUsers = new ReadCSV("users.csv");
		try {
			// auto-generated primary key for authors table
			Integer userId = 1;
			while (true) {
				List<String> tuple = readUsers.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				User user = new User();

				// read user ID from CSV file, but don't use it
				// it's there for reference purposes, just make sure that it is correct
				// when setting up the BookAuthors CSV file				
				Integer.parseInt(i.next());
				// auto-generate user ID, instead
				user.setUserID(userId++);
				user.setUser(i.next());
				user.setPassword(i.next());
				user.setSecurityQuestion(i.next());
				user.setSecurityAnswer(i.next());
				userList.add(user);
				
			}
			System.out.println("userList loaded from CSV file");
			return userList;
		} finally {
			readUsers.close();
		}
	}
	
	public static List<Game> getMoves() throws IOException {
		List<Game> moveList = new ArrayList<Game>();
		ReadCSV readMoves = new ReadCSV("moves.csv");
		try {
			// auto-generated primary key for authors table
			Integer moveId = 1;
			while (true) {
				List<String> tuple = readMoves.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Game move = new Game();

				// read user ID from CSV file, but don't use it
				// it's there for reference purposes, just make sure that it is correct
				// when setting up the BookAuthors CSV file				
				Integer.parseInt(i.next());
				// auto-generate user ID, instead
				//move.setMoveID(moveId++);
				//move.setStuff(i.next());

				moveList.add(move);
				
			}
			System.out.println("moveList loaded from CSV file");
			return moveList;
		} finally {
			readMoves.close();
		}
	}
	
	public static List<userGames> getUserGames() throws IOException {
		List<userGames> userGamesList = new ArrayList<userGames>();
		ReadCSV readUserGames = new ReadCSV("userGames.csv");
		try {

			while (true) {
				List<String> tuple = readUserGames.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				userGames game = new userGames();
				game.setGame_ID(Integer.parseInt(i.next()));
				game.setPlayer1_ID(Integer.parseInt(i.next()));
				game.setPlayer2_ID(Integer.parseInt(i.next()));
				userGamesList.add(game);
			}
			System.out.println("bookAuthorList loaded from CSV file");			
			return userGamesList;
		} finally {
			readUserGames.close();
		}
	}
	

}