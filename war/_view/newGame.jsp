<html>

<head>
<title>New Game</title>

<style type="text/css">

body{
	margin: 0;
	background-image: linear-gradient(to bottom right, blue, gray);
	background-attachment: fixed;
	height: 100%;
	padding: 20px;
}
#whitePawn{
	position: absolute;
	top: 475px;
	left: 30px;
}
#whitePawn1{
	position: absolute;
	top: 475px;
	left: 105px;
}
#whitePawn2{
	position: absolute;
	top: 475px;
	left: 180px;
}
#whitePawn3{
	position: absolute;
	top: 475px;
	left: 255px;
}
#whitePawn4{
	position: absolute;
	top: 475px;
	left: 330px;
}
#whitePawn5{
	position: absolute;
	top: 475px;
	left: 405px;
}
#whitePawn6{
	position: absolute;
	top: 475px;
	left: 480px;
}
#whitePawn7{
	position: absolute;
	top: 475px;
	left: 555px;
}



#whiteKnight{
position: absolute;
	top: 550px;
	left: 105px;
}
#whiteKnight1{
position: absolute;
	top: 550px;
	left: 480px;
}


#whiteBishop{
position: absolute;
	top: 550px;
	left: 180px;
}
#whiteBishop1{
position: absolute;
	top: 550px;
	left: 405px;
}



#whiteKing{
position: absolute;
	top: 550px;
	left: 330px;
}
#whiteQueen{
position: absolute;
	top: 550px;
	left: 255px;
}

#whiteRook{
position: absolute;
	top: 550px;
	left: 30px;
}
#whiteRook1{
position: absolute;
	top: 550px;
	left: 555px;
}

</style>


<canvas id="myCanvas" width="600" height="600" style="border:1px solid #000000;">
Your browser does not support the HTML canvas tag.
</canvas>


<body>
<img id="whitePawn" src="_view/whitePawn.png" width="60" height="60">
<img id="whitePawn1" src="_view/whitePawn.png" width="60" height="60">
<img id="whitePawn2" src="_view/whitePawn.png" width="60" height="60">
<img id="whitePawn3" src="_view/whitePawn.png" width="60" height="60">
<img id="whitePawn4" src="_view/whitePawn.png" width="60" height="60">
<img id="whitePawn5" src="_view/whitePawn.png" width="60" height="60">
<img id="whitePawn6" src="_view/whitePawn.png" width="60" height="60">
<img id="whitePawn7" src="_view/whitePawn.png" width="60" height="60">

<img id="whiteKnight" src="_view/whiteKnight.png" width="60" height="60">
<img id="whiteKnight1" src="_view/whiteKnight.png" width="60" height="60">

<img id="whiteBishop" src="_view/whiteBishop.png" width="60" height="60">
<img id="whiteBishop1" src="_view/whiteBishop.png" width="60" height="60">

<img id="whiteKing" src="_view/whiteKing.png" width="60" height="60">
<img id="whiteQueen" src="_view/whiteQueen.png" width="60" height="60">

<img id="whiteRook" src="_view/whiteRook.png" width="60" height="60">
<img id="whiteRook1" src="_view/whiteRook.png" width="60" height="60">

<script>
	
	var c = document.getElementById("myCanvas");
	var ctx = c.getContext("2d");
	
whitePawn.onmousedown = function(event) {
  let shiftX = event.clientX - whitePawn.getBoundingClientRect().left;
  let shiftY = event.clientY - whitePawn.getBoundingClientRect().top;

  whitePawn.style.position = 'absolute';
  whitePawn.style.zIndex = 1000;
  document.body.append(whitePawn);

  moveAt(event.pageX, event.pageY);

  function moveAt(pageX, pageY) {
    whitePawn.style.left = pageX - shiftX + 'px';
    whitePawn.style.top = pageY - shiftY + 'px';
  }

  function onMouseMove(event) {
    moveAt(event.pageX, event.pageY);
  }

  document.addEventListener('mousemove', onMouseMove);

  whitePawn.onmouseup = function() {
    document.removeEventListener('mousemove', onMouseMove);
    whitePawn.onmouseup = null;
  };

};

