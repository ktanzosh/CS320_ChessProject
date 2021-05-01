package edu.ycp.cs320.ChessProject.UserDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.DriverManager;
import java.util.Scanner;

import edu.ycp.cs320.ChessProject.Chess.Game;
import edu.ycp.cs320.ChessProject.Chess.Move;



public class awsDatabse implements IDatabase {
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			//Class.forName("org.mysql.Driver");
		} catch (Exception e) {
			throw new IllegalStateException("Could not load MySQL driver");
		}
	}
	
	private interface Transaction<ResultType> {
		public ResultType execute(Connection conn) throws SQLException;
	}
	
	private static final int MAX_ATTEMPTS = 10;
	
	// The main method creates the database tables and loads the initial data.
	public static void main(String[] args) throws IOException {
		System.out.println("Creating tables...");
		DerbyDatabase db = new DerbyDatabase();
		//db.createTables();
		
		//System.out.println("Loading initial data...");
		//db.loadInitialData();
		
		System.out.println("Library DB successfully initialized!");
	}
	
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
					conn.commit();
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
	
	//Connection method - Connects to AWS RDS DB
	private Connection connect() throws SQLException {
		
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
		      System.out.println(db_name);
		      db_username = myReader.nextLine();
		      System.out.println(db_username);
		      db_password = myReader.nextLine();
		      System.out.println(db_password);
		      port = myReader.nextLine();
		      System.out.println(port);
		      
		      myReader.close();
    	  } 
    	  catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
    	  }
		  	  
	      //Class.forName("com.mysql.cj.jdbc.Driver");
	      String jdbcUrl = "jdbc:mysql://" + "localhost" + ":" + port + "/" + db_name + "?user=" + db_username + "&password=" + db_password;
	      System.out.println("Getting remote connection with connection string from environment variables.");
	      System.out.println(jdbcUrl);
	      Connection conn = DriverManager.getConnection(jdbcUrl);
	      System.out.println("Remote connection successful.");
	      return conn;
      }
      /*
	  catch (ClassNotFoundException e) { 
	    	System.out.println(e.toString());
	  } 
	  */
	  catch (SQLException e) { 
	    	System.out.println(e.toString());
	  }
	    
	  return null;
	  }
	
	//-------Below follows the list of DB transactions-------//
	
	//Transaction to insert a Chess move into the moves table
	@Override
	public Game insertNewMove(int id, String move) {
		return executeTransaction(new Transaction<Game>() {
			@Override
			public Game execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				
				// prepare SQL insert statement to add Move to moves table
				stmt1 = conn.prepareStatement(
						"insert into moves (game_id, move)" +
						" values(?, ?) "
				);
				stmt1.setInt(1, id);
				stmt1.setString(2, move);
				
				
				// execute the update
				stmt1.executeUpdate();
				
				DBUtil.closeQuietly(stmt1);
				
				return null;
			}
		});
	}
	

	//Transaction to pull the moves list(s) for a user
	@Override
	public List<Pair<User, Game>> findAllGamesForUser(String user) {
		return executeTransaction(new Transaction<List<Pair<User, Game>>>() {
			@Override
			public List<Pair<User, Game>> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select moves.move " +
							" from users, moves, userGames" +
							" where ((? = userGames.player1_id)" +
							" or (? = userGames.player2_id))" +
							" and moves.game_id = userGames.game_id"
					);
					stmt.setString(1, user);
					stmt.setString(2, user);
					
					List<Pair<User, Game>> result = new ArrayList<Pair<User, Game>>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						User user = new User();
						loadUser(user, resultSet, 1);
						Game move = new Game();
						loadMove(move, resultSet, 6);
						
						result.add(new Pair<User, Game>(user, move));
					}
					
					// check if any moves were found
					if (!found) {
						System.out.println("No games were found in the database");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	//Transaction to add a user to the user table
	public User insertNewUser(String username, String password, String question, String answer, String SALT) {
		return executeTransaction(new Transaction<User>() {
			@Override
			public User execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				
				// prepare SQL insert statement to add User to users table
				stmt1 = conn.prepareStatement(
						"insert into users (username, password, securityquestion, securityanswer, SALT)" +
						" values(?, ?, ?, ?, ?) "
				);
				stmt1.setString(1, username);
				stmt1.setString(2, password);
				stmt1.setString(3, question);
				stmt1.setString(4, answer);
				stmt1.setString(4, SALT);
				
				
				// execute the update
				stmt1.executeUpdate();
				
				DBUtil.closeQuietly(stmt1);
				
				return null;
			}
		});
	}
	
	//Transaction to check if a specified username exists in the users table
	@Override
	public Integer checkIfUserExists(String username) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;			
				ResultSet resultSet1 = null;
				

				stmt1 = conn.prepareStatement(
						"select user_id from users " +
						" where username = ?"
				);
				stmt1.setString(1, username);
				
				// execute the query, get the result
				resultSet1 = stmt1.executeQuery();

				
				// if User was found then save user_id					
				if (resultSet1.next()) {
					return 1;			
				}
				
				else {
					return 2;
				}
			}
		});
	}
	
	//Transaction to pull user model info of specified user
	@Override
	public User getUserInfo(String username) {
		return executeTransaction(new Transaction<User>() {
			@Override
			public User execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				User user = new User();
				try {
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
	
	//Transaction to update the password field for a specified user
	@Override
	public User updatePassword(String username, String Password) {
		return executeTransaction(new Transaction<User>() {
			@Override
			public User execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				stmt = conn.prepareStatement(
						"update users" +
						" set password = ?" +
						" and set SALT = ?" +
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
	
	@Override
	public User changeRank(String username, int rank) {
		return executeTransaction(new Transaction<User>() {
			@Override
			public User execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;			
				//ResultSet resultSet1 = null;
				

				stmt = conn.prepareStatement(
						"update users" +
						" set rankScore = ?" +
						" and set SALT = ?" +
						"  where users.username = ? "
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
	
	// retrieves User information from query result set
	private void loadUser(User user, ResultSet resultSet, int index) throws SQLException {
		user.setUserID(resultSet.getInt(index++));
		user.setUser(resultSet.getString(index++));
		user.setPassword(resultSet.getString(index++));
		user.setSecurityQuestion(resultSet.getString(index++));
		user.setSecurityAnswer(resultSet.getString(index++));
		
	}
	
	// retrieves Move information from a query result set
	private void loadMove(Game move, ResultSet resultSet, int index) throws SQLException {
		//move.getLastMove().getMove().(resultSet.getInt(index++));
		//move.setUser(resultSet.getString(index++));
		//move.setPassword(resultSet.getString(index++));
		//move.setSecurityQuestion(resultSet.getString(index++));
		//move.setSecurityAnswer(resultSet.getString(index++));
		
	}
	
	//creates the User, Moves, and Games tables
	public void createTables() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;				
			
				try {
					
					stmt1 = conn.prepareStatement(
					/*
						"create table users (" +
						"	user_id int primary key " +
						"		generated always as identity (start with 1, increment by 1), " +									
						"	username varchar(50)," +
						"	password varchar(150)," +
						"	securityQuestion varchar(50)," +
						"	securityAnswer varchar(150)" +
						")"
						*/
						"CREATE TABLE `users` (" +
						"  `user-id` int NOT NULL AUTO_INCREMENT," +
						"  `username` varchar(50) DEFAULT NULL," +
						"  `password` varchar(150) DEFAULT NULL," +
						"  `securityQuestion` varchar(50) DEFAULT NULL," +
						"  `securityAnswer` varchar(150) DEFAULT NULL," +
						"  `rankScore` int DEFAULT NULL," +
						"  PRIMARY KEY (`user-id`)"	
						
					);	
					
					stmt1.executeUpdate();
					
					System.out.println("Users table created");
					
					stmt2 = conn.prepareStatement(
					/*
							"create table moves (" +
							"	move_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +
							"	game_id int," +
							"	move varchar(10)" +
							")"
							
							*/
							
							"CREATE TABLE `moves` (" +
	  						"  `move_id` int NOT NULL AUTO_INCREMENT," +
	  						"	`game_id` int DEFAULT NULL," +
	  						"		`move` varchar(15) DEFAULT NULL," +
	  						"			PRIMARY KEY (`move_id`)"
					);
					stmt2.executeUpdate();
					
					System.out.println("Moves table created");					
					
					stmt3 = conn.prepareStatement(
							/*
							"create table userGames (" +
							"	game_id   integer constraint game_id references moves, " +
							"	player1_id integer constraint player1_id references users, " +
							"	player2_id integer constraint player2_id references users " +
							")"
							*/
							
							"CREATE TABLE `userGames` (" +
							"  `game_id` int NOT NULL AUTO_INCREMENT," +
							"  `player1_id` int DEFAULT NULL," +
							"  `player2_id` int DEFAULT NULL," +
							"  `end` varchar(25) DEFAULT NULL," +
							"  `winner` int DEFAULT NULL," +
							"  PRIMARY KEY (`game_id`)"
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
	
	// loads data retrieved from CSV files into DB tables in batch mode
	public void loadInitialData() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				List<User> usersList;
				List<Game> gamesList;
				List<userGames> userGamesList;
				
				try {
					usersList = InitialData.getUsers();
					//gamesList = InitialData.getMoves();
					//userGamesList = InitialData.getUserGames();	
				
				} catch (IOException e) {
					throw new SQLException("Couldn't read initial data", e);
				}

				PreparedStatement insertUsers = null;
				PreparedStatement insertMoves = null;
				PreparedStatement insertUserGames = null;
				try {
					// must completely populate Authors table before populating BookAuthors table because of primary keys
					insertUsers = conn.prepareStatement("insert into users (username, password, securityquestion, securityanswer) values (?, ?, ?, ?)");
					for (User user : usersList) {
//							insertUser.setInt(1, user.getUserId());	// auto-generated primary key, don't insert this
						insertUsers.setString(1, user.getUser());
						insertUsers.setString(2, user.getPassword());
						insertUsers.setString(3, user.getSecurityQuestion());
						insertUsers.setString(4, user.getSecurityAnswer());
						insertUsers.addBatch();
					}
					insertUsers.executeBatch();
					System.out.println("Users table populated");	 
					/*
						insertMoves = conn.prepareStatement("insert into moves (game_id, move) values (?, ?)");
						for(Game game : gamesList) {
//							insertMoves.setInt(1, move.getMove_ID);	// auto-generated primary key, don't insert this
							insertMoves.setString(1, game.getGameID()); //TODO: ADD GAME_ID
							insertMoves.setString(2, game.getLastMove().getMove());
						}
						insertUsers.executeBatch();
						System.out.println("Users table populated");
						
						
						insertUserGames = conn.prepareStatement("insert into userGames (game_id, player1_id, player2_id) values (?, ?, ?)");
						for (userGames game : userGamesList) {
							insertUserGames.setInt(1,  game.getGame_ID());
							insertUserGames.setInt(2,  game.getPlayer1_ID());
							insertUserGames.setInt(3,  game.getPlayer2_ID());
							insertUserGames.addBatch();
						}
						insertUserGames.executeBatch();	
						
						System.out.println("userGames table populated");		
						*/
					return true;
				} finally {
					DBUtil.closeQuietly(insertUsers);	
					DBUtil.closeQuietly(insertMoves);	
					DBUtil.closeQuietly(insertUserGames);	
				}
			}
		});
	}
	
}


