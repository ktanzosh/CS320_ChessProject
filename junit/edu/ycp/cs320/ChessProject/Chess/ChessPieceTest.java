package edu.ycp.cs320.ChessProject.Chess;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import edu.ycp.cs320.ChessProject.Chess.ChessBoard;
import edu.ycp.cs320.ChessProject.Chess.ChessPiece;
import edu.ycp.cs320.ChessProject.Chess.RookPiece;
import edu.ycp.cs320.ChessProject.Chess.BishopPiece;
import edu.ycp.cs320.ChessProject.Chess.QueenPiece;
import edu.ycp.cs320.ChessProject.Chess.KingPiece;
import edu.ycp.cs320.ChessProject.Chess.PawnPiece;

public class ChessPieceTest {
	private RookPiece RookTest;
	private RookPiece RookTest2;
	private BishopPiece BishopTest;
	private KnightPiece KnightTest;
	private QueenPiece QueenTest;
	private KingPiece KingTest;
	private PawnPiece WhitePawn;
	private PawnPiece BlackPawn;
	private PawnPiece BlockerPiece;
	private PawnPiece BlockerPiece2;
	private PawnPiece BlockerPiece3;
	private PawnPiece BlockerPiece4;
	private PawnPiece BlockerPiece5;
	private PawnPiece BlockerPiece6;
	private PawnPiece BlockerPiece7;
	private ChessBoard board;
	private Tile test;
	private Tile test2;
	private Player testPlayer;
	private Player testPlayer2;
	private Game game;
	
	
	@Before
	public void setUp() 
	{
		RookTest = new RookPiece(0, 0, true, 1);
		RookTest2 = new RookPiece(7, 7, false, 1);
		BishopTest = new BishopPiece(4, 4, true, 1);
		KnightTest = new KnightPiece(4, 4, true, 1);
		QueenTest = new QueenPiece(4, 4, true, 1);
		KingTest = new KingPiece(1, 1, true, 1);
		WhitePawn = new PawnPiece(6, 6, true, 1);
		BlackPawn = new PawnPiece(1, 1, false, 1);
		BlockerPiece = new PawnPiece(3, 0, true, 1);
		BlockerPiece2 = new PawnPiece(5, 5, true, 1);
		BlockerPiece3 = new PawnPiece(3, 2, true, 1);
		BlockerPiece4 = new PawnPiece(5, 2, false, 1);
		BlockerPiece5 = new PawnPiece(1, 2, false, 1);
		BlockerPiece6 = new PawnPiece(1, 3, false, 1);
		BlockerPiece7 = new PawnPiece(4, 6, false, 1);
		board = new ChessBoard();
		testPlayer = new Player(true);
		testPlayer2 = new Player(false);
		game = new Game();
	}

	@Test
	public void testRookCheckMove() 
	{
		assertTrue(RookTest.checkMove(7, 0, board));
		test = new Tile(BlockerPiece);
		board.setTile(BlockerPiece.getPosX(), BlockerPiece.getPosY(), test);
		assertTrue(RookTest.checkMove(7, 0, board) == false); // with obstacle now false
		assertTrue(RookTest.checkMove(0, 3, board));
		assertTrue(RookTest2.checkMove(0, 7, board));
		assertTrue(RookTest2.checkMove(7, 4, board));
		assertTrue(RookTest.checkMove(0,0, board) == false); //no movement
		assertTrue(RookTest.checkMove(8,0, board) == false); //out of bounds
		assertTrue(RookTest.checkMove(-1,0, board) == false); //out of bounds
		assertTrue(RookTest.checkMove(1,  1, board) == false); //diagonal movement
	}
	
	@Test
	public void testBishopBoardCheckMove() 
	{
		assertTrue(BishopTest.checkMove(7, 7, board)); 
		test = new Tile(BlockerPiece2);
		board.setTile(BlockerPiece2.getPosX(), BlockerPiece2.getPosY(), test);
		assertTrue(BishopTest.checkMove(7, 7, board) == false); //now with obstacle
		assertTrue(BishopTest.checkMove(0, 0, board));
		assertTrue(BishopTest.checkMove(1, 7, board));
		assertTrue(BishopTest.checkMove(7, 1, board));
		assertTrue(BishopTest.checkMove(4, 4, board) == false); //no movement
		assertTrue(BishopTest.checkMove(8, 8, board) == false); //out of bounds
		assertTrue(BishopTest.checkMove(-1, -1, board) == false); //out of bounds
		assertTrue(BishopTest.checkMove(4, 5, board) == false); //non-diagonal movement
	}
	
