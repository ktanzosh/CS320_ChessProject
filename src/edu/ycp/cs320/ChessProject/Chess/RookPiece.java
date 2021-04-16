package edu.ycp.cs320.ChessProject.Chess;

public class RookPiece extends ChessPiece
{
	
	public RookPiece(int x, int y, boolean w, int p)
	{
		this.setPosX(x);
		this.setPosY(y);
		this.setColor(w);
		this.setHaveMoved(false);
		this.setPieceNumber(p);
	}
	
	public String whatInitial()
	{
		return "R";
	}
	
	public String whatPiece()
	{
		return "Rook";
	}
	
	public boolean checkMove(int newx, int newy)
	{
		if(newx < 0 || newx > 7 || newy < 0 || newy > 7)
		{
			return false;
		}
		
		int changeX = Math.abs(newx - this.getPosX());
		int changeY = Math.abs(newy - this.getPosY());
		
		//makes sure that the movements are vertical or horizontal, and actually moving not just up and down
		if(changeX * changeY == 0 && changeX + changeY != 0)
		{
			return true;
		}
				
		else
		{
			return false;
		}
	}
	
	public boolean checkMove(int newx, int newy, ChessBoard cb)
	{
		System.out.println("Rook Test from "  + this.getPosX() + ", " + this.getPosY() + " to " + newx + ", " + newy);
		
		if(newx < 0 || newx > 7 || newy < 0 || newy > 7)
		{
			return false;
		}
		
		int oldx = this.getPosX();
		int oldy = this.getPosY();
		int changeX = Math.abs(newx - oldx);
		int changeY = Math.abs(newy - oldy);
		
		//check to see if other pieces are in the way
		//if horizontal movement
		if(changeY == 0 && changeX != 0)
		{
			//if moving up in x
			if(newx > oldx)
			{
				//cycles through all the possible spots it could go to 
				for(int i = oldx+=1; i < newx; i++)
				{
					//System.out.println("Rook Piece at " + i + " and " + getPosY());
					Tile t = cb.getTile(i, newy);
					
					try
					{
						if(t.getPiece() != null)
						{
							System.out.println("Chess Piece at " + i + " and " + getPosY());
							return false;
						}
					}
					
					catch(NullPointerException e)
					{
						System.out.println("No Chess Piece at " + i + " and " + getPosX());
					}
				}
			}
			
			else if(oldx > newx)
			{
				//cycles through all the possible spots it could go to 
				for(int i = newx+=1; i < oldx; i++)
				{
					//System.out.println("Rook Piece at " + i + " and " + getPosY());
					Tile t = cb.getTile(i, newy);
					try
					{
						if(t.getPiece() != null)
						{
							System.out.println("Chess Piece at " + i + " and " + getPosY());
							return false;
						}
					}
					
					catch(NullPointerException e)
					{
						System.out.println("No Chess Piece at " + i + " and " + getPosY());
					}
				}
			}
		}
		
		if(changeX == 0 && changeY != 0)
		{
			//if moving up in x
			if(newy > oldy)
			{
				//cycles through all the possible spots it could go to 
				for(int i = oldy+=1; i < newy; i++)
				{
					//System.out.println("Rook Piece at " + getPosX() + " and " + i);
					Tile t = cb.getTile(getPosX(), i);
					try
					{
						if(t.getPiece() != null)
						{
							System.out.println("Chess Piece at " + getPosX() + " and " + i);
							return false;
						}
					}
					
					catch(NullPointerException e)
					{
						System.out.println("No Chess Piece at " + getPosX() + " and " + i);
					}
				}
			}
			
			else if(oldy > newy)
			{
				//cycles through all the possible spots it could go to 
				for(int i = newy+=1; i < oldy; i++)
				{
					//System.out.println("Rook Piece at " + getPosX() + " and " + i);
					Tile t = cb.getTile(getPosX(), i);
					try
					{
						if(t.getPiece() != null)
						{
							System.out.println("Chess Piece at " + getPosX() + " and " + i);
							return false;
						}
					}
					
					catch(NullPointerException e)
					{
						System.out.println("No Chess Piece at " + getPosX() + " and " + i);
					}
				}
			}
		}
		
		System.out.println("");
		
		//check to see if they take a piece
		try
		{
			if(cb.getTile(newx, newy).getPiece().getColor() == this.getColor())
			{
				return false;
			}
		}
		catch(NullPointerException n)
		{
			System.out.println("No one at " + newx + ", " + newy);
		}
		
		//makes sure that the movements are vertical or horizontal, and actually moving not just up and down
		if(changeX * changeY == 0 && changeX + changeY != 0)
		{
			return true;
		}
		
		else
		{
			return false;
		}
	}
}
