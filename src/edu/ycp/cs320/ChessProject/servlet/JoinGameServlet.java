package edu.ycp.cs320.ChessProject.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.ChessProject.Chess.Game;
import edu.ycp.cs320.ChessProject.UserDatabase.DatabaseProvider;
import edu.ycp.cs320.ChessProject.UserDatabase.IDatabase;
import edu.ycp.cs320.ChessProject.UserDatabase.User;

public class JoinGameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Join Game: doGet");
		
		//Check if user is logged in
		HttpSession userSession = req.getSession(false);
		
		//If not logged in, send to the login page
		if(userSession == null) {
			userSession = req.getSession(true);
			resp.sendRedirect("/ChessProject/loginPage");
			return;
		}
		
		// If logged in, display username
		else {
			User userModel = (User) userSession.getAttribute("userInfo");
			
			if(userModel == null) {
				resp.sendRedirect("/ChessProject/loginPage");
				return;
			}
			
			String username = userModel.getUser();
			req.setAttribute("username", username);
			req.getRequestDispatcher("/_view/joinGame.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Join Game Servlet: doPost");
		
		String stringGameID = null;
		
		// holds the error message text, if there is any
		String errorMessage = null;
		String errorMessageInvalidC = null;
		
		HttpSession userSession = req.getSession(false);
		User user = (User) userSession.getAttribute("userInfo");
		
		//If main screen button is clicked, send to main screen
		if(req.getParameter("main") != null) {
			resp.sendRedirect("/ChessProject/index");
			return;		
		}
		
		// Get game id, if entered
		else {
			// Get game ID
			try {
				stringGameID = getStringFromParameter(req, "gameID");
				
				if (stringGameID == null) {
					errorMessage = "Please enter a Game ID";
				}
			}
			catch (NumberFormatException e) {
				errorMessage = "Invalid entry";
			}
			
		}
		if(errorMessage == null) {
			
			int player2ID = user.getUserID();
			int gameID = Integer.parseInt(stringGameID);

			//Check if user can join game
			IDatabase db = DatabaseProvider.getInstance();
			boolean success = db.insertSecondPlayer(player2ID, gameID);
			
			//If join was successful, setup session for game to interact with NewGameServlet
			if(success == true) {
				Game sessionGame = new Game();
				sessionGame.setGame();
				sessionGame.setGameID(gameID);
				ArrayList<String> moves = new ArrayList<String>();

				userSession.setAttribute("sessionGame", sessionGame);
				userSession.setAttribute("moves", moves);
				
				System.err.println("Joining the Game");
				
				resp.sendRedirect("/ChessProject/newGame");
				return;		
			}
			
			// If game ID is not good, send error
			else {
				errorMessageInvalidC = "Invalid Game ID.";
				req.setAttribute("errorMessage", errorMessageInvalidC);
				String username = user.getUser();
				req.setAttribute("username", username);
				req.getRequestDispatcher("/_view/joinGame.jsp").forward(req, resp);
			}
		}
		
		else {
			req.setAttribute("errorMessage", errorMessage);
			String username = user.getUser();
			req.setAttribute("username", username);
			req.getRequestDispatcher("/_view/joinGame.jsp").forward(req, resp);
		}
	}

	// gets double from the request with attribute named s
	private String getStringFromParameter(HttpServletRequest request, String name) {
		if (request.getParameter(name) == null || request.getParameter(name).equals("")) {
			return null;
		} 
		else {
			return new String (request.getParameter(name));
		}
	}
	
}
