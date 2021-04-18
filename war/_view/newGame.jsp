<!DOCTYPE html>

<html>

<head>
<title>New Game</title>
</head>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="_view/newGameDesign.css"/>

<body>

  <h1>Welcome to a new game, ${username}</h1>

<div class="wrapper">
  <div class="row">
    <span id="57" class="sq l"><span class="pc b rook">&#9820</span></span>
    <span id="58" class="sq d"><span class="pc b knight">&#9822</span></span>
    <span id="59" class="sq l"><span class="pc b bishop">&#9821</span></span>
    <span id="60" class="sq d"><span class="pc b queen">&#9819</span></span>
    <span id="61" class="sq l"><span class="pc b king">&#9818</span></span>
    <span id="62" class="sq d"><span class="pc b bishop">&#9821</span></span>
    <span id="63" class="sq l"><span class="pc b knight">&#9822</span></span>
    <span id="64" class="sq d"><span class="pc b rook">&#9820</span></span>
  </div>
  <div class="row">
    <span id="49" class="sq d"><span class="pc b pawn">&#9823</span></span>
    <span id="50" class="sq l"><span class="pc b pawn">&#9823</span></span>
    <span id="51" class="sq d"><span class="pc b pawn">&#9823</span></span>
    <span id="52" class="sq l"><span class="pc b pawn">&#9823</span></span>
    <span id="53" class="sq d"><span class="pc b pawn">&#9823</span></span>
    <span id="54" class="sq l"><span class="pc b pawn">&#9823</span></span>
    <span id="55" class="sq d"><span class="pc b pawn">&#9823</span></span>
    <span id="56" class="sq l"><span class="pc b pawn">&#9823</span></span>
  </div>
  <div class="row">
    <span id="41" class="sq l"></span>
    <span id="42" class="sq d"></span>
    <span id="43" class="sq l"></span>
    <span id="44" class="sq d"></span>
    <span id="45" class="sq l"></span>
    <span id="46" class="sq d"></span>
    <span id="47" class="sq l"></span>
    <span id="48" class="sq d"></span>
  </div>
  <div class="row">
    <span id="33" class="sq d"></span>
    <span id="34" class="sq l"></span>
    <span id="35" class="sq d"></span>
    <span id="36" class="sq l"></span>
    <span id="37" class="sq d"></span>
    <span id="38" class="sq l"></span>
    <span id="39" class="sq d"></span>
    <span id="40" class="sq l"></span>
  </div>
  <div class="row">
    <span id="25" class="sq l"></span>
    <span id="26" class="sq d"></span>
    <span id="27" class="sq l"></span>
    <span id="28" class="sq d"></span>
    <span id="29" class="sq l"></span>
    <span id="30" class="sq d"></span>
    <span id="31" class="sq l"></span>
    <span id="32" class="sq d"></span>
  </div>
  <div class="row">
    <span id="17" class="sq d"></span>
    <span id="18" class="sq l"></span>
    <span id="19" class="sq d"></span>
    <span id="20" class="sq l"></span>
    <span id="21" class="sq d"></span>
    <span id="22" class="sq l"></span>
    <span id="23" class="sq d"></span>
    <span id="24" class="sq l"></span>
  </div>
  <div class="row">
    <span id="9" class="sq l"><span class="pc w pawn">&#9817</span></span>
    <span id="10" class="sq d"><span class="pc w pawn">&#9817</span></span>
    <span id="11" class="sq l"><span class="pc w pawn">&#9817</span></span>
    <span id="12" class="sq d"><span class="pc w pawn">&#9817</span></span>
    <span id="13" class="sq l"><span class="pc w pawn">&#9817</span></span>
    <span id="14" class="sq d"><span class="pc w pawn">&#9817</span></span>
    <span id="15" class="sq l"><span class="pc w pawn">&#9817</span></span>
    <span id="16" class="sq d"><span class="pc w pawn">&#9817</span></span>
  </div>
  <div class="row">
    <span id="1" class="sq d"><span class="pc w rook">&#9814</span></span>
    <span id="2" class="sq l"><span class="pc w knight">&#9816</span></span>
    <span id="3" class="sq d"><span class="pc w bishop">&#9815</span></span>
    <span id="4" class="sq l"><span class="pc w queen">&#9813</span></span>
    <span id="5" class="sq d"><span class="pc w king">&#9812</span></span>
    <span id="6" class="sq l"><span class="pc w bishop">&#9815</span></span>
    <span id="7" class="sq d"><span class="pc w knight">&#9816</span></span>
    <span id="8" class="sq l"><span class="pc w rook">&#9814</span></span>
  </div>
