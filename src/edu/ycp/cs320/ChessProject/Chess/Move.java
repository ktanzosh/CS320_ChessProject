package edu.ycp.cs320.ChessProject.Chess;

public class Move 
{
	private ChessPiece movedPiece;
	private String xpos;
	private int ypos;
	
	public Move(ChessPiece cp, int x, int y)
	{
		this.movedPiece = cp;
		this.ypos = (8 - y);
		
		if(x == 0)
		{
			this.xpos = "a";
		}
		
		else if(x == 1)
		{
			this.xpos = "b";
		}
		
		else if(x == 2)
		{
			this.xpos = "c";
		}
		
		else if(x == 3)
		{
			this.xpos = "d";
		}
		
		else if(x == 4)
		{
			this.xpos = "e";
		}
		
		else if(x == 5)
		{
			this.xpos = "f";
		}
		
		else if(x == 6)
		{
			this.xpos = "g";
		}
		
		else if(x == 7)
		{
			this.xpos = "h";
		}
		
	}
	
	public void printMove()
	{
		System.out.println(movedPiece.whatInitial() + "" + xpos + "" + ypos);
	}
	
	public String getMove()
	{
		return movedPiece.whatInitial() + "" + xpos + "" + ypos;
	}
}