	@Test
	public void testKnightBoardCheckMove()
	{
		assertTrue(KnightTest.checkMove(5, 2, board)); //1 forward 2 side
		assertTrue(KnightTest.checkMove(3, 2, board)); //1 back 2 side
		test = new Tile(BlockerPiece3);
		board.setTile(BlockerPiece3.getPosX(), BlockerPiece3.getPosY(), test);
		assertTrue(KnightTest.checkMove(3,  2, board) == false); //now another test but now a white piece is in the way
		test2 = new Tile(BlockerPiece4);
		board.setTile(BlockerPiece4.getPosX(), BlockerPiece4.getPosY(), test2); 
		assertTrue(KnightTest.checkMove(5, 2, board)); //1 forward 2 side//someone in the way, but an enemy piece
		
		assertTrue(KnightTest.checkMove(5, 6, board)); //1 forward 2 side
		assertTrue(KnightTest.checkMove(3, 6, board)); //1 back 2 side
		assertTrue(KnightTest.checkMove(2, 5, board)); //1 side 2 back
		assertTrue(KnightTest.checkMove(2, 3, board)); //1 side 2 back
		assertTrue(KnightTest.checkMove(6, 5, board)); //1 side 2 forward
		assertTrue(KnightTest.checkMove(6, 3, board)); //1 side 2 forward
		
		assertTrue(KnightTest.checkMove(4, 4, board) == false); //didnt move
		assertTrue(KnightTest.checkMove(2, 2, board) == false); //2 back 2 side
		assertTrue(KnightTest.checkMove(2, 1, board) == false); //2 back 3 side
		assertTrue(KnightTest.checkMove(3, 3, board) == false); //1 back 1 side
		assertTrue(KnightTest.checkMove(-1, 4, board) == false); //out of bounds
		assertTrue(KnightTest.checkMove(8, 4, board) == false); //out of bounds
	}
	
	@Test
	public void testQueenCheckMove() 
	{
		assertTrue(QueenTest.checkMove(7, 7, board)); //diaganol movement
		assertTrue(QueenTest.checkMove(2, 6, board)); //another diagnol
		assertTrue(QueenTest.checkMove(0, 0, board)); //another diagnol
		assertTrue(QueenTest.checkMove(4, 7, board)); //vertical movement
		assertTrue(QueenTest.checkMove(7, 4, board)); //horizontal diagnol
		assertTrue(QueenTest.checkMove(4, 4, board) == false); //no movement
		assertTrue(QueenTest.checkMove(5, 6, board) == false); //2 by 1 movement
		assertTrue(QueenTest.checkMove(8, 4, board) == false); //out of bounds
		assertTrue(QueenTest.checkMove(-1, -1, board) == false); //out of bounds
		
		test = new Tile(BlockerPiece2);
		board.setTile(BlockerPiece2.getPosX(), BlockerPiece2.getPosY(), test);
		assertTrue(QueenTest.checkMove(7, 7, board) == false); //diaganol movement now with blocker
		
		test2 = new Tile(BlockerPiece7);
		board.setTile(BlockerPiece7.getPosX(), BlockerPiece7.getPosY(), test);
		assertTrue(QueenTest.checkMove(4, 7, board) == false); //vertical movement now with blocker
	}
	
	@Test
	public void testKingCheckMove()
	{
		assertTrue(KingTest.checkMove(0, 0, board)); //diaganol movement
		assertTrue(KingTest.checkMove(2, 0, board)); //another diagnol
		assertTrue(KingTest.checkMove(1, 0, board)); //vertical movement
		assertTrue(KingTest.checkMove(0, 1, board)); //horizontal diagnol
		assertTrue(KingTest.checkMove(1, 1, board) == false); //no movement
		assertTrue(KingTest.checkMove(1, 3, board) == false); //2 movement
		assertTrue(KingTest.checkMove(-1, -1, board) == false); //out of bounds
		assertTrue(KingTest.checkMove(8, 8, board) == false); //out of bounds
	}
	
