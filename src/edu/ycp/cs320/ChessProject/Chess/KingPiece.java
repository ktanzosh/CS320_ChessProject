package edu.ycp.cs320.ChessProject.Chess;

public class KingPiece extends ChessPiece
{
	private boolean haveMoved;
	
	public KingPiece(int x, int y, boolean w)
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
		return "K";
	}
	
	public String whatPiece()
	{
		return "King";
	}
	
	public boolean checkMove(int newx, int newy, ChessBoard cb)
	{
		System.out.println("King Test from "  + this.getPosX() + ", " + this.getPosY() + " to " + newx + ", " + newy);
		
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
}
