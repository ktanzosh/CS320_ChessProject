package edu.ycp.cs320.ChessProject.controller;
import edu.ycp.cs320.ChessProject.model.AccountRecovery;

public class AccountRecoveryController 
{
	private AccountRecovery model;
	private String account = "Account123";
	
	public void setModel(AccountRecovery m)
	{
		model = m;
	}
	
	public boolean isRightUsername(String a)
	{
		return a.equals(account);
	}
}