	@Test
	public void testPawnCheckMove()
	{
		assertTrue(WhitePawn.checkMove(5, 6, board)); // 1 forward no move 
		assertTrue(WhitePawn.checkMove(4, 6, board)); // 2 forward no move 
		assertTrue(WhitePawn.checkMove(5, 5, board) == false); // diagonal movement(no person there)
		
		Tile test2 = new Tile(BlockerPiece6);
		board.setTile(4, 6, test2);
		assertTrue(WhitePawn.checkMove(4, 6, board) == false); // a person is in the way for vertical movements.
		
		Tile test = new Tile(BlockerPiece5);
		board.setTile(5, 6, test);
		assertTrue(WhitePawn.checkMove(5, 6, board) == false); // a person is in the way for vertical movements.
		
		WhitePawn.setHaveMoved(true);
		board.setTile(4, 6);
		board.setTile(5, 6);
		assertTrue(WhitePawn.checkMove(5, 6, board)); // 1 forward has move 
		assertTrue(WhitePawn.checkMove(4, 6, board) == false); // 2 forward has move 
		
		assertTrue(BlackPawn.checkMove(2, 1, board)); // 1 forward no move 
		assertTrue(BlackPawn.checkMove(3, 1, board)); // 2 forward no move 
		assertTrue(BlackPawn.checkMove(2, 2, board) == false); // diagonal movement(no person there)
		
		Tile test3 = new Tile(BlockerPiece2);
		board.setTile(2, 2, test3);
		assertTrue(BlackPawn.checkMove(2, 2, board)); // diagonal movement(person there)
		
		BlackPawn.setHaveMoved(true);
		
		assertTrue(BlackPawn.checkMove(2, 1, board)); // 1 forward has move 
		assertTrue(BlackPawn.checkMove(3, 1, board) == false); // 2 forward has move 
	}
	
	@Test
	public void testTile()
	{
		test = new Tile(BlockerPiece);
		board.setTile(3, 0, test);
		assertTrue(test.getPiece() == BlockerPiece);
	}
	
	@Test
	public void testMove()
	{
		assertTrue(RookTest.checkMove(0,0) == false);
		assertTrue(RookTest.checkMove(0,7) == true);
		game.testMove(board, RookTest, 0, 7);
		// move = new Move(RookTest, 0, 7);
		//System.out.println("\n " + "After Move rook is: " + RookTest.getPosX() + " " + RookTest.getPosY() + "\n");
		assertTrue(RookTest.checkMove(0,0) == true); //after moving these are now switched
		assertTrue(RookTest.checkMove(0,7) == false);
	}
	
	@Test
	public void testCheck()
	{
		test = new Tile(KingTest);
		board.setTile(1,1, test);
		assertTrue(testPlayer.isCheck(board, KingTest) == false);
		
		BishopPiece problemBishop = new BishopPiece(6, 6, false, 2);
		Tile PutInCheck = new Tile(problemBishop);
		board.setTile(6, 6, PutInCheck);
		assertTrue(testPlayer.isCheck(board, KingTest)); //Now with a black Bishop that puts them in check.
		
		RookPiece problemRook = new RookPiece(6, 6, false, 2);
		PutInCheck = new Tile(problemRook);
		board.setTile(6, 6, PutInCheck);
		assertTrue(testPlayer.isCheck(board, KingTest) == false); //Now with a Black Rook, not a problem
		
		PutInCheck = new Tile(problemBishop);
		board.setTile(6, 6, PutInCheck);
		
		PawnPiece blockerPawn = new PawnPiece(3, 3, true, 2);
		Tile blockerCheck = new Tile(blockerPawn);
		board.setTile(3, 3, blockerCheck);
		assertTrue(testPlayer.isCheck(board, KingTest) == false); //Black Bishop would be in check, but white pawn blocks
		
		problemRook = new RookPiece(1, 6, false, 2);
		PutInCheck = new Tile(problemRook);
		board.setTile(1, 6, PutInCheck);
		assertTrue(testPlayer.isCheck(board, KingTest)); //Now with a black Rook that puts them in check.
		
		PawnPiece blockerPawn2 = new PawnPiece(1, 5, false, 2);
		Tile blockerCheck2 = new Tile(blockerPawn2);
		board.setTile(1, 5, blockerCheck2);
		assertTrue(testPlayer.isCheck(board, KingTest) == false); //pawn blocker even though the pawn is black
		
		KnightPiece problemKnight = new KnightPiece(3, 2, false, 2);
		PutInCheck = new Tile(problemKnight);
		board.setTile(3, 2, PutInCheck);
		assertTrue(testPlayer.isCheck(board, KingTest)); // Now with a black knight
	}
	
