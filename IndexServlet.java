package edu.ycp.cs320.lab02a.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Index Servlet: doGet");
		
		//call JSP to generate form
		req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Index Servlet: doPost");
		
			//run add numbers if chosen
			if(req.getParameter("Add") != null) {
				AddNumbersServlet sum = new AddNumbersServlet();
				sum.doGet(req, resp);
			}
			
			//run guessing game is chosen
			else if(req.getParameter("Guess") != null) {
				GuessingGameServlet guess = new GuessingGameServlet();
				guess.doGet(req, resp);
			}
			
			//run multiply numbers if chosen
			else if(req.getParameter("Multiply") != null) {
				MultiplyNumbersServlet product = new MultiplyNumbersServlet();
				product.doGet(req, resp);
			}
	}

}
