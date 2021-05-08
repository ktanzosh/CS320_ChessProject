package edu.ycp.cs320.ChessProject.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.ChessProject.UserDatabase.User;


public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Index Servlet / Main Menu: doGet");
		
		
		//Check if User is logged in
		HttpSession userSession = req.getSession(false);
		
		//Send to login page if not logged in
		if(userSession == null) {
			userSession = req.getSession(true);
			resp.sendRedirect("/ChessProject/loginPage");
			return;
		}
		
		//Display username if logged in
		else {
			User userModel = (User) userSession.getAttribute("userInfo");
			
			if(userModel == null) {
				resp.sendRedirect("/ChessProject/loginPage");
				return;
			}
			
			String username = userModel.getUser();
			req.setAttribute("username", username);
			req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		
		System.out.println("Index Servlet: doPost");
			
		//To a new Game if respective button is clicked
		if(req.getParameter("newGame") != null) {
			resp.sendRedirect("/ChessProject/newGame");
			return;		
		}
		
		//To enter a Game ID to a join a game if respective button is clicked
		else if(req.getParameter("joinGame") != null) {
			resp.sendRedirect("/ChessProject/joinGame");
			return;		
		}
		
		//To Game History if respective button is clicked
		else if(req.getParameter("gameHistory") != null) {
			resp.sendRedirect("/ChessProject/gameHistory");
			return;		
		}
		
		//To logout landing page if respective button is clicked
		else if(req.getParameter("logout") != null) {
			resp.sendRedirect("/ChessProject/logoutPage");
			return;		
		}

	}

}