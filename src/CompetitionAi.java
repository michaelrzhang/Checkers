package src;
import adam.*;
import lib.stdlib.*;
import java.util.*;
public class CompetitionAi{
	public static void main(String[] args){
		StdDraw.setXscale(0, 8);
        StdDraw.setYscale(0, 8);
        DrawBoard board = new DrawBoard();
        GameTree gt = new GameTree(board);
        teachableAi player1 = new teachableAi(board, gt, 1, 0);
        teachableAi player2 = new teachableAi(board, gt, -1, 0);
        board.drawBoard();
        while(board.winner() == 0){
        	if (board.getTurn() == -1){
        		player2.makeMove();
        	}
            else if (board.getTurn() == 1) {
                player1.makeMove();
            }
            board.drawBoard();
            StdDraw.show(5);
        }
        board.winnerString();
    }
}