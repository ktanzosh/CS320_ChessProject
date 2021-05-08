package edu.ycp.cs320.ChessProject.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.ChessProject.UserDatabase.User;

public class LoginPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("LoginPage Servlet: doGet");	
		
		HttpSession userSession = req.getSession(false);
		
		//Give login error message if sent from another web page
		if(userSession != null) {
			String errorMessageNoLogin = "Please Login";
			req.setAttribute("errorMessage", errorMessageNoLogin);
		}

		req.getRequestDispatcher("/_view/loginPage.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("LoginPage Servlet: doPost");
		
		User userModel = new User();
		boolean userFound = false;
		String user = null;
		String password = null;

		// holds the error message text, if there is any
		String errorMessage = null;
		
		String errorMessageInvalidC = null;

		
		// Get username and password
		try {
			user = getStringFromParameter(req, "user");
			password = getStringFromParameter(req,"password");

			if (user == null) {
				errorMessage = "Please enter a username";
			}
			
			else if (password == null) {
				errorMessage = "Please enter a password";
			}
			
			//Check user info if nothing is blank
			else {
				userFound = userModel.checkInfo(user, password);
			}
			
		} catch (NumberFormatException e) {
			errorMessage = "Invalid entry";
		}

		req.setAttribute("model", userModel);

		// this adds the errorMessage text and the result to the response
		req.setAttribute("errorMessage", errorMessage);

		
		// Set up session if log-in is valid
		if (userFound == true) {
			userModel = userModel.getUserInfo(user);
			HttpSession userSession = req.getSession(true);
			userSession.setAttribute("userInfo", userModel);
			resp.sendRedirect("/ChessProject/index");
			return;
		}
		
		//If info is not good, send error
		else {
			errorMessageInvalidC = "Invalid Username or Password";
			req.setAttribute("errorMessage", errorMessageInvalidC);
			req.getRequestDispatcher("/_view/loginPage.jsp").forward(req, resp);
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
