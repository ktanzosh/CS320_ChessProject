package edu.ycp.cs320.ChessProject.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
		//Get empty form of sign up info required
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

		
		// Get the sign up info
		try {
			user = getStringFromParameter(req, "user");
			password = getStringFromParameter(req,"password");
			confirmPassword = getStringFromParameter(req, "confirmPassword");
			securityQ = getStringFromParameter(req,"sec_question");
			securityA = getStringFromParameter(req,"sec_answer");

			// Check for any empty fields
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
			
			//Ensure passwords match
			if((password.equals(confirmPassword)) == true) {
				passwordMatch = true;
			}
			
			//Ensure password length is appropriate
			if((password.length() > 9) && (password.length() < 25)) {
				passwordLong = true;
			}
			
			//Check is security answer is appropriate
			if(securityA.length() < 25) {
				answerShort = true;
			}
			
			userExists = userModel.checkIfUserExists(user);
				
		} catch (NumberFormatException e) {
			errorMessage = "Invalid entry";
		}
		
		// this adds the errorMessage text and the result to the response
		req.setAttribute("errorMessage", errorMessage);
		
		// If user does not already exist, add the suer
		if (!userExists == true) {
			//ensure checks passed
			if ((passwordMatch == true) && (passwordLong == true) && (answerShort == true)) {

				InitDatabase.init();
				
				String[] pass_info = new String[2];
				pass_info = User.encryptThisString(password);
				String enc_password = pass_info[0];
				String salt = pass_info[1];
				//System.out.println("Hashed Password: " + enc_password);
				
				String enc_secAnswer = User.decryptThisString(securityA, salt);
				//System.out.println("Hashed Security Answer: " + enc_secAnswer);
				IDatabase db = DatabaseProvider.getInstance();
				db.insertNewUser(user, enc_password, securityQ, enc_secAnswer, salt);
				
				userModel = userModel.getUserInfo(user);
				HttpSession resetSession = req.getSession(true);
				resetSession.setAttribute("userInfo", userModel);
				String username = userModel.getUser();
				req.setAttribute("username", username);
				req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
			}
			
			// If password do not match, send error
			else if (passwordMatch != true){
				errorMessageInvalidC = "Passwords do not Match.";
				req.setAttribute("errorMessage", errorMessageInvalidC);
				req.getRequestDispatcher("/_view/signupPage.jsp").forward(req, resp);
			}
			
			// If password is wrong length, send error
			else if (passwordLong != true){
				if(password.length() > 31) {
					errorMessageInvalidC = "Password must be less than 25 characters long.";
				}
				else {
					errorMessageInvalidC = "Password must be at least 10 characters long.";
				}
				req.setAttribute("errorMessage", errorMessageInvalidC);
				req.getRequestDispatcher("/_view/signupPage.jsp").forward(req, resp);
			}
			
			// If security answer is too long, send error
			else if (answerShort != true){
				errorMessageInvalidC = "Security Answer must be less than 25 characters long.";
				req.setAttribute("errorMessage", errorMessageInvalidC);
				req.getRequestDispatcher("/_view/signupPage.jsp").forward(req, resp);
			}
		}
		
		//If username is not available 
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
