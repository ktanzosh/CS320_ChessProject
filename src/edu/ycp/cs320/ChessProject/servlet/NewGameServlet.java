package edu.ycp.cs320.ChessProject.servlet;

import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import edu.ycp.cs320.ChessProject.Chess.ChessPiece;
<<<<<<< Updated upstream
=======
import edu.ycp.cs320.ChessProject.Chess.Game;
import edu.ycp.cs320.ChessProject.Chess.Move;
import edu.ycp.cs320.ChessProject.UserDatabase.DatabaseProvider;
import edu.ycp.cs320.ChessProject.UserDatabase.IDatabase;

>>>>>>> Stashed changes
import edu.ycp.cs320.ChessProject.UserDatabase.User;

public class NewGameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("New Game: doGet");
		
		
		
		HttpSession userSession = req.getSession(false);
		
		if(userSession == null) {
			userSession = req.getSession(true);
			resp.sendRedirect("/ChessProject/loginPage");
			return;
		}
		
		else {
			User userModel = (User) userSession.getAttribute("userInfo");
			
			if(userModel == null) {
				resp.sendRedirect("/ChessProject/loginPage");
				return;
			}
			
<<<<<<< Updated upstream
=======
			Game sessionGame = new Game();
			sessionGame.setGame();
			sessionGame.setGameID(1);
			
			ArrayList<String> moves = new ArrayList<String>();
			
			HttpSession gameSession = req.getSession(true);
			gameSession.setAttribute("sessionGame", sessionGame);
			gameSession.setAttribute("moves", moves);
			
>>>>>>> Stashed changes
			String username = userModel.getUser();
			req.setAttribute("username", username);
			req.getRequestDispatcher("/_view/newGame.jsp").forward(req, resp);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		
		System.out.println("New Game Servlet: doPost");
<<<<<<< Updated upstream
		//System.out.println("New Game Servlet: doPost");
		//System.out.println("THE POST HAS BEEN ACTIVATED");
		
=======

		HttpSession gameSession = req.getSession(false);
		Game playGame = (Game) gameSession.getAttribute(("sessionGame"));
>>>>>>> Stashed changes
		
		ArrayList<String> moves = (ArrayList<String>) gameSession.getAttribute("moves");
		
		//IF THERE ARE NO MOVES***********************
		if(moves.isEmpty()) {
			System.out.println("no Games");
		}
		
		BufferedReader reader = req.getReader();
		
		String line = null;
		String total = "";
		while((line = reader.readLine()) != null) {
			//Parse per line?
			total+=line;
			System.out.println(line);
		}
		
		
		if(total.contains(":") != true)
		{
			resp.getWriter().write("You are missing some information to continue");
			resp.sendRedirect("/ChessProject/index");
			return;		
		}
	
		int a = total.indexOf(":");
<<<<<<< Updated upstream
		String color = total.substring(a, a+1);
		boolean enemyColor= false;
=======
		String orig = total.substring(a+1, a+3);
		
		if(orig.contains("\""))
		{
			orig = total.substring(a+2, a+4);
		}
		int parsedOrig = Integer.parseInt(orig);
		
		int b = total.indexOf(":", a+1);
		
		int c = total.indexOf(":", b+1);
<<<<<<< HEAD
		String color = total.substring(c+2, c+3);
		boolean friendlyColor= false;
=======
		boolean friendlyColor = false;
		String color = total.substring(c+2, c+3);
		System.out.println(color);
		
>>>>>>> 961e960f90cf798ef34cccb4247e2e707f9f2445
>>>>>>> Stashed changes
		//only if enemyColor is white do you change the value of enemyColor
		
		if(color.equals("w"))
		{
<<<<<<< Updated upstream
			enemyColor = true;
		}
		
		int b = total.indexOf(":", a);
		String orig = total.substring(b, b+2);
		int parsedOrig = Integer.parseInt(orig);
=======
			friendlyColor = true;
			System.out.println("white");
		}
		else {
			friendlyColor = false;
			System.out.println("black");
		}
		
		int d = total.indexOf(":", c+1);
		
		int e = total.indexOf(":", d+1);
		//get string but they are different sizes
		String pieceString = "";
		
		if(e != -1)
		{
			//getPieceString
		}
>>>>>>> Stashed changes
		
		int c = total.indexOf(":", b);
		String newOne = total.substring(c, c+2);
		int parsedNew = Integer.parseInt(newOne);
		
<<<<<<< Updated upstream
		int ix = 7 - ((parsedOrig - 1) / 8);
=======
<<<<<<< HEAD
		System.out.println("A: " + a + "B: " + b + "C: " + c + "D: " + d + "E: " + e);
		int ix = 7 - ((parsedOrig - 1) / 8);
=======
		//System.out.println("A: " + a + "B: " + b + "C: " + c + "D " + d);
		int ix = ((parsedOrig - 1) / 8);
>>>>>>> 961e960f90cf798ef34cccb4247e2e707f9f2445
>>>>>>> Stashed changes
		int iy = (parsedOrig % 8) - 1;
		if(iy == -1)
		{
			iy = 7;
		}
		
