package edu.ycp.cs320.ChessProject.servlet;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.ChessProject.Chess.ChessPiece;
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
		//System.out.println("New Game Servlet: doPost");
		//System.out.println("THE POST HAS BEEN ACTIVATED");
		
		
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
		String color = total.substring(a, a+1);
		boolean enemyColor= false;
		//only if enemyColor is white do you change the value of enemyColor
		if(color.equals("w"))
		{
			enemyColor = true;
		}
		
		int b = total.indexOf(":", a);
		String orig = total.substring(b, b+2);
		int parsedOrig = Integer.parseInt(orig);
		
		int c = total.indexOf(":", b);
		String newOne = total.substring(c, c+2);
		int parsedNew = Integer.parseInt(newOne);
		
		int ix = 7 - ((parsedOrig - 1) / 8);
		int iy = (parsedOrig % 8) - 1;
		if(iy == -1)
		{
			iy = 7;
		}
		
		int dx = 7 - ((parsedOrig - 1) / 8);
		int dy = (parsedOrig % 8) - 1;
		if(dy == -1)
		{
			dy = 7;
		}
		System.out.println("Move from " + ix + ", " + iy + " to " + dx + ", " + dy);
		//ChessPiece movePiece = SessionGame.getTile(ix, iy).getPiece();
		//if(movePiece.getColor() == true && enemyColor.equals("b")
		//resp.getWriter().write(SessionGame.checkMove(dx, dy, SessionGame.getChessboard());
		//SessionGame.doMove(movePiece, SessionGame.getChessBoard(), dx, dy);
		
		if(enemyColor == true)
		{
		//resp.getWriter().write(SessionGame.getResult(SessionGame.getWhitePlayer(), SessionGame.getChessboard(), SessionGame.getWhiteKing(), SessionGame.getWhitePieces());
		}
		else
		{
		//resp.getWriter().write(SessionGame.getResult(SessionGame.getBlackPlayer(), SessionGame.getChessboard(), SessionGame.getBlackKing(), SessionGame.getBlackPieces());
		}
			if(req.getParameter("index") != null) {
				resp.sendRedirect("/ChessProject/index");
				return;		
			}

		}
}

