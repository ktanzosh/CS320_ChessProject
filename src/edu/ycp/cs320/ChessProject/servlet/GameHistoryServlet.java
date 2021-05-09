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

		//Check that the user is logged in
		HttpSession userSession = req.getSession(false);
		
		if(userSession == null) {
			userSession = req.getSession(true);
			resp.sendRedirect("/ChessProject/loginPage");
			return;
		}
		
		//If user is logged in, get their game history
		else {
			User userModel = (User) userSession.getAttribute("userInfo");
			
			if(userModel == null) {
				resp.sendRedirect("/ChessProject/loginPage");
				return;
			}

			String username = userModel.getUser();
			int player_id = userModel.getUserID();
			req.setAttribute("username", username);
			ArrayList<Pair<ArrayList<String>, ArrayList<String>>> gameList = new ArrayList<Pair<ArrayList<String>, ArrayList<String>>>();
			
			//Get game history
			IDatabase db = DatabaseProvider.getInstance();
			gameList = db.findAllGamesForUser(player_id);
	
			//Sleep statements to ensure previous code ran
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			req.setAttribute("gameList", gameList);
			req.getRequestDispatcher("/_view/gameHistory.jsp").forward(req, resp);
		}
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		
		System.out.println("Game History Servlet: doPost");
			
			//Return to main screen if button clicked
			if(req.getParameter("index") != null) {
				resp.sendRedirect("/ChessProject/index");
				return;		
			}

		}
	
	//Print function for an ArrayList, found online at https://stackoverflow.com/questions/9265719/print-arraylist
	public static void printArrayListElements(ArrayList a) {
		for (int i = 0; i < a.size(); i++) {
			if (i == a.size()) {
				System.out.print(a.get(i));
			}
			System.out.print(a.get(i) + ", ");
		}
	}
}
