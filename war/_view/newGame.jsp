<!DOCTYPE html>

<html>

<head>
<title>New Game</title>
</head>

<link rel="stylesheet" type="text/css" href="_view/newGameCSS5.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>


<body>

	<h1>Welcome to a new game, ${username}</h1>
	<br> </br>
	
	<style>
		h2{
			position:absolute;
			top:200px;
			right: 200px;
			color:white;
		
		}
	
	</style>
	<h2> Heres your game id, ${game_id}</h2>
	<br> </br>
	
	<%-- <style>
		h3{
			position:absolute;
			top:300px;
			right: 200px;
			color:white;
		
		}
	
	</style>
	<h3> ${color}, made the game</h3> --%>

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
			<span id="41" class="sq l"></span> <span id="42" class="sq d"></span>
			<span id="43" class="sq l"></span> <span id="44" class="sq d"></span>
			<span id="45" class="sq l"></span> <span id="46" class="sq d"></span>
			<span id="47" class="sq l"></span> <span id="48" class="sq d"></span>
		</div>
		<div class="row">
			<span id="33" class="sq d"></span> <span id="34" class="sq l"></span>
			<span id="35" class="sq d"></span> <span id="36" class="sq l"></span>
			<span id="37" class="sq d"></span> <span id="38" class="sq l"></span>
			<span id="39" class="sq d"></span> <span id="40" class="sq l"></span>
		</div>
		<div class="row">
			<span id="25" class="sq l"></span>	 <span id="26" class="sq d"></span>
			<span id="27" class="sq l"></span> <span id="28" class="sq d"></span>
			<span id="29" class="sq l"></span> <span id="30" class="sq d"></span>
			<span id="31" class="sq l"></span> <span id="32" class="sq d"></span>
		</div>
		<div class="row">
			<span id="17" class="sq d"></span> 
			<span id="18" class="sq l"></span>
			<span id="19" class="sq d"></span> <span id="20" class="sq l"></span>
			<span id="21" class="sq d"></span> <span id="22" class="sq l"></span>
			<span id="23" class="sq d"></span> <span id="24" class="sq l"></span>
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
	<br>
	</br>
	<style>
p {
	top: 850px;
	position: absolute;
	color: white;
	font-size: 20px;
}
</style>
	<p id="chessNotation"></p>
	<p id="pawnPromo"></p>
	


<div id="winner"></div>

<form action="${pageContext.servletContext.contextPath}/index">
    <input type="submit" value="Go to Main Menu" />
</form>

<button class="ping" onclick="myFunction()">Start Game!</button>

<!----------------------------- START OF JAVASCRIPT ----------------------------->
<script>
document.getElementById("winner").style.visibility = "hidden";

//----BLACK UNICODE----//
var bKing = "&#9818";
var bQueen = "&#9819";
var bRook = "&#9820";
var bBishop = "&#9821"; 
var bKnight = "&#9822";
var bPawn =  "&#9823";
//----WHITE UNICODE----//
var wKing = "&#9812";
var wQueen = "&#9813";
var wRook = "&#9814";
var wBishop = "&#9815"; 
var wKnight = "&#9816";
var wPawn =  "&#9817";

var pieceChoice;

var newMoves = true;

var chessNotation = ["a1", "b1", "c1", "d1","e1", "f1", "g1", "h1", 
	"a2", "b2", "c2", "d2","e2", "f2", "g2", "h2",
	"a3", "b3", "c3", "d3","e3", "f3", "g3", "h3",
	"a4", "b4", "c4", "d4","e4", "f4", "g4", "h4",
	"a5", "b5", "c5", "d5","e5", "f5", "g5", "h5",
	"a6", "b6", "c6", "d6","e6", "f6", "g6", "h6",
	"a7", "b7", "c7", "d7","e7", "f7", "g7", "h7",
	"a8", "b8", "c8", "d8","e8", "f8", "g8", "h8"];
//----TEMPORARY VARIABLES----//
var intialPosition;
var squareID_;
var playerColor;
var pieceName;
var currentPiece;
var promo = null;
var promoChoice;
var logicB; 
var logic;
var pawnPromotion = false;
document.getElementById("winner").style.animationPlayState = "paused";


// move to final position -- this works
//document.getElementById(finalsq).innerHTML = "<span class="+className+">"+bRook+"</span>";

