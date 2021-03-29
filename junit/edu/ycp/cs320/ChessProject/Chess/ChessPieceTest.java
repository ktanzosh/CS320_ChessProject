package edu.ycp.cs320.ChessProject.Chess;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

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
	
	
	@Before
	public void setUp() 
	{
		RookTest = new RookPiece(0, 0, true);
		RookTest2 = new RookPiece(7, 7, false);
		BishopTest = new BishopPiece(4, 4, true);
		KnightTest = new KnightPiece(4, 4, true);
		QueenTest = new QueenPiece(4, 4, true);
		KingTest = new KingPiece(1, 1, true);
		WhitePawn = new PawnPiece(1, 1, true);
		BlackPawn = new PawnPiece(6, 6, false);
		BlockerPiece = new PawnPiece(3, 0, true);
		BlockerPiece2 = new PawnPiece(5, 5, true);
		BlockerPiece3 = new PawnPiece(3, 2, true);
		BlockerPiece4 = new PawnPiece(5, 2, false);
		BlockerPiece5 = new PawnPiece(1, 2, false);
		BlockerPiece6 = new PawnPiece(1, 3, false);
		BlockerPiece7 = new PawnPiece(4, 6, false);
		board = new ChessBoard();
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
		assertTrue(WhitePawn.checkMove(1, 2, board)); // 1 forward no move 
		assertTrue(WhitePawn.checkMove(1, 3, board)); // 2 forward no move 
		assertTrue(WhitePawn.checkMove(2, 2, board) == false); // diagonal movement(no person there)
		
		Tile test2 = new Tile(BlockerPiece6);
		board.setTile(1, 3, test2);
		assertTrue(WhitePawn.checkMove(1, 3, board) == false); // a person is in the way for vertical movements.
		
		Tile test = new Tile(BlockerPiece5);
		board.setTile(1, 2, test);
		assertTrue(WhitePawn.checkMove(1, 2, board) == false); // a person is in the way for vertical movements.
		
		WhitePawn.setHaveMoved(true);
		board.setTile(1, 2);
		board.setTile(1,3);
		assertTrue(WhitePawn.checkMove(1, 2, board)); // 1 forward has move 
		assertTrue(WhitePawn.checkMove(1, 3, board) == false); // 2 forward has move 
		
		assertTrue(BlackPawn.checkMove(6, 5, board)); // 1 forward no move 
		assertTrue(BlackPawn.checkMove(6, 4, board)); // 2 forward no move 
		assertTrue(BlackPawn.checkMove(5, 5, board) == false); // diagonal movement(no person there)
		
		Tile test3 = new Tile(BlockerPiece2);
		board.setTile(5, 5, test3);
		assertTrue(BlackPawn.checkMove(5, 5, board)); // diagonal movement(person there)
		
		BlackPawn.setHaveMoved(true);
		
		assertTrue(BlackPawn.checkMove(6, 5, board)); // 1 forward has move 
		assertTrue(BlackPawn.checkMove(6, 4, board) == false); // 2 forward has move 
	}
	
	@Test
	public void testTile()
	{
		test = new Tile(BlockerPiece);
		board.setTile(3, 0, test);
		assertTrue(test.getPiece() == BlockerPiece);
	}
}
