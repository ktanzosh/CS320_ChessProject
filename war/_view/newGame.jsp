<html ng-app>

<head>
<title>New Game</title>
</head>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="_view/newGameCSS_.css"/>


<body>
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



<script>
var S = { 
  turnInt:1, selectedPiece:0, moves:0, 
  
  ChangeTurn:function() {
    $(this.selectedPiece).removeClass("pcActive");  //removes selected piece from activePiece  
    $([".w",".b"][this.turnInt]).removeClass("pcTurn");
    this.selectedPiece = this.moves = 0; 
    this.turnInt = 1 - this.turnInt;
    $([".w",".b"][this.turnInt]).addClass("pcTurn");       
  },  
  ClickSquare:function (square) {  
    var child = square.children().eq(0);
    if (child.hasClass(["w","b"][this.turnInt])){ //if has piece to select, do so
      this.ClickPiece(child); 
    }
    else if (this.selectedPiece !== 0) {
      var squareID = parseInt(square.attr("id"));
      if ($.inArray(squareID, this.moves) > -1) {
        if (child.hasClass(["b","w"][this.turnInt])) {          
          child.remove();
        }
        square.append(this.selectedPiece);
        this.ChangeTurn();

      }
    }
  },
  
  ClickPiece:function (piece) {
    if (piece.hasClass(["w","b"][this.turnInt])) {
      $(this.selectedPiece).removeClass("pcActive");
      this.selectedPiece = piece;
      $(this.selectedPiece).addClass("pcActive");
      this.moves = GetPieceMoveArray(["b","w"][this.turnInt], $(piece));
    }
    else if (this.selectedPiece !== 0) {
      this.ClickSquare($(piece.parent())); 
      }
  },  
  
  
  Deselect:function () {
    $(this.selectedPiece).removeClass("pcActive");
    this.selectedPiece = 0;
  }
}; //END OF BIG FUNC

S.ChangeTurn();

$(document).ready(function() {  //CLICK EVENT
  $(document).mousedown(function( event ) {        
    if ($(event.target).is(".pc")){
      S.ClickPiece($(event.target)); 
    }
    else if ($(event.target).is(".sq")){
      S.ClickSquare($(event.target)); 
    }
    else {
      S.Deselect(); 
    }
  });
});

function GetPieceString (piece) {
  var classList = $(piece).attr('class').split(/\s+/);  
  for (var i = 0; i < classList.length; i++) {
     if (classList[i].length > 2) { return classList[i]; }
  }
}

function GetPieceMoveArray (enemyStr, piece) {
  var sqInt = parseInt($(piece).parent().attr('id'));
  var pcStr = GetPieceString($(piece));
  
  switch (pcStr) {
    case 'king': return GetMoves(enemyStr,pcStr,sqInt,[-9,-8,-7,-1,1,7,8,9],1);   
    case 'queen': return GetMoves(enemyStr,pcStr,sqInt,[-9,-8,-7,-1,1,7,8,9],7);
    case 'rook': return GetMoves(enemyStr,pcStr,sqInt,[-8,-1,1,8],7);
    case 'bishop': return GetMoves(enemyStr,pcStr,sqInt,[-9,-7,7,9],7);
    case 'knight': return GetMoves(enemyStr,pcStr,sqInt,[-17,-15,-10,-6,6,10,15,17],1);
    case 'pawn': return GetMoves(enemyStr, pcStr, sqInt, [-7,7,-8,8,-10,10,-2,2], 2); //other for pawn
      
      //var mult = (enemyStr === "b" ? 1 : -1);
      //return GetMoves(enemyStr, pcStr, sqInt, [7 * mult,8 * mult, 10 * mult], 2); //other for pawn
      
  }
}

function GetMoves (enemyStr, pcStr, sqInt, dirArr, maxSteps) {
  var moves = [];
  for (var i = 0; i < 8; i++) {
    for(var j = 1; j <= maxSteps; j++) {  
      
      var v = GetSquareStatus(enemyStr, pcStr, sqInt, j, dirArr[i]);
       if (v < 2) {
        moves.push(sqInt + j * dirArr[i]); 
      }
      if (v > 0) {
        break; 
      }
    }
  }
  return moves;
}

function GetSquareStatus (enemyStr, pcStr, startSq, step, dir) {
  var sqFrom = startSq + ((step - 1) * dir); //intial
  var sqTo = startSq + (step * dir); //move to 
  
  
    // rcs: 0=move&Cont 1=move&Stop 2=illegal 3=blocked 4=pawnFail
  if (startSq === sqTo || sqTo < 1 || sqTo > 64) {
    return 0; 
  }
  
  if (Math.abs((sqFrom - 1) % 8 - (sqTo - 1) % 8) > 2) {
    return 0; 
  }
  
  if ($('#' + sqTo).children().length > 0) {
    
    if (pcStr === "pawn" && (dir % 8 === 0 || step > 1)) {
      return 0; 
    }
    
    if ($('#' + sqTo).children().eq(0).hasClass(enemyStr)) {
      return 0; 
    }
    else {
      return 0; 
    }
  } 
  
  if (pcStr === "pawn") {
    if (dir % 8 !== 0) {
      return 4; 
    }
    if (step > 1) {
      if ((dir > 0 && startSq > 16) || (dir < 0 && startSq < 49)) {
        return 4; 
      }
    }
  }  
  return 0;
}
</script>
</body>
</html>
