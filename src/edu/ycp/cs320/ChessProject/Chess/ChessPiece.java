package edu.ycp.cs320.ChessProject.Chess;

public abstract class ChessPiece 
{
	private int posx;
	private int posy;
	private boolean white;
	private boolean killed;
	private boolean haveMoved;
	
	public void setPosX(int px)
	{
		posx = px;
	}
	
	public int getPosX()
	{
		return this.posx;
	}
	
	public void setPosY(int py)
	{
		posy = py;
	}
	
	public int getPosY()
	{
		return this.posy;
	}
	
	public void setColor(boolean w)
	{
		white = w;
	}
	
	public boolean getColor()
	{
		return this.white;
	}
	
	public void isKilled()
	{
		killed = true;
	}
	
	public boolean getKilled()
	{
		return this.killed;
	}
	
	public void setHaveMoved(boolean hm)
	{
		haveMoved = hm;
	}
	
	public boolean getHaveMoved()
	{
		return this.haveMoved;
	}
	
	public abstract String whatPiece();
	
	public abstract String whatInitial();
	
	public abstract boolean checkMove(int newx, int newy, ChessBoard cb);
}