	@Test
	public void testCheckmate()
	{
		ArrayList<ChessPiece> friendlyPieces;
		friendlyPieces = new ArrayList<ChessPiece>();
		test = new Tile(KingTest);
		board.setTile(1,1, test);
		assertTrue(testPlayer.isCheckmate(board, KingTest, friendlyPieces) == false);
		
		RookPiece problemRook = new RookPiece(1, 5, false, 3);
		Tile PutInCheck = new Tile(problemRook);
		board.setTile(1, 5, PutInCheck);
		assertTrue(testPlayer.isCheck(board, KingTest));
		assertTrue(testPlayer.isCheckmate(board, KingTest, friendlyPieces) == false);
		
		RookPiece problemRook2 = new RookPiece(0, 5, false, 3);
		Tile PutInCheck2 = new Tile(problemRook2);
		board.setTile(0, 5, PutInCheck2);
		assertTrue(testPlayer.isCheck(board, KingTest));
		assertTrue(testPlayer.isCheckmate(board, KingTest, friendlyPieces) == false);
		
		QueenPiece problemQueen = new QueenPiece(2, 5, false, 3);
		Tile PutInCheck3 = new Tile(problemQueen);
		board.setTile(2,  5, PutInCheck3);
		assertTrue(testPlayer.isCheck(board, KingTest));
		assertTrue(testPlayer.isCheckmate(board, KingTest, friendlyPieces));
		
		KnightPiece friendlyKnight = new KnightPiece(3, 6, true, 3);
		friendlyPieces.add(friendlyKnight);
		Tile getOutofCheck = new Tile(friendlyKnight);
		board.setTile(3,  6, getOutofCheck);
		assertTrue(testPlayer.isCheck(board, KingTest));
		//knight can take threatening piece
		assertTrue(testPlayer.isCheckmate(board, KingTest, friendlyPieces) == false);
	}
	
	@Test
	public void testDraw()
	{
		ArrayList<ChessPiece> friendlyPieces;
		ChessBoard newBoard = new ChessBoard();
		friendlyPieces = new ArrayList<ChessPiece>();
		KingPiece King = new KingPiece(1, 1, true, 4);
		friendlyPieces.add(King);
		test = new Tile(King);
		board.setTile(1,  1, test);
		assertTrue(testPlayer.isDraw(board, King, friendlyPieces) == false);
		
		RookPiece Threat1 = new RookPiece(0, 7, false, 4);
		Tile drawTile = new Tile(Threat1);
		board.setTile(0, 7, drawTile);
		RookPiece Threat2 = new RookPiece(2, 7, false, 4);
		Tile drawTile2 = new Tile(Threat2);
		board.setTile(2, 7, drawTile2);
		RookPiece Threat3 = new RookPiece(7, 0, false, 4);
		Tile drawTile3 = new Tile(Threat3);
		board.setTile(7, 0, drawTile3);
		RookPiece Threat4 = new RookPiece(7, 2, false, 4);
		Tile drawTile4 = new Tile(Threat4);
		board.setTile(7, 2, drawTile4);
		
		System.out.println("");
		//currently not working
		assertTrue(testPlayer.isCheck(board,  King) == false);
		assertTrue(testPlayer.isDraw(board,  KingTest,  friendlyPieces) == true);
		
		//adding friendly moves that wouldn't threaten them
		KnightPiece Friendly1 = new KnightPiece(4, 4, true, 4);
		friendlyPieces.add(Friendly1);
		Tile drawTile5 = new Tile(Friendly1);
		board.setTile(4, 4, drawTile5);
		assertTrue(testPlayer.isDraw(board,  KingTest,  friendlyPieces) == false);
	}
	
	@Test
	public void testCastling()
	{
		KingPiece kp = new KingPiece(7, 4, true, 5);
		Tile kingTile = new Tile(kp);
		board.setTile(7, 4, kingTile);
		assertTrue(kp.canCastle(7, 6, board) == false);
		
		BishopPiece bp = new BishopPiece(7, 7, true, 5);
		Tile CastleTile = new Tile(bp);
		board.setTile(7, 7, CastleTile);
		assertTrue(kp.canCastle(7, 6, board) == false);
		
		RookPiece brp = new RookPiece(7, 7, false, 5);
		CastleTile = new Tile(brp);
		board.setTile(7, 7, CastleTile);
		assertTrue(kp.canCastle(7, 6, board) == false);
		
		RookPiece wrp = new RookPiece(7, 7, true, 5);
		CastleTile = new Tile(wrp);
		board.setTile(7, 7, CastleTile);
		//assertTrue(kp.canCastle(7, 6, board) == true);
		
		wrp.setHaveMoved(true);
		assertTrue(kp.canCastle(7, 6, board) == false);
		wrp.setHaveMoved(false);
		
		RookPiece wrp2 = new RookPiece(7, 5, true, 5);
		Tile CastleTile2 = new Tile(wrp2);
		board.setTile(7, 5, CastleTile2);
		assertTrue(kp.canCastle(7, 7, board) == false);
		
	}
	
