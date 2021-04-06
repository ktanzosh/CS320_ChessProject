package edu.ycp.cs320.ChessProject.Chess;

public class Player 
{
	private boolean color;
	
	public Player(boolean c)
	{
		this.color = c;
	}
	
	public boolean getColor()
	{
		return this.color;
	}
	
	public boolean isCheck(ChessBoard cb, KingPiece kingPiece)
	{
		int x = kingPiece.getPosX();
		int y = kingPiece.getPosY();
		
		System.out.println("");
		System.out.println("TESTING CHECK");
		
		//maxes out to 7,7
		//diaganol movement ++
		int i = 1;
		int newx = x + i;
		int newy = y + i;
		
		while(newx < 8 && newy < 8 )
		{
			try
			{
				//get piece at ++
				ChessPiece potentialPiece = cb.getTile(newx,  newy).getPiece();
				//if piece is friendly, dont worry about direction, we gucci
				if(potentialPiece.getColor() == kingPiece.getColor())
				{
					System.out.println(kingPiece.getColor() + " piece in the way");
					break;
				}
				//if not friendly, and is a piece that can move diaganoly, we are in check
				else if(potentialPiece.whatPiece().equals("Queen") || potentialPiece.whatPiece().equals("Bishop"))
				{
					return true;
				}
				
				else
				{
					break;
				}
			}
			
			catch(NullPointerException n)
			{
				//If no one here, no problem, just continue on to the next tile, until out of bounds
				System.out.println("No one at Chess Piece " + newx + ", " + newy);
				i++;
				newx = x + i;
				newy = y + i;
			}
		}
		
		System.out.println("Reset");
		i = 1;
		newx = x - i;
		newy = y - i;
		while(newx >= 0 && newy >= 0)
		{
			try
			{
				//get piece at --
				ChessPiece potentialPiece = cb.getTile(newx,  newy).getPiece();
				//if piece is friendly, dont worry about direction, we are good
				if(potentialPiece.getColor() == kingPiece.getColor())
				{
					System.out.println(newx + ", " + newy  + " i: " + i);
					break;
				}
				//if not friendly, and is a piece that can move diaganolly, we are in check
				else if(potentialPiece.whatPiece().equals("Queen") || potentialPiece.whatPiece().equals("Bishop"))
				{
					return true;
				}
				
				else
				{
					break;
				}
			}
			
			catch(NullPointerException n)
			{
				//If no one here, no problem, just continue on to the next tile, until out of bounds
				System.out.println("No one at Chess Piece " + newx + ", " + newy);
				i++;
				newx = x - i;
				newy = y - i;
			}
		}
		
		System.out.println("Reset");
		i = 1;
		newx = x + i;
		newy = y - i;
		while(newx < 8 && newy >= 0)
		{
			try
			{
				//get piece at +-
				ChessPiece potentialPiece = cb.getTile(newx,  newy).getPiece();
				//if piece is friendly, dont worry about direction, we are fine
				if(potentialPiece.getColor() == kingPiece.getColor())
				{
					System.out.println(newx + ", " + newy  + " i: " + i);
					break;
				}
				//if not friendly, and is a piece that can move diaganoly, we are in check
				else if(potentialPiece.whatPiece().equals("Queen") || potentialPiece.whatPiece().equals("Bishop"))
				{
					return true;
				}
				
				else
				{
					break;
				}
			}
			
			catch(NullPointerException n)
			{
				//If no one here, no problem, just continue on to the next tile, until out of bounds
				System.out.println("No one at Chess Piece " + newx + ", " + newy);
				i++;
				newx = x + i;
				newy = y - i;
			}
		}
		
		System.out.println("Reset");
		i = 1;
		newx = x - i;
		newy = y + i;
		while(newx >= 0 && newy < 8) // adds 1 when it shouldnt
		{
			try
			{
				//get piece at -+
				ChessPiece potentialPiece = cb.getTile(newx,  newy).getPiece();
				//if piece is friendly, dont worry about direction, we are fine
				if(potentialPiece.getColor() == kingPiece.getColor())
				{
					System.out.println(newx + ", " + newy  + " i: " + i);
					break;
				}
				//if not friendly, and is a piece that can move diaganolly, we are in check
				else if(potentialPiece.whatPiece().equals("Queen") || potentialPiece.whatPiece().equals("Bishop"))
				{
					return true;
				}
				
				else
				{
					break;
				}
			}
			
			catch(NullPointerException n)
			{
				//If no one here, no problem, just continue on to the next tile, until out of bounds
				System.out.println("No one at Chess Piece " + newx + ", " + newy);
				i++;
				newx = x - i;
				newy = y + i;
			}
		}
		
		System.out.println("Reset");
		i = 1;
		newx = x + i;
		while(newx < 8)
		{
			try
			{
				//get piece at +0
				ChessPiece potentialPiece = cb.getTile(newx,  y).getPiece();
				//if piece is friendly, dont worry about direction, we are fine
				if(potentialPiece.getColor() == kingPiece.getColor())
				{
					break;
				}
				//if not friendly, and is a piece that can move vertical, we are in check
				else if(potentialPiece.whatPiece().equals("Queen") || potentialPiece.whatPiece().equals("Rook"))
				{
					return true;
				}
				
				else
				{
					break;
				}
			}
			
			catch(NullPointerException n)
			{
				//If no one here, no problem, just continue on to the next tile, until out of bounds
				System.out.println("No one at Chess Piece " + newx + ", " + y);
				i++;
				newx = x + i;
			}
		}
		
		System.out.println("Reset");
		i = 1;
		newx = x - i;
		while(newx >= 0)
		{
			try
			{
				//get piece at -0
				ChessPiece potentialPiece = cb.getTile(newx,  y).getPiece();
				//if piece is friendly, dont worry about direction, we are fine
				if(potentialPiece.getColor() == kingPiece.getColor())
				{
					break;
				}
				//if not friendly, and is a piece that can move vertical, we are in check
				else if(potentialPiece.whatPiece().equals("Queen") || potentialPiece.whatPiece().equals("Rook"))
				{
					return true;
				}
				
				else
				{
					break;
				}
			}
			
			catch(NullPointerException n)
			{
				//If no one here, no problem, just continue on to the next tile, until out of bounds
				System.out.println("No one at Chess Piece " + newx + ", " + y);
				i++;
				newx = x - i;
			}
		}
		
		System.out.println("Reset");
		i = 1;
		newy = y + i;
		while(newy < 8)
		{
			try
			{
				//get piece at 0+
				ChessPiece potentialPiece = cb.getTile(x, newy).getPiece();
				//if piece is friendly, dont worry about direction, we are fine
				if(potentialPiece.getColor() == kingPiece.getColor())
				{
					break;
				}
				//if not friendly, and is a piece that can move horizontal, we are in check
				else if(potentialPiece.whatPiece().equals("Queen") || potentialPiece.whatPiece().equals("Rook"))
				{
					return true;
				}
				
				else
				{
					break;
				}
			}
			
			catch(NullPointerException n)
			{
				//If no one here, no problem, just continue on to the next tile, until out of bounds
				System.out.println("No one at Chess Piece " + x + ", " + newy);
				i++;
				newy = y + i;
			}
		}
		
		System.out.println("Reset");
		i = 1;
		newy = y - 1;
		while(newy >= 0)
		{
			try
			{
				//get piece at 0-
				ChessPiece potentialPiece = cb.getTile(x,  newy).getPiece();
				//if piece is friendly, dont worry about direction, we are fine
				if(potentialPiece.getColor() == kingPiece.getColor())
				{
					break;
				}
				//if not friendly, and is a piece that can move horizontal, we are in check
				else if(potentialPiece.whatPiece().equals("Queen") || potentialPiece.whatPiece().equals("Rook"))
				{
					return true;
				}
				
				else
				{
					break;
				}
			}
			
			catch(NullPointerException n)
			{
				//If no one here, no problem, just continue on to the next tile, until out of bounds
				System.out.println("No one at Chess Piece " + x + ", " + newy);
				i++;
				newy = y - i;
			}
		}
		
		//STARTING KNIGHT CHECKERS
		try
		{
			//get piece at 2+1+
			newx = x+=2;
			newy = y+=1;
			//only check if in bounds
			if(newx < 8 && newy < 8)
			{
				ChessPiece potentialPiece = cb.getTile(newx, newy).getPiece();
				//if piece is friendly, dont worry about direction, we are fine
				if(potentialPiece.getColor() == kingPiece.getColor())
				{
					//Nothing happens so its all good
				}
				//if not friendly, and is a piece that move in a way of a knight, we are in check
				else if(potentialPiece.whatPiece().equals("Knight"))
				{
					return true;
				}
			}
		}
			
		catch(NullPointerException n)
		{
			System.out.println("No one at Chess Piece " + newx + ", " + newy);
		}
		
		try
		{
			//get piece at 2+1-
			newx = x+=2;
			newy = y-=1;
			
			if(newx < 8 && newy >= 0)
			{
				ChessPiece potentialPiece = cb.getTile(newx, newy).getPiece();
				//if piece is friendly, dont worry about direction, we are fine
				if(potentialPiece.getColor() == kingPiece.getColor())
				{
					//Nothing happens so its all good
				}
				//if not friendly, and is a piece that move in a way of a knight, we are in check
				else if(potentialPiece.whatPiece().equals("Knight"))
				{
					return true;
				}
			}
		}
			
		catch(NullPointerException n)
		{
			System.out.println("No one at Chess Piece " + newx + ", " + newy);
		}
		
		try
		{
			//get piece at 2-1+
			newx = x-=2;
			newy = y+=1;
			
			if(newx >= 0 && newy < 8)
			{
				ChessPiece potentialPiece = cb.getTile(newx, newy).getPiece();
				//if piece is friendly, dont worry about direction, we are fine
				if(potentialPiece.getColor() == kingPiece.getColor())
				{
					//Nothing happens so its all good
				}
				//if not friendly, and is a piece that move in a way of a knight, we are in check
				else if(potentialPiece.whatPiece().equals("Knight"))
				{
					return true;
				}
			}
		}
			
		catch(NullPointerException n)
		{
			System.out.println("No one at Chess Piece " + newx + ", " + newy);
		}
		
		try
		{
			//get piece at 2-1-
			newx = x-=2;
			newy = y-=1;
			
			if(newx >= 0 && newy >= 0)
			{
				ChessPiece potentialPiece = cb.getTile(newx, newy).getPiece();
				//if piece is friendly, dont worry about direction, we are fine
				if(potentialPiece.getColor() == kingPiece.getColor())
				{
					//Nothing happens so its all good
				}
				//if not friendly, and is a piece that move in a way of a knight, we are in check
				else if(potentialPiece.whatPiece().equals("Knight"))
				{
					return true;
				}
			}
		}
			
		catch(NullPointerException n)
		{
			System.out.println("No one at Chess Piece " + newx + ", " + newy);
		}
		
		try
		{
			//get piece at 1+2+
			newx = x+=1;
			newy = y+=2;
			
			if(newx < 8 && newy < 8)
			{
				ChessPiece potentialPiece = cb.getTile(newx, newy).getPiece();
				//if piece is friendly, dont worry about direction, we are fine
				if(potentialPiece.getColor() == kingPiece.getColor())
				{
					//Nothing happens so its all good
				}
				//if not friendly, and is a piece that move in a way of a knight, we are in check
				else if(potentialPiece.whatPiece().equals("Knight"))
				{
					return true;
				}
			}
		}
			
		catch(NullPointerException n)
		{
			System.out.println("No one at Chess Piece " + newx + ", " + newy);
		}
		
		try
		{
			//get piece at 1+2-
			newx = x+=1;
			newy = y-=2;
			
			if(newx < 8 && newy >= 0)
			{
				ChessPiece potentialPiece = cb.getTile(newx, newy).getPiece();
				//if piece is friendly, dont worry about direction, we are fine
				if(potentialPiece.getColor() == kingPiece.getColor())
				{
					//Nothing happens so its all good
				}
				//if not friendly, and is a piece that move in a way of a knight, we are in check
				else if(potentialPiece.whatPiece().equals("Knight"))
				{
					return true;
				}
			}
		}
			
		catch(NullPointerException n)
		{
			System.out.println("No one at Chess Piece " + newx + ", " + newy);
		}
		
		try
		{
			//get piece at 1-2+
			newx = x-=1;
			newy = y+=2;
			
			if(newx >= 0 && newy < 8)
			{
				ChessPiece potentialPiece = cb.getTile(newx, newy).getPiece();
				//if piece is friendly, dont worry about direction, we are fine
				if(potentialPiece.getColor() == kingPiece.getColor())
				{
					//Nothing happens so its all good
				}
				//if not friendly, and is a piece that move in a way of a knight, we are in check
				else if(potentialPiece.whatPiece().equals("Knight"))
				{
					return true;
				}
			}
		}
			
		catch(NullPointerException n)
		{
			System.out.println("No one at Chess Piece " + newx + ", " + newy);
		}
		
		try
		{
			//get piece at 1-2-
			newx = x-=1;
			newy = y-=2;
			
			if(newx >= 0 && newy >= 0)
			{
				ChessPiece potentialPiece = cb.getTile(newx, newy).getPiece();
				//if piece is friendly, dont worry about direction, we are fine
				if(potentialPiece.getColor() == kingPiece.getColor())
				{
					//Nothing happens so its all good
				}
				//if not friendly, and is a piece that move in a way of a knight, we are in check
				else if(potentialPiece.whatPiece().equals("Knight"))
				{
					return true;
				}
			}
		}
			
		catch(NullPointerException n)
		{
			System.out.println("No one at Chess Piece " + newx + ", " + newy);
		}
		
		//still need to implement pawn checking because that's a pain in the ass
		
		
		//only if all directions (diaganol, four ways and horizontal and vertical ways) do not have a threatening piece 
		//of the opposite color
		//if the king is white
		
		if(kingPiece.getColor() == true)
		{
			System.out.println("Reset");
			//and not on the opposite edge since they start at 1
			if(kingPiece.getPosY() != 0)
			{
				newx = kingPiece.getPosX() + 1;
				newy = kingPiece.getPosY() + 1;
				try
				{
					ChessPiece potentialPiece = cb.getTile(newx,  newy).getPiece();
					if(potentialPiece.getColor() == kingPiece.getColor())
					{
						//no problem
					}
					//if piece is of an opposite color and is a pawn, check
					else if(potentialPiece.whatPiece().equals("Pawn"))
					{
						return true;
					}
				}
				
				catch(NullPointerException n)
				{
					System.out.println("No one at Chess Piece " + newx + ", " + newy);
				}
				
				newy = kingPiece.getPosY() - 1;
				try
				{
					ChessPiece potentialPiece = cb.getTile(newx,  newy).getPiece();
					if(potentialPiece.getColor() == kingPiece.getColor())
					{
						//no problem
					}
					//if piece is of an opposite color and is a pawn, check
					else if(potentialPiece.whatPiece().equals("Pawn"))
					{
						return true;
					}
				}
				
				catch(NullPointerException n)
				{
					System.out.println("No one at Chess Piece " + newx + ", " + newy);
				}
			}
		}
		
		
		if(kingPiece.getColor() == false)
		{
			System.out.println("Reset");
			//and not on the opposite edge since they start at 1
			if(kingPiece.getPosY() != 7)
			{
				newx = kingPiece.getPosX() - 1;
				newy = kingPiece.getPosY() + 1;
				try
				{
					ChessPiece potentialPiece = cb.getTile(newx,  newy).getPiece();
					if(potentialPiece.getColor() == kingPiece.getColor())
					{
						//no problem
					}
					//if piece is of an opposite color and is a pawn, check
					else if(potentialPiece.whatPiece().equals("Pawn"))
					{
						return true;
					}
				}
				
				catch(NullPointerException n)
				{
					System.out.println("No one at Chess Piece " + newx + ", " + newy);
				}
				
				newy = kingPiece.getPosY() - 1;
				try
				{
					ChessPiece potentialPiece = cb.getTile(newx,  newy).getPiece();
					if(potentialPiece.getColor() == kingPiece.getColor())
					{
						//no problem
					}
					//if piece is of an opposite color and is a pawn, check
					else if(potentialPiece.whatPiece().equals("Pawn"))
					{
						return true;
					}
				}
				
				catch(NullPointerException n)
				{
					System.out.println("No one at Chess Piece " + newx + ", " + newy);
				}
			}
		}
		
		return false;
	}
	
