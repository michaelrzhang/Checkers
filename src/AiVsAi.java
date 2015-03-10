package src;
import src.*;
import lib.stdlib.*;
import java.util.*;
public class AiVsAi{
	public static void main(String[] args){
		StdDraw.setXscale(0, 8);
        StdDraw.setYscale(0, 8);
        int x;
        int y;
        DrawBoard board = new DrawBoard();
        FindBestMove CompMove2 = new FindBestMove(board, -1,0);
        FindBestMove CompMove1 = new FindBestMove(board, 1,1);
        board.drawBoard();
        while(board.winner() == 0){
        	if (board.getTurn() == -1){
        		board.setBoard(CompMove2.findBest(8));
        	}
            else{
                board.setBoard(CompMove1.findBest(8));
            }
            board.drawBoard();
            StdDraw.show(20);
        }
        System.out.println(board.winner());
    }
}