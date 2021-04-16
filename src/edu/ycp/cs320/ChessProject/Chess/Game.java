package edu.ycp.cs320.ChessProject.Chess;

import java.util.ArrayList;

public class Game 
{
	private ChessBoard cb;
	private Player WhitePlayer;
	private Player BlackPlayer;
	private Player winnerPlayer;
	private boolean finished;
	
	private ArrayList<Move> MoveList;
	private ArrayList<ChessPiece> WhitePieces;
	private ArrayList<ChessPiece> BlackPieces;
	
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
	
	public Game()
	{
		MoveList = new ArrayList<Move>();
		finished = false;
	}
	
	public void setGame(boolean f)
	{
		cb = new ChessBoard();
	}
	
	public void setGame()
	{
		cb = new ChessBoard();
		WhitePlayer = new Player(true);
		BlackPlayer = new Player(false);
		WhitePieces = new ArrayList<ChessPiece>();
		BlackPieces = new ArrayList<ChessPiece>();
		MoveList = new ArrayList<Move>();
		finished = false;
		
		//put all pieces at the right spot
		BlackRook1 = new RookPiece(0, 0, false, 1);
		Tile BlackRookTile1 = new Tile(BlackRook1);
		cb.setTile(0, 0, BlackRookTile1);
		
		BlackKnight1 = new KnightPiece(0, 1, false, 2);
		Tile BlackKnightTile1 = new Tile(BlackKnight1);
		cb.setTile(0, 1, BlackKnightTile1);
		
		BlackBishop1 = new BishopPiece(0, 2, false, 3);
		Tile BlackBishopTile1 = new Tile(BlackBishop1);
		cb.setTile(0, 2, BlackBishopTile1);
		
		BlackQueen = new QueenPiece(0, 3, false, 4);
		Tile BlackQueenTile = new Tile(BlackQueen);
		cb.setTile(0, 3, BlackQueenTile);
		
		BlackKing = new KingPiece(0, 4, false, 5);
		Tile BlackKingTile = new Tile(BlackKing);
		cb.setTile(0, 4, BlackKingTile);
		
		BlackBishop2 = new BishopPiece(0, 5, false, 6);
		Tile BlackBishopTile2 = new Tile(BlackBishop2);
		cb.setTile(0, 5, BlackBishopTile2);
		
		BlackKnight2 = new KnightPiece(0, 6, false, 7);
		Tile BlackKnightTile2 = new Tile(BlackKnight2);
		cb.setTile(0,  6, BlackKnightTile2);
		
		BlackRook2 = new RookPiece(0, 7, false, 8);
		Tile BlackRookTile2 = new Tile(BlackRook2);
		cb.setTile(0,  7, BlackRookTile2);
		
		BlackPawn1 = new PawnPiece(1, 0, false, 9);
		Tile BlackPawnTile = new Tile(BlackPawn1);
		cb.setTile(1, 0, BlackPawnTile);
		
		BlackPawn2 = new PawnPiece(1, 1, false, 10);
		Tile BlackPawnTile2 = new Tile(BlackPawn2);
		cb.setTile(1, 1, BlackPawnTile2);
		
		BlackPawn3 = new PawnPiece(1, 2, false, 11);
		Tile BlackPawnTile3 = new Tile(BlackPawn3);
		cb.setTile(1, 2, BlackPawnTile3);
		
		BlackPawn4 = new PawnPiece(1, 3, false, 12);
		Tile BlackPawnTile4 = new Tile(BlackPawn4);
		cb.setTile(1, 3, BlackPawnTile4);
		
		BlackPawn5 = new PawnPiece(1, 4, false, 13);
		Tile BlackPawnTile5 = new Tile(BlackPawn5);
		cb.setTile(1, 4, BlackPawnTile5);
		
		BlackPawn6 = new PawnPiece(1, 5, false, 14);
		Tile BlackPawnTile6 = new Tile(BlackPawn6);
		cb.setTile(1, 5, BlackPawnTile6);
		
		BlackPawn7 = new PawnPiece(1, 6, false, 15);
		Tile BlackPawnTile7 = new Tile(BlackPawn7);
		cb.setTile(1, 6, BlackPawnTile7);
		
		BlackPawn8 = new PawnPiece(1, 7, false, 16);
		Tile BlackPawnTile8 = new Tile(BlackPawn8);
		cb.setTile(1, 7, BlackPawnTile8);
		
		
		WhiteRook1 = new RookPiece(7, 0, true, 1);
		Tile WhiteRookTile1 = new Tile(WhiteRook1);
		cb.setTile(7, 0, WhiteRookTile1);
		
		WhiteKnight1 = new KnightPiece(7, 1, true, 2);
		Tile WhiteKnightTile1 = new Tile(WhiteKnight1);
		cb.setTile(7,  1, WhiteKnightTile1);
		
		WhiteBishop1 = new BishopPiece(7, 2, true, 3);
		Tile WhiteBishopTile1 = new Tile(WhiteBishop1);
		cb.setTile(7,  2, WhiteBishopTile1);
		
		WhiteQueen = new QueenPiece(7, 3, true, 4);
		Tile WhiteQueenTile = new Tile(WhiteQueen);
		cb.setTile(7,  3, WhiteQueenTile);
		
		WhiteKing = new KingPiece(7, 4, true, 5);
		Tile WhiteKingTile = new Tile(WhiteKing);
		cb.setTile(7,  4, WhiteKingTile);
		
		WhiteBishop2 = new BishopPiece(7, 5, true, 6);
		Tile WhiteBishopTile2 = new Tile(WhiteBishop2);
		cb.setTile(7,  5, WhiteBishopTile2);
		
		WhiteKnight2 = new KnightPiece(7, 6, true, 7);
		Tile WhiteKnightTile2 = new Tile(WhiteKnight2);
		cb.setTile(7,  6, WhiteKnightTile2);
		
		WhiteRook2 = new RookPiece(7, 7, true, 8);
		Tile WhiteRookTile2 = new Tile(WhiteRook2);
		cb.setTile(7,  7, WhiteRookTile2);
		
		WhitePawn1 = new PawnPiece(6, 0, true, 9);
		Tile WhitePawnTile = new Tile(WhitePawn1);
		cb.setTile(6, 0, WhitePawnTile);
		
		WhitePawn2 = new PawnPiece(6, 1, true, 10);
		Tile WhitePawnTile2 = new Tile(WhitePawn2);
		cb.setTile(6, 1, WhitePawnTile2);
		
		WhitePawn3 = new PawnPiece(6, 2, true, 11);
		Tile WhitePawnTile3 = new Tile(WhitePawn3);
		cb.setTile(6, 2, WhitePawnTile3);
		
		WhitePawn4 = new PawnPiece(6, 3, true, 12);
		Tile WhitePawnTile4 = new Tile(WhitePawn4);
		cb.setTile(6, 3, WhitePawnTile4);
		
		WhitePawn5 = new PawnPiece(6, 4, true, 13);
		Tile WhitePawnTile5 = new Tile(WhitePawn5);
		cb.setTile(6, 4, WhitePawnTile5);
		
		WhitePawn6 = new PawnPiece(6, 5, true, 14);
		Tile WhitePawnTile6 = new Tile(WhitePawn6);
		cb.setTile(6, 5, WhitePawnTile6);
		
		WhitePawn7 = new PawnPiece(6, 6, true, 15);
		Tile WhitePawnTile7 = new Tile(WhitePawn7);
		cb.setTile(6, 6, WhitePawnTile7);
		
		WhitePawn8 = new PawnPiece(6, 7, true, 16);
		Tile WhitePawnTile8 = new Tile(WhitePawn8);
		cb.setTile(6, 7, WhitePawnTile8);
		
		WhitePieces.add(WhiteRook1);
		WhitePieces.add(WhiteKnight1);
		WhitePieces.add(WhiteBishop1);
		WhitePieces.add(WhiteQueen);
		WhitePieces.add(WhiteKing);
		WhitePieces.add(WhiteBishop2);
		WhitePieces.add(WhiteKnight2);
		WhitePieces.add(WhiteRook2);
		
		WhitePieces.add(WhitePawn1);
		WhitePieces.add(WhitePawn2);
		WhitePieces.add(WhitePawn3);
		WhitePieces.add(WhitePawn4);
		WhitePieces.add(WhitePawn5);
		WhitePieces.add(WhitePawn6);
		WhitePieces.add(WhitePawn7);
		WhitePieces.add(WhitePawn8);
		
		BlackPieces.add(BlackRook1);
		BlackPieces.add(BlackKnight1);
		BlackPieces.add(BlackBishop1);
		BlackPieces.add(BlackQueen);
		BlackPieces.add(BlackKing);
		BlackPieces.add(BlackBishop2);
		BlackPieces.add(BlackKnight2);
		BlackPieces.add(BlackRook2);
		
		BlackPieces.add(BlackPawn1);
		BlackPieces.add(BlackPawn2);
		BlackPieces.add(BlackPawn3);
		BlackPieces.add(BlackPawn4);
		BlackPieces.add(BlackPawn5);
		BlackPieces.add(BlackPawn6);
		BlackPieces.add(BlackPawn7);
		BlackPieces.add(BlackPawn8);
		
	}
	
