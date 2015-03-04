package src;
import src.*;
import lib.stdlib.*;
import java.util.*;
public class VsAi{
	public static void main(String[] args){
		StdDraw.setXscale(0, 8);
        StdDraw.setYscale(0, 8);
        int x;
        int y;
        Board board = new Board();
        FindBestMove CompMove2 = new FindBestMove(board, -1, 0);
        while(board.winner() == 0){
        	if (board.getTurn() == -1){
        		board.setBoard(CompMove2.findBest(6));
        	}
            else if (StdDraw.mousePressed()){
                x = (int) StdDraw.mouseX();
                y = (int) StdDraw.mouseY();
                board.select(x,y);
            }
            board.drawBoard();
            StdDraw.show(5);
        }
        System.out.println(board.winner());
    }
}