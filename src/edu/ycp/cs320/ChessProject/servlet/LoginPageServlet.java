package edu.ycp.cs320.ChessProject.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import edu.ycp.cs320.ChessProject.controller.LoginPageController;
import edu.ycp.cs320.ChessProject.model.LoginPage;

public class LoginPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("LoginPage Servlet: doGet");	
		
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/loginPage.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("LoginPage Servlet: doPost");
		

		LoginPage model = new LoginPage();
		

		LoginPageController controller = new LoginPageController();
		controller.setModel(model);
		
		// holds the error message text, if there is any
		String errorMessage = null;
		
		String errorMessageInvalidC = null;

		
		// decode POSTed form parameters and dispatch to controller
		try {
			String user = getStringFromParameter(req, "user");
			String password = getStringFromParameter(req,"password");

			// check for errors in the form data before using is in a calculation
			if (user == null) {
				errorMessage = "Please enter a username";
			}
			
			if (password == null) {
				errorMessage = "Please enter a password";
			}
			
			// otherwise, data is good, do the calculation
			// must create the controller each time, since it doesn't persist between POSTs
			// the view does not alter data, only controller methods should be used for that
			// thus, always call a controller method to operate on the data
			else {
				model.setUser(user);
				model.setPassword(password);
				model.setInfo(controller.checkInfo(user, password));
			}
		} catch (NumberFormatException e) {
			errorMessage = "Invalid entry";
		}
		
		// Add parameters as request attributes
		// this creates attributes named "first" and "second for the response, and grabs the
		// values that were originally assigned to the request attributes, also named "first" and "second"
		// they don't have to be named the same, but in this case, since we are passing them back
		// and forth, it's a good idea
		req.setAttribute("model", model);
		//req.setAttribute("second", req.getParameter("second"));
		//req.setAttribute("third", req.getParameter("third"));
		
		// add result objects as attributes
		// this adds the errorMessage text and the result to the response
		req.setAttribute("errorMessage", errorMessage);
		//req.setAttribute("result", result);
		
		// Forward to view to render the result HTML document
		if (model.getInfo() == true) {
			req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
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