	public void setChessBoard(ChessBoard c)
	{
		this.cb = c;
	}
	
	public ChessBoard getChessBoard()
	{
		return this.cb;
	}
	
	public ArrayList<ChessPiece> getWhitePieces()
	{
		return this.WhitePieces;
	}
	
	public ArrayList<ChessPiece> getBlackPieces()
	{
		return this.BlackPieces;
	}
	
	public KingPiece getWhiteKing() 
	{
		return this.WhiteKing;
	}
	
	public KingPiece getBlackKing() 
	{
		return this.BlackKing;
	}
	
	public Player getWhitePlayer() 
	{
		return this.WhitePlayer;
	}
	
	public Player getBlackPlayer() 
	{
		return this.BlackPlayer;
	}
	
	public void isFinish()
	{
		this.finished = true;
	}
	
	public boolean getFinish()
	{
		return this.finished;
	}
	
	public ChessPiece getPiece(int n, ArrayList<ChessPiece> pieces)
	{
		for(ChessPiece p : pieces)
		{
			if(p.getPieceNumber() == n && p.getKilled() == false)
			{
				return p;
			}
		}
		
		return null;
	}
	
	public Player getWinner()
	{
		if(this.getFinish() == true)
		{
			return this.winnerPlayer;
		}
		
		else
		{
			return null;
		}
	}
	
