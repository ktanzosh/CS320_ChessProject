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
	

	<h1>Bye, ${username}</h1>

	<body>
		You have been logged out. </br></br>
		
		<form action="${pageContext.servletContext.contextPath}/logoutPage" method="POST">

			<input type="Submit" name="login" value="Log in"> </br>

		</form>
	</body>

</html>

