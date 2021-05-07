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
			
			ArrayList<Pair<ArrayList<String>, ArrayList<String>>> gameList = new ArrayList<Pair<ArrayList<String>, ArrayList<String>>>();
			gameList = db.findAllGamesForUser(player_id);
			
			//int temp_num = 0;
			
			
			for (Pair<ArrayList<String>, ArrayList<String>> game : gameList) {
				ArrayList<String> gameInfo = game.getLeft();
				System.out.print("Game Information: ");
				printArrayListElements(gameInfo);
				System.out.println();

				ArrayList<String> gameMoves = game.getRight();
				System.out.print("Move List: ");
				printArrayListElements(gameMoves);
				System.out.println();
			}
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			req.setAttribute("gameList", gameList);
			
			req.setAttribute("moves", moves);
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
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
	
	public static void printArrayListElements(ArrayList a) {
		for (int i = 0; i < a.size(); i++) {
			if (i == a.size()) {
				System.out.print(a.get(i));
			}
			System.out.print(a.get(i) + ", ");
		}
	}
}
