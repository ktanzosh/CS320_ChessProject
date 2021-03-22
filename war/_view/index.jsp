<!DOCTYPE html>

<html>
	<head>
		<title>Index view</title>
	</head>
<style type="text/css">
p{
	text-shadow: 2px 2px 5px black;
	font-size:40px;
}
</style>

<link rel="stylesheet" type="text/css" href="_view/possibleLook.css"/>
<h1>Hi,
${model.user}</h1>

	<body>
		Pick what you want todo
		<br/><br/>
		<a href = "newGame">
		<input type="Submit" name="submit" value="Start a New Game Here!">
		<br/><br/>
		</a>
		
		<a href = "gameHistory">
		<input type="Submit" name="submit" value="See Game History Here!">
		<br/><br/>
		</a>

	</body>
</html>
