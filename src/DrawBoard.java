package src;
import src.*;
import lib.stdlib.*;
import java.util.*;
public class DrawBoard extends Board{
	public void drawBoard(){
		int p;
        for (int i= 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
            	p = Grid[i][j];
                if ((selected[0]==i) && (selected[1]==j)){
                    StdDraw.setPenColor(StdDraw.WHITE);
                }
                else if ((i + j) % 2 == 0){
                    StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
                }
                else{
                    StdDraw.setPenColor(StdDraw.DARK_GRAY);  
                }
                StdDraw.filledSquare(i + .5, j + .5, 0.5);
                if (p == -2){
                	StdDraw.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                }
                else if (p == -1){
                	StdDraw.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                }
                else if (p == 1){
                	StdDraw.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);	
                }
                else if (p == 2){
                	StdDraw.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                }
            }
        }
	}
	public void endGame(){
		System.out.println(winner());
		drawBoard();
	}

}