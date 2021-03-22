package edu.ycp.cs320.ChessProject.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.ChessProject.controller.SecurityController;
import edu.ycp.cs320.ChessProject.model.Security;
import edu.ycp.cs320.ChessProject.model.PasswordReset;

public class SecurityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("Security Servlet: doGet");	
		
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/security.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Security Servlet: doPost");
		
		
		Security model = new Security();
		

		SecurityController controller = new SecurityController();
		controller.setModel(model);
		

		// holds the error message text, if there is any
		String errorMessage = null;
		
		String errorMessageInvalidC = null;
		
		// decode POSTed form parameters and dispatch to controller
		try {
			String securitya = getStringFromParameter(req, "securityAnswer");

			// check for errors in the form data before using is in a calculation
			if (securitya == null) {
				errorMessage = "Please fill in your answer";
			}
			
			else if(controller.isRightAnswer(securitya) == false)
			{
				errorMessage = "Incorrect security answer";
			}
			// otherwise, data is good, do the calculation
			// must create the controller each time, since it doesn't persist between POSTs
			// the view does not alter data, only controller methods should be used for that
			// thus, always call a controller method to operate on the data
			else {
				model.setSecurityAnswer(securitya);
				model.setInfo(controller.isRightAnswer(securitya));
			}
		} catch (NumberFormatException e) {
			errorMessage = "Invalid double";
		}
		
		// Add parameters as request attributes
		// this creates attributes named "first" and "second for the response, and grabs the
		// values that were originally assigned to the request attributes, also named "first" and "second"
		// they don't have to be named the same, but in this case, since we are passing them back
		// and forth, it's a good idea
		req.setAttribute("model", model);
		
		//req.setAttribute("first", req.getParameter("first"));
		//req.setAttribute("second", req.getParameter("second"));
		
		// add result objects as attributes
		// this adds the errorMessage text and the result to the response
		req.setAttribute("errorMessage", errorMessage);
		//req.setAttribute("result", product);
		
		// Forward to view to render the result HTML document
		//req.getRequestDispatcher("/_view/security.jsp").forward(req, resp);
		
		if (model.getInfo() == true) {
			//starts up the next information
			PasswordReset prModel = new PasswordReset();
			req.setAttribute("model", prModel);
			req.getRequestDispatcher("/_view/passwordReset.jsp").forward(req, resp);
			
		}
		else {
			errorMessageInvalidC = "Invalid username";
			req.setAttribute("errorMessage", errorMessageInvalidC);
			req.getRequestDispatcher("/_view/security.jsp").forward(req, resp);
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