whitePawn.ondragstart = function() {
  return false;
};

//*****************************************************************************
whitePawn1.onmousedown = function(event) {
  let shiftX = event.clientX - whitePawn1.getBoundingClientRect().left;
  let shiftY = event.clientY - whitePawn1.getBoundingClientRect().top;

  whitePawn1.style.position = 'absolute';
  whitePawn1.style.zIndex = 1000;
  document.body.append(whitePawn1);

  moveAt(event.pageX, event.pageY);

  function moveAt(pageX, pageY) {
    whitePawn1.style.left = pageX - shiftX + 'px';
    whitePawn1.style.top = pageY - shiftY + 'px';
  }

  function onMouseMove(event) {
    moveAt(event.pageX, event.pageY);
  }

  document.addEventListener('mousemove', onMouseMove);

  whitePawn1.onmouseup = function() {
    document.removeEventListener('mousemove', onMouseMove);
    whitePawn1.onmouseup = null;
  };

};

whitePawn1.ondragstart = function() {
  return false;
};

//*****************************************************************************
whitePawn2.onmousedown = function(event) {
  let shiftX = event.clientX - whitePawn2.getBoundingClientRect().left;
  let shiftY = event.clientY - whitePawn2.getBoundingClientRect().top;

  whitePawn2.style.position = 'absolute';
  whitePawn2.style.zIndex = 1000;
  document.body.append(whitePawn);

  moveAt(event.pageX, event.pageY);

  function moveAt(pageX, pageY) {
    whitePawn2.style.left = pageX - shiftX + 'px';
    whitePawn2.style.top = pageY - shiftY + 'px';
  }

  function onMouseMove(event) {
    moveAt(event.pageX, event.pageY);
  }

  document.addEventListener('mousemove', onMouseMove);

  whitePawn2.onmouseup = function() {
    document.removeEventListener('mousemove', onMouseMove);
    whitePawn2.onmouseup = null;
  };

};

whitePawn2.ondragstart = function() {
  return false;
};

//*****************************************************************************
whitePawn3.onmousedown = function(event) {
  let shiftX = event.clientX - whitePawn3.getBoundingClientRect().left;
  let shiftY = event.clientY - whitePawn3.getBoundingClientRect().top;

  whitePawn3.style.position = 'absolute';
  whitePawn3.style.zIndex = 1000;
  document.body.append(whitePawn3);

  moveAt(event.pageX, event.pageY);

  function moveAt(pageX, pageY) {
    whitePawn3.style.left = pageX - shiftX + 'px';
    whitePawn3.style.top = pageY - shiftY + 'px';
  }

  function onMouseMove(event) {
    moveAt(event.pageX, event.pageY);
  }

  document.addEventListener('mousemove', onMouseMove);

  whitePawn3.onmouseup = function() {
    document.removeEventListener('mousemove', onMouseMove);
    whitePawn3.onmouseup = null;
  };

};

whitePawn3.ondragstart = function() {
  return false;
};

//*****************************************************************************
whitePawn4.onmousedown = function(event) {
  let shiftX = event.clientX - whitePawn4.getBoundingClientRect().left;
  let shiftY = event.clientY - whitePawn4.getBoundingClientRect().top;

  whitePawn4.style.position = 'absolute';
  whitePawn4.style.zIndex = 1000;
  document.body.append(whitePawn4);

  moveAt(event.pageX, event.pageY);

  function moveAt(pageX, pageY) {
    whitePawn4.style.left = pageX - shiftX + 'px';
    whitePawn4.style.top = pageY - shiftY + 'px';
  }

  function onMouseMove(event) {
    moveAt(event.pageX, event.pageY);
  }

  document.addEventListener('mousemove', onMouseMove);

  whitePawn4.onmouseup = function() {
    document.removeEventListener('mousemove', onMouseMove);
    whitePawn4.onmouseup = null;
  };

};

