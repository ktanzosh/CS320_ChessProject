package edu.ycp.cs320.ChessProject.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.ChessProject.UserDatabase.User;
//import edu.ycp.cs320.ChessProject.controller.LoginPageController;
//import edu.ycp.cs320.ChessProject.model.LoginPage;

public class LoginPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("LoginPage Servlet: doGet");	
		
		HttpSession userSession = req.getSession(false);
		
		if(userSession != null) {
			String errorMessageNoLogin = "Please Login";
			req.setAttribute("errorMessage", errorMessageNoLogin);
		}
		
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/loginPage.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("LoginPage Servlet: doPost");
		

		//LoginPage loginModel = new LoginPage();
		User userModel = new User();
		boolean userFound = false;
		String user = null;
		String password = null;

		//LoginPageController controller = new LoginPageController();
		//controller.setModel(model);
		
		// holds the error message text, if there is any
		String errorMessage = null;
		
		String errorMessageInvalidC = null;

		
		// decode POSTed form parameters and dispatch to controller
		try {
			user = getStringFromParameter(req, "user");
			password = getStringFromParameter(req,"password");

			// check for errors in the form data before using is in a calculation
			if (user == null) {
				errorMessage = "Please enter a username";
			}
			
			else if (password == null) {
				errorMessage = "Please enter a password";
			}
			
			// otherwise, data is good, do the calculation
			// must create the controller each time, since it doesn't persist between POSTs
			// the view does not alter data, only controller methods should be used for that
			// thus, always call a controller method to operate on the data
			else {
				//userFound = userModel.checkInfo(user, password);
				userFound = true;
			}
			
		} catch (NumberFormatException e) {
			errorMessage = "Invalid entry";
		}
		
		// Add parameters as request attributes
		// this creates attributes named "first" and "second for the response, and grabs the
		// values that were originally assigned to the request attributes, also named "first" and "second"
		// they don't have to be named the same, but in this case, since we are passing them back
		// and forth, it's a good idea
		req.setAttribute("model", userModel);
		//req.setAttribute("second", req.getParameter("second"));
		//req.setAttribute("third", req.getParameter("third"));
		
		// add result objects as attributes
		// this adds the errorMessage text and the result to the response
		req.setAttribute("errorMessage", errorMessage);
		//req.setAttribute("result", result);
		
		// Forward to view to render the result HTML document
		if (userFound == true) {
			userModel = userModel.getUserInfo(user);
			HttpSession userSession = req.getSession(true);
			userSession.setAttribute("userInfo", userModel);
			resp.sendRedirect("/ChessProject/index");
			return;
		}
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
