<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@page import="java.util.ArrayList"%>
<%@page import="edu.ycp.cs320.ChessProject.UserDatabase.Pair"%>
<html>
	<head>
		<title>Game History</title>
	</head>

	<link rel="stylesheet" type="text/css" href="_view/possibleLook1.css"/>
	
	<h1>Below are your past games, ${username}</h1>
	

	
	<body>
		<form action="${pageContext.servletContext.contextPath}/gameHistory" method="POST">

			<input type="Submit" name="index" value="Return to Main Menu"> </br>
	
		</form>
		</br>
		
		<% ArrayList<Pair<ArrayList<String>, ArrayList<String>>> gameList = (ArrayList<Pair<ArrayList<String>, ArrayList<String>>>)request.getAttribute("gameList");
 		
			for(Pair<ArrayList<String>, ArrayList<String>> game : gameList) {
				out.print("Game Information:");
				out.print("<br/>");
				out.print("Game ID: " + game.getLeft().get(0));
				out.print("<br/>");
				out.print("Player 1: " + game.getLeft().get(1));
				out.print("<br/>");
				out.print("Player 2: " + game.getLeft().get(2));
				out.print("<br/>");
				out.print("End Result: " + game.getLeft().get(3));
				out.print("<br/>");
				out.print("Winner: " + game.getLeft().get(4));
				out.print("<br/>");

				

				
				out.print("Move List: " + game.getRight());
				out.print("<br/>");	

				out.print("<br/>");
				out.print("<br/>");
			}
		%>
		
		
		
		
		<form action="${pageContext.servletContext.contextPath}/gameHistory" method="POST">

			<input type="Submit" name="index" value="Return to Main Menu"> </br>
	
		</form>

	</body>
</html>