whitePawn4.ondragstart = function() {
  return false;
};
//*****************************************************************************

whitePawn5.onmousedown = function(event) {
  let shiftX = event.clientX - whitePawn5.getBoundingClientRect().left;
  let shiftY = event.clientY - whitePawn5.getBoundingClientRect().top;

  whitePawn5.style.position = 'absolute';
  whitePawn5.style.zIndex = 1000;
  document.body.append(whitePawn5);

  moveAt(event.pageX, event.pageY);

  function moveAt(pageX, pageY) {
    whitePawn5.style.left = pageX - shiftX + 'px';
    whitePawn5.style.top = pageY - shiftY + 'px';
  }

  function onMouseMove(event) {
    moveAt(event.pageX, event.pageY);
  }

  document.addEventListener('mousemove', onMouseMove);

  whitePawn5.onmouseup = function() {
    document.removeEventListener('mousemove', onMouseMove);
    whitePawn5.onmouseup = null;
  };

};

whitePawn5.ondragstart = function() {
  return false;
};
//*****************************************************************************

whitePawn6.onmousedown = function(event) {
  let shiftX = event.clientX - whitePawn6.getBoundingClientRect().left;
  let shiftY = event.clientY - whitePawn6.getBoundingClientRect().top;

  whitePawn6.style.position = 'absolute';
  whitePawn6.style.zIndex = 1000;
  document.body.append(whitePawn6);

  moveAt(event.pageX, event.pageY);

  function moveAt(pageX, pageY) {
    whitePawn6.style.left = pageX - shiftX + 'px';
    whitePawn6.style.top = pageY - shiftY + 'px';
  }

  function onMouseMove(event) {
    moveAt(event.pageX, event.pageY);
  }

  document.addEventListener('mousemove', onMouseMove);

  whitePawn6.onmouseup = function() {
    document.removeEventListener('mousemove', onMouseMove);
    whitePawn6.onmouseup = null;
  };

};

whitePawn6.ondragstart = function() {
  return false;
};
//*****************************************************************************

whitePawn7.onmousedown = function(event) {
  let shiftX = event.clientX - whitePawn7.getBoundingClientRect().left;
  let shiftY = event.clientY - whitePawn7.getBoundingClientRect().top;

  whitePawn7.style.position = 'absolute';
  whitePawn7.style.zIndex = 1000;
  document.body.append(whitePawn7);

  moveAt(event.pageX, event.pageY);

  function moveAt(pageX, pageY) {
    whitePawn7.style.left = pageX - shiftX + 'px';
    whitePawn7.style.top = pageY - shiftY + 'px';
  }

  function onMouseMove(event) {
    moveAt(event.pageX, event.pageY);
  }

  document.addEventListener('mousemove', onMouseMove);

  whitePawn7.onmouseup = function() {
    document.removeEventListener('mousemove', onMouseMove);
    whitePawn7.onmouseup = null;
  };

};

whitePawn7.ondragstart = function() {
  return false;
};
//*****************************************************************************

whiteKnight.onmousedown = function(event) {

  let shiftX = event.clientX - whiteKnight.getBoundingClientRect().left;
  let shiftY = event.clientY - whiteKnight.getBoundingClientRect().top;

  whiteKnight.style.position = 'absolute';
  whiteKnight.style.zIndex = 1000;
  document.body.append(whiteKnight);

  moveAt(event.pageX, event.pageY);

  function moveAt(pageX, pageY) {
    whiteKnight.style.left = pageX - shiftX + 'px';
    whiteKnight.style.top = pageY - shiftY + 'px';
  }

  function onMouseMove(event) {
    moveAt(event.pageX, event.pageY);
  }

  document.addEventListener('mousemove', onMouseMove);

  whiteKnight.onmouseup = function() {
    document.removeEventListener('mousemove', onMouseMove);
    whiteKnight.onmouseup = null;
  };

};

