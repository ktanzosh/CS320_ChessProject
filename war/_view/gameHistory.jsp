<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Game History</title>
	</head>

	<link rel="stylesheet" type="text/css" href="_view/possibleLook1.css"/>
	
	<h1>Below are your past games, ${username}</h1>
	

	
	<body>
		<c:choose>
			<c:when test="${moves == null}">
				<!--The Third Step </br> -->
				
				It looks like you don't have any past games!

			</c:when> 

			<c:otherwise>    <!-- else condition -->
				<!-- The First Step </br> -->

				Here are your past moves!
				<h2>Your Game</h2>
				<p>${moves}</p>

			</c:otherwise>
	</c:choose>
		
		
		
		
		<form action="${pageContext.servletContext.contextPath}/gameHistory" method="POST">

			<input type="Submit" name="index" value="Return to Main Menu"> </br>
	
		</form>

	</body>
</html>