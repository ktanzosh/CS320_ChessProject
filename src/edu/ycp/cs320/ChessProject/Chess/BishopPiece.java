package edu.ycp.cs320.ChessProject.Chess;

public class BishopPiece extends ChessPiece
{
	public BishopPiece(int x, int y, boolean w)
	{
		this.setPosX(x);
		this.setPosY(y);
		this.setColor(w);
	}
	
	public String whatPiece()
	{
		return "Bishop";
	}
	
	public void setHaveMoved(boolean hm)
	{
		//Is not necessary
	}
	
	public boolean checkMove(int newx, int newy, ChessBoard cb)
	{
		System.out.println("Bishop Test from "  + this.getPosX() + ", " + this.getPosY() + " to " + newx + ", " + newy);
		
		if(newx < 0 || newx > 7 || newy < 0 || newy > 7)
		{
			return false;
		}
		
		int oldx = this.getPosX();
		int oldy = this.getPosY();
		int changeX = newx - oldx;
		int changeY = newy - oldy;
		
		//check to see if other pieces are in the way
		if(changeX > 0 && changeY > 0)
		{
			for(int i = oldx+=1; i < newx; i++)
			{
				//System.out.println("Bishop Piece at " + i + " and " + i);
				Tile t = cb.getTile(i, i);
				try
				{
					if(t.getPiece() != null)
					{
						System.out.println("Chess Piece at " + i + " and " + i);
						return false;
					}
				}
				
				catch(NullPointerException e)
				{
					System.out.println("No Chess Piece at " + i + " and " + i);
				}
			}
		}
		
		else if(changeX < 0 && changeY < 0)
		{
			for(int i = newx+=1; i < oldx; i++)
			{
				//System.out.println("Bishop Piece at " + i + " and " + i);
				Tile t = cb.getTile(i, i);
				try
				{
					if(t.getPiece() != null)
					{
						System.out.println("Chess Piece at " + i + " and " + i);
						return false;
					}
				}
				
				catch(NullPointerException e)
				{
					System.out.println("No Chess Piece at " + i + " and " + i);
				}
			}
		}
		
		else if(changeX > 0 && changeY < 0)
		{
			int testX = oldx + 1;
			int testY = oldy - 1;
			while(testX < newx || testY > newy)
			{
				//System.out.println("Bishop Piece at " + testX + " and " + testY);
				Tile t = cb.getTile(testX, testY);
				
				try
				{
					if(t.getPiece() != null)
					{
						System.out.println("Chess Piece at " + testX + " and " + testY);
						return false;
					}
				}
				
				catch(NullPointerException e)
				{
					System.out.println("No Chess Piece at " + testX + " and " + testY);
				}
				
				testX++;
				testY--;
			}
		}
		
		else if(changeX < 0 && changeY > 0)
		{
			int testX = oldx - 1;
			int testY = oldy + 1;
			while(testX > newx || testY < newy)
			{
				//System.out.println("Bishop Piece at " + testX + " and " + testY);
				Tile t = cb.getTile(testX, testY);
				
				try
				{
					if(t.getPiece() != null)
					{
						System.out.println("Chess Piece at " + testX + " and " + testY);
						return false;
					}
				}
				
				catch(NullPointerException e)
				{
					System.out.println("No Chess Piece at " + testX + " and " + testY);
				}
				
				testX--;
				testY++;
			}
		}
		
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
		
		System.out.println("");
		
		changeX = Math.abs(changeX);
		changeY = Math.abs(changeY);
		//makes sure it's diaganol and not a fake move and putting it right back down
		if(changeX == changeY && changeX + changeY != 0)
		{
			return true;
		}
		
		else
		{
			return false;
		}
	}
}
