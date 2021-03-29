package edu.ycp.cs320.ChessProject.Chess;

public class Player 
{
	private boolean color;
	
	public Player(boolean c)
	{
		this.color = c;
	}
	
	public boolean getColor()
	{
		return this.color;
	}
	
	public boolean isCheck()
	{
		return false;
	}
	
	public boolean isCheckmate()
	{
		return false;
	}
	
	public boolean isDraw()
	{
		return false;
	}
}
