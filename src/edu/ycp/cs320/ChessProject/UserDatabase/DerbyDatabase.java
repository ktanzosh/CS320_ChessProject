package edu.ycp.cs320.ChessProject.UserDatabase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.ycp.cs320.ChessProject.Chess.Game;
import edu.ycp.cs320.ChessProject.Chess.Move;


public class DerbyDatabase implements IDatabase {
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

		} catch (Exception e) {
			throw new IllegalStateException("Could not load MySQL driver");
		}
	}
	
	private interface Transaction<ResultType> {
		public ResultType execute(Connection conn) throws SQLException;
	}

	private static final int MAX_ATTEMPTS = 10;

	// wrapper SQL transaction function that calls actual transaction function (which has retries)
	public<ResultType> ResultType executeTransaction(Transaction<ResultType> txn) {
		try {
			return doExecuteTransaction(txn);
		} catch (SQLException e) {
			throw new PersistenceException("Transaction failed", e);
		}
	}
	
	// SQL transaction function which retries the transaction MAX_ATTEMPTS times before failing
	public<ResultType> ResultType doExecuteTransaction(Transaction<ResultType> txn) throws SQLException {
		Connection conn = connect();
		
		try {
			int numAttempts = 0;
			boolean success = false;
			ResultType result = null;
			
			while (!success && numAttempts < MAX_ATTEMPTS) {
				try {
					result = txn.execute(conn);

					success = true;
				} catch (SQLException e) {
					if (e.getSQLState() != null && e.getSQLState().equals("41000")) {
						// Deadlock: retry (unless max retry count has been reached)
						numAttempts++;
					} else {
						// Some other kind of SQLException
						throw e;
					}
				}
			}
			
			if (!success) {
				throw new SQLException("Transaction failed (too many retries)");
			}
			
			// Success!
			return result;
		} finally {
			DBUtil.closeQuietly(conn);
		}
	}

	private Connection connect() throws SQLException {
	//Get DB credentials from hidden text file
	  String db_name = null;
      String db_username = null;
      String db_password = null;
      String db_hostname = null;
      String port = null;
      
      try {
    	  try {
		      File myObj = new File("DB_cred.txt");
		      Scanner myReader = new Scanner(myObj);
		      db_name = myReader.nextLine();
		      db_username = myReader.nextLine();
		      db_password = myReader.nextLine();
		      port = myReader.nextLine();
		      db_hostname = myReader.nextLine();
		      
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }

	      String jdbcUrl = "jdbc:mysql://" + db_hostname + ":" + port + "/" + db_name + "?user=" + db_username + "&password=" + db_password;
	      Connection conn = DriverManager.getConnection(jdbcUrl);
	      return conn;
      }
	    catch (SQLException e) { 
	    	System.out.println(e.toString());
	    }
	    
	    return null;
	  }
	
	// The main method creates the database tables and loads the initial data.
	public static void main(String[] args) throws IOException {
		System.out.println("Creating tables...");
		DerbyDatabase db = new DerbyDatabase();
		
		System.out.println("Library DB successfully initialized!");
	}
	
	// retrieves Author information from query result set
	private void loadUser(User user, ResultSet resultSet, int index) throws SQLException {
		user.setUserID(resultSet.getInt(index++));
		user.setUser(resultSet.getString(index++));
		user.setPassword(resultSet.getString(index++));
		user.setSecurityQuestion(resultSet.getString(index++));
		user.setSecurityAnswer(resultSet.getString(index++));
		user.setSALT(resultSet.getString(index++));
		
	}
	
	//  creates the users, moves, and UserGames tables (No longer useful in permanent AWS DB)
	/*
	public void createTables() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;				
			
				try {
					
					stmt1 = conn.prepareStatement(
						"CREATE TABLE `users` (" +
						"  `user-id` int NOT NULL AUTO_INCREMENT," +
						"  `username` varchar(50) DEFAULT NULL," +
						"  `password` varchar(150) DEFAULT NULL," +
						"  `securityQuestion` varchar(50) DEFAULT NULL," +
						"  `securityAnswer` varchar(150) DEFAULT NULL," +
						"  `salt` varchar(5) DEFAULT NULL," +
						"  `rankScore` int DEFAULT NULL," +
						"  PRIMARY KEY (`user-id`))"	
						
					);	
					
					stmt1.executeUpdate();
					
					System.out.println("Users table created");
					
					stmt2 = conn.prepareStatement(
							"CREATE TABLE `moves` (" +
	  						"  `move_id` int NOT NULL AUTO_INCREMENT," +
	  						"	`game_id` int DEFAULT NULL," +
	  						"		`move` varchar(15) DEFAULT NULL," +
	  						"		`piece_id` int DEFAULT NULL," +
	  						"			PRIMARY KEY (`move_id`))"
					);
					stmt2.executeUpdate();
					
					System.out.println("Moves table created");					
					
					stmt3 = conn.prepareStatement(
							"CREATE TABLE `userGames` (" +
							"  `game_id` int NOT NULL AUTO_INCREMENT," +
							"  `player1_id` int DEFAULT NULL," +
							"  `player2_id` int DEFAULT NULL," +
							"  `end` varchar(25) DEFAULT NULL," +
							"  `winner` int DEFAULT NULL," +
							"  PRIMARY KEY (`game_id`))"
					);
					stmt3.executeUpdate();
					
					System.out.println("userGames table created");					
									
					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt3);
				}
			}
		});
	}
	*/

	//Add a new user by username, password, security info, and SALT (return N/A)
	public User insertNewUser(String username, String password, String question, String answer, String SALT) {
			return executeTransaction(new Transaction<User>() {
				@Override
				public User execute(Connection conn) throws SQLException {
					PreparedStatement stmt1 = null;
					
					// add user info
					stmt1 = conn.prepareStatement(
							"insert into users (username, password, securityquestion, securityanswer, SALT)" +
							" values(?, ?, ?, ?, ?) "
					);
					stmt1.setString(1, username);
					stmt1.setString(2, password);
					stmt1.setString(3, question);
					stmt1.setString(4, answer);
					stmt1.setString(5, SALT);
					
					
					// execute the update
					stmt1.executeUpdate();
					
					DBUtil.closeQuietly(stmt1);
					
					return null;
				}
			});
		}

		// Check if user exists for acct recovery purpose and sign-up overlap check (return 1 for found)
		public Integer checkIfUserExists(String username) {
			return executeTransaction(new Transaction<Integer>() {
				@Override
				public Integer execute(Connection conn) throws SQLException {
					PreparedStatement stmt1 = null;			
					ResultSet resultSet1 = null;
					
					//grab user by username
					stmt1 = conn.prepareStatement(
							"select users.user_id from users " +
							" where users.username = ?"
					);
					stmt1.setString(1, username);
					
					// execute the query, get the result
					resultSet1 = stmt1.executeQuery();

					// if User was found then say so					
					if (resultSet1.next()) {
						return 1;			
					}
					
					else {
						return 2;
					}
				}
			});
		}
		
		// Get user movdel info for a given username for Acct recovery (Return User model)
		public User getUserInfo(String username) {
			return executeTransaction(new Transaction<User>() {
				@Override
				public User execute(Connection conn) throws SQLException {
					PreparedStatement stmt = null;
					ResultSet resultSet = null;
					User user = new User();
					try {
						//select user by username
						stmt = conn.prepareStatement(
								"select users.*" +
								" from  users" +
								"  where users.username = ? "
						);
						stmt.setString(1, username);
				
						resultSet = stmt.executeQuery();
						
						while (resultSet.next()) {
							loadUser(user, resultSet, 1);
						}
						
						return user;
						
					} finally {
						DBUtil.closeQuietly(resultSet);
						DBUtil.closeQuietly(stmt);
					}
				}
			});
		}
		
		//Find user info by the user Id for use with game list conversion (Return User model)
		public User getUserInfoByID(int user_id) {
			return executeTransaction(new Transaction<User>() {
				@Override
				public User execute(Connection conn) throws SQLException {
					PreparedStatement stmt = null;
					ResultSet resultSet = null;
					User user = new User();
					try {
						//Select user by ID
						stmt = conn.prepareStatement(
								"select users.*" +
								" from  users" +
								"  where users.user_id = ? "
						);
						stmt.setInt(1, user_id);
				
						resultSet = stmt.executeQuery();
						
						while (resultSet.next()) {
							loadUser(user, resultSet, 1);
						}
						
						return user;
						
					} finally {
						DBUtil.closeQuietly(resultSet);
						DBUtil.closeQuietly(stmt);
					}
				}
			});
		}

		// Change password field of a user (Return N/A)
		public User updatePassword(String username, String Password) {
			return executeTransaction(new Transaction<User>() {
				@Override
				public User execute(Connection conn) throws SQLException {
					PreparedStatement stmt = null;
					
					//update user password
					stmt = conn.prepareStatement(
							"update users" +
							" set users.password = ?" +
							"  where users.username = ? "
					);
					stmt.setString(1, Password);
					stmt.setString(2, username);
					
					
					// execute the update
					stmt.executeUpdate();
					
					DBUtil.closeQuietly(stmt);
					
					return null;
				}
			});
		}
		
		// Update Rank score of the user (Return N/A)
		public User changeRank(String username, int rank) {
			return executeTransaction(new Transaction<User>() {
				@Override
				public User execute(Connection conn) throws SQLException {
					PreparedStatement stmt = null;			

					// Update rank score of the user
					stmt = conn.prepareStatement(
							"update users" +
							" set rankScore = ?" +
							" and set SALT = ?" +
							" where users.username = ?"
					);
					stmt.setInt(1, rank);
					stmt.setString(2, username);
					
					
					// execute the update
					stmt.executeUpdate();
					
					DBUtil.closeQuietly(stmt);
					
					return null;
				}
			});
		}

	    //Add a new game to the DB by inserting a Player 1 (Return Game_ID)
		public Integer insertNewGame(int player1) {
			return executeTransaction(new Transaction<Integer>() {
				@Override
				public Integer execute(Connection conn) throws SQLException {
					PreparedStatement stmt1 = null;
					PreparedStatement stmt2 = null;
					ResultSet result = null;
					int game_id = -1;
					
					// Add player 1 (Game) to the DB
					stmt1 = conn.prepareStatement(
							"insert into userGames (player1_id)" +
							" values(?)"
					);
					stmt1.setInt(1, player1);	
					
					// execute the update
					stmt1.executeUpdate();
					
					//Grab the game_id auto-generated for multi-player functionality
					stmt2 = conn.prepareStatement(
							"select last_insert_id() from userGames"
					);
					
					result = stmt2.executeQuery();
					
					if(result.next()){
						game_id = result.getInt(1);
					}
					
					
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					
					return game_id;
				}
			});
		}

	    // Add a Second Player to a game if there is no player yet (Return whether adding Player 2 is success)
		public Boolean insertSecondPlayer(int player2, int game_id) {
			return executeTransaction(new Transaction<Boolean>() {
				@Override
				public Boolean execute(Connection conn) throws SQLException {
					PreparedStatement stmt1 = null;
					PreparedStatement stmt2 = null;
					PreparedStatement stmt3 = null;
					ResultSet result1 = null;
					ResultSet result2 = null;
					
					// Stmt to check if there is a player 2 in the game
					stmt1 = conn.prepareStatement(
							"select userGames.player2_id" +
							" from userGames" +
							" where userGames.game_id = ?"
					);
					
					stmt1.setInt(1, game_id);
					
					// execute the update
					result1 = stmt1.executeQuery();
					
					// Give error if a player 2 is already present
					if(result1.next()){
						int currentID = result1.getInt(1);

						if((currentID != 0) && (currentID != 13)) {
							System.out.println("There is already a player 2");
							return false;
						}
					}
					
					
					// Check that the game exists in the DB Game list
					stmt2 = conn.prepareStatement(
							"select userGames.player1_id" +
							" from userGames" +
							" where userGames.game_id = ?"
					);
					
					stmt2.setInt(1, game_id);
					
					// execute the update
					result2 = stmt2.executeQuery();
					
					if(result2.next()){
						int currentID = result2.getInt(1);
						
						//Give an error if the game has not been created (No Player 1)
						if(currentID == 0) {
							System.out.println("No player 1");
							return false;
						}
						
						// Add Player 2 to the game
						stmt3 = conn.prepareStatement(
								"update userGames" +
								" set userGames.player2_id = ?" +
								" where userGames.game_id = ?"
						);
						stmt3.setInt(1, player2);
						stmt3.setInt(2, game_id);
						
						// execute the update
						stmt3.executeUpdate();
						
					}
					
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt3);

					return true;
					
				}
			});
		}

	    // Add the next move in a game by move string and piece moved ID (Return N/A)
		public Game insertNewMove(int id, String move, int piece_id) {
			return executeTransaction(new Transaction<Game>() {
				@Override
				public Game execute(Connection conn) throws SQLException {
					PreparedStatement stmt1 = null;
					
					// add move by game_id, move string, and piece moved ID
					stmt1 = conn.prepareStatement(
							"insert into moves (game_id, move, piece_id)" +
							" values(?, ?, ?) "
					);
					stmt1.setInt(1, id);
					stmt1.setString(2, move);
					stmt1.setInt(3, piece_id);
					
					
					// execute the update
					stmt1.executeUpdate();
					
					DBUtil.closeQuietly(stmt1);
					
					return null;
				}
			});
		}

		//Add the ending to a game (End state and Winner) (Return is N/A)
		public Integer insertGameEnd(int game_id, String finish, int winner) {
			return executeTransaction(new Transaction<Integer>() {
				@Override
				public Integer execute(Connection conn) throws SQLException {
					PreparedStatement stmt1 = null;
					
					// Add ending and winner
					stmt1 = conn.prepareStatement(
							"update userGames" +
							" set end = ?, winner = ?" +
							" where game_id = ?"
					);
					stmt1.setString(1, finish);
					stmt1.setInt(2, winner);
					stmt1.setInt(3, game_id);
					
					// execute the update
					stmt1.executeUpdate();

					DBUtil.closeQuietly(stmt1);

					
					return game_id;
				}
			});
		}
	    
		// Get all the game info/move list as for a given user by user_id (Return a Pair of ArrayList with game info - move list as strings)
		public ArrayList<Pair<ArrayList<String>, ArrayList<String>>> findAllGamesForUser(int id) {
			return executeTransaction(new Transaction<ArrayList<Pair<ArrayList<String>, ArrayList<String>>>>() {
				@Override
				public ArrayList<Pair<ArrayList<String>, ArrayList<String>>> execute(Connection conn) throws SQLException {
					PreparedStatement stmt1 = null;
					PreparedStatement stmt2 = null;
					ResultSet resultSet1 = null;
					ResultSet resultSet2 = null;
					
					try {
						//Get all the Game IDs of the games played by user as creator or joiner
						stmt1 = conn.prepareStatement(
								"select userGames.*" +
								" from userGames" +
								" where ((? = userGames.player1_id)" +
								" or (? = userGames.player2_id))" +
								" order by userGames.game_id"
						);
						stmt1.setInt(1, id);
						stmt1.setInt(2, id);
						
						resultSet1 = stmt1.executeQuery();
						
						ArrayList<Pair<ArrayList<String>, ArrayList<String>>> result = new ArrayList<Pair<ArrayList<String>, ArrayList<String>>>();

						// for testing that a result was returned
						Boolean found = false;
						int current_game_id = -1;
						User temp_user = null;
						String temp_name = null;
						
						while (resultSet1.next()) {
							found = true;
							ArrayList<String> gameInfo = new ArrayList<String>();
							ArrayList<String> gameMoves = new ArrayList<String>();
							
							//Convert the returned game info to usernames instead of just IDs
							 gameInfo.add(resultSet1.getString(1)); //gameID
							 
							 temp_user = getUserInfoByID(resultSet1.getInt(2));
							 temp_name = temp_user.getUser();
								
							 gameInfo.add(temp_name); //player 1
							 
							 temp_user = getUserInfoByID(resultSet1.getInt(3));
							 temp_name = temp_user.getUser();
								
							 gameInfo.add(temp_name); //player 2
							 
							 gameInfo.add(resultSet1.getString(4)); //end result
							 
							 temp_user = getUserInfoByID(resultSet1.getInt(5));
							 temp_name = temp_user.getUser();
								
							 gameInfo.add(temp_name); //Winner Player
							

							current_game_id = resultSet1.getInt(1);
							
							//Get the move list for the given game_id
							stmt2 = conn.prepareStatement(
									"select moves.move" +
									" from moves" +
									" where moves.game_id = ?" +
									" order by moves.move_id"
							);
							stmt2.setInt(1, current_game_id);

							
							resultSet2 = stmt2.executeQuery();
							
							while (resultSet2.next()) {
								gameMoves.add(resultSet2.getString(1));
							}
							
							//add game info - move list pair to Pair
							result.add(new Pair<ArrayList<String>, ArrayList<String>>(gameInfo, gameMoves));

							
						}
						
						// check if no games were found
						if (!found) {
							System.out.println("No games were found in the database");
						}
						
						return result;
					} finally {
						DBUtil.closeQuietly(stmt1);
						DBUtil.closeQuietly(stmt2);
					}
				}
			});
		}	

	    //Get the moves list of a given game (Return an arraylist of moves as string)
		public ArrayList<String> getMoveList(int game_id) {
			return executeTransaction(new Transaction<ArrayList<String>>() {
				@Override
				public ArrayList<String> execute(Connection conn) throws SQLException {
					PreparedStatement stmt1 = null;
					ResultSet result = null;
					
					// Get the moves of the game in the order the moves were made
					stmt1 = conn.prepareStatement(
							"select moves.move" +
							" from moves" +
							" where moves.game_id = ?" +
							" order by moves.move_id"
					);
					stmt1.setInt(1, game_id);
					
					
					result = stmt1.executeQuery();
					
					ArrayList<String> moveList = new ArrayList<String>();
						
					while (result.next()) {
						moveList.add(result.getString(1));
					}

					DBUtil.closeQuietly(stmt1);
					
					return moveList;
				}
			});
		}
		
		//Get the move list but by the piece_id of each move made (Return ArrayList of integers of piece ID's)
		public ArrayList<Integer> getMoveListbyPieceID(int game_id) {
			return executeTransaction(new Transaction<ArrayList<Integer>>() {
				@Override
				public ArrayList<Integer> execute(Connection conn) throws SQLException {
					PreparedStatement stmt1 = null;
					ResultSet result = null;
					
					// Get the moves in the order the moves were made
					stmt1 = conn.prepareStatement(
							"select moves.piece_id" +
							" from moves" +
							" where moves.game_id = ?" +
							" order by moves.move_id"
					);
					stmt1.setInt(1, game_id);
					
					
					result = stmt1.executeQuery();
					
					ArrayList<Integer> moveList = new ArrayList<Integer>();
						
					while (result.next()) {
						moveList.add(result.getInt(1));
					}

					DBUtil.closeQuietly(stmt1);
					
					return moveList;
				}
			});
		}
		
		//Get move list ofr a given game with move string/ piece id in odd/even pair (Return ArrayList of moves/piece ID's as string)
		public ArrayList<String> getMoveListIncludingPieceID(int game_id) {
			return executeTransaction(new Transaction<ArrayList<String>>() {
				@Override
				public ArrayList<String> execute(Connection conn) throws SQLException {
					PreparedStatement stmt1 = null;
					ResultSet result = null;
					
					// get moves in the order performed
					stmt1 = conn.prepareStatement(
							"select moves.move, moves.piece_id" +
							" from moves" +
							" where moves.game_id = ?" +
							" order by moves.move_id"
					);
					stmt1.setInt(1, game_id);
					
					
					result = stmt1.executeQuery();
					
					
					ArrayList<String> moveList = new ArrayList<String>();
						
					while (result.next()) {
						moveList.add(result.getString(1));
						moveList.add(result.getString(2));
					}

					DBUtil.closeQuietly(stmt1);
					
					return moveList;
				}
			});
		}

	    //Find the Second player of a game by the Game ID (Return Player User Info)
		public User getSecondPlayerInfo(int game_id) {
			return executeTransaction(new Transaction<User>() {
				@Override
				public User execute(Connection conn) throws SQLException {
					PreparedStatement stmt1 = null;
					PreparedStatement stmt2 = null;
					ResultSet result1 = null;
					ResultSet result2 = null;
					
					int player2_id = 0;
					User user = new User();
					
					// Get Player 2 ID
					stmt1 = conn.prepareStatement(
							"select userGames.player2_id" +
							" from userGames" +
							" where userGames.game_id = ?"
					);
					stmt1.setInt(1, game_id);
					
					
					result1 = stmt1.executeQuery();
					
						
					while (result1.next()) {
						player2_id = result1.getInt(1);
					}

					//Get Player 2 info by previous found player ID
					stmt2 = conn.prepareStatement(
							"select users.*" +
							" from  users" +
							"  where users.user_id = ? "
					);
					stmt2.setInt(1, player2_id);
			
					result2 = stmt2.executeQuery();
					
					while (result2.next()) {
						loadUser(user, result2, 1);
					}
					
						
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					
					return user;
				}
			});
		}
	
}
