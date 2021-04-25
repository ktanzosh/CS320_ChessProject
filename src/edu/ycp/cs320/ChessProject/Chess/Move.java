package edu.ycp.cs320.ChessProject.Chess;

public class Move 
{
	private ChessPiece movedPiece;
	private int xpos;
	private String ypos;
	private String state;
	private boolean tookPiece;
	private boolean wasCastle;
	
	public Move(ChessPiece cp, int x, int y, String st, boolean tp, boolean wc)
	{
		this.movedPiece = cp;
		this.xpos = (8 - x);
		
		if(y == 0)
		{
			this.ypos = "a";
		}
		
		else if(y == 1)
		{
			this.ypos = "b";
		}
		
		else if(y == 2)
		{
			this.ypos = "c";
		}
		
		else if(y == 3)
		{
			this.ypos = "d";
		}
		
		else if(y == 4)
		{
			this.ypos = "e";
		}
		
		else if(y == 5)
		{
			this.ypos = "f";
		}
		
		else if(y == 6)
		{
			this.ypos = "g";
		}
		
		else if(y == 7)
		{
			this.ypos = "h";
		}
		
		if(st.equals("Checkmate"))
		{
			this.state = "#";
		}
		
		else if(st.equals("Check")) 
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
<<<<<<< HEAD
	{		
		if(wasCastle == true && ypos.equals("h"))
=======
	{
<<<<<<< Updated upstream
		if(wasCastle == true && ypos.equals("B"))
=======
		//System.out.println("printMove() function");
		if(wasCastle == true && ypos.equals("g"))
>>>>>>> 961e960f90cf798ef34cccb4247e2e707f9f2445
>>>>>>> Stashed changes
		{
			System.out.println("0-0");
		}
		
<<<<<<< Updated upstream
		if(wasCastle == true && ypos.equals("G"))
=======
		else if(wasCastle == true && ypos.equals("a"))
>>>>>>> Stashed changes
		{
			System.out.println("0-0-0");
		}
		
		else if(tookPiece == false)
		{
			System.out.println(movedPiece.whatInitial() + "" + state + "" + ypos + "" + xpos);
		}
		
		else if(tookPiece == true)
		{
			System.out.println(movedPiece.whatInitial() + "x" + state + "" + ypos + "" + xpos);
		}
		
	}
	
	public String getMove()
	{
<<<<<<< Updated upstream
		if(wasCastle == true && ypos.equals("B"))
=======
<<<<<<< HEAD
		if(wasCastle == true && ypos.equals("h"))
=======
		//System.out.println("getMove() function");
		if(wasCastle == true && ypos.equals("g"))
>>>>>>> 961e960f90cf798ef34cccb4247e2e707f9f2445
>>>>>>> Stashed changes
		{
			return "0-0";
		}
		
<<<<<<< Updated upstream
		if(wasCastle == true && ypos.equals("G"))
=======
		else if(wasCastle == true && ypos.equals("a"))
>>>>>>> Stashed changes
		{
			return "0-0-0";
		}
		
		else if(tookPiece == false)
		{
			return movedPiece.whatInitial() + "" + state + "" + ypos + "" + xpos;
		}
		
		else if(tookPiece == true)
		{
			return movedPiece.whatInitial() + "x" + state + "" + ypos + "" + xpos;
		}
		
		else
		{
			return null;
		}
	}
}
