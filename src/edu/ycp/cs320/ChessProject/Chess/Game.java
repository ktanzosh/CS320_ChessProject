package edu.ycp.cs320.ChessProject.Chess;

public class Game 
{
	private ChessBoard cb;
	private Player WhitePlayer;
	private Player BlackPlayer;
	
	private KingPiece WhiteKing;
	private QueenPiece WhiteQueen;
	private BishopPiece WhiteBishop1;
	private BishopPiece WhiteBishop2;
	private KnightPiece WhiteKnight1;
	private KnightPiece WhiteKnight2;
	private RookPiece WhiteRook1;
	private RookPiece WhiteRook2;
	
	private PawnPiece WhitePawn1;
	private PawnPiece WhitePawn2;
	private PawnPiece WhitePawn3;
	private PawnPiece WhitePawn4;
	private PawnPiece WhitePawn5;
	private PawnPiece WhitePawn6;
	private PawnPiece WhitePawn7;
	private PawnPiece WhitePawn8;
	
	private KingPiece BlackKing;
	private QueenPiece BlackQueen;
	private BishopPiece BlackBishop1;
	private BishopPiece BlackBishop2;
	private KnightPiece BlackKnight1;
	private KnightPiece BlackKnight2;
	private RookPiece BlackRook1;
	private RookPiece BlackRook2;
	
	private PawnPiece BlackPawn1;
	private PawnPiece BlackPawn2;
	private PawnPiece BlackPawn3;
	private PawnPiece BlackPawn4;
	private PawnPiece BlackPawn5;
	private PawnPiece BlackPawn6;
	private PawnPiece BlackPawn7;
	private PawnPiece BlackPawn8;
	
	public void setGame()
	{
		cb = new ChessBoard();
		WhitePlayer = new Player(true);
		BlackPlayer = new Player(false);
		//put all pieces at the right spot
		
	}
	
	public void doMove(ChessBoard cb, ChessPiece cp, int x, int y)
	{	
		int oldx = cp.getPosX();
		int oldy = cp.getPosY();
		
		Tile newTile = new Tile(cp);
		cb.setTile(x,  y, newTile);
		//sets old tile to null, so no tile is there
		cb.setTile(oldx,  oldy);
		
		if(cp.whatPiece() == "Pawn" || cp.whatPiece() == "Rook" || cp.whatPiece() == "King")
		{
			cp.setHaveMoved(true);
		}
		
	}
	
	public void playGame(Player player1, Player player2)
	{
		this.setGame();
		
		while(this.WhitePlayer.isCheckmate() == false && this.BlackPlayer.isCheckmate() == false)
		{
			/*Player 1
			boolean selected = false;
			boolean legalMove = false;
			ChessPiece selectedPiece;
			while(selected == false)
			
			//find out how to select a piece
			if(selectedPiece.color == player1.getColor()
			{
				selected = true;
			}
			while(legalMove == false)
			{
				legalMove = selected.isLegalMove(cb, int, cb);
			}
			*/
			
			
			/*Player 2
			//resetting false and selectedPiece
			selected == false
			selectedPiece = null;
			while(selected == false)
			
			//find out how to select a piece
			if(selectedPiece.color == player2.getColor()
			{
				selected = true;
			}
			
			this.doMove(selectedPiece, int, int);
			*/
		}
	}
}
