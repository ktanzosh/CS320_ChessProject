<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Join Game</title>
	</head>

	<link rel="stylesheet" type="text/css" href="_view/possibleLook1.css"/>
	
	<h1>Enter the game ID to join a game, ${username}</h1>
	
    <style type="text/css">
        .error {
            color: red;
        }
    </style>

<body>
    <c:if test="${! empty errorMessage}">
        <div class="error">${errorMessage}</div>
    </c:if>

    <form action="${pageContext.servletContext.contextPath}/joinGame" method="post">
        <tr>
            <td class="label">Game ID:</td></br>
            <td><input type="text" name="gameID" size="12" /></td>
        </tr>
    </br>
    <input type="Submit" name="submit" value="Join Game"></br></br>

    <!--Main Menu Link-->
    <input type="Submit" name="main" value="Return to Main Menu"> </br></br>
</form>



</body>
</html>