</div>

<ul id="moveList"></ul>
<br> </br>

<script>

var moveList = [];
document.getElementById("moveList").innerHTML = "Your moves will go here: " + moveList;


async function postData(address, objectToPost){
	return await(await fetch(address,{
		method: 'POST',
		headers: {
			'Content-Type' : 'application/json'
		},
		body: JSON.stringify(objectToPost)
	})).json();
}


var S = { 
  turnInt:1, selectedPiece:0, moves:0, 
  
  ChangeTurn:function() {
    $(this.selectedPiece).removeClass("pcActive");  //removes selected piece from activePiece  
    $([".w",".b"][this.turnInt]).removeClass("pcTurn"); //removes pcTurn class from turnInt
    this.selectedPiece = this.moves = 0;  //set equal to none
    this.turnInt = 1 - this.turnInt; //change turn 
    $([".w",".b"][this.turnInt]).addClass("pcTurn");   //add pcTurn as class in .w and .b
   
    if(this.turnInt == 0){
    	var turn = "WHITES TURN:"
    }
    else{
    	var turn = "BLACKS TURN:"
    }
    
    moveList.push("<br>" + turn);
    document.getElementById("moveList").innerHTML = moveList;
  },  
  //******************************************************************************************
  ClickSquare:function (square) {
    var child = square.children().eq(0);
    if (child.hasClass(["w","b"][this.turnInt])){ //if has piece to select, do so
      this.ClickPiece(child); // ~child is chess piece~
    }
    else if (this.selectedPiece !== 0) {
      var squareID = parseInt(square.attr("id"));   // get number associated with sqaure
      
      moveList.push("<br>" + "To Square: " + squareID);
      document.getElementById("moveList").innerHTML = moveList;
      
      val = {
    			playerMovedTo: squareID
    		  };
    		  postData('newGame', val).then(function(data){
    		  	console.log(data);
    		  });
      if ($.inArray(squareID, this.moves) > -1) {
        if (child.hasClass(["b","w"][this.turnInt])) {  //if square has piece -> remove piece       
          child.remove();
        }
        square.append(this.selectedPiece);  //append piece to square
        this.ChangeTurn();  //change turn

      }
    }
  },
  
  ClickPiece:function (piece) {
    if (piece.hasClass(["w","b"][this.turnInt])) {  //if piece has a .w or .b 
      $(this.selectedPiece).removeClass("pcActive");  //remove pcActive class from selected piece
      this.selectedPiece = piece; //set selected piece to piece passed in
      $(this.selectedPiece).addClass("pcActive"); //add class pcActive to selected piece
      this.moves = GetPieceMoveArray(["b","w"][this.turnInt], $(piece)); //go to GetPieceMoveArray and get the next move
    }
    else if (this.selectedPiece !== 0) {
      this.ClickSquare($(piece.parent())); //else just click the square
      }
  },  
  Deselect:function () {
    $(this.selectedPiece).removeClass("pcActive"); //remove pcActive class from selectedPiece
    this.selectedPiece = 0; // back to none when selected
  }
  
  
}; //END OF BIG FUNC

S.ChangeTurn();

$(document).ready(function() {  //CLICK EVENT
  $(document).mousedown(function( event ) { //once mousedown event triggered       
    if ($(event.target).is(".pc")){ //event target for individual pieces
      S.ClickPiece($(event.target)); //Get the element that triggered a specific event
    }
    else if ($(event.target).is(".sq")){ //event target for squares (if selected)
      S.ClickSquare($(event.target)); //Get the element that triggered a specific event
    }
    else {
      S.Deselect(); //  deselect piece 
    }
  
//************RETURNS LOGIC WHETHER PIECE OR SQUARE HAS BEEN CLICKED
/*
    val = {
    		pieceIsClicked: $(event.target).is(".pc"),
    		squareIsClicked:$(event.target).is(".sq")
    };
    postData('newGame', val).then(function(data){
    	console.log(data);
    });
*/
  });
});




function GetPieceString (piece) {
  var classList = $(piece).attr('class').split(/\s+/);  // classList = array of pieces [1,2,3,4...] from "class="
  for (var i = 0; i < classList.length; i++) {  //for the length of classList array, if the length is greater than 2 return
     if (classList[i].length > 2) {
       return classList[i]; 
     }
  }
  
  
}

