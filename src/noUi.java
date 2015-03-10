package src;
import src.*;
import java.util.*;
public class noUi{
	public static void main(String[] args){
        int x;
        int y;
        Board board = new Board();
        FindBestMove CompMove2 = new FindBestMove(board, -1,0);
        FindBestMove CompMove1 = new FindBestMove(board, 1,1);
        while(board.winner() == 0){
        	if (board.getTurn() == -1){
        		board.setBoard(CompMove2.findBest(10));
        	}
            else{
                board.setBoard(CompMove1.findBest(10));
            }
        }
        System.out.println(board.winner());
    }
}