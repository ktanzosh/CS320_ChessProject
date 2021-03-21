package edu.ycp.cs320.ChessProject.model;

public class PasswordReset 
{
	private String newPassword;
	private String checkPassword;
	private boolean info;
	
	public PasswordReset()
	{
		
	}
	
	public void setNewPassword(String np)
	{
		newPassword = np;
	}
	
	public String getNewPassword()
	{
		return this.newPassword;
	}
	
	public void setCheckPassword(String cp)
	{
		checkPassword = cp;
	}
	
	public String getCheckPassword()
	{
		return this.checkPassword;
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
