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
			req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		
		System.out.println("Index Servlet: doPost");
			
			if(req.getParameter("newGame") != null) {
				resp.sendRedirect("/ChessProject/newGame");
				return;		
			}
			
			else if(req.getParameter("joinGame") != null) {
				resp.sendRedirect("/ChessProject/joinGame");
				return;		
			}
			
			else if(req.getParameter("gameHistory") != null) {
				resp.sendRedirect("/ChessProject/gameHistory");
				return;		
			}
			
			else if(req.getParameter("logout") != null) {
				resp.sendRedirect("/ChessProject/logoutPage");
				return;		
			}

		}

}