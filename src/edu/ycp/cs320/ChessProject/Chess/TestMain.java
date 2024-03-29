package edu.ycp.cs320.ChessProject.Chess;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

public class TestMain 
{
	public static void main(String[] args)
	{
		Game testGame = new Game();
		testGame.setGame();
		
		/*Move lastMove = new Move(testGame.getChessBoard().getTile(1, 7).getPiece(), 2, 7, "", false, false, false);
		testGame.getMoves().add(lastMove);
		System.out.println(lastMove.getMove());
		
		//int pieceID = testGame.getChessBoard().getTile(6, 0).getPiece().getPieceNumber();
		//System.out.println(pieceID);
		
		int lastPiece = testGame.getChessBoard().getTile(1, 7).getPiece().getPieceNumber();//pawn id
		String lastMoveInfo = lastMove.getMove();
		int ogSquare = testGame.getSquareNumber(testGame.getLastMoveOrigXPos(testGame.getBlackPieces(), lastPiece), testGame.getLastMoveOrigYPos(testGame.getBlackPieces(), lastPiece));
		int finalSquare = testGame.getSquareNumber(testGame.getLastMoveFinalXPos(lastMoveInfo), testGame.getLastMoveFinalYPos(lastMoveInfo));
		
		System.out.println(testGame.getLastMoveOrigXPos(testGame.getBlackPieces(), lastPiece) + "/" + testGame.getLastMoveOrigYPos(testGame.getBlackPieces(), lastPiece));
		System.out.println(testGame.getLastMoveFinalXPos(lastMoveInfo) + "/" + testGame.getLastMoveFinalYPos(lastMoveInfo));
		
		testGame.doLastMove(lastMoveInfo, testGame.getBlackPieces(), lastPiece);
		String pieceMove = testGame.getLastMovePieceString(lastMoveInfo);
		
		System.out.println(ogSquare + "/" + finalSquare + "/" + pieceMove);*/
		/*Player WhitePlayer = new Player(true);;
		Player BlackPlayer = new Player(false);
		
		Game testGame = new Game();
		testGame.setGame();
		drawBoard(testGame);
		
		testGame.doMove(testGame.getChessBoard(), testGame.getChessBoard().getTile(6, 7).getPiece(), 4, 7);
		drawBoard(testGame);
		
		Move lastMove = new Move(testGame.getChessBoard().getTile(1, 0).getPiece(), 3, 0, "", false, false, false);
		testGame.getMoves().add(lastMove);
		testGame.doLastMove(testGame.getLastMove().getMove(), testGame.getBlackPieces(), 9);
		drawBoard(testGame);*/
		
		Player WhitePlayer = new Player(true);;
		Player BlackPlayer = new Player(false);
		
		testGame.setWhitePlayer(WhitePlayer);
		testGame.setBlackPlayer(BlackPlayer);
		testGame.setGame(true);
		
		ChessBoard cb = testGame.getChessBoard();
		KingPiece KingP = new KingPiece(7, 4, true, 1);
		testGame.getWhitePieces().add(KingP);
		testGame.setWhiteKing(KingP);
		Tile KingTile = new Tile(KingP);
		cb.setTile(7,  4, KingTile);
		
		KingPiece BlackKing = new KingPiece(0, 4, false, 1);
		testGame.getBlackPieces().add(BlackKing);
		testGame.setBlackKing(BlackKing);
		Tile BlackKingTile = new Tile(BlackKing);
		cb.setTile(0,  4, BlackKingTile);
		
		
		RookPiece RookP = new RookPiece(7, 0, true, 1);
		testGame.getWhitePieces().add(RookP);
		Tile RookTile = new Tile(RookP);
		cb.setTile(7,  0, RookTile);
		
		PawnPiece PromotionPiece = new PawnPiece(0, 2, true, 1);
		testGame.getWhitePieces().add(PromotionPiece);
		Tile PromotionTile = new Tile(PromotionPiece);
		cb.setTile(0,  2, PromotionTile);
		drawBoard(testGame);
		
		//if(testGame.checkMove(7, 6, testGame.getChessBoard(), testGame.getChessBoard().getTile(7,  4).getPiece(), testGame.getWhitePlayer()))
		if(testGame.checkMove(7, 1, testGame.getChessBoard(), testGame.getChessBoard().getTile(7,  4).getPiece(), testGame.getWhitePlayer()))
		{
			testGame.doMove(testGame.getChessBoard(), testGame.getChessBoard().getTile(7,  4).getPiece(), 7, 1);
			//testGame.PawnPromotion(0, 2, testGame.getWhitePieces(), "quee");
			drawBoard(testGame);
			testGame.printMoveList();
		}
		
		else
		{
			System.out.println("NO");
			drawBoard(testGame);
		}
		
		PawnPiece TakerPawn = new PawnPiece(6, 3, false, 2);
		testGame.getBlackPieces().add(TakerPawn);
		Tile BlackPawnTile = new Tile(TakerPawn);
		cb.setTile(6,  3, BlackPawnTile);
		drawBoard(testGame);
		
		if(testGame.checkMove(7, 2, testGame.getChessBoard(), testGame.getChessBoard().getTile(6, 3).getPiece(), testGame.getBlackPlayer()))
		{
			testGame.doMove(testGame.getChessBoard(), testGame.getChessBoard().getTile(6, 3).getPiece(), 7, 2);
			testGame.PawnPromotion(7, 2, testGame.getBlackPieces(), "bish");
			drawBoard(testGame);
			testGame.printMoveList();
		}
		
		else
		{
			System.out.println("NO");
			drawBoard(testGame);
		}
		
		/*if(RookP.getKilled() == true)
		{
			System.out.println("Actually killed");
		}*/
		
		/*testGame.setGame();
		drawBoard(testGame);

		
		//if(testGame.getChessBoard().getTile(6,  5).getPiece().checkMove(5, 5, testGame.getChessBoard()))
		if(testGame.checkMove(5, 5, testGame.getChessBoard(), testGame.getChessBoard().getTile(6, 5).getPiece(), WhitePlayer))
		{
			testGame.doMove(testGame.getChessBoard(), testGame.getChessBoard().getTile(6,  5).getPiece(), 5, 5);
			drawBoard(testGame);
		}
		
		
		//if(testGame.getChessBoard().getTile(1,  4).getPiece().checkMove(2, 4, testGame.getChessBoard()))
		if(testGame.checkMove(2, 4, testGame.getChessBoard(), testGame.getChessBoard().getTile(1, 4).getPiece(), BlackPlayer))
		{
			testGame.doMove(testGame.getChessBoard(), testGame.getChessBoard().getTile(1,  4).getPiece(), 2, 4);
			drawBoard(testGame);
		}
		
		//if(testGame.getChessBoard().getTile(6,  6).getPiece().checkMove(4, 6, testGame.getChessBoard()))
		if(testGame.checkMove(4, 6, testGame.getChessBoard(), testGame.getChessBoard().getTile(6, 6).getPiece(), WhitePlayer))
		{
			testGame.doMove(testGame.getChessBoard(), testGame.getChessBoard().getTile(6,  6).getPiece(), 4, 6);
			drawBoard(testGame);
		}
		
		//if(testGame.getChessBoard().getTile(0,  3).getPiece().checkMove(4, 7, testGame.getChessBoard()))
		if(testGame.checkMove(4, 7, testGame.getChessBoard(), testGame.getChessBoard().getTile(0, 3).getPiece(), BlackPlayer))
		{
			testGame.doMove(testGame.getChessBoard(), testGame.getChessBoard().getTile(0,  3).getPiece(), 4, 7);
			drawBoard(testGame);
		}
		
		//System.out.println(testGame.getMoveList());
		
		KingPiece kp = testGame.getWhiteKing();
		String res = testGame.getResult(WhitePlayer, testGame.getChessBoard(), kp, testGame.getWhitePieces());
		System.out.println(res);
		
		drawBoard(testGame);
		testGame.printMoveList();
		
		if(testGame.checkMove(0, 3, testGame.getChessBoard(), testGame.getChessBoard().getTile(0, 4).getPiece(), BlackPlayer))
		{
			testGame.doMove(testGame.getChessBoard(), testGame.getChessBoard().getTile(0,  4).getPiece(), 0, 3);
			drawBoard(testGame);
		}*/
	}
	
	public static void drawBoard(Game test)
	{
		System.out.println("  |A|B|C|D|E|F|G|H|");
		System.out.println("  -----------------");
		
		for(int i = 0; i < 8; i++)
		{
			System.out.print(8-i + " |");
			for(int j = 0; j < 8; j++)
			{
				try
				{
					String c = test.getChessBoard().getTile(i,  j).getPiece().whatInitial();
					System.out.print(c + "|");
				}
				
				catch(NullPointerException n)
				{
					System.out.print(" |");
				}
			}
			System.out.println("\n" + "  -----------------");
		}
		System.out.println("");
	}
}