// GOOD FOR GETTING RID OF INTIIAL POSITION
//$("#8").empty(); // jQuery route
//document.getElementById(initialsq).innerHTML = "";

var servletColor = "${color}";
//document.getElementById("chessNotation").innerHTML = servletColor;
	
var moveList = [];
document.getElementById("moveList").innerHTML = "Your moves will go here: ";

async function postData(address, objectToPost){
	return await(await fetch(address,{
		method: 'POST',
		headers: {
			'Content-Type' : 'application/json'
		},
		body: JSON.stringify(objectToPost)
	})).text();
}
//---------------------------PINGING INTERVAL FUNCTION---------------------------//
function myFunction() {
	
	setInterval(function(){
		
		  postData('ping').then(function(data){
			  	console.log(data);
			 
			  	if(data == "No moves to be updated"){
			  		newMoves = false;
			  		document.getElementById("chessNotation").innerHTML = "Game has started";
			  	}
			  	
			  	else{
			  		
			  		newMoves = true;
			  		 var moves = data.split("/");
			  		 var initialPos = moves[0];
			  		 var finalPos = moves[1];
			  		 var pieceChoice = moves[2];
			  		 var gameState = moves[3];
			  		 var className = "''";
			  		 
			  		//document.getElementById("chessNotation").innerHTML = initialPos + " to " + finalPos + " piece: " + pieceChoice;
			 
//---------------------------IF PLAYER WHITE APPEND BLACK PIECE---------------------------//			  		
			  if(servletColor == "white"){			
			  		if (pieceChoice == "king"){
			  			pieceChoice = bKing;
			  			className = "'pc b king'";
			  		}
			  		else if (pieceChoice == "queen"){
			  			pieceChoice = bQueen;
			  			className = "'pc b queen'";
			  		}
			  		else if (pieceChoice == "rook"){
			  			pieceChoice = bRook;
			  			className = "'pc b rook'";
			  		}
			  		else if (pieceChoice == "bishop"){
			  			pieceChoice = bBishop;
			  			className = "'pc b bishop'";
			  		}
			  		else if (pieceChoice == "knight"){
			  			pieceChoice = bKnight;
			  			className = "'pc b knight'";
			  		}
			  		else if (pieceChoice == "pawn"){
			  			pieceChoice = bPawn;
			  			className = "'pc b pawn'";
			  		}
			  		
			  		if(gameState == "Check"){
			  			moveList.push("<br>" + "Black player in check");
					    document.getElementById("moveList").innerHTML = moveList;
			  		}
			  		
			  		document.getElementById(initialPos).innerHTML = "";
			  		document.getElementById(finalPos).innerHTML = "<span class="+className+">"+pieceChoice+"</span>";
			  	}
//---------------------------IF PLAYER BLACK APPEND WHITE PIECE---------------------------//
			  
			  else if(servletColor == "black"){	
				  
			  		if (pieceChoice == "king"){
			  			pieceChoice = wKing;
			  			className = "'pc w king'";
			  		}
			  		else if (pieceChoice == "queen"){
			  			pieceChoice = wQueen;
			  			className = "'pc w queen'";
			  		}
			  		else if (pieceChoice == "rook"){
			  			pieceChoice = wRook;
			  			className = "'pc w rook'";
			  		}
			  		else if (pieceChoice == "bishop"){
			  			pieceChoice = wBishop;
			  			className = "'pc w bishop'";
			  		}
			  		else if (pieceChoice == "knight"){
			  			pieceChoice = wKnight;
			  			className = "'pc w knight'";
			  		}
			  		else if (pieceChoice == "pawn"){
			  			pieceChoice = wPawn;
			  			className = "'pc w pawn'";
			  		}
			  		
			  		if(gameState == "Check"){
			  			moveList.push("<br>" + "White player in check");
					    document.getElementById("moveList").innerHTML = moveList;
			  		}
//---------------------------APPEND PIECE---------------------------//			  		
			  		document.getElementById(initialPos).innerHTML = "";
			  		document.getElementById(finalPos).innerHTML = "<span class="+className+">"+pieceChoice+"</span>";
			  		
			  		
//---------------------------ADD ENEMY MOVE TO MOVE LIST---------------------------//
			    
			  }
				 moveList.push("<br>" + "OPPONENT PLAYER: " +"<br>"+ "moved from: " + chessNotation[initialPos - 1],"<br>" +  "Piece: " + pieceChoice);
				  	document.getElementById("moveList").innerHTML = moveList;
				  		
				  moveList.push("<br>" + "Moved to: " + chessNotation[finalPos - 1]);
				    document.getElementById("moveList").innerHTML = moveList;

				    
				if(gameState == "Checkmate"){
					document.getElementById("winner").style.visibility = "visible";
					if(servletColor == "white"){
						document.getElementById("winner").append("BLACK WINS!");
						document.getElementById("winner").style.animationPlayState = "running";
						$("#winner").delay(5200).fadeOut(500);
					}
					else if(servletColor == "black"){
						document.getElementById("winner").append("WHITE WINS!");
						document.getElementById("winner").style.animationPlayState = "running";
						$("#winner").delay(5200).fadeOut(500);
					}
				} 
				
				else if(gameState == "Draw"){
					document.getElementById("winner").style.visibility = "visible";
					document.getElementById("winner").append("IT'S A DRAW!");
				}
				
				/* else if(servletColor == "black" && gameState == "Checkmate"){
					document.getElementById("winner").style.visibility = "visible";
					document.getElementById("winner").innerHTML("WHITE WINS!");
				} */
			 }
		  });
		}, 1000);	  
}
//---------------------------DETERMINE WHETHER WHITE OR BLACK---------------------------//
if(servletColor == "white")  {
	turnInt = 1;
}
else if (servletColor == "black"){
	turnInt = 0;
}
var S = { 	
  turnInt, selectedPiece:0, moves:0, 
  
  ChangeTurn:function() {

	    $(this.selectedPiece).removeClass("pcActive");  //removes selected piece from activePiece  
	    $([".w",".b"][this.turnInt]).removeClass("pcTurn"); //removes pcTurn class from turnInt
	    this.selectedPiece = this.moves = 0;  //set equal to none
	    this.turnInt = 1 - this.turnInt; //change turn 
	    $([".w",".b"][this.turnInt]).addClass("pcTurn");   //add pcTurn as class in .w and .b
	   	    
	    if(this.turnInt == 0){
	    	document.getElementById("moveList").style.color = "white";
	    	var turn = "WHITES TURN:"
	    	playerColor = "b";	
	    }
	    else{
	    	document.getElementById("moveList").style.color = "black";
	    	var turn = "BLACKS TURN:"
	    	playerColor = "w";
	    }
	  },   
 //******************************************************************************************
  ClickSquare:function (square) {
	
    var child = square.children().eq(0);
    
    if (child.hasClass(["w","b"][this.turnInt])){ //if has piece to select, do so
      this.ClickPiece(child); // ~child is chess piece~
    }
 
    else if (this.selectedPiece !== 0) {
    	//squareID = finalPosition
      var squareID = parseInt(square.attr("id"));   // get number associated with square
      
      if (squareID < 10){
    	  var finalPosi = ('0' + squareID).slice(-2);
    	  squareID_ = finalPos;
      }
      else{
    	  squareID_ = squareID;
      }
      
      //FINAL POSITION FOR PLAYER
     // document.getElementById("chessNotation").innerHTML = squareID_;
      
//---------------------------UPDATE MOVELIST FOR PLAYER---------------------------//
      moveList.push("<br>" + "Moved to: " + chessNotation[squareID - 1]);
      document.getElementById("moveList").innerHTML = moveList;
      
      // IF THE PIECE HAS MOVED FROM INITIAL POSITION - DO SOMETHING
      if (initialPosition != squareID_){
    	  var x = document.getElementsByClassName("pawn")[0].id;
    	}

      if ($.inArray(squareID, this.moves) > -1) {
        if (child.hasClass(["b","w"][this.turnInt])) {  //if square has piece -> remove piece       
          child.remove();
        }
        
//---------------------------PAWN PROMOTION---------------------------//
		// WHITE
        if (this.selectedPiece.hasClass("pawn") && (squareID > 56)) {
        	pawnPromotion = true;
         this.selectedPiece.removeClass("pawn");
          this.selectedPiece.addClass("queen");
			this.selectedPiece.empty().html("&#9813");
			var promotion = " White pawn promoted to queen";
			moveList.push("<br>" + promotion);
    		document.getElementById("moveList").innerHTML = moveList;
        }
			
			// BLACK
			else if (this.selectedPiece.hasClass("pawn") && 
		            (squareID < 9)) {
				pawnPromotion = true;
		         this.selectedPiece.removeClass("pawn");
		          this.selectedPiece.addClass("queen");
					this.selectedPiece.empty().html("&#9819");
					var promotion = " Black pawn promoted to queen";
					moveList.push("<br>" + promotion);
		    		document.getElementById("moveList").innerHTML = moveList;
         
        }

        //do pawn promotion for white pawns
/*         if(squareID_ > 57 && pieceName == "pawn" && playerColor == "w"){
        	
        	pawnPromotion = true;
        	document.getElementById("moveList").style.color = "white";
        	document.getElementById("myBtn").style.visibility = "visible";
        	var promotion = "WHITE'S PAWN PROMOTION";
    		moveList.push("<br>" + promotion);
    		document.getElementById("moveList").innerHTML = moveList;	
        }
        // pawn promotion for black pieces 
        else if(squareID_ < 9 && pieceName == "pawn" && playerColor == "b"){
        	pawnPromotion = true;
        	document.getElementById("moveList").style.color = "black";
        	document.getElementById("myBtn").style.visibility = "visible";
        	var promotion = "BLACK'S PAWN PROMOTION";
    		moveList.push("<br>" + promotion);
    		document.getElementById("moveList").innerHTML = moveList;	
        }
        
        else{
        	
        	pawnPromotion = false;
        } */
        
        val = {
				initialPosition: initialPosition,
				pieceName: pieceName,
				playerColor: playerColor,
				finalPosition: squareID_,
				pawnPromotion: pawnPromotion,
				promotionChoice: promoChoice
				
			  };

			  postData('newGame', val).then(function(data){
				
			  	console.log(data);
			
			  	logicB = data;
			  	
				var logic = logicB.split("/");
		
			  // white checkmate
			  if(logic[1] == "Checkmate" && playerColor == "b"){
				  
				var check = "White in checkmate";
				document.getElementById("winner").style.visibility = "visible";
				document.getElementById("winner").append("WHITE WINS!");
				document.getElementById("winner").style.animationPlayState = "running";
				$("#winner").delay(3200).fadeOut(300);
				moveList.push("<br>" + check);
		    	document.getElementById("moveList").innerHTML = moveList;
				goodMove = 0;
		    		
			  }
			  else if(logic[1] == "Check" && playerColor == "b"){
				  
				var check = "White in check";
				moveList.push("<br>" + check);
				document.getElementById("moveList").innerHTML = moveList;
				goodMove = 1;
			  }
			  else if(logic[1] == "Checkmate" && playerColor == "w"){
				  
				  var check = "Black in checkmate";
				  document.getElementById("winner").style.visibility = "visible";
					document.getElementById("winner").append("BLACK WINS!");
					document.getElementById("winner").style.animationPlayState = "running";
					$("#winner").delay(5200).fadeOut(500);
				  moveList.push("<br>" + check);
		    	document.getElementById("moveList").innerHTML = moveList;
		    	goodMove = 0;
			  }
			  else if(logic[1] == "Check" && playerColor == "w"){
				  
				  var check = "Black in check";
				  moveList.push("<br>" + check);
		    		document.getElementById("moveList").innerHTML = moveList;
		    		goodMove = 1;
			  }
			  else if(logic[1] == "Draw"){
				  
				  var check = "Draw";
				  document.getElementById("winner").style.visibility = "visible";
					document.getElementById("winner").append("IT'S A DRAW!");
					$("#winner").delay(5200).fadeOut(500);
				  moveList.push("<br>" + check);
		    		document.getElementById("moveList").innerHTML = moveList;
		    		goodMove = 0;
			  }
				
			  else if (logic[0] == "true"){
				  // goodMove is true?
				  goodMove = 1;
			  }
			  			  
		});
			 		
				square.append(this.selectedPiece);  //append piece to square
				
				this.ChangeTurn();  //change turn
				
				this.ChangeTurn();

      }
    }
  },
//******************************************************************************************
  ClickPiece:function (piece) {
    if (piece.hasClass(["w","b",][this.turnInt])) {  //if piece has a .w or .b 
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


//******************************************************************************************
S.ChangeTurn();
$(document).ready(function() {  //CLICK EVENT
  $(document).mousedown(function( event ) { //once mousedown event triggered  
    if ($(event.target).is(".pc")){ //event target for individual pieces
      S.ClickPiece($(event.target)); //Get the element that triggered a specific event
      //document.getElementById("chessNotation").innerHTML = S.ClickPiece($(event.target));
      
    }
    else if ($(event.target).is(".sq")){ //event target for squares (if selected)
      S.ClickSquare($(event.target)); //Get the element that triggered a specific event
    }
    else {
      S.Deselect(); //  deselect piece 
    }
			  
  });
});
//******************************************************************************************
function GetPieceString (piece) {
	
  var classList = $(piece).attr('class').split(/\s+/);  // classList = array of pieces [1,2,3,4...] from "class="


  for (var i = 0; i < classList.length; i++) {  //for the length of classList array, if the length is greater than 2 return
     if (classList[i].length > 2) {
       return classList[i]; 
     }
  }
  
  
}
//******************************************************************************************
function GetPieceMoveArray (enemyString, piece) {
	var squareInt = parseInt($(piece).parent().attr('id')); //get number related to square accessing through parent
	var stringOfPieces = GetPieceString($(piece)); //set stringOfPieces to the pieces passed in
	currentPiece = stringOfPieces;	// current piece selected to be replaced in pawn promotion
	
	//INITIAL POSITION OF PLAYER
	//document.getElementById("chessNotation").innerHTML = squareInt;
	
	var pieceSymbol;
	if (stringOfPieces == "pawn" && enemyString == "w"){
		pieceSymbol = bPawn;
	} 
	else if (stringOfPieces == "rook" && enemyString == "w"){
		pieceSymbol = bRook;
	}
	else if (stringOfPieces == "bishop" && enemyString == "w"){
		pieceSymbol = bBishop;
	}
	else if (stringOfPieces == "queen" && enemyString == "w"){
		pieceSymbol = bQueen;
	}
	else if (stringOfPieces == "king" && enemyString == "w"){
		pieceSymbol = bKing;
	}
	else if (stringOfPieces == "knight" && enemyString == "w"){
		pieceSymbol = bKnight;
	}
	else if (stringOfPieces == "pawn" && enemyString == "b"){
		pieceSymbol = wPawn;
	} 
	else if (stringOfPieces == "rook" && enemyString == "b"){
		pieceSymbol = wRook;
	}
	else if (stringOfPieces == "bishop" && enemyString == "b"){
		pieceSymbol = wBishop;
	}
	else if (stringOfPieces == "queen" && enemyString == "b"){
		pieceSymbol = wQueen;
	}
	else if (stringOfPieces == "king" && enemyString == "b"){
		pieceSymbol = wKing;
	}
	else if (stringOfPieces == "knight" && enemyString == "b"){
		pieceSymbol = wKnight;
	}
	
	//document.getElementById("chessNotation").innerHTML = currentPiece;
//********LOCATION OF SQUARE && PIECE NAME****************
	//document.getElementById("chessNotation").innerHTML = stringOfPieces + "has been moved";
  
  moveList.push("<br>" + "YOUR MOVE: "+"<br>"+ "moved from: " + chessNotation[squareInt - 1],"<br>" +  "Piece: " + pieceSymbol);
  document.getElementById("moveList").innerHTML = moveList;
  
  if (squareInt < 10){
	  forNum = ('0' + squareInt).slice(-2);
	  initialPosition = forNum;
  }
  else{
	  initialPosition = squareInt;
	  
  }
  
pieceName = stringOfPieces;
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
      }
      else{
        mult = -1;
      }
      return GetMoves(enemyString, stringOfPieces, squareInt, [7 * mult,8 * mult, 9 * mult], 2);
  }
}
//******************************************************************************************
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
 
  return moves;
}
//******************************************************************************************
function GetSquareStatus (enemyString, stringOfPieces, startSquare, step, dir) {
  var fromSquare = startSquare + ((step - 1) * dir); //intial
  var toSquare = startSquare + (step * dir); //move to 
  
  
    // 0 = move -> go
    // 1 = move -> stop
    //2 = illegal
    //3 = block
    //4 = pawn movement failed 
    
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
	

</script>
</body>
</html>