package edu.ycp.cs320.ChessProject.servlet;

import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import edu.ycp.cs320.ChessProject.Chess.ChessPiece;
import edu.ycp.cs320.ChessProject.Chess.Game;
import edu.ycp.cs320.ChessProject.Chess.Move;
import edu.ycp.cs320.ChessProject.UserDatabase.DatabaseProvider;
import edu.ycp.cs320.ChessProject.UserDatabase.IDatabase;

import edu.ycp.cs320.ChessProject.UserDatabase.User;

public class PingServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException 
	{
		HttpSession userSession = req.getSession(false);
		Game playGame = (Game) userSession.getAttribute(("sessionGame"));
		String playerColor = (String) userSession.getAttribute("color");
		
		IDatabase db = DatabaseProvider.getInstance();
		int id = playGame.getGameID();
		
		try
		{
			User user = db.getSecondPlayerInfo(id);
			
			if(playGame.getFinish())
			{
				resp.getWriter().write("Game is over, no need");
				//resp.sendRedirect("/ChessProject/index");
				return;
			}
			
			ArrayList<String> moves = (ArrayList<String>) userSession.getAttribute("moves");
			ArrayList<String> testMoves = db.getMoveList(id);
			
			String response = "";
				
			if(testMoves.size() > moves.size()) 
			{
				ArrayList<Integer> pieceID = db.getMoveListbyPieceID(id);
				int lastPiece = pieceID.get(pieceID.size()-1);
				ArrayList<String> moveList = db.getMoveList(id);
				String lastMoveInfo = moveList.get(testMoves.size() - 1);
				System.out.println("PieceID: " + lastPiece + " ,moves: " + moves);
					
				int ogSquare = 0;
				int finalSquare = 0;
					
				if(playerColor.equals("w"))
				{
					ogSquare = playGame.getSquareNumber(playGame.getLastMoveOrigXPos(playGame.getWhitePieces(), lastPiece), playGame.getLastMoveOrigYPos(playGame.getWhitePieces(), lastPiece));
					finalSquare = playGame.getSquareNumber(playGame.getLastMoveFinalXPos(lastMoveInfo), playGame.getLastMoveFinalYPos(lastMoveInfo));
					playGame.doLastMove(lastMoveInfo, playGame.getWhitePieces(), lastPiece);
					userSession.setAttribute("sessionGame", playGame);
					moves = (ArrayList<String>) userSession.getAttribute("moves");
					userSession.setAttribute("moves", moves);
				}
					
				else
				{
					ogSquare = playGame.getSquareNumber(playGame.getLastMoveOrigXPos(playGame.getBlackPieces(), lastPiece), playGame.getLastMoveOrigYPos(playGame.getBlackPieces(), lastPiece));
					finalSquare = playGame.getSquareNumber(playGame.getLastMoveFinalXPos(lastMoveInfo), playGame.getLastMoveFinalYPos(lastMoveInfo));
					playGame.doLastMove(lastMoveInfo, playGame.getBlackPieces(), lastPiece);
					userSession.setAttribute("sessionGame", playGame);
					moves = (ArrayList<String>) userSession.getAttribute("moves");
					userSession.setAttribute("moves", moves);
				}
				
				System.out.println(lastMoveInfo);
				System.out.println(playGame.getLastMoveOrigXPos(playGame.getBlackPieces(), lastPiece) + "/" + playGame.getLastMoveOrigYPos(playGame.getBlackPieces(), lastPiece));
				System.out.println(playGame.getLastMoveFinalXPos(lastMoveInfo) + "/" + playGame.getLastMoveFinalYPos(lastMoveInfo));	
				System.out.println(String.valueOf(ogSquare) + "/" +  String.valueOf(finalSquare));
				System.out.println(moves);
				response = String.valueOf(ogSquare) + "/" +  String.valueOf(finalSquare);
				//1 to 17 -? 1/17
			}
			
			else
			{
				response = "No moves to be updated";
			}
			
			resp.getWriter().write(response);
			
			if(req.getParameter("index") != null) 
			{
				resp.sendRedirect("/ChessProject/index");
				return;		
			}
		}
		
		catch(NullPointerException ne)
		{
			resp.getWriter().write("No second player");
			//resp.sendRedirect("/ChessProject/index");
			return;
		}
	}
}