	public boolean isCheckmate(ChessBoard cb, KingPiece kingPiece)
	{
		System.out.println("TESTING CHECKMATE:");
		
		//if not in check, can't be in check mate
		if(this.isCheck(cb,  kingPiece) == false)
		{
			System.out.println("Not in check");
			return false;
		}
		
		int oldx = kingPiece.getPosX();
		int oldy = kingPiece.getPosY();
		
		//+1,0
		int newx = kingPiece.getPosX() + 1;
		int newy = kingPiece.getPosY();
		
		//all of this is to not change the original board, but to get information on a potential move
		//we change the original position of the king, and test the updated board
		KingPiece testKing = new KingPiece(newx, newy, kingPiece.getColor());
		ChessBoard testBoard = cb;
		
		Tile kingTile = new Tile(testKing);
		//testBoard.setTile(newx, newy, kingTile);
		//testBoard.setTile(oldx,  oldy);
		
		//if there is a legal move where king is not in check, king is not in checkmate
		if(this.isCheck(testBoard, testKing) == false && kingPiece.checkMove(newx, newy, testBoard) == true)
		{
			return false;
		}
		
		//+1+1
		newx = kingPiece.getPosX() + 1;
		newy = kingPiece.getPosY() + 1;
		
		testKing = new KingPiece(newx, newy, kingPiece.getColor());
		testBoard = cb;
		kingTile = new Tile(testKing);
		testBoard.setTile(newx, newy, kingTile);
		//deletes where old 
		testBoard.setTile(oldx,  oldy);
		
		//if there is a legal move where king is not in check, king is not in checkmate
		if(this.isCheck(testBoard, testKing) == false && kingPiece.checkMove(newx, newy, testBoard) == true)
		{
			System.out.println(newx + ", " + newy + " is a legal move to get them out of check");
			return false;
		}
		
		//+1-1
		newx = kingPiece.getPosX() + 1;
		newy = kingPiece.getPosY() - 1;
				
		testKing = new KingPiece(newx, newy, kingPiece.getColor());
		testBoard = cb;
		kingTile = new Tile(testKing);
		testBoard.setTile(newx, newy, kingTile);
		testBoard.setTile(oldx,  oldy);
				
		//if there is a legal move where king is not in check, king is not in checkmate
		if(this.isCheck(testBoard, testKing) == false && kingPiece.checkMove(newx, newy, testBoard) == true)
		{
			return false;
		}
		
		//0+1
		newx = kingPiece.getPosX();
		newy = kingPiece.getPosY() + 1;
				
		testKing = new KingPiece(newx, newy, kingPiece.getColor());
		testBoard = cb;
		kingTile = new Tile(testKing);
		testBoard.setTile(newx, newy, kingTile);
		testBoard.setTile(oldx,  oldy);
				
		//if there is a legal move where king is not in check, king is not in checkmate
		if(this.isCheck(testBoard, testKing) == false && kingPiece.checkMove(newx, newy, testBoard) == true)
		{
			return false;
		}
		
		//0-1
		newx = kingPiece.getPosX();
		newy = kingPiece.getPosY() - 1;
				
		testKing = new KingPiece(newx, newy, kingPiece.getColor());
		testBoard = cb;
		kingTile = new Tile(testKing);
		testBoard.setTile(newx, newy, kingTile);
		testBoard.setTile(oldx,  oldy);
				
		//if there is a legal move where king is not in check, king is not in checkmate
		if(this.isCheck(testBoard, testKing) == false && kingPiece.checkMove(newx, newy, testBoard) == true)
		{
			return false;
		}
		
		//-1+1
		newx = kingPiece.getPosX() - 1;
		newy = kingPiece.getPosY() + 1;
				
		testKing = new KingPiece(newx, newy, kingPiece.getColor());
		testBoard = cb;
		kingTile = new Tile(testKing);
		testBoard.setTile(newx, newy, kingTile);
		testBoard.setTile(oldx,  oldy);
				
		//if there is a legal move where king is not in check, king is not in checkmate
		if(this.isCheck(testBoard, testKing) == false && kingPiece.checkMove(newx, newy, testBoard) == true)
		{
			return false;
		}
		
		//-1,0
		newx = kingPiece.getPosX() - 1;
		newy = kingPiece.getPosY();
				
		testKing = new KingPiece(newx, newy, kingPiece.getColor());
		testBoard = cb;
		kingTile = new Tile(testKing);
		testBoard.setTile(newx, newy, kingTile);
		testBoard.setTile(oldx,  oldy);
				
		//if there is a legal move where king is not in check, king is not in checkmate
		if(this.isCheck(testBoard, testKing) == false && kingPiece.checkMove(newx, newy, testBoard) == true)
		{
			return false;
		}
		
		//-1-1
		newx = kingPiece.getPosX() + 1;
		newy = kingPiece.getPosY() + 1;
				
		testKing = new KingPiece(newx, newy, kingPiece.getColor());
		testBoard = cb;
		kingTile = new Tile(testKing);
		testBoard.setTile(newx, newy, kingTile);
		testBoard.setTile(oldx,  oldy);
				
		//if there is a legal move where king is not in check, king is not in checkmate
		if(this.isCheck(testBoard, testKing) == false && kingPiece.checkMove(newx, newy, testBoard) == true)
		{
			return false;
		}
		
		//if no possible moves, return true
		return true;
	}
	
	public boolean isDraw()
	{
		return false;
	}
}