whiteKnight.ondragstart = function() {
  return false;
};
//*****************************************************************************
whiteKnight1.onmousedown = function(event) {

  let shiftX = event.clientX - whiteKnight1.getBoundingClientRect().left;
  let shiftY = event.clientY - whiteKnight1.getBoundingClientRect().top;

  whiteKnight1.style.position = 'absolute';
  whiteKnight1.style.zIndex = 1000;
  document.body.append(whiteKnight);

  moveAt(event.pageX, event.pageY);

  function moveAt(pageX, pageY) {
    whiteKnight1.style.left = pageX - shiftX + 'px';
    whiteKnight1.style.top = pageY - shiftY + 'px';
  }

  function onMouseMove(event) {
    moveAt(event.pageX, event.pageY);
  }

  document.addEventListener('mousemove', onMouseMove);

  whiteKnight1.onmouseup = function() {
    document.removeEventListener('mousemove', onMouseMove);
    whiteKnight1.onmouseup = null;
  };

};

whiteKnight1.ondragstart = function() {
  return false;
};
//*****************************************************************************
whiteBishop.onmousedown = function(event) {

  let shiftX = event.clientX - whiteBishop.getBoundingClientRect().left;
  let shiftY = event.clientY - whiteBishop.getBoundingClientRect().top;

  whiteBishop.style.position = 'absolute';
  whiteBishop.style.zIndex = 1000;
  document.body.append(whiteBishop);

  moveAt(event.pageX, event.pageY);

  function moveAt(pageX, pageY) {
    whiteBishop.style.left = pageX - shiftX + 'px';
    whiteBishop.style.top = pageY - shiftY + 'px';
  }

  function onMouseMove(event) {
    moveAt(event.pageX, event.pageY);
  }

  document.addEventListener('mousemove', onMouseMove);

  whiteBishop.onmouseup = function() {
    document.removeEventListener('mousemove', onMouseMove);
    whiteBishop.onmouseup = null;
  };

};

whiteBishop.ondragstart = function() {
  return false;
};
//*****************************************************************************
whiteBishop1.onmousedown = function(event) {

  let shiftX = event.clientX - whiteBishop1.getBoundingClientRect().left;
  let shiftY = event.clientY - whiteBishop1.getBoundingClientRect().top;

  whiteBishop1.style.position = 'absolute';
  whiteBishop1.style.zIndex = 1000;
  document.body.append(whiteBishop1);

  moveAt(event.pageX, event.pageY);

  function moveAt(pageX, pageY) {
    whiteBishop1.style.left = pageX - shiftX + 'px';
    whiteBishop1.style.top = pageY - shiftY + 'px';
  }

  function onMouseMove(event) {
    moveAt(event.pageX, event.pageY);
  }

  document.addEventListener('mousemove', onMouseMove);

  whiteBishop1.onmouseup = function() {
    document.removeEventListener('mousemove', onMouseMove);
    whiteBishop1.onmouseup = null;
  };

};

whiteBishop1.ondragstart = function() {
  return false;
};
//*****************************************************************************
whiteQueen.onmousedown = function(event) {

  let shiftX = event.clientX - whiteQueen.getBoundingClientRect().left;
  let shiftY = event.clientY - whiteQueen.getBoundingClientRect().top;

  whiteQueen.style.position = 'absolute';
  whiteQueen.style.zIndex = 1000;
  document.body.append(whiteQueen);

  moveAt(event.pageX, event.pageY);

  function moveAt(pageX, pageY) {
    whiteQueen.style.left = pageX - shiftX + 'px';
    whiteQueen.style.top = pageY - shiftY + 'px';
  }

  function onMouseMove(event) {
    moveAt(event.pageX, event.pageY);
  }

  document.addEventListener('mousemove', onMouseMove);

  whiteQueen.onmouseup = function() {
    document.removeEventListener('mousemove', onMouseMove);
    whiteQueen.onmouseup = null;
  };

};

