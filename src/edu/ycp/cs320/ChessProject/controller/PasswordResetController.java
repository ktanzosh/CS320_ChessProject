package edu.ycp.cs320.ChessProject.controller;

import edu.ycp.cs320.ChessProject.model.PasswordReset;

public class PasswordResetController 
{
	private PasswordReset model;
	
	public void setModel(PasswordReset m)
	{
		model = m;
	}
	
	public boolean equalPassword(String np, String cp)
	{
		return np.equals(cp);
	}
}
