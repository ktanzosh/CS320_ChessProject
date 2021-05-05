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


//import edu.ycp.cs320.ChessProject.booksdb.persist.DerbyDatabase.Transaction;

//import edu.ycp.cs320.booksdb.model.Author;


public class DerbyDatabase implements IDatabase {
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

	
	// transaction that retrieves a Book, and its Author by Title
	/*
	@Override
	public List<Pair<Author, Book>> findAuthorAndBookByTitle(final String title) {
		return executeTransaction(new Transaction<List<Pair<Author,Book>>>() {
			@Override
			public List<Pair<Author, Book>> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select authors.*, books.* " +
							"  from  authors, books, bookAuthors " +
							"  where books.title = ? " +
							"    and authors.author_id = bookAuthors.author_id " +
							"    and books.book_id     = bookAuthors.book_id"
					);
					stmt.setString(1, title);
					
					List<Pair<Author, Book>> result = new ArrayList<Pair<Author,Book>>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						Author author = new Author();
						loadAuthor(author, resultSet, 1);
						Book book = new Book();
						loadBook(book, resultSet, 4);
						
						result.add(new Pair<Author, Book>(author, book));
					}
					
					// check if the title was found
					if (!found) {
						System.out.println("<" + title + "> was not found in the books table");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	
	// transaction that retrieves a list of Books with their Authors, given Author's last name
	@Override
	public List<Pair<Author, Book>> findAuthorAndBookByAuthorLastName(final String lastName) {
		return executeTransaction(new Transaction<List<Pair<Author,Book>>>() {
			@Override
			public List<Pair<Author, Book>> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;

				// try to retrieve Authors and Books based on Author's last name, passed into query
				try {
					stmt = conn.prepareStatement(
							"select authors.*, books.* " +
							"  from  authors, books, bookAuthors " +
							"  where authors.lastname = ? " +
							"    and authors.author_id = bookAuthors.author_id " +
							"    and books.book_id     = bookAuthors.book_id "   +
							"  order by books.title asc, books.published asc"
					);
					stmt.setString(1, lastName);
					
					// establish the list of (Author, Book) Pairs to receive the result
					List<Pair<Author, Book>> result = new ArrayList<Pair<Author,Book>>();
					
					// execute the query, get the results, and assemble them in an ArrayLsit
					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						Author author = new Author();
						loadAuthor(author, resultSet, 1);
						Book book = new Book();
						loadBook(book, resultSet, 4);
						
						result.add(new Pair<Author, Book>(author, book));
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	
	// transaction that retrieves all Books in Library, with their respective Authors
	@Override
	public List<Pair<Author, Book>> findAllBooksWithAuthors() {
		return executeTransaction(new Transaction<List<Pair<Author,Book>>>() {
			@Override
			public List<Pair<Author, Book>> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select authors.*, books.* " +
							"  from authors, books, bookAuthors " +
							"  where authors.author_id = bookAuthors.author_id " +
							"    and books.book_id     = bookAuthors.book_id "   +
							"  order by books.title asc"
					);
					
					List<Pair<Author, Book>> result = new ArrayList<Pair<Author,Book>>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						Author author = new Author();
						loadAuthor(author, resultSet, 1);
						Book book = new Book();
						loadBook(book, resultSet, 4);
						
						result.add(new Pair<Author, Book>(author, book));
					}
					
					// check if any books were found
					if (!found) {
						System.out.println("No books were found in the database");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}	
	
	
	// transaction that retrieves all Authors in Library
	@Override
	public List<Author> findAllAuthors() {
		return executeTransaction(new Transaction<List<Author>>() {
			@Override
			public List<Author> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select * from authors " +
							" order by lastname asc, firstname asc"
					);
					
					List<Author> result = new ArrayList<Author>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						Author author = new Author();
						loadAuthor(author, resultSet, 1);
						
						result.add(author);
					}
					
					// check if any authors were found
					if (!found) {
						System.out.println("No authors were found in the database");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	
	// transaction that inserts new Book into the Books table
	// also first inserts new Author into Authors table, if necessary
	// and then inserts entry into BookAuthors junction table
	@Override
	public Integer insertBookIntoBooksTable(final String title, final String isbn, final int published, final String lastName, final String firstName) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;
				PreparedStatement stmt4 = null;
				PreparedStatement stmt5 = null;
				PreparedStatement stmt6 = null;				
				
				ResultSet resultSet1 = null;
				ResultSet resultSet3 = null;
				ResultSet resultSet5 = null;				
				
				// for saving author ID and book ID
				Integer author_id = -1;
				Integer book_id   = -1;

				// try to retrieve author_id (if it exists) from DB, for Author's full name, passed into query
				try {
					stmt1 = conn.prepareStatement(
							"select author_id from authors " +
							"  where lastname = ? and firstname = ? "
					);
					stmt1.setString(1, lastName);
					stmt1.setString(2, firstName);
					
					// execute the query, get the result
					resultSet1 = stmt1.executeQuery();

					
					// if Author was found then save author_id					
					if (resultSet1.next())
					{
						author_id = resultSet1.getInt(1);
						System.out.println("Author <" + lastName + ", " + firstName + "> found with ID: " + author_id);						
					}
					else
					{
						System.out.println("Author <" + lastName + ", " + firstName + "> not found");
				
						// if the Author is new, insert new Author into Authors table
						if (author_id <= 0) {
							// prepare SQL insert statement to add Author to Authors table
							stmt2 = conn.prepareStatement(
									"insert into authors (lastname, firstname) " +
									"  values(?, ?) "
							);
							stmt2.setString(1, lastName);
							stmt2.setString(2, firstName);
							
							// execute the update
							stmt2.executeUpdate();
							
							System.out.println("New author <" + lastName + ", " + firstName + "> inserted in Authors table");						
						
							// try to retrieve author_id for new Author - DB auto-generates author_id
							stmt3 = conn.prepareStatement(
									"select author_id from authors " +
									"  where lastname = ? and firstname = ? "
							);
							stmt3.setString(1, lastName);
							stmt3.setString(2, firstName);
							
							// execute the query							
							resultSet3 = stmt3.executeQuery();
							
							// get the result - there had better be one							
							if (resultSet3.next())
							{
								author_id = resultSet3.getInt(1);
								System.out.println("New author <" + lastName + ", " + firstName + "> ID: " + author_id);						
							}
							else	// really should throw an exception here - the new author should have been inserted, but we didn't find them
							{
								System.out.println("New author <" + lastName + ", " + firstName + "> not found in Authors table (ID: " + author_id);
							}
						}
					}
					
					// now insert new Book into Books table
					// prepare SQL insert statement to add new Book to Books table
					stmt4 = conn.prepareStatement(
							"insert into books (title, isbn, published) " +
							"  values(?, ?, ?) "
					);
					stmt4.setString(1, title);
					stmt4.setString(2, isbn);
					stmt4.setInt(3, published);
					
					// execute the update
					stmt4.executeUpdate();
					
					System.out.println("New book <" + title + "> inserted into Books table");					

					// now retrieve book_id for new Book, so that we can set up BookAuthor entry
					// and return the book_id, which the DB auto-generates
					// prepare SQL statement to retrieve book_id for new Book
					stmt5 = conn.prepareStatement(
							"select book_id from books " +
							"  where title = ? and isbn = ? and published = ? "
									
					);
					stmt5.setString(1, title);
					stmt5.setString(2, isbn);
					stmt5.setInt(3, published);

					// execute the query
					resultSet5 = stmt5.executeQuery();
					
					// get the result - there had better be one
					if (resultSet5.next())
					{
						book_id = resultSet5.getInt(1);
						System.out.println("New book <" + title + "> ID: " + book_id);						
					}
					else	// really should throw an exception here - the new book should have been inserted, but we didn't find it
					{
						System.out.println("New book <" + title + "> not found in Books table (ID: " + book_id);
					}
					
					// now that we have all the information, insert entry into BookAuthors table
					// which is the junction table for Books and Authors
					// prepare SQL insert statement to add new Book to Books table
					stmt6 = conn.prepareStatement(
							"insert into bookAuthors (book_id, author_id) " +
							"  values(?, ?) "
					);
					stmt6.setInt(1, book_id);
					stmt6.setInt(2, author_id);
					
					// execute the update
					stmt6.executeUpdate();
					
					System.out.println("New entry for book ID <" + book_id + "> and author ID <" + author_id + "> inserted into BookAuthors junction table");						
					
					System.out.println("New book <" + title + "> inserted into Books table");					
					
					return book_id;
				} finally {
					DBUtil.closeQuietly(resultSet1);
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);					
					DBUtil.closeQuietly(resultSet3);
					DBUtil.closeQuietly(stmt3);					
					DBUtil.closeQuietly(stmt4);
					DBUtil.closeQuietly(resultSet5);
					DBUtil.closeQuietly(stmt5);
					DBUtil.closeQuietly(stmt6);
				}
			}
		});
	}
	
	
	// transaction that deletes Book (and possibly its Author) from Library
	@Override
	public List<Author> removeBookByTitle(final String title) {
		return executeTransaction(new Transaction<List<Author>>() {
			@Override
			public List<Author> execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;
				PreparedStatement stmt4 = null;
				PreparedStatement stmt5 = null;
				PreparedStatement stmt6 = null;							
				
				ResultSet resultSet1    = null;			
				ResultSet resultSet2    = null;
				ResultSet resultSet5    = null;
				
				try {
					// first get the Author(s) of the Book to be deleted
					// just in case it's the only Book by this Author
					// in which case, we should also remove the Author(s)
					stmt1 = conn.prepareStatement(
							"select authors.* " +
							"  from  authors, books, bookAuthors " +
							"  where books.title = ? " +
							"    and authors.author_id = bookAuthors.author_id " +
							"    and books.book_id     = bookAuthors.book_id"
					);
					
					// get the Book's Author(s)
					stmt1.setString(1, title);
					resultSet1 = stmt1.executeQuery();
					
					// assemble list of Authors from query
					List<Author> authors = new ArrayList<Author>();					
				
					while (resultSet1.next()) {
						Author author = new Author();
						loadAuthor(author, resultSet1, 1);
						authors.add(author);
					}
					
					// check if any Authors were found
					// this shouldn't be necessary, there should not be a Book in the DB without an Author
					if (authors.size() == 0) {
						System.out.println("No author was found for title <" + title + "> in the database");
					}
										
					// now get the Book(s) to be deleted
					// we will need the book_id to remove associated entries in BookAuthors (junction table)
				
					stmt2 = conn.prepareStatement(
							"select books.* " +
							"  from  books " +
							"  where books.title = ? "
					);
					
					// get the Book(s)
					stmt2.setString(1, title);
					resultSet2 = stmt2.executeQuery();
					
					// assemble list of Books from query
					List<Book> books = new ArrayList<Book>();					
				
					while (resultSet2.next()) {
						Book book = new Book();
						loadBook(book, resultSet2, 1);
						books.add(book);
					}
					
					// first delete entries in BookAuthors junction table
					// can't delete entries in Books or Authors tables while they have foreign keys in junction table
					// prepare to delete the junction table entries (bookAuthors)
					stmt3 = conn.prepareStatement(
							"delete from bookAuthors " +
							"  where book_id = ? "
					);
					
					// delete the junction table entries from the DB for this title
					// this works if there are not multiple books with the same name
					stmt3.setInt(1, books.get(0).getBookId());
					stmt3.executeUpdate();
					
					System.out.println("Deleted junction table entries for book(s) <" + title + "> from DB");									
					
					// now delete entries in Books table for this title
					stmt4 = conn.prepareStatement(
							"delete from books " +
							"  where title = ? "
					);
					
					// delete the Book entries from the DB for this title
					stmt4.setString(1, title);
					stmt4.executeUpdate();
					
					System.out.println("Deleted book(s) with title <" + title + "> from DB");									
					
					// now check if the Author(s) have any Books remaining in the DB
					// only need to check if there are any entries in junction table that have this author ID
					for (int i = 0; i < authors.size(); i++) {
						// prepare to find Books for this Author
						stmt5 = conn.prepareStatement(
								"select books.book_id from books, bookAuthors " +
								"  where bookAuthors.author_id = ? "
						);
						
						// retrieve any remaining books for this Author
						stmt5.setInt(1, books.get(i).getAuthorId());
						resultSet5 = stmt5.executeQuery();						

						// if nothing returned, then delete Author
						if (!resultSet5.next()) {
							stmt6 = conn.prepareStatement(
								"delete from authors " +
								"  where author_id = ?"
							);
							
							// delete the Author from DB
							stmt6.setInt(1, authors.get(i).getAuthorId());
							stmt6.executeUpdate();
							
							System.out.println("Deleted author <" + authors.get(i).getLastname() + ", " + authors.get(i).getFirstname() + "> from DB");
							
							// we're done with this, so close it, since we might have more to do
							DBUtil.closeQuietly(stmt6);
						}
						
						// we're done with this, so close it since we might have more to do
						DBUtil.closeQuietly(resultSet5);
						DBUtil.closeQuietly(stmt5);
					}
					return authors;
				} finally {
					DBUtil.closeQuietly(resultSet1);
					DBUtil.closeQuietly(resultSet2);
					
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt3);					
					DBUtil.closeQuietly(stmt4);					
				}
			}
		});
	}
	
	*/
	
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
				
