package edu.ycp.cs320.ChessProject.controller;
import edu.ycp.cs320.ChessProject.model.Security;

public class SecurityController 
{
	private Security model;
	private String recover = "Recover123";
	
	public void setModel(Security m)
	{
		model = m;
	}
	
	public boolean isRightAnswer(String sa)
	{
		return sa.equals(recover);
	}
}
