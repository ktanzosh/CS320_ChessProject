package edu.ycp.cs320.ChessProject.Chess;

public class QueenPiece extends ChessPiece
{
	public QueenPiece(int x, int y, boolean w, int p)
	{
		this.setPosX(x);
		this.setPosY(y);
		this.setColor(w);
		this.setPieceNumber(p);
	}
	
	public String whatInitial()
	{
		return "Q";
	}
	
	public String whatPiece()
	{
		return "Queen";
	}
	
	public void setHaveMoved(boolean hm)
	{
		//Is not necessary
	}
	
	public boolean checkMove(int newx, int newy, ChessBoard cb)
	{
		//System.out.println("Queen Test from "  + this.getPosX() + ", " + this.getPosY() + " to " + newx + ", " + newy);
		
		if(newx < 0 || newx > 7 || newy < 0 || newy > 7)
		{
			return false;
		}
		
		int changeX = (newx - this.getPosX());
		int changeY = (newy - this.getPosY());
		int oldx = this.getPosX();
		int oldy = this.getPosY();
		
		//check to see if other pieces are in the way
		if(changeY == 0 && changeX != 0)
		{
			//if moving up in x
			if(newx > oldx)
			{
				//cycles through all the possible spots it could go to 
				for(int i = oldx+=1; i < newx; i++)
				{
					//System.out.println("Queen Piece at " + i + " and " + getPosY());
					Tile t = cb.getTile(i, newy);
					
					try
					{
						if(t.getPiece() != null)
						{
							//System.out.println("Chess Piece at " + i + " and " + getPosY());
							return false;
						}
					}
					
					catch(NullPointerException e)
					{
						//System.out.println("No Chess Piece at " + i + " and " + getPosX());
					}
				}
			}
			
			else if(oldx > newx)
			{
				//cycles through all the possible spots it could go to 
				for(int i = newx+=1; i < oldx; i++)
				{
					//System.out.println("Queen Piece at " + i + " and " + getPosY());
					Tile t = cb.getTile(i, newy);
					try
					{
						if(t.getPiece() != null)
						{
							//System.out.println("Chess Piece at " + i + " and " + getPosY());
							return false;
						}
					}
					
					catch(NullPointerException e)
					{
						//System.out.println("No Chess Piece at " + i + " and " + getPosY());
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
							//System.out.println("Chess Piece at " + getPosX() + " and " + i);
							return false;
						}
					}
					
					catch(NullPointerException e)
					{
						//System.out.println("No Chess Piece at " + getPosX() + " and " + i);
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
							//System.out.println("Chess Piece at " + getPosX() + " and " + i);
							return false;
						}
					}
					
					catch(NullPointerException e)
					{
						//System.out.println("No Chess Piece at " + getPosX() + " and " + i);
					}
				}
			}
		}
		
		if(changeX > 0 && changeY > 0 && Math.abs(changeX) == Math.abs(changeY))
		{
			for(int i = oldx+=1; i < newx; i++)
			{
				//System.out.println("Bishop Piece at " + i + " and " + i);
				int j = oldy + i - oldx + 1; //had += 1 earlier so need to + 1 since we subtract the extra + 1
				Tile t = cb.getTile(i, j);
				try
				{
					if(t.getPiece() != null)
					{
						//System.out.println("Chess Piece at " + i + " and " + j);
						return false;
					}
				}
				
				catch(NullPointerException e)
				{
					//System.out.println("No Chess Piece at " + i + " and " + j);
				}
			}
		}
		
		else if(changeX < 0 && changeY < 0 && Math.abs(changeX) == Math.abs(changeY))
		{
			for(int i = newx+=1; i < oldx; i++)
			{
				//System.out.println("Bishop Piece at " + i + " and " + i);
				int j = oldy + i - oldx;
				Tile t = cb.getTile(i, j);
				try
				{
					if(t.getPiece() != null)
					{
						//System.out.println("Chess Piece at " + i + " and " + j);
						return false;
					}
				}
				
				catch(NullPointerException e)
				{
					//System.out.println("No Chess Piece at " + i + " and " + j);
				}
			}
			newx--;
		}
		
		else if(changeX > 0 && changeY < 0 && Math.abs(changeX) == Math.abs(changeY))
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
						//System.out.println("Chess Piece at " + testX + " and " + testY);
						return false;
					}
				}
				
				catch(NullPointerException e)
				{
					//System.out.println("No Chess Piece at " + testX + " and " + testY);
				}
				
				testX++;
				testY--;
			}
		}
		
		else if(changeX < 0 && changeY > 0 && Math.abs(changeX) == Math.abs(changeY))
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
						//System.out.println("Chess Piece at " + testX + " and " + testY);
						return false;
					}
				}
				
				catch(NullPointerException e)
				{
					//System.out.println("No Chess Piece at " + testX + " and " + testY);
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
				//System.out.println("");
				return false;
			}
		}
		catch(NullPointerException n)
		{
			//System.out.println("No one at " + newx + ", " + newy);
		}
		
		changeX = Math.abs(changeX);
		changeY = Math.abs(changeY);
		
		//makes sure that the movements are vertical or horizontal, and actually moving not just up and down
		//System.out.println("");
		if(((changeX * changeY == 0) || (changeX == changeY)) && changeX + changeY !=0)
		{
			return true;
		}
		
		else
		{
			return false;
		}
	}
}
