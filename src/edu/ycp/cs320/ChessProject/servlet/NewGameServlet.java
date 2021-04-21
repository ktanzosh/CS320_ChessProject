package edu.ycp.cs320.ChessProject.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import edu.ycp.cs320.ChessProject.Chess.ChessPiece;
import edu.ycp.cs320.ChessProject.Chess.Game;
import edu.ycp.cs320.ChessProject.Chess.Move;
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
			
			String username = userModel.getUser();
			req.setAttribute("username", username);
			req.getRequestDispatcher("/_view/newGame.jsp").forward(req, resp);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		
		System.out.println("New Game Servlet: doPost");
		//System.out.println("THE POST HAS BEEN ACTIVATED");
		Game newGame = new Game();
		newGame.setGame();
		
		
		
		
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
		String color = total.substring(c+1, c+2);
		boolean friendlyColor= false;
		//only if enemyColor is white do you change the value of enemyColor
		if(color.equals("w"))
		{
			friendlyColor = true;
		}
		
		int d = total.indexOf(":", c+1);
		
		
		String newOne = total.substring(d+1, d+3);
		if(newOne.contains("\""))
		{
			newOne = total.substring(d+2, d+4);
		}
		int parsedNew = Integer.parseInt(newOne);
		
		//System.out.println("A: " + a + "B: " + b + "C: " + c + "D " + d);
		int ix = ((parsedOrig - 1) / 8);
		int iy = (parsedOrig % 8) - 1;
		if(iy == -1)
		{
			iy = 7;
		}
		
		int dx = ((parsedNew - 1) / 8);
		int dy = (parsedNew % 8) - 1;
		if(dy == -1)
		{
			dy = 7;
		}

		System.out.println("Move from " + ix + ", " + iy + " to " + dx + ", " + dy);

		
		
		
		
		
		
		
		ChessPiece movePiece = newGame.getChessBoard().getTile(ix, iy).getPiece();
		if(friendlyColor == true)
		{
			if(newGame.checkMove(dx, dy, newGame.getChessBoard(), movePiece, newGame.getWhitePlayer()) == true)
			{
				resp.getWriter().write("true");
				newGame.doMove(newGame.getChessBoard(), movePiece, dx, dy);
			}
			
			else
			{
				resp.getWriter().write("false");
			}
			
			resp.getWriter().write(newGame.getResult(newGame.getBlackPlayer(), newGame.getChessBoard(), newGame.getBlackKing(), newGame.getBlackPieces()));
			//System.out.println(newGame.getResult(newGame.getBlackPlayer(), newGame.getChessBoard(), newGame.getBlackKing(), newGame.getBlackPieces()));
		}

		
		
		//req.getRequestDispatcher("/_view/gameHistory.jsp").forward(req, resp);
		//System.out.println("posted to game history page?");

		
		else if(friendlyColor == false)
		{
			if(newGame.checkMove(dx, dy, newGame.getChessBoard(), movePiece, newGame.getBlackPlayer()) == true)
			{
				resp.getWriter().write("true");
				newGame.doMove(newGame.getChessBoard(), movePiece, dx, dy);
			}
			
			else
			{
				resp.getWriter().write("false");
			}
			
			resp.getWriter().write(newGame.getResult(newGame.getWhitePlayer(), newGame.getChessBoard(), newGame.getWhiteKing(), newGame.getWhitePieces()));
			//System.out.println(newGame.getResult(newGame.getWhitePlayer(), newGame.getChessBoard(), newGame.getWhiteKing(), newGame.getWhitePieces()));
		}
		
		if(req.getParameter("index") != null) 
		{
			resp.sendRedirect("/ChessProject/index");
			return;		
		}

		}
}

