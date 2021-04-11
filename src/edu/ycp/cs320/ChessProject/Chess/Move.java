package edu.ycp.cs320.ChessProject.Chess;

public class Move 
{
	private ChessPiece movedPiece;
	private String xpos;
	private int ypos;
	private String state;
	private boolean tookPiece;
	private boolean wasCastle;
	
	public Move(ChessPiece cp, int x, int y, String st, boolean tp, boolean wc)
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
		
		if(st == "Checkmate")
		{
			this.state = "#";
		}
		
		else if(st == "Check") 
		{
			this.state = "+";
		}
		
		else
		{
			this.state = "";
		}
		
		tookPiece = tp;
		wasCastle = wc;
		
	}
	
	public void printMove()
	{
		if(wasCastle == true)
		{
			System.out.println("0-0");
		}
		
		if(tookPiece == false)
		{
			System.out.println(movedPiece.whatInitial() + "" + state + "" + xpos + "" + ypos);
		}
		
		else if(tookPiece == true)
		{
			System.out.println(movedPiece.whatInitial() + " x " + state + "" + xpos + "" + ypos);
		}
		
	}
	
	public String getMove()
	{
		if(wasCastle == true)
		{
			return "0-0";
		}
		
		if(tookPiece == false)
		{
			return movedPiece.whatInitial() + "" + state + "" + xpos + "" + ypos;
		}
		
		else if(tookPiece == true)
		{
			return movedPiece.whatInitial() + " x " + state + "" + xpos + "" + ypos;
		}
		
		else
		{
			return null;
		}
	}
}
