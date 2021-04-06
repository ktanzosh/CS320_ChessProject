package edu.ycp.cs320.ChessProject.Chess;

public class TestMain 
{
	public static void main(String[] args)
	{
		Player WhitePlayer = new Player(true);
		Player BlackPlayer = new Player(false);
		
		Game testGame = new Game();
		testGame.setGame();
		drawBoard(testGame);
		
		
		//ChessPiece movePiece = testGame.getChessBoard().getTile(6,  5).getPiece();
		//if(movePiece.checkMove(5, 5, testGame.getChessBoard()))
		//{
			testGame.doMove(testGame.getChessBoard(), testGame.getChessBoard().getTile(6,  5).getPiece(), 5, 5);
			drawBoard(testGame);
		//}
		
		testGame.doMove(testGame.getChessBoard(), testGame.getChessBoard().getTile(1,  4).getPiece(), 2, 4);
		drawBoard(testGame);
		
		testGame.doMove(testGame.getChessBoard(), testGame.getChessBoard().getTile(6,  6).getPiece(), 4, 6);
		drawBoard(testGame);
		
		testGame.doMove(testGame.getChessBoard(), testGame.getChessBoard().getTile(0,  3).getPiece(), 4, 7);
		drawBoard(testGame);
			
		//Testing Checkmate stuff
		/*testGame.setGame(true);
		KingPiece KingTest = new KingPiece(1, 1, true);
		Tile test = new Tile(KingTest);
		testGame.getChessBoard().setTile(1,1, test);
		drawBoard(testGame);
		
		RookPiece problemRook = new RookPiece(1, 5, false);
		Tile PutInCheck = new Tile(problemRook);
		testGame.getChessBoard().setTile(1, 5, PutInCheck);
		drawBoard(testGame);
		
		RookPiece problemRook2 = new RookPiece(0, 5, false);
		Tile PutInCheck2 = new Tile(problemRook2);
		testGame.getChessBoard().setTile(0, 5, PutInCheck2);
		drawBoard(testGame);
		
		Game copyGame = new Game();
		copyGame = testGame;
		drawBoard(copyGame);
		
		KingPiece testKing = new KingPiece(2, 1, true);
		
		Tile kingTile = new Tile(testKing);
		copyGame.getChessBoard().setTile(2, 1, kingTile);
		copyGame.getChessBoard().setTile(1,  1);
		drawBoard(copyGame);
		drawBoard(testGame);//testGame is the same as copyGame, so it prints out the same board, KingTest doesn't move then, so not legal Move
		//Also, the test says from 1, 1 to 2, 1 since kingTest is never touched
		
		if(WhitePlayer.isCheck(copyGame.getChessBoard(), testKing) == false && KingTest.checkMove(2,  1, testGame.getChessBoard()) == true)
		{
			System.out.println("This is a legal move that puts you out of check");
		}
		
		else
		{
			System.out.println("Uh oh");
		}*/
		
		//copyGame.doMove(copyGame.getChessBoard(), KingTest, 2, 0);
		//drawBoard(copyGame);
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