				// prepare SQL insert statement to add Author to Authors table
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
	
	public Boolean insertSecondPlayer(int player2, int game_id) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;
				ResultSet result1 = null;
				ResultSet result2 = null;
				
				// prepare SQL insert statement to add Author to Authors table
				stmt1 = conn.prepareStatement(
						"select userGames.player2_id" +
						" from userGames" +
						" where userGames.game_id = ?"
				);
				
				stmt1.setInt(1, game_id);
				
				// execute the update
				result1 = stmt1.executeQuery();
				
				if(result1.next()){
					int currentID = result1.getInt(1);
					//System.out.println(currentID);
					if(currentID != 0) {
						System.out.println("There is already a player 2");
						return false;
					}
				}
				
				
				// prepare SQL insert statement to add Author to Authors table
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
					//System.out.println(currentID);
					if(currentID == 0) {
						System.out.println("No player 1");
						return false;
					}
					// prepare SQL insert statement to add Author to Authors table
					stmt3 = conn.prepareStatement(
							"update userGames" +
							" set userGames.player2_id = ?" +
							" where userGames.game_id = ?"
					);
					stmt3.setInt(1, player2);
					//System.out.println("Player 2 ID" + player2);
					stmt3.setInt(2, game_id);
					//System.out.println("Game ID: " + game_id);
					
