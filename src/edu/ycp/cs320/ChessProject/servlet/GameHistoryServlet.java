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
import edu.ycp.cs320.ChessProject.UserDatabase.DatabaseProvider;
import edu.ycp.cs320.ChessProject.UserDatabase.IDatabase;
import edu.ycp.cs320.ChessProject.UserDatabase.User;
import edu.ycp.cs320.ChessProject.UserDatabase.Pair;


public class GameHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Game History: doGet");
		boolean gamesExist;
		
		HttpSession userSession = req.getSession(false);
		HttpSession gameSession = req.getSession(false);
		
		
		if(userSession == null) {
			userSession = req.getSession(true);
			resp.sendRedirect("/ChessProject/loginPage");
			return;
		}
		
		if(gameSession == null) {
			gamesExist = false;
			req.setAttribute("gamesExist", gamesExist);
		}
		
		
		else {
			User userModel = (User) userSession.getAttribute("userInfo");
			
			if(userModel == null) {
				resp.sendRedirect("/ChessProject/loginPage");
				return;
			}

			
			//arraylist of moves
			ArrayList<String> moves = (ArrayList<String>) gameSession.getAttribute("moves");
			String username = userModel.getUser();
			int player_id = userModel.getUserID();
			req.setAttribute("username", username);
			
			IDatabase db = DatabaseProvider.getInstance();
			ArrayList<Pair<ArrayList<String>, ArrayList<String>>> gameList = db.findAllGamesForUser(player_id);
			ArrayList<Pair<ArrayList<String>, ArrayList<String>>> newGameList = new ArrayList<Pair<ArrayList<String>, ArrayList<String>>>();
			
			
			ArrayList<String> gameInfo = null;
			ArrayList<String> moveList = null;
			ArrayList<String> newGameInfo = new ArrayList<String>();
			String convertInfo = null;
			int user_id = 0;
			User user = new User();
			
			for (Pair<ArrayList<String>, ArrayList<String>> game : gameList) {
				gameInfo = game.getLeft();
				newGameInfo.add(gameInfo.get(1));
				
				convertInfo = gameInfo.get(2);
				user_id = Integer.parseInt(convertInfo);
				user = db.getUserInfoByID(user_id);
				newGameInfo.add(user.getUser());
				
				convertInfo = gameInfo.get(3);
				user_id = Integer.parseInt(convertInfo);
				user = db.getUserInfoByID(user_id);
				newGameInfo.add(user.getUser());
				
				newGameInfo.add(gameInfo.get(4));
				newGameInfo.add(gameInfo.get(5));
				
				moveList = game.getRight();
				newGameList.add(new Pair<ArrayList<String>, ArrayList<String>>(newGameInfo, moveList));
			}
			
			req.setAttribute("gameList", newGameList);
			
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
