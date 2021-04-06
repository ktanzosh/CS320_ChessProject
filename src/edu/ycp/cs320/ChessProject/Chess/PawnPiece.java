package edu.ycp.cs320.ChessProject.Chess;

public class PawnPiece extends ChessPiece
{
	//THIS WILL BE UPDATED TO FIX A FEW BUGS ABOUT 2D ARRAYS JUST SO YOU KNOW
	
	private boolean haveMoved;
	
	public PawnPiece(int x, int y, boolean w)
	{
		this.setPosX(x);
		this.setPosY(y);
		this.setColor(w);
		haveMoved = false;
	}
	
	public void setHaveMoved(boolean hm)
	{
		haveMoved = hm;
	}
	
	public boolean getHaveMoved()
	{
		return this.haveMoved;
	}
	
	public String whatInitial()
	{
		return "P";
	}
	
	public String whatPiece()
	{
		return "Pawn";
	}
	
	public boolean checkMove(int newx, int newy, ChessBoard cb)
	{
		System.out.println("Pawn Test from "  + this.getPosX() + ", " + this.getPosY() + " to " + newx + ", " + newy);
		
		if(newx < 0 || newx > 7 || newy < 0 || newy > 7)
		{
			return false;
		}
		
		//not absolute value, since 
		int changeX = Math.abs(newx - this.getPosX());
		int changeY = newy - this.getPosY();
		
		//check to see if other pieces are in the way
		//vertical movement
		if(changeX == 0)
		{
			try
			{
				//can't take pieces when forward
				if(cb.getTile(newx, newy).getPiece() != null && changeX == 0)
				{
					System.out.println("");
					return false;
				}
				
				//If it moves forward 2 checks the one right in front of it too
				if(Math.abs(changeY) == 2)
				{
					if(cb.getTile(newx, newy-=1).getPiece() != null && changeX == 0)
					{
						System.out.println("");
						return false;
					}
				}
				
				if(cb.getTile(newx, newy).getPiece().getColor() == this.getColor())
				{
					System.out.println("");
					return false;
				}
			}
			catch(NullPointerException n)
			{
				System.out.println("No one at " + newx + ", " + newy);
			}
			
			//check to see if they take a piece
			
			//makes sure that the movements are one forward in the right direction
			//if they haven't moved can move two forward
			if(haveMoved == false)
			{
				if(this.getColor() == true)//color is white
				{
					if((changeY == -1 || changeY == -2) && changeX == 0)
					{
						return true;
					}
					
					else
					{
						return false;
					}
				}
				
				if(this.getColor() == false)//color is black
				{
					if((changeY == 1 || changeY == 2) && changeX == 0)
					{
						return true;
					}
					
					else
					{
						return false;
					}
				}
			}
			
			else if(haveMoved == true)
			{
				if(this.getColor() == true)//color is white
				{
					if(changeY == -1 && changeX == 0)
					{
						return true;
					}
					
					else
					{
						return false;
					}
				}
				
				else if(this.getColor() == false) //color is black
				{
					if(changeY == 1 && changeX == 0)
					{
						return true;
					}
					
					else
					{
						return false;
					}
				}
			}
		}
		
		//taking a piece
		else if(changeX == 1)
		{
			if(Math.abs(changeY) != 1)
			{
				return false;
			}
			
			//not moving forward
			if(this.getColor() == true && changeY != -1)
			{
				return false;
			}
			
			//not moving forward
			if(this.getColor() == false && changeY != 1)
			{
				return false;
			}
			
			try
			{				
				if(cb.getTile(newx, newy).getPiece().getColor() == this.getColor())
				{
					System.out.println("");
					return false;
				}
			}
			catch(NullPointerException n)
			{
				System.out.println("No one at " + newx + ", " + newy);
				return false;
			}
			
			//only if you move 1 forward in the right direction and the color' dont match, then you can move diagnolly
			return true;
		}
		
		return false;
	}
}
