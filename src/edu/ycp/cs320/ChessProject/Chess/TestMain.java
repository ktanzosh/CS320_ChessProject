package edu.ycp.cs320.ChessProject.Chess;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

public class TestMain 
{
	public static void main(String[] args)
	{
		Player WhitePlayer = new Player(true);
		Player BlackPlayer = new Player(false);
		
		Game testGame = new Game();
		testGame.setGame();
		drawBoard(testGame);

		
		if(testGame.getChessBoard().getTile(6,  5).getPiece().checkMove(5, 5, testGame.getChessBoard()))
		{
			testGame.doMove(testGame.getChessBoard(), testGame.getChessBoard().getTile(6,  5).getPiece(), 5, 5);
			drawBoard(testGame);
		}
		
		
		if(testGame.getChessBoard().getTile(1,  4).getPiece().checkMove(2, 4, testGame.getChessBoard()))
		{
			testGame.doMove(testGame.getChessBoard(), testGame.getChessBoard().getTile(1,  4).getPiece(), 2, 4);
			drawBoard(testGame);
		}
		
		if(testGame.getChessBoard().getTile(6,  6).getPiece().checkMove(4, 6, testGame.getChessBoard()))
		{
			testGame.doMove(testGame.getChessBoard(), testGame.getChessBoard().getTile(6,  6).getPiece(), 4, 6);
			drawBoard(testGame);
		}
		
		if(testGame.getChessBoard().getTile(0,  3).getPiece().checkMove(4, 7, testGame.getChessBoard()))
		{
			testGame.doMove(testGame.getChessBoard(), testGame.getChessBoard().getTile(0,  3).getPiece(), 4, 7);
			drawBoard(testGame);
		}

		System.out.println(testGame.getMoveList());
		
		
		//KingPiece kp = new KingPiece(7, 4, true);
		KingPiece kp = testGame.getWhiteKing();
		String res = testGame.getResult(WhitePlayer, testGame.getChessBoard(), kp, testGame.getWhitePieces());
		System.out.println(res);
		
		drawBoard(testGame);
		testGame.printMoveList();
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