	@Test
	public void testGetResult()
	{
		Game newGame = new Game();
		newGame.setGame(true);
		ArrayList<ChessPiece> friendlyPieces;
		friendlyPieces = new ArrayList<ChessPiece>();
		friendlyPieces.add(KingTest);
		test = new Tile(KingTest);
		ChessBoard testBoard = newGame.getChessBoard();
		testBoard.setTile(1,1, test);
		
		String res = newGame.getResult(testPlayer, newGame.getChessBoard(), KingTest, friendlyPieces);
		assertTrue(res == "");
		assertTrue(newGame.getFinish() == false);
		assertTrue(newGame.getWinner() == null);
		
		
		
		RookPiece problemRook = new RookPiece(1, 5, false, 6);
		Tile PutInCheck = new Tile(problemRook);
		testBoard.setTile(1, 5, PutInCheck);
		RookPiece problemRook2 = new RookPiece(0, 5, false, 6);
		Tile PutInCheck2 = new Tile(problemRook2);
		testBoard.setTile(0, 5, PutInCheck2);
		QueenPiece problemQueen = new QueenPiece(2, 5, false, 6);
		Tile PutInCheck3 = new Tile(problemQueen);
		testBoard.setTile(2,  5, PutInCheck3);
		res = newGame.getResult(testPlayer, newGame.getChessBoard(), KingTest, friendlyPieces);
		System.out.println(res);
		assertTrue(res == "Checkmate");
		assertTrue(newGame.getFinish() == true);
	}
	
	@Test
	public void testGameMove()
	{
		TestMain tm = new TestMain();
		Game newGame = new Game();
		newGame.setGame();
		ChessPiece testPawn = newGame.getChessBoard().getTile(6,  5).getPiece();
		ChessBoard cboard = newGame.getChessBoard();
		tm.drawBoard(newGame);
		assertTrue(testPawn.checkMove(5, 5, cboard));
		assertTrue(newGame.checkMove(5, 5, cboard, testPawn, newGame.getWhitePlayer()) == true);
		//newGame.doMove(cboard, testPawn, 5, 5);
		
		BishopPiece threatenPiece = new BishopPiece(4, 7, false, 7);
		Tile PutInCheck = new Tile(threatenPiece);
		cboard.setTile(4, 7, PutInCheck);
		tm.drawBoard(newGame);
		assertTrue(testPawn.checkMove(5, 5, cboard));
		assertTrue(newGame.checkMove(5, 5, cboard, testPawn, newGame.getWhitePlayer()) == false);
	}
	
	@Test
	public void testDoMove()
	{
		TestMain tm = new TestMain();
		
		game.setGame();
		game.setGame(false);
		Tile RookTile = new Tile(RookTest);
		game.getChessBoard().setTile(0, 0, RookTile);
		
		System.out.println("Set Game");
		tm.drawBoard(game);
		
		System.out.println(RookTest.getPosX() + " " + RookTest.getPosY());
		game.doMove(game.getChessBoard(), RookTest, 0, 7);
		
		System.out.println(RookTest.getPosX() + " " + RookTest.getPosY());
		System.out.println("After Move Game");
		tm.drawBoard(game);
		game.printMoveList();
		
		game.setGame(false);
		RookPiece TakeRook = new RookPiece(0, 7, false, 1);
		Tile TakePiece = new Tile(TakeRook);
		RookTest.setPosX(0);
		RookTest.setPosY(0);
		game.getChessBoard().setTile(TakeRook.getPosX(), TakeRook.getPosY(), TakePiece);
		System.out.println(RookTest.getPosX() + " " + RookTest.getPosY());
		System.out.println("Reset Game");
		tm.drawBoard(game);
		
		game.doMove(game.getChessBoard(), RookTest, 0, 7);
		tm.drawBoard(game);
		game.printMoveList();
	}
}