<<<<<<< Updated upstream
		int dx = 7 - ((parsedOrig - 1) / 8);
		int dy = (parsedOrig % 8) - 1;
=======
		int dx = 7 - ((parsedNew - 1) / 8);
		int dy = (parsedNew % 8) - 1;
>>>>>>> Stashed changes
		if(dy == -1)
		{
			dy = 7;
		}
<<<<<<< Updated upstream
		System.out.println("Move from " + ix + ", " + iy + " to " + dx + ", " + dy);
		//ChessPiece movePiece = SessionGame.getTile(ix, iy).getPiece();
		//if(movePiece.getColor() == true && enemyColor.equals("b")
		//resp.getWriter().write(SessionGame.checkMove(dx, dy, SessionGame.getChessboard());
		//SessionGame.doMove(movePiece, SessionGame.getChessBoard(), dx, dy);
		
		if(enemyColor == true)
		{
		//resp.getWriter().write(SessionGame.getResult(SessionGame.getWhitePlayer(), SessionGame.getChessboard(), SessionGame.getWhiteKing(), SessionGame.getWhitePieces());
=======
<<<<<<< HEAD
		
		System.out.println("Move from " + ix + ", " + iy + " to " + dx + ", " + dy + " with color " + color);
=======

		System.out.println("Move from " + ix + ", " + iy + " to " + dx + ", " + dy);


		playGame.printMoveList();

>>>>>>> 961e960f90cf798ef34cccb4247e2e707f9f2445
		
		ChessPiece movePiece = playGame.getChessBoard().getTile(ix, iy).getPiece();
		String playerWhite = "White's move: ";
		String playerBlack = "Black's move: ";
		
		if(friendlyColor == true)
			
		{
			if(playGame.checkMove(dx, dy, playGame.getChessBoard(), movePiece, playGame.getWhitePlayer()) == true)
			{
				resp.getWriter().write("true");
				playGame.doMove(playGame.getChessBoard(), movePiece, dx, dy);
				gameSession.setAttribute("sessionGame", playGame);
				Move sendMove = playGame.getLastMove(); //need
				String moveString = sendMove.getMove(); //need
				int id = playGame.getGameID();
				
				IDatabase db = DatabaseProvider.getInstance();
				db.insertNewMove(id, moveString);
				moves.add(System.lineSeparator() + playerWhite + moveString);
				gameSession.setAttribute("moves", moves);
				
				resp.getWriter().write(playGame.getResult(playGame.getBlackPlayer(), playGame.getChessBoard(), playGame.getBlackKing(), playGame.getBlackPieces()));
<<<<<<< HEAD
				//System.out.println(playGame.getResult(playGame.getBlackPlayer(), playGame.getChessBoard(), playGame.getBlackKing(), playGame.getBlackPieces()));
				
				if(e != -1)
				{
					playGame.PawnPromotion(playGame.getWhitePieces(), pieceString);
				}
=======
>>>>>>> 961e960f90cf798ef34cccb4247e2e707f9f2445
			}
			
			else
			{
				resp.getWriter().write("false");
				resp.getWriter().write("Invalid Move");
			}

>>>>>>> Stashed changes
		}
		else
		{
<<<<<<< Updated upstream
		//resp.getWriter().write(SessionGame.getResult(SessionGame.getBlackPlayer(), SessionGame.getChessboard(), SessionGame.getBlackKing(), SessionGame.getBlackPieces());
=======
			if(playGame.checkMove(dx, dy, playGame.getChessBoard(), movePiece, playGame.getBlackPlayer()) == true)
			{
				resp.getWriter().write("true");
				playGame.doMove(playGame.getChessBoard(), movePiece, dx, dy);
				gameSession.setAttribute("sessionGame", playGame);
				Move sendMove = playGame.getLastMove();
				String moveString = sendMove.getMove();
				int id = playGame.getGameID();
				
				IDatabase db = DatabaseProvider.getInstance();
				db.insertNewMove(id, moveString);
				moves.add(System.lineSeparator() + playerBlack + moveString);
				gameSession.setAttribute("moves", moves);
				
				resp.getWriter().write(playGame.getResult(playGame.getWhitePlayer(), playGame.getChessBoard(), playGame.getWhiteKing(), playGame.getWhitePieces()));
				//System.out.println(playGame.getResult(playGame.getWhitePlayer(), playGame.getChessBoard(), playGame.getWhiteKing(), playGame.getWhitePieces()));
				
				if(e != -1)
				{
					playGame.PawnPromotion(playGame.getBlackPieces(), pieceString);
				}
			}
			
			else
			{
				resp.getWriter().write("false");
				resp.getWriter().write("Invalid Move");
			}

		}
		
		if(req.getParameter("index") != null) 
		{
			resp.sendRedirect("/ChessProject/index");
			return;		
>>>>>>> Stashed changes
		}
			if(req.getParameter("index") != null) {
				resp.sendRedirect("/ChessProject/index");
				return;		
			}

		}
}

