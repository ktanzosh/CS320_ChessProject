package edu.ycp.cs320.ChessProject.UserDatabase;

import java.util.Scanner;

import edu.ycp.cs320.ChessProject.UserDatabase.DatabaseProvider;
import edu.ycp.cs320.ChessProject.UserDatabase.DerbyDatabase;


public class InitDatabase {
	public static void init(Scanner keyboard) {
			DatabaseProvider.setInstance(new DerbyDatabase());
	}
}