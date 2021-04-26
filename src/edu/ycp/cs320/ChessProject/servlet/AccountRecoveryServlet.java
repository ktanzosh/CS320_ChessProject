package edu.ycp.cs320.ChessProject.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.ChessProject.UserDatabase.User;

public class AccountRecoveryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("Account Recovery Servlet: doGet");	
		
		HttpSession resetSession = req.getSession(true);
		
		boolean securityQAnswered = false;
		resetSession.setAttribute("securityQAnswered", securityQAnswered);
		req.setAttribute("securityQAnswered", securityQAnswered);
		
		boolean usernameFound = false;
		resetSession.setAttribute("usernameFound", usernameFound);
		req.setAttribute("usernameFound", usernameFound);
		
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/accountRecovery.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
		System.out.println("Account Recovery Servlet: doPost");
		HttpSession resetSession = req.getSession(false);
		//HttpSession userSession = req.getSession(false);
		//String securityQAnswereds = "filled";
		//req.setAttribute("securityQAnswereds", securityQAnswereds);
		//String usernameFounds = null;
		//req.setAttribute("usernameFounds", usernameFounds);
		//boolean securityQAnswered = false;
		//boolean usernameFound = false;
		
		if((boolean) (resetSession.getAttribute("usernameFound")) == false) {
			//resetSession = req.getSession(true);
			//AccountRecovery recoverModel = new AccountRecovery();
			User userModel = new User();
			boolean usernameFound = false;
			String user = null;
			//AccountRecoveryController controller = new AccountRecoveryController();
			//controller.setModel(model);
			
			// holds the error message text, if there is any
			String errorMessage = null;
			String errorMessageInvalidC = null;
			
			// decode POSTed form parameters and dispatch to controller
			try {
				user = getStringFromParameter(req, "username");
				usernameFound = userModel.checkIfUserExists(user);
					
			} catch (NumberFormatException e) {
				errorMessage = "Invalid Entry";
			}
			
			// add result objects as attributes
			// this adds the errorMessage text and the result to the response
			req.setAttribute("errorMessage", errorMessage);
			//req.setAttribute("result", product);
			
			// Forward to view to render the result HTML document
			if (usernameFound == true) {		
				resetSession.setAttribute("usernameFound", true);
				userModel = userModel.getUserInfo(user);
				resetSession.setAttribute("userInfo", userModel);
				String username = userModel.getUser();
				req.setAttribute("username", username);
				String securityQuestion = userModel.getSecurityQuestion();				
				req.setAttribute("securityQuestion", securityQuestion);
				req.getRequestDispatcher("/_view/accountRecovery.jsp").forward(req, resp);
			}
			else {
			errorMessageInvalidC = "Invalid Username";
			req.setAttribute("errorMessage", errorMessageInvalidC);
			req.getRequestDispatcher("/_view/accountRecovery.jsp").forward(req, resp);
			}
		}
		
		else if((boolean) (resetSession.getAttribute("securityQAnswered")) == true) {
			boolean passwordsMatch = false;
			User userModel = new User();
			String newpass = null;
			String passcompare = null;
			//AccountRecovery acctModel = new AccountRecovery();
			
			// holds the error message text, if there is any
			String errorMessage = null;
			String errorMessageInvalidC = null;
			
			// decode POSTed form parameters and dispatch to controller
			try {
				newpass = getStringFromParameter(req, "newPassword");
				passcompare = getStringFromParameter(req, "checkPassword");
				

				if(newpass.equals(passcompare)) {
					passwordsMatch = true;
				}

			} catch (NumberFormatException e) {
				errorMessage = "Invalid Input";
			}

			// add result objects as attributes
			// this adds the errorMessage text and the result to the response
			req.setAttribute("errorMessage", errorMessage);
			//req.setAttribute("result", product);
			
			// Forward to view to render the result HTML document
			//req.getRequestDispatcher("/_view/security.jsp").forward(req, resp);
			
			if (passwordsMatch == true) {
				userModel = (User) resetSession.getAttribute("userInfo");
				String username = userModel.getUser();
				String salt = userModel.getSALT();
				userModel.setNewPassword(username, newpass, salt);
				resp.sendRedirect("/ChessProject/index");
				return;
				
			}
			else {
				errorMessageInvalidC = "Passwords Do Not Match";
				req.setAttribute("errorMessage", errorMessageInvalidC);
				String username = userModel.getUser();
				req.setAttribute("username", username);
				String securityQuestion = userModel.getSecurityQuestion();			
				req.setAttribute("securityQuestion", securityQuestion);
				String securityAnswer = (String) resetSession.getAttribute("securityAnswer");			
				req.setAttribute("securityAnswer", securityAnswer);
				req.getRequestDispatcher("/_view/accountRecovery.jsp").forward(req, resp);
			}
		}
		
		else if((boolean) (resetSession.getAttribute("usernameFound")) == true) {
			boolean securityAnswerCorrect = false;
			User userModel = new User();
			// holds the error message text, if there is any
			String errorMessage = null;
			String errorMessageInvalidC = null;
			String securitya = null;
			// decode POSTed form parameters and dispatch to controller
			try {
				securitya = getStringFromParameter(req, "securityAnswer");
				userModel = (User) resetSession.getAttribute("userInfo");
				String user = userModel.getUser();
				String SALT = userModel.getSALT();
				securityAnswerCorrect = userModel.checkUserSecurityAnswer(user, securitya, SALT);

			} catch (NumberFormatException e) {
				errorMessage = "Invalid Input";
			}

			// add result objects as attributes
			// this adds the errorMessage text and the result to the response
			req.setAttribute("errorMessage", errorMessage);
			//req.setAttribute("result", product);
			
			// Forward to view to render the result HTML document
			//req.getRequestDispatcher("/_view/security.jsp").forward(req, resp);
			
			if (securityAnswerCorrect == true) {
				resetSession.setAttribute("securityQAnswered", true);
				//String securityAnswer = userModel.getSecurityAnswer();
				req.setAttribute("securityAnswer", securitya);
				resetSession.setAttribute("securityAnswer", securitya);
				String username = userModel.getUser();
				req.setAttribute("username", username);
				String securityQuestion = userModel.getSecurityQuestion();				
				req.setAttribute("securityQuestion", securityQuestion);
				req.getRequestDispatcher("/_view/accountRecovery.jsp").forward(req, resp);
				
			}
			else {
				errorMessageInvalidC = "Incorrect Security Answer";
				req.setAttribute("errorMessage", errorMessageInvalidC);
				String username = userModel.getUser();
				req.setAttribute("username", username);
				String securityQuestion = userModel.getSecurityQuestion();				
				req.setAttribute("securityQuestion", securityQuestion);
				req.getRequestDispatcher("/_view/accountRecovery.jsp").forward(req, resp);
			}
		}
		
		
		
	}

	// gets double from the request with attribute named s
	private String getStringFromParameter(HttpServletRequest request, String name) {
        if (request.getParameter(name) == null || request.getParameter(name).equals("")) {
            return null;
        } else {
            return new String (request.getParameter(name));
        }
    }
}
