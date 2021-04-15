package edu.ycp.cs320.ChessProject.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.ChessProject.UserDatabase.User;
import edu.ycp.cs320.ChessProject.UserDatabase.InitDatabase;
import edu.ycp.cs320.ChessProject.UserDatabase.DatabaseProvider;
import edu.ycp.cs320.ChessProject.UserDatabase.IDatabase;




public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("SignUpPage Servlet: doGet");	
		
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/signupPage.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("SignUpPage Servlet: doPost");
		

		User userModel = new User();
		String user = null;
		String password = null;
		String confirmPassword = null;
		String securityQ = null;
		String securityA = null;
		boolean userExists = false;
		boolean passwordMatch = false;
		boolean passwordLong = false;
		boolean answerShort = false;
		
		// holds the error message text, if there is any
		String errorMessage = null;
		
		String errorMessageInvalidC = null;

		
		// decode POSTed form parameters and dispatch to controller
		try {
			user = getStringFromParameter(req, "user");
			password = getStringFromParameter(req,"password");
			//System.out.println(password.length());
			confirmPassword = getStringFromParameter(req, "confirmPassword");
			securityQ = getStringFromParameter(req,"sec_question");
			//System.out.println(securityQ);
			securityA = getStringFromParameter(req,"sec_answer");

			// check for errors in the form data before using is in a calculation
			if (user == null) {
				errorMessage = "Please enter a username";
			}
			
			else if (password == null) {
				errorMessage = "Please enter a password";
			}
			
			else if (confirmPassword == null) {
				errorMessage = "Please enter the password confirmation";
			}
			
			else if (securityQ == null) {
				errorMessage = "Please choose a security question";
			}
			
			else if (securityA == null) {
				errorMessage = "Please enter a security answer";
			}
			
			if((password.equals(confirmPassword)) == true) {
				passwordMatch = true;
			}
			
			if((password.length() > 9) && (password.length() < 32)) {
				passwordLong = true;
			}
			
			if(securityA.length() < 32) {
				answerShort = true;
			}
			
			// otherwise, data is good, do the calculation
			// must create the controller each time, since it doesn't persist between POSTs
			// the view does not alter data, only controller methods should be used for that
			// thus, always call a controller method to operate on the data
			else {
				userExists = userModel.checkIfUserExists(user);
				
			}
		} catch (NumberFormatException e) {
			errorMessage = "Invalid entry";
		}
		
		// add result objects as attributes
		// this adds the errorMessage text and the result to the response
		req.setAttribute("errorMessage", errorMessage);
		
		// Forward to view to render the result HTML document
		if (!userExists == true) {
			if ((passwordMatch == true) && (passwordLong == true) && (answerShort == true)) {

				//TODO: CREATE NEW USER IN DATABASE
				InitDatabase.init();
				IDatabase db = DatabaseProvider.getInstance();
				String enc_password = User.encryptThisString(password);
				System.out.println(enc_password);
				String enc_secAnswer = User.encryptThisString(securityA);
				System.out.println(enc_secAnswer);
				db.insertNewUser(user, enc_password, securityQ, enc_secAnswer);
				
				req.getRequestDispatcher("/_view/loginPage.jsp").forward(req, resp);
			}
			
			else if (passwordMatch != true){
				errorMessageInvalidC = "Passwords do not Match.";
				req.setAttribute("errorMessage", errorMessageInvalidC);
				req.getRequestDispatcher("/_view/signupPage.jsp").forward(req, resp);
			}
			
			else if (passwordLong != true){
				if(password.length() > 31) {
					errorMessageInvalidC = "Password must be less than 32 characters long.";
				}
				else {
					errorMessageInvalidC = "Password must be at least 10 characters long.";
				}
				req.setAttribute("errorMessage", errorMessageInvalidC);
				req.getRequestDispatcher("/_view/signupPage.jsp").forward(req, resp);
			}
			
			else if (answerShort != true){
				errorMessageInvalidC = "Security Answer must be less than 32 characters long.";
				req.setAttribute("errorMessage", errorMessageInvalidC);
				req.getRequestDispatcher("/_view/signupPage.jsp").forward(req, resp);
			}
		}
		else {
			errorMessageInvalidC = "Username already taken.";
			req.setAttribute("errorMessage", errorMessageInvalidC);
			req.getRequestDispatcher("/_view/signupPage.jsp").forward(req, resp);
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
