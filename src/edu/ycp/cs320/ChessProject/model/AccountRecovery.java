package edu.ycp.cs320.ChessProject.model;

public class AccountRecovery 
{
	private String username;
	private boolean info;
	
	public AccountRecovery()
	{
		
	}
	
	public void setUsername(String u)
	{
		username = u;
	}
	
	public String getUsername()
	{
		return this.username;
	}
	
	public void setInfo(boolean i)
	{
		info = i;
	}

	public boolean getInfo()
	{
		return this.info;
	}
	
}