					// execute the update
					stmt3.executeUpdate();
					
				}
				
				DBUtil.closeQuietly(stmt1);
				DBUtil.closeQuietly(stmt2);
				DBUtil.closeQuietly(stmt3);
				
				//System.out.println("Made it to the end");
				return true;
				
			}
		});
	}
	
	public Integer insertNewGame(int player1) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				ResultSet result = null;
				int game_id = -1;
				
				// prepare SQL insert statement to add Author to Authors table
				stmt1 = conn.prepareStatement(
						"insert into userGames (player1_id)" +
						" values(?)"
				);
				stmt1.setInt(1, player1);	
				
				// execute the update
				stmt1.executeUpdate();
				
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
	
	public Integer insertGameEnd(int game_id, String finish, int winner) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				
				// prepare SQL insert statement to add Author to Authors table
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
	
	public ArrayList<String> getMoveList(int game_id) {
		return executeTransaction(new Transaction<ArrayList<String>>() {
			@Override
			public ArrayList<String> execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				ResultSet result = null;
				
				// prepare SQL insert statement to add Author to Authors table
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
	
	public ArrayList<Integer> getMoveListbyPieceID(int game_id) {
		return executeTransaction(new Transaction<ArrayList<Integer>>() {
			@Override
			public ArrayList<Integer> execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				ResultSet result = null;
				
				// prepare SQL insert statement to add Author to Authors table
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
	public ArrayList<String> getMoveListIncludingPieceID(int game_id) {
		return executeTransaction(new Transaction<ArrayList<String>>() {
			@Override
			public ArrayList<String> execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				ResultSet result = null;
				
				// prepare SQL insert statement to add Author to Authors table
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
	
	public Game insertNewMove(int id, String move, int piece_id) {
		return executeTransaction(new Transaction<Game>() {
			@Override
			public Game execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				
				// prepare SQL insert statement to add Author to Authors table
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
	
	@Override
	public ArrayList<Pair<ArrayList<String>, ArrayList<String>>> findAllGamesForUser(int id) {
		return executeTransaction(new Transaction<ArrayList<Pair<ArrayList<String>, ArrayList<String>>>>() {
			@Override
			public ArrayList<Pair<ArrayList<String>, ArrayList<String>>> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select userGames.*, moves.move" +
							" from users, moves, userGames" +
							" where ((? = userGames.player1_id)" +
							" or (? = userGames.player2_id))" +
							" order by userGames.game_id"
					);
					stmt.setInt(1, id);
					stmt.setInt(2, id);

					ArrayList<Pair<ArrayList<String>, ArrayList<String>>> result = new ArrayList<Pair<ArrayList<String>, ArrayList<String>>>();
					ArrayList<String> gameInfo = new ArrayList<String>();
					ArrayList<String> gameMoves = new ArrayList<String>();
					
					resultSet = stmt.executeQuery();

					// for testing that a result was returned
					Boolean found = false;
					Boolean first = true;
					int current_game_id = -1;
					String lastMove = null;
					
					while (resultSet.next()) {
						found = true;
						System.out.println(resultSet.getInt(1));
						if(resultSet.getInt(1) == current_game_id) {
							if(!resultSet.getString(6).equals(lastMove)) {
								gameMoves.add(resultSet.getString(6));
								lastMove = resultSet.getString(6);
							}
							
							
						}
						else if (resultSet.getInt(1) != -1){
							if(first == false) {
								result.add(new Pair<ArrayList<String>, ArrayList<String>>(gameInfo, gameMoves));
							
								gameInfo.clear();
								gameMoves.clear();
								
								
							}
							
							else {
								
								first = false;
								
							}
							
							//gameInfo = loadGameInfo(resultSet, 1);
							gameInfo.add(resultSet.getString(1));
							gameInfo.add(resultSet.getString(2));
							gameInfo.add(resultSet.getString(3));
							gameInfo.add(resultSet.getString(4));
							gameInfo.add(resultSet.getString(5));
							
							gameMoves.add(resultSet.getString(6));
							lastMove = resultSet.getString(6);
							
							
							current_game_id = resultSet.getInt(1);
							
							
						}
						
					}
					
					// check if any books were found
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
	
	public User insertNewUser(String username, String password, String question, String answer, String SALT) {
		return executeTransaction(new Transaction<User>() {
			@Override
			public User execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				
				// prepare SQL insert statement to add Author to Authors table
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
	
	@Override
	public Integer checkIfUserExists(String username) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;			
				ResultSet resultSet1 = null;
				

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
	
	public User getUserInfoByID(int user_id) {
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
	
	
	@Override
	public User updatePassword(String username, String Password) {
		return executeTransaction(new Transaction<User>() {
			@Override
			public User execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
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
	
	// retrieves Author information from query result set
	private void loadUser(User user, ResultSet resultSet, int index) throws SQLException {
		user.setUserID(resultSet.getInt(index++));
		user.setUser(resultSet.getString(index++));
		user.setPassword(resultSet.getString(index++));
		user.setSecurityQuestion(resultSet.getString(index++));
		user.setSecurityAnswer(resultSet.getString(index++));
		user.setSALT(resultSet.getString(index++));
		
	}
	
	private void loadMove(Game move, ResultSet resultSet, int index) throws SQLException {
		//move.getLastMove().getMove().(resultSet.getInt(index++));
		//move.setUser(resultSet.getString(index++));
		//move.setPassword(resultSet.getString(index++));
		//move.setSecurityQuestion(resultSet.getString(index++));
		//move.setSecurityAnswer(resultSet.getString(index++));
		
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

	// TODO: Here is where you name and specify the location of your Derby SQL database
	// TODO: Change it here and in SQLDemo.java under CS320_LibraryExample_Lab06->edu.ycp.cs320.sqldemo
	// TODO: DO NOT PUT THE DB IN THE SAME FOLDER AS YOUR PROJECT - that will cause conflicts later w/Git
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
		      db_username = myReader.nextLine();
		      db_password = myReader.nextLine();
		      port = myReader.nextLine();
		      db_hostname = myReader.nextLine();
		      
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		  	  
	      //Class.forName("com.mysql.cj.jdbc.Driver");
	      String jdbcUrl = "jdbc:mysql://" + db_hostname + ":" + port + "/" + db_name + "?user=" + db_username + "&password=" + db_password;
	      //System.out.println("Getting remote connection with connection string from environment variables.");
	      Connection conn = DriverManager.getConnection(jdbcUrl);
	      //System.out.println("Remote connection successful.");
	      return conn;
      }
	    //catch (ClassNotFoundException e) { 
	    //	System.out.println(e.toString());
    	//}
	    catch (SQLException e) { 
	    	System.out.println(e.toString());
	    }
	    
	    return null;
	  }
/*
		if(os.contains("Win")) {
			conn = DriverManager.getConnection("jdbc:derby:C:/CS320-2021-ChessGame-DB/chess.db;create=true");	
		}
		else {
			conn = DriverManager.getConnection("jdbc:derby:../../../CS320-2021-ChessGame-DB/chess.db;create=true");
		}
		// Set autocommit() to false to allow the execution of
		// multiple queries/statements as part of the same transaction.
		conn.setAutoCommit(false);
	
		return conn;
	}
*/
	/*
	
	// retrieves Author information from query result set
	private void loadAuthor(Author author, ResultSet resultSet, int index) throws SQLException {
		author.setAuthorId(resultSet.getInt(index++));
		author.setLastname(resultSet.getString(index++));
		author.setFirstname(resultSet.getString(index++));
	}
	
	// retrieves Book information from query result set
	private void loadBook(Book book, ResultSet resultSet, int index) throws SQLException {
		book.setBookId(resultSet.getInt(index++));
//		book.setAuthorId(resultSet.getInt(index++));  // no longer used
		book.setTitle(resultSet.getString(index++));
		book.setIsbn(resultSet.getString(index++));
		book.setPublished(resultSet.getInt(index++));
	}
	
	// retrieves WrittenBy information from query result set
	private void loadBookAuthors(BookAuthor bookAuthor, ResultSet resultSet, int index) throws SQLException {
		bookAuthor.setBookId(resultSet.getInt(index++));
		bookAuthor.setAuthorId(resultSet.getInt(index++));
	}
	
	*/
	
	
	
	//  creates the Authors and Books tables
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
						"  `salt` varchar(5) DEFAULT NULL," +
						"  `rankScore` int DEFAULT NULL," +
						"  PRIMARY KEY (`user-id`))"	
						
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
	  						"		`piece_id` int DEFAULT NULL," +
	  						"			PRIMARY KEY (`move_id`))"
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
	
	// loads data retrieved from CSV files into DB tables in batch mode
	//NO LONGER USED DUE TO AWS RDS
	/*
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
//						insertUser.setInt(1, user.getUserId());	// auto-generated primary key, don't insert this
						insertUsers.setString(1, user.getUser());
						insertUsers.setString(2, user.getPassword());
						insertUsers.setString(3, user.getSecurityQuestion());
						insertUsers.setString(4, user.getSecurityAnswer());
						insertUsers.addBatch();
					}
					insertUsers.executeBatch();
					System.out.println("Users table populated");	 
					
					insertMoves = conn.prepareStatement("insert into moves (game_id, move) values (?, ?)");
					for(Game game : gamesList) {
//						insertMoves.setInt(1, move.getMove_ID);	// auto-generated primary key, don't insert this
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
					
					return true;
				} finally {
					DBUtil.closeQuietly(insertUsers);	
					DBUtil.closeQuietly(insertMoves);	
					DBUtil.closeQuietly(insertUserGames);	
				}
			}
		});
	}
	*/
	// The main method creates the database tables and loads the initial data.
	public static void main(String[] args) throws IOException {
		System.out.println("Creating tables...");
		DerbyDatabase db = new DerbyDatabase();
		//db.createTables();
		
		//System.out.println("Loading initial data...");
		//db.loadInitialData();
		
		System.out.println("Library DB successfully initialized!");
	}
}
