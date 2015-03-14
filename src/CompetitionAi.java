package src;
import lib.stdlib.*;
import java.util.*;
public class CompetitionAi{
	public static void main(String[] args){
		StdDraw.setXscale(0, 8);
        StdDraw.setYscale(0, 8);
        DrawBoard board = new DrawBoard();
        Ai player1 = new Ai(board, 1);
        Ai player2 = new Ai(board, -1);
        board.drawBoard();
        while(board.winner() == 0){
        	if (board.getTurn() == -1){
        		player1.makeMove();
        	}
            else{
                player2.makeMove();
            }
            board.drawBoard();
            StdDraw.show(5);
        }
        board.winnerString();
    }
}