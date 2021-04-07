package edu.ycp.cs320.ChessProject.Chess;

import java.util.ArrayList;

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
	
	public boolean isCheckmate(ChessBoard cb, KingPiece kingPiece, ArrayList<ChessPiece> pieces)
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
		Game testGame = new Game();
		testGame.setChessBoard(cb);
		
		//+1,0
		int newx = kingPiece.getPosX() + 1;
		int newy = kingPiece.getPosY();
		
		//if legal move, check to see if it's out of check, if not not important
		if(kingPiece.checkMove(newx, newy, cb) == true)
		{
			testGame.doMove(cb,  kingPiece, newx, newy);
			if(this.isCheck(testGame.getChessBoard(), kingPiece))
			{
				//if still in check, go back to oldx, oldy and continue the program
				testGame.doMove(cb, kingPiece, oldx, oldy);
			}
			
			else 
			{
				//if not in check, return the board just in case, then 
				testGame.doMove(cb, kingPiece, oldx, oldy);
				return false;
			}
		}
		
		//+1, +1
		newx = kingPiece.getPosX() + 1;
		newy = kingPiece.getPosY() + 1;
		
		//if legal move, check to see if it's out of check, if not not important
		if(kingPiece.checkMove(newx, newy, cb) == true)
		{
			testGame.doMove(cb,  kingPiece, newx, newy);
			if(this.isCheck(testGame.getChessBoard(), kingPiece))
			{
				//if still in check, go back to oldx, oldy and continue the program
				testGame.doMove(cb, kingPiece, oldx, oldy);
			}
			
			else 
			{
				//if not in check, return the board just in case, then 
				testGame.doMove(cb, kingPiece, oldx, oldy);
				return false;
			}
		}
		
		//+1, -1
		newx = kingPiece.getPosX() + 1;
		newy = kingPiece.getPosY() - 1;
				
		//if legal move, check to see if it's out of check, if not not important
		if(kingPiece.checkMove(newx, newy, cb) == true)
		{
			testGame.doMove(cb,  kingPiece, newx, newy);
			if(this.isCheck(testGame.getChessBoard(), kingPiece))
			{
				//if still in check, go back to oldx, oldy and continue the program
				testGame.doMove(cb, kingPiece, oldx, oldy);
			}
					
			else 
			{
				//if not in check, return the board just in case, then 
				testGame.doMove(cb, kingPiece, oldx, oldy);
				return false;
			}
		}
		
		//+1, -1
		newx = kingPiece.getPosX() + 1;
		newy = kingPiece.getPosY() - 1;
				
		//if legal move, check to see if it's out of check, if not not important
		if(kingPiece.checkMove(newx, newy, cb) == true)
		{
			testGame.doMove(cb,  kingPiece, newx, newy);
			if(this.isCheck(testGame.getChessBoard(), kingPiece))
			{
				//if still in check, go back to oldx, oldy and continue the program
				testGame.doMove(cb, kingPiece, oldx, oldy);
			}
					
			else 
			{
				//if not in check, return the board just in case, then 
				testGame.doMove(cb, kingPiece, oldx, oldy);
				return false;
			}
		}
		
		//0, +1
		newx = kingPiece.getPosX();
		newy = kingPiece.getPosY() + 1;
				
		//if legal move, check to see if it's out of check, if not not important
		if(kingPiece.checkMove(newx, newy, cb) == true)
		{
			testGame.doMove(cb,  kingPiece, newx, newy);
			if(this.isCheck(testGame.getChessBoard(), kingPiece))
			{
				//if still in check, go back to oldx, oldy and continue the program
				testGame.doMove(cb, kingPiece, oldx, oldy);
			}
					
			else 
			{
				//if not in check, return the board just in case, then 
				testGame.doMove(cb, kingPiece, oldx, oldy);
				return false;
			}
		}
		
		//0, -1
		newx = kingPiece.getPosX();
		newy = kingPiece.getPosY() - 1;
				
		//if legal move, check to see if it's out of check, if not not important
		if(kingPiece.checkMove(newx, newy, cb) == true)
		{
			testGame.doMove(cb,  kingPiece, newx, newy);
			if(this.isCheck(testGame.getChessBoard(), kingPiece))
			{
				//if still in check, go back to oldx, oldy and continue the program
				testGame.doMove(cb, kingPiece, oldx, oldy);
			}
					
			else 
			{
				//if not in check, return the board just in case, then 
				testGame.doMove(cb, kingPiece, oldx, oldy);
				return false;
			}
		}
		
		//-1, 0
		newx = kingPiece.getPosX() - 1;
		newy = kingPiece.getPosY();
				
		//if legal move, check to see if it's out of check, if not not important
		if(kingPiece.checkMove(newx, newy, cb) == true)
		{
			testGame.doMove(cb,  kingPiece, newx, newy);
			if(this.isCheck(testGame.getChessBoard(), kingPiece))
			{
				//if still in check, go back to oldx, oldy and continue the program
				testGame.doMove(cb, kingPiece, oldx, oldy);
			}
			
			else 
			{
				//if not in check, return the board just in case, then 
				testGame.doMove(cb, kingPiece, oldx, oldy);
				return false;
			}
		}
		
		//-1, +1
		newx = kingPiece.getPosX() - 1;
		newy = kingPiece.getPosY() + 1;
				
		//if legal move, check to see if it's out of check, if not not important
		if(kingPiece.checkMove(newx, newy, cb) == true)
		{
			testGame.doMove(cb,  kingPiece, newx, newy);
			if(this.isCheck(testGame.getChessBoard(), kingPiece))
			{
				//if still in check, go back to oldx, oldy and continue the program
				testGame.doMove(cb, kingPiece, oldx, oldy);
			}
					
			else 
			{
				//if not in check, return the board just in case, then 
				testGame.doMove(cb, kingPiece, oldx, oldy);
				return false;
			}
		}
		
		//-1, -1
		newx = kingPiece.getPosX() - 1;
		newy = kingPiece.getPosY() - 1;
				
		//if legal move, check to see if it's out of check, if not not important
		if(kingPiece.checkMove(newx, newy, cb) == true)
		{
			testGame.doMove(cb,  kingPiece, newx, newy);
			if(this.isCheck(testGame.getChessBoard(), kingPiece))
			{
				//if still in check, go back to oldx, oldy and continue the program
				testGame.doMove(cb, kingPiece, oldx, oldy);
			}
					
			else 
			{
				//if not in check, return the board just in case, then 
				testGame.doMove(cb, kingPiece, oldx, oldy);
				return false;
			}
		}
		
		//for all friendly pieces
		for(ChessPiece piece : pieces)
		{
			//as long as the piece is not killed
			if(piece.getKilled() == false)
			{
				for(int i = 0; i < 8; i++)
				{
					for(int j = 0; j < 8; j++)
					{
						//if there is a legal move that has the king not in check
						if(piece.checkMove(i, j, cb) == true)
						{
							//do move, then check to see if it puts them in check
							testGame.doMove(testGame.getChessBoard(),  piece,  i,  j);
							if(this.isCheck(testGame.getChessBoard(), kingPiece) == false)
							{
								//return move to og spot
								testGame.doMove(testGame.getChessBoard(), piece,  piece.getPosX(),  piece.getPosY());
								return false;
							}
							
							else
							{
								testGame.doMove(testGame.getChessBoard(), piece,  piece.getPosX(),  piece.getPosY());
							}
						}
					}
				}
			}
		}
		
		//if no possible moves, return true
		return true;
	}
	
	public boolean isDraw(ChessBoard cb, KingPiece kingPiece, ArrayList<ChessPiece> pieces)
	{
		//If in check, can't be a draw
		if(this.isCheck(cb,  kingPiece) == false)
		{
			System.out.println("Not in check");
			return true;
		}
		
		Game testGame = new Game();
		testGame.setChessBoard(cb);
		//for all friendly pieces
		for(ChessPiece piece : pieces)
		{
			//as long as the piece is not killed
			if(piece.getKilled() == false)
			{
				for(int i = 0; i < 8; i++)
				{
					for(int j = 0; j < 8; j++)
					{
						//if there is a legal move that has the king not in check
						if(piece.checkMove(i, j, cb) == true)
						{
							//do move, then check to see if it puts them in check
							testGame.doMove(testGame.getChessBoard(),  piece,  i,  j);
							if(this.isCheck(testGame.getChessBoard(), kingPiece) == false)
							{
								//return move to og spot
								testGame.doMove(testGame.getChessBoard(), piece,  piece.getPosX(),  piece.getPosY());
								return false;
							}
									
							else
							{
								testGame.doMove(testGame.getChessBoard(), piece,  piece.getPosX(),  piece.getPosY());
							}
						}
					}
				}
			}
		}
		
		return true;
	}
}
