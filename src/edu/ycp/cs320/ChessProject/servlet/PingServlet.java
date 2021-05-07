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
import edu.ycp.cs320.ChessProject.Chess.TestMain;
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
		TestMain tm = new TestMain();
		
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
			
			//System.out.println("Session MoveList: " + moves);
			//System.out.println("Database MoveList: " + testMoves);
			
			String response = "";
				
			if(testMoves.size() > moves.size()) 
			{
				ArrayList<Integer> pieceID = db.getMoveListbyPieceID(id);
				int lastPiece = pieceID.get(pieceID.size()-1);
				ArrayList<String> moveList = db.getMoveList(id);
				String lastMoveInfo = moveList.get(testMoves.size() - 1);
				//System.out.println("PieceID: " + lastPiece + " ,moves: " + moves);
					
				int ogSquare = 0;
				int finalSquare = 0;
				String pieceName = "";
				String state = "";

				//if you are black you are pulling a white move
				if(playerColor.equals("black"))
				{
					ogSquare = playGame.getSquareNumber(playGame.getLastMoveOrigXPos(playGame.getWhitePieces(), lastPiece), playGame.getLastMoveOrigYPos(playGame.getWhitePieces(), lastPiece));
					finalSquare = playGame.getSquareNumber(playGame.getLastMoveFinalXPos(lastMoveInfo), playGame.getLastMoveFinalYPos(lastMoveInfo));
					//pieceName = playGame.getInfoFromMove(null)
					//System.out.println("Premove");
					//tm.drawBoard(playGame);
					playGame.doLastMove(lastMoveInfo, playGame.getWhitePieces(), lastPiece);
					//System.out.println("Postmove");
					//tm.drawBoard(playGame);
					pieceName = playGame.getLastMovePieceString(lastMoveInfo);
					userSession.setAttribute("sessionGame", playGame);
					moves.add(System.lineSeparator() + " White's Move: " + lastMoveInfo);
					userSession.setAttribute("moves", moves);
					state = playGame.getResult(playGame.getBlackPlayer(), playGame.getChessBoard(), playGame.getBlackKing(), playGame.getBlackPieces());
				}
					
				//if you are white you are pulling a black move
				else if(playerColor.equals("white"))
				{
					ogSquare = playGame.getSquareNumber(playGame.getLastMoveOrigXPos(playGame.getBlackPieces(), lastPiece), playGame.getLastMoveOrigYPos(playGame.getBlackPieces(), lastPiece));
					finalSquare = playGame.getSquareNumber(playGame.getLastMoveFinalXPos(lastMoveInfo), playGame.getLastMoveFinalYPos(lastMoveInfo));
					//System.out.println("Premove");
					//tm.drawBoard(playGame);
					playGame.doLastMove(lastMoveInfo, playGame.getBlackPieces(), lastPiece);
					//System.out.println("Postmove");
					//tm.drawBoard(playGame);
					pieceName = playGame.getLastMovePieceString(lastMoveInfo);
					userSession.setAttribute("sessionGame", playGame);
					moves.add(System.lineSeparator() + " White's Move: " + lastMoveInfo);
					userSession.setAttribute("moves", moves);
					state = playGame.getResult(playGame.getWhitePlayer(), playGame.getChessBoard(), playGame.getWhiteKing(), playGame.getWhitePieces());
				}
				
				System.out.println(lastMoveInfo);
				System.out.println(playGame.getLastMoveOrigXPos(playGame.getBlackPieces(), lastPiece) + "/" + playGame.getLastMoveOrigYPos(playGame.getBlackPieces(), lastPiece));
				System.out.println(playGame.getLastMoveFinalXPos(lastMoveInfo) + "/" + playGame.getLastMoveFinalYPos(lastMoveInfo));	
				System.out.println(String.valueOf(ogSquare) + "/" +  String.valueOf(finalSquare));
				//System.out.println(moves);
				response = String.valueOf(ogSquare) + "/" +  String.valueOf(finalSquare) + "/" + pieceName + "/" + state;
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