whiteQueen.ondragstart = function() {
  return false;
};
//*****************************************************************************
whiteKing.onmousedown = function(event) {

  let shiftX = event.clientX - whiteKing.getBoundingClientRect().left;
  let shiftY = event.clientY - whiteKing.getBoundingClientRect().top;

  whiteKing.style.position = 'absolute';
  whiteKing.style.zIndex = 1000;
  document.body.append(whiteKing);

  moveAt(event.pageX, event.pageY);

  function moveAt(pageX, pageY) {
    whiteKing.style.left = pageX - shiftX + 'px';
    whiteKing.style.top = pageY - shiftY + 'px';
  }

  function onMouseMove(event) {
    moveAt(event.pageX, event.pageY);
  }

  document.addEventListener('mousemove', onMouseMove);

  whiteKing.onmouseup = function() {
    document.removeEventListener('mousemove', onMouseMove);
    whiteKing.onmouseup = null;
  };

};

whiteKing.ondragstart = function() {
  return false;
};
//*****************************************************************************
whiteRook.onmousedown = function(event) {

  let shiftX = event.clientX - whiteRook.getBoundingClientRect().left;
  let shiftY = event.clientY - whiteRook.getBoundingClientRect().top;

  whiteRook.style.position = 'absolute';
  whiteRook.style.zIndex = 1000;
  document.body.append(whiteRook);

  moveAt(event.pageX, event.pageY);

  function moveAt(pageX, pageY) {
    whiteRook.style.left = pageX - shiftX + 'px';
    whiteRook.style.top = pageY - shiftY + 'px';
  }

  function onMouseMove(event) {
    moveAt(event.pageX, event.pageY);
  }

  document.addEventListener('mousemove', onMouseMove);

  whiteRook.onmouseup = function() {
    document.removeEventListener('mousemove', onMouseMove);
    whiteRook.onmouseup = null;
  };

};

whiteRook.ondragstart = function() {
  return false;
};
//*****************************************************************************
whiteRook1.onmousedown = function(event) {

  let shiftX = event.clientX - whiteRook1.getBoundingClientRect().left;
  let shiftY = event.clientY - whiteRook1.getBoundingClientRect().top;

  whiteRook1.style.position = 'absolute';
  whiteRook1.style.zIndex = 1000;
  document.body.append(whiteRook1);

  moveAt(event.pageX, event.pageY);

  function moveAt(pageX, pageY) {
    whiteRook1.style.left = pageX - shiftX + 'px';
    whiteRook1.style.top = pageY - shiftY + 'px';
  }

  function onMouseMove(event) {
    moveAt(event.pageX, event.pageY);
  }

  document.addEventListener('mousemove', onMouseMove);

  whiteRook1.onmouseup = function() {
    document.removeEventListener('mousemove', onMouseMove);
    whiteRook1.onmouseup = null;
  };

};

whiteRook1.ondragstart = function() {
  return false;
};
//*****************************************************************************
//*****************************************************************************
//*****************************************************************************
//*****************************************************************************
//*****************************************************************************
//*****************************************************************************
//*****************************************************************************
//*****************************************************************************
//*****************************************************************************
	

	for(var i = 0; i < 600; i+=150)
	{
		for(var j = 0; j < 600; j+=150)
		{	
			ctx.fillStyle = "white";
			ctx.fillRect(j, i, 75, 75);
		}

		for(var j = 75; j < 600; j+=150)
		{
			ctx.fillStyle = "brown";
			ctx.fillRect(j, i, 75, 75);
		}
	}

	for(var i = 75; i < 600; i+=150)
	{
		for(var j = 75; j < 600; j+=150)
		{
			ctx.fillStyle = "white";
			ctx.fillRect(j, i, 75, 75);
		}

		for(var j = 0; j < 600; j+=150)
		{
			ctx.fillStyle = "brown";
			ctx.fillRect(j, i, 75, 75);
		}
	}
	
</script>
</body>
</html>
