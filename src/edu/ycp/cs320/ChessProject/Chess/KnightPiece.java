package edu.ycp.cs320.ChessProject.Chess;

public class KnightPiece extends ChessPiece
{
	public KnightPiece(int x, int y, boolean w, int p)
	{
		this.setPosX(x);
		this.setPosY(y);
		this.setColor(w);
		this.setPieceNumber(p);
	}
	
	public String whatInitial()
	{
		return "N";
	}
	
	public String whatPiece()
	{
		return "Knight";
	}
	
	public void setHaveMoved(boolean hm)
	{
		//Is not necessary
	}
	
	public boolean checkMove(int newx, int newy, ChessBoard cb)
	{
		System.out.println("Knight Test from "  + this.getPosX() + ", " + this.getPosY() + " to " + newx + ", " + newy);
		
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
				return false;
			}
		}
		catch(NullPointerException n)
		{
			System.out.println("No one at " + newx + ", " + newy);
		}
		
		System.out.println("");
		//makes sure they move in a 2 and 1 pattern
		if(changeX * changeY == 2)
		{
			return true;
		}
		
		else
		{
			return false;
		}
	}
}
