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
import edu.ycp.cs320.ChessProject.Chess.Game;
import edu.ycp.cs320.ChessProject.Chess.Move;
import edu.ycp.cs320.ChessProject.UserDatabase.DatabaseProvider;
import edu.ycp.cs320.ChessProject.UserDatabase.IDatabase;

import edu.ycp.cs320.ChessProject.UserDatabase.User;

public class NewGameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.err.println("New Game: doGet");
		
		HttpSession userSession = req.getSession(false);
		
		
		if(userSession == null) {
			userSession = req.getSession(true);
			resp.sendRedirect("/ChessProject/loginPage");
			return;
		}
		
		else {
			IDatabase db = DatabaseProvider.getInstance();
			User userModel = (User) userSession.getAttribute("userInfo");
			
			if(userModel == null) {
				resp.sendRedirect("/ChessProject/loginPage");
				return;
			}
			
			Game sessionGame = new Game();
			sessionGame.setGame();
			
			int key = db.insertNewGame(userModel.getUserID());
			
			if(key == -1) {
				System.err.println("An error has occured in creating the game.");
			}
			else {
				sessionGame.setGameID(key);
			}
			
			ArrayList<String> moves = new ArrayList<String>();
			
			//HttpSession gameSession = req.getSession(true);
			Game tryGameSession = (Game) userSession.getAttribute("sessionGame");
			
			if(tryGameSession == null) {
				userSession.setAttribute("sessionGame", sessionGame);
				userSession.setAttribute("moves", moves);
				System.err.println("New Game stuff");
			}
			
			else {
				String valid = (String) userSession.getAttribute("validity");
				req.setAttribute("valid", valid);
				System.err.println("Found the validity");
				
			}
			
			
			String username = userModel.getUser();
			req.setAttribute("username", username);
			req.getRequestDispatcher("/_view/newGame.jsp").forward(req, resp);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		
		System.err.println("New Game Servlet: doPost");

		HttpSession userSession = req.getSession(false);
		Game playGame = (Game) userSession.getAttribute(("sessionGame"));
		
		ArrayList<String> moves = (ArrayList<String>) userSession.getAttribute("moves");
		
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
		String orig = total.substring(a+1, a+3);
		
		if(orig.contains("\""))
		{
			orig = total.substring(a+2, a+4);
		}
		int parsedOrig = Integer.parseInt(orig);
		
		int b = total.indexOf(":", a+1);
		
		int c = total.indexOf(":", b+1);
		boolean friendlyColor = false;
		String color = total.substring(c+2, c+3);
		System.out.println(color);
		
		//only if enemyColor is white do you change the value of enemyColor
		
		if(color.equals("w"))
		{
			friendlyColor = true;
			System.out.println("white");
		}
		else {
			friendlyColor = false;
			System.out.println("black");
		}
		
		int d = total.indexOf(":", c+1);
		
		String newOne = total.substring(d+1, d+3);
		if(newOne.contains("\""))
		{
			newOne = total.substring(d+2, d+4);
		}
		int parsedNew = Integer.parseInt(newOne);
		
		int e = total.indexOf(":", d+1);
		String promotion = total.substring(e+1, e+6);
		boolean prom = Boolean.parseBoolean(promotion);
		
		System.out.println("A: " + a + "B: " + b + "C: " + c + "D: " + d + "E: " + e + " " + promotion);
		
		int ix = 7 - ((parsedOrig - 1) / 8);
		int iy = (parsedOrig % 8) - 1;
		if(iy == -1)
		{
			iy = 7;
		}
		
		int dx = 7 - ((parsedNew - 1) / 8);
		int dy = (parsedNew % 8) - 1;
		if(dy == -1)
		{
			dy = 7;
		}

		System.out.println("Move from " + ix + ", " + iy + " to " + dx + ", " + dy);

		ChessPiece movePiece = playGame.getChessBoard().getTile(ix, iy).getPiece();
		String playerWhite = "White's move: ";
		String playerBlack = "Black's move: ";
		
		if(friendlyColor == true)
			
		{
			if(playGame.checkMove(dx, dy, playGame.getChessBoard(), movePiece, playGame.getWhitePlayer()) == true)
			{
				resp.getWriter().write("true");
				userSession.setAttribute("validity", "True");
				playGame.doMove(playGame.getChessBoard(), movePiece, dx, dy);
				userSession.setAttribute("sessionGame", playGame);
				Move sendMove = playGame.getLastMove(); //need
				String moveString = sendMove.getMove(); //need
				int id = playGame.getGameID();
				
				IDatabase db = DatabaseProvider.getInstance();
				db.insertNewMove(id, moveString);
				moves.add(System.lineSeparator() + playerWhite + moveString);
				userSession.setAttribute("moves", moves);
				
				if(prom == true) 
				{
					
					int f = total.indexOf(":", e+1);
					String piece = total.substring(f+2, f+6);
					System.out.println("Promotion time " + piece);
					//playGame.PawnPromotion(playGame.getWhitePieces(), piece);
				}
				
				resp.getWriter().write(playGame.getResult(playGame.getBlackPlayer(), playGame.getChessBoard(), playGame.getBlackKing(), playGame.getBlackPieces()));
			}
			
			else
			{
				resp.getWriter().write("false");
				userSession.setAttribute("validity", "False");
			}

		}
		
		else if(friendlyColor == false)
		{
			if(playGame.checkMove(dx, dy, playGame.getChessBoard(), movePiece, playGame.getBlackPlayer()) == true)
			{
				resp.getWriter().write("true");
				userSession.setAttribute("validity", "True");
				playGame.doMove(playGame.getChessBoard(), movePiece, dx, dy);
				userSession.setAttribute("sessionGame", playGame);
				Move sendMove = playGame.getLastMove();
				String moveString = sendMove.getMove();
				int id = playGame.getGameID();
				
				IDatabase db = DatabaseProvider.getInstance();
				db.insertNewMove(id, moveString);
				moves.add(System.lineSeparator() + playerBlack + moveString);
				userSession.setAttribute("moves", moves);
				
				if(prom == true) 
				{
					
					int f = total.indexOf(":", e+1);
					String piece = total.substring(f+2, f+6);
					System.out.println("Promotion time " + piece);
					//playGame.PawnPromotion(playGame.getBlackPieces(), piece);
				}
				
				resp.getWriter().write(playGame.getResult(playGame.getWhitePlayer(), playGame.getChessBoard(), playGame.getWhiteKing(), playGame.getWhitePieces()));
				//System.out.println(playGame.getResult(playGame.getWhitePlayer(), playGame.getChessBoard(), playGame.getWhiteKing(), playGame.getWhitePieces()));
			}
			
			else
			{
				resp.getWriter().write("false");
				userSession.setAttribute("validity", "False");
			}

		}
		
		playGame.printMoveList();
		
		if(req.getParameter("index") != null) 
		{
			resp.sendRedirect("/ChessProject/index");
			return;		
		}
		
		req.getRequestDispatcher("/_view/newGame.jsp").forward(req, resp);

		}
}

