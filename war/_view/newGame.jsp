<html>

<head>
<title>New Game</title>

<canvas id="myCanvas" width="600" height="600" style="border:1px solid #000000;">
Your browser does not support the HTML canvas tag.
</canvas>


<script>
	var c = document.getElementById("myCanvas");
	var ctx = c.getContext("2d");

	

	for(var i = 0; i < 600; i+=150)
	{
		for(var j = 0; j < 600; j+=150)
		{	
			ctx.fillStyle = "#1e140a";
			ctx.fillRect(j, i, 75, 75);
		}

		for(var j = 75; j < 600; j+=150)
		{
			ctx.fillStyle = "#472f17";
			ctx.fillRect(j, i, 75, 75);
		}
	}

	for(var i = 75; i < 600; i+=150)
	{
		for(var j = 75; j < 600; j+=150)
		{
			ctx.fillStyle = "#1e140a";
			ctx.fillRect(j, i, 75, 75);
		}

		for(var j = 0; j < 600; j+=150)
		{
			ctx.fillStyle = "#472f17";
			ctx.fillRect(j, i, 75, 75);
		}
	}
	
</script>
</body>
</html>