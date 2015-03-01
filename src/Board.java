package src;
import java.util.*;
public class Board{
	private int[][] Grid = new int[8][8]; //the Board > 0 black < 0 red
	int[] selected;
	int turn;
	public Board(){
		for (int i = 0; i< 8; i++){
			for (int j = 0; j< 8; j++)
				if(j>=5 && (j%2 == i%2) {
					Grid[i][j] = -1;
				}
				else if (j <= 3 && (j%2==i%2)){
					Grid[i][j] = 1;
				}
				else{
					Grid[i][j] = 0
				}
		}
	}
	public void select(int x, int y, int turn){
		if (turn*Grid[x][y] > 0){
			selected = new int[] {x,y};
		}
		else{
			Move m = new Move(selected[0], selected[1], x, y);
			BS = new BoardState(this);
			if (BS.checkValid(m)){
				m.change(Board);
				if (!m.isCapture()){
					endTurn();
				}
				else if (!BS.validMoves()){
					endTurn();
				}
			}
		}
	}
	public 
	public void endTurn(){
		turn = turn*-1;
		selected = new int[] {-1, -1};
		moves = new Moves(this)
	}
	public int[][] getGrid(){
		return Grid;
	}
	public void setGrid(int[][] g){
		this.Grid = g;
	}
	public int getTurn(){
		return turn;
	}
	public void drawBoard(){
        for (int i= 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if ((selected[0]==i) && (selected[1]==j)){
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                }
                else if ((i + j) % 2 == 0){
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }
                else{
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);   
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if (p == -2){
                	StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                }
                else if (p == -1){
                	StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                }
                else if (p == 1){
                	StdDrawPlus.filledSquare(i + .5, j + .5, .5);	
                }
                else if (p == 2){
                	StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                }
            }
        }
	}
	public int winner(){
		(!moves.validMoves()){
			return turn * -1;
		}
	}
	public static void main(String args[]){
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        int x;
        int y;
        Board board = new Board();
        while(winner() == 0){
            board.drawBoard();
            if (StdDrawPlus.mousePressed()){
                x = (int) StdDrawPlus.mouseX();
                y = (int) StdDrawPlus.mouseY();
                board.select(x,y);
            }
            StdDrawPlus.show(1);
        }
        System.out.println(board.winner());
    }
}