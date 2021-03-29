package edu.ycp.cs320.ChessProject.model;

public class Security 
{
	private String securityAnswer;
	public boolean info;
	
	public Security()
	{
		
	}
	
	public void setSecurityAnswer(String sa)
	{
		securityAnswer = sa;
	}
	
	public String getSecurityAnswer()
	{
		return this.securityAnswer;
	}
	
	public void setInfo(boolean i)
	{
		info = i;
	}
	
	public boolean getInfo()
	{
		return info;
	}
}
