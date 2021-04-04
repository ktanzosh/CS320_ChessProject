<!DOCTYPE html>

<html>
	<head>
		<title>Game History</title>
	</head>

	<link rel="stylesheet" type="text/css" href="_view/possibleLook.css"/>
	
	<h1>Below are your past games, ${username}</h1>

	<body>
		Game history will be listed here
		<ul>
		  <li>Player A moved to </li>
		  <li>Player B moved to </li>
		  <li>Player A moved to </li>
		  <li>Player B moved to </li>
		  <li> . . . </li>
		</ul>
		
		<form action="${pageContext.servletContext.contextPath}/gameHistory" method="POST">

			<input type="Submit" name="index" value="Return to Main Menu"> </br>

		</form>

	</body>
</html>