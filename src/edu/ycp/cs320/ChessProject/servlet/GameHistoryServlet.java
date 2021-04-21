package edu.ycp.cs320.ChessProject.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.ChessProject.Chess.Game;
import edu.ycp.cs320.ChessProject.Chess.Move;
import edu.ycp.cs320.ChessProject.UserDatabase.User;


public class GameHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Game History: doGet");
		
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
			
			
			//connecting game class - kayla
			// get kevins code in here after he decodes?
			
			//User games = new User();
			//List<Game> userGames = games.getGameList();
			//games.getMoves();
			//System.out.println(userGames);
			//req.setAttribute("userGames", userGames);
			
			
			//MAYBE THIS WORKS
			Game game = new Game();
			
			ArrayList<Integer> moves = game.getMoves();
			System.out.println(moves);
			
			req.setAttribute("moves", moves);
		
			req.getRequestDispatcher("/_view/gameHistory.jsp").forward(req, resp);
		}

			
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		
		System.out.println("Game History Servlet: doPost");
			
			if(req.getParameter("index") != null) {
				resp.sendRedirect("/ChessProject/index");
				return;		
			}
			

		}
	
	
	private String getStringFromParameter(HttpServletRequest request, String name) {
		if (request.getParameter(name) == null || request.getParameter(name).equals("")) {
			return null;
		} 
		else {
			return new String (request.getParameter(name));
		}
	}
}