function GetPieceMoveArray (enemyString, piece) {
  var squareInt = parseInt($(piece).parent().attr('id')); //get number related to square accessing through parent
  var stringOfPieces = GetPieceString($(piece)); //set stringOfPieces to the pieces passed in
  
//********LOCATION OF SQUARE && PIECE NAME****************

  moveList.push("<br>" + "Moved from: " + squareInt,"<br>" +  "Piece: " + stringOfPieces);
  document.getElementById("moveList").innerHTML = moveList;
  
  
  val = {
	intialPosition: squareInt,
	pieceName: stringOfPieces
  };
  postData('newGame', val).then(function(data){
  	console.log(data);
  });
 
  
  switch (stringOfPieces) {
      // case for each piece
      // fix game logic -- some issues going on 
      // re-look at numbers given in array - issues with moves
    case 'king': return GetMoves(enemyString,stringOfPieces,squareInt,[-9,-8,-7,-1,1,7,8,9],1);   
    case 'queen': return GetMoves(enemyString,stringOfPieces,squareInt,[-9,-8,-7,-1,1,7,8,9],7);
    case 'rook': return GetMoves(enemyString,stringOfPieces,squareInt,[-8,-1,1,8],7);
    case 'bishop': return GetMoves(enemyString,stringOfPieces,squareInt,[-9,-7,7,9],7);
    case 'knight': return GetMoves(enemyString,stringOfPieces,squareInt,[-17,-15,-10,-6,6,10,15,17],1);
    case 'pawn': 
     var mult;
      if (enemyString === "b"){
        mult = 1;
        //if turn is white
        val = {
    			enemyString: enemyString
    		  };
    		  postData('newGame', val).then(function(data){
    		  	console.log(data);
    		  });
      }
      else{
        mult = -1;
        // if turn is black
        val = {
    			enemyString: enemyString
    		  };
    		  postData('newGame', val).then(function(data){
    		  	console.log(data);
    		  });
      }
      return GetMoves(enemyString, stringOfPieces, squareInt, [7 * mult,8 * mult, 9 * mult], 2);
      
      
    
  }
}

function GetMoves (enemyString, stringOfPieces, squareInt, dirArr, maxSteps) {
  var moves = [];
  var squareStatus;
  for (var i = 0; i < 8; i++) {
    for(var j = 1; j <= maxSteps; j++) {  
      squareStatus = GetSquareStatus(enemyString, stringOfPieces, squareInt, j, dirArr[i]);
      
      // bringing in logic that came from GetSquare Status -> squareStatus
       if (squareStatus < 2) {
        moves.push(squareInt + j * dirArr[i]);  // passes -- gets added into moves array 
      } 
      if (squareStatus > 0) { // illegal move being made
        break; 
      }
    }
  }
 
//**********RETURNS POSSIBLE MOVES******bottom left is 1****count on so forth *******
/*   val = {
			ableToMoveToSquare: moves
		  };
		  postData('newGame', val).then(function(data){
		  	console.log(data);
		  }); */
//***********************************************************************************
  return moves;
}

function GetSquareStatus (enemyString, stringOfPieces, startSquare, step, dir) {
  var fromSquare = startSquare + ((step - 1) * dir); //intial
  var toSquare = startSquare + (step * dir); //move to 
  
    // 0=move and go
    // 1=move and stop 
    //2=illegal 
    //4=pawnFail
  if (startSquare === toSquare || toSquare < 1 || toSquare > 64) {  //if starting pos is same as chosen OR out of bounds
    return 0; 
  }
  
  if ($('#' + toSquare).children().length > 0) {
    if (stringOfPieces === "pawn" && (dir % 8 === 0 || step > 1)) {
     return 3; 
     }
    if ($('#' + toSquare).children().eq(0).hasClass(enemyString)) {
     return 1; 
     }
    else {
     return 3; 
     }
  }
  if (stringOfPieces === "pawn") { // if piece is pawn -> 
    if (dir % 8 !== 0) {
      return 4; 
    }
    if (step > 1) {
      if ((dir > 0 && startSquare > 16) || (dir < 0 && startSquare < 49)) {
        return 4; 
      }
    }
  }  
  return 0;
}


// fix for later on 
	$('#restart-btn').on('click', function() {
		resetGame();
	});

	var resetGame = function() {
    alert("Resetting game");
  }
</script>
</body>
</html>
