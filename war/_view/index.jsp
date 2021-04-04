<!DOCTYPE html>

<html>
	<head>
		<title>Main Screen</title>
	</head>

	<style type="text/css">
	
	p{
		text-shadow: 2px 2px 5px black;
		font-size:40px;
	}
	</style>

	<link rel="stylesheet" type="text/css" href="_view/possibleLook.css"/>
	

	<h1>Hi, ${username}</h1>

	<body>
		Choose to start a new game or view your previous games. </br></br>
		
		<form action="${pageContext.servletContext.contextPath}/index" method="POST">

			<input type="Submit" name="newGame" value="Start a New Game Here!"> </br></br>

			<input type="Submit" name="gameHistory" value="See Game History Here!"> </br></br>

			<input type="Submit" name="logout" value="Log Out"> </br>

		</form>
	</body>

</html>

