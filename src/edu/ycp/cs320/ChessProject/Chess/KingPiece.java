package edu.ycp.cs320.ChessProject.Chess;

public class KingPiece extends ChessPiece
{
	
	public KingPiece(int x, int y, boolean w, int p)
	{
		this.setPosX(x);
		this.setPosY(y);
		this.setColor(w);
		this.setHaveMoved(false);
		this.setPieceNumber(p);
	}
	
	public String whatInitial()
	{
		return "K";
	}
	
	public String whatPiece()
	{
		return "King";
	}
	
	public boolean checkMove(int newx, int newy, ChessBoard cb)
	{
		System.out.println("King Test from "  + this.getPosX() + ", " + this.getPosY() + " to " + newx + ", " + newy);
		
		//if(this.canCastle(newx, newy, cb) == true)
		//{
			//Have to implement but basically if they can castle, and do some repeat logic then checkMove returns true
		//}
		
		if(newx < 0 || newx > 7 || newy < 0 || newy > 7)
		{
			return false;
		}
		
		int changeX = Math.abs(newx - this.getPosX());
		int changeY = Math.abs(newy - this.getPosY());
		
		//check to see if they take a piece
		try
		{
			if(cb.getTile(newx, newy).getPiece().getColor() == this.getColor())
			{
				System.out.println("Someone here at " + newx + ", " + newy + " with a matching color");
				return false;
			}
		}
		catch(NullPointerException n)
		{
			System.out.println("No one at " + newx + ", " + newy);
		}
		
		System.out.println("");
		//makes sure that the movements are vertical or horizontal, and actually moving not just up and down
		if((changeX * changeY == 1 || changeX + changeY == 1) && changeX + changeY != 0)
		{
			return true;
		}
		
		else
		{
			return false;
		}
	}
	
	public boolean canCastle(int newx, int newy, ChessBoard cb)
	{
		System.out.println("");
		System.out.println("Testing Castling");
		if(this.getHaveMoved() == true)
		{
			return false;
		}
		
		try
		{
			//goes to 7,7 or 0,7
			if(newy == 6)
			{
				newy++;
			}
			
			else if(newy == 1)
			{
				newy--;
			}
			
			ChessPiece castleRook = cb.getTile(newx, newy).getPiece();
			if(castleRook.whatPiece().equals("Rook") == false)
			{
				System.out.println("Not a rook");
				return false;
			}
			
			else if(castleRook.getHaveMoved() == true)
			{
				System.out.println("Rook has moved");
				return false;
			}
			
			else if(castleRook.getColor() != this.getColor())
			{
				System.out.println("Not a friendly piece");
				return false;
			}
			
			//checkIn between the pieces
			if(newy > this.getPosY())
			{
				for(int i = this.getPosY(); i < newy; i++)
				{
					try
					{
						ChessPiece blockerPiece = cb.getTile(newx, i).getPiece();
						//if there is a piece in the way, return false since theres something in the way
						return false;
					}
					
					catch(NullPointerException ne)
					{
						//We still all good
						System.out.println("No piece blocking castling");
					}
				}
			}
			
			else
			{
				for(int i = newy; i < this.getPosY(); i++)
				{
					try
					{
						ChessPiece blockerPiece = cb.getTile(newx, i).getPiece();
						//if there is a piece in the way, return false since theres something in the way
						return false;
					}
					
					catch(NullPointerException ne)
					{
						//We still all good
						System.out.println("No piece blocking castling");
					}
				}
			}
			
			//if white
			if(this.getColor() == true)
			{
				//if King is not in proper location, dont do it
				if(this.getPosX() != 7 && this.getPosY() != 4)
				{
					return false;
				}
				
				//if it is a rook, in the friendly corner
				//this is just triple checking
				if(newx == 7 && newy == 7)
				{
					return true;
				}
				
				else if(newx == 7 && newy == 0)
				{
					return true;
				}
				
				else
				{
					System.out.println("Piece is not in the corner");
					return false;
				}
			}
			
			//if black
			if(this.getColor() == false)
			{
				//if King is not in proper location, dont do it
				if(this.getPosX() != 0 && this.getPosY() != 4)
				{
					return false;
				}
				
				//if it is a rook, in the friendly corner
				//this is just triple checking
				if(newx == 0 && newy == 7)
				{
					return true;
				}
				
				else if(newx == 0 && newy == 0)
				{
					return true;
				}
				
				else
				{
					return false;
				}
			}
		}
		
		catch(NullPointerException n)
		{
			//if no piece there, cant castle
			System.out.println("No one there");
			return false;
		}
		
		return false;
	}
}