	public boolean checkMove(int newx, int newy, ChessBoard cb, ChessPiece cp, Player p)
	{
		if(cp.checkMove(newx, newy, cb))
		{
			int oldx = cp.getPosX();
			int oldy = cp.getPosY();
			Game updatedGame = new Game();
			updatedGame.setGame();
			updatedGame.setChessBoard(this.getChessBoard());
			updatedGame.testMove(cb, cp, newx, newy);
			
			if(p.getColor() == true)
			{
				if(p.isCheck(updatedGame.getChessBoard(), updatedGame.getWhiteKing()))
				{
					//reset board
					updatedGame.testMove(cb, cp, oldx, oldy);
					return false;
				}
				
				//reset board
				updatedGame.testMove(cb, cp, oldx, oldy);
				return true;
			}
			
			else
			{
				if(p.isCheck(updatedGame.getChessBoard(), updatedGame.getBlackKing()))
				{
					//reset board
					updatedGame.testMove(cb, cp, oldx, oldy);
					return false;
				}
				
				//reset board
				updatedGame.testMove(cb, cp, oldx, oldy);
				return true;
			}
		}
		
		return false;
	}
	
	public void testMove(ChessBoard cb, ChessPiece cp, int x, int y)
	{	
		int oldx = cp.getPosX();
		int oldy = cp.getPosY();
		
		try
		{
			ChessPiece potentialPiece = cb.getTile(x,  y).getPiece();
			//put it out of bounds so it can't be touched.
			//potentialPiece.setPosX(8);
			//potentialPiece.setPosY(8);
		}
		catch(NullPointerException n)
		{
		}
		
		Tile newTile = new Tile(cp);
		cb.setTile(x,  y, newTile);
		//sets old tile to null, so no tile is there
		cb.setTile(oldx,  oldy);
		cp.setPosX(x);
		cp.setPosY(y);
	}
	
	public void doMove(ChessBoard cb, ChessPiece cp, int x, int y)
	{	
		int oldx = cp.getPosX();
		int oldy = cp.getPosY();
		boolean takesPiece = false;
		
		try
		{
			ChessPiece potentialPiece = cb.getTile(x,  y).getPiece();
			takesPiece = true;
			potentialPiece.isKilled();
		}
		catch(NullPointerException n)
		{
			takesPiece = false;
		}
		
		Tile newTile = new Tile(cp);
		cb.setTile(x,  y, newTile);
		//sets old tile to null, so no tile is there
		cb.setTile(oldx,  oldy);
		cp.setPosX(x);
		cp.setPosY(y);
		
		if(cp.whatPiece() == "Pawn" || cp.whatPiece() == "Rook" || cp.whatPiece() == "King")
		{
			cp.setHaveMoved(true);
		}
		
		Move thisMove;
		if(cp.getColor() == true)
		{
			thisMove = new Move(cp, x, y, this.getResult(this.getWhitePlayer(), this.getChessBoard(), this.getWhiteKing(), this.getWhitePieces()), takesPiece, false);
		}
		
		else
		{
			thisMove = new Move(cp, x, y, this.getResult(this.getBlackPlayer(), this.getChessBoard(), this.getBlackKing(), this.getBlackPieces()), takesPiece, false);
		}
		
		MoveList.add(thisMove);
		
	}
	
	public String getResult(Player player, ChessBoard cb, KingPiece kingPiece, ArrayList<ChessPiece> pieces)
	{
		String result;
		if(player.isCheck(cb, kingPiece) == true)
		{
			System.out.println("In some form of check");
			//if in check, check to see if they are also in checkmate
			if(player.isCheckmate(cb, kingPiece, pieces) == true)
			{
				result = "Checkmate";
				finished = true;
			}
			else
			{
				result = "Check";
			}
		}
	
		
		else if(player.isDraw(cb, kingPiece, pieces) == true)
		{
			System.out.println("Not in any type of check");
			result = "Draw";
			finished = true;
		}
		
		else
		{
			result = "";
		}
		
		return result;
	}
	
	public void printMoveList()
	{
		for(Move m : this.MoveList)
		{
			m.printMove();
		}
	}
	
	public String getMoveList()
	{
		String finalString = "";
		for(Move m : this.MoveList)
		{
			finalString += m.getMove() + "\n";
		}
		return finalString;
	}
	
	public void playGame(Player player1, Player player2)
	{
		this.setGame();
		
		//while(this.WhitePlayer.isCheckmate(this.getChessBoard(), WhiteKing) == false && this.BlackPlayer.isCheckmate(this.getChessBoard(), BlackKing) == false)
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
