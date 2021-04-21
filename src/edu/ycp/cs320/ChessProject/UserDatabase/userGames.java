package edu.ycp.cs320.ChessProject.UserDatabase;

public class userGames {
	
	private int game_id, player1_id, player2_id;
	
	public userGames() {
		
	}
	
	public void setGame_ID(int game_ID) {
		this.game_id = game_ID;
	}
	
	public void setPlayer1_ID(int player1_ID) {
		this.player1_id = player1_ID;
	}
	
	public void setPlayer2_ID(int player2_ID) {
		this.player2_id = player2_ID;
	}
	
	public int getGame_ID() {
		return game_id;
	}
	
	public int getPlayer1_ID() {
		return player1_id;
	}
	
	public int getPlayer2_ID() {
		return player2_id;
	}
	
}
