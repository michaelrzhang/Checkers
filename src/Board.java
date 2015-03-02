package src;
import src.*;
import lib.stdlib.*;
import java.util.*;
public class Board{
	private int[][] Grid = new int[8][8]; //the Board > 0 black < 0 red
	int[] selected;
	int turn;
	BoardState b;
	int lastCapture; // number of moves since last Capture
	int[] capturePiece;
	public Board(){
		for (int i = 0; i< 8; i++){
			for (int j = 0; j< 8; j++)
				if(j>=5 && (j%2 == i%2)) {
					Grid[i][j] = -1;;
				}
				else if (j <= 2 && (j%2==i%2)){
					Grid[i][j] = 1;
				}
				else{
					Grid[i][j] = 0;
				}
		}
		turn = 1;
		lastCapture = 0;
		capturePiece = new int[] {-1,-1};
		selected = new int[] {-1,-1};
		b = new BoardState(this);	
	}
	public Board(Board board){
		for (int i = 0; i< 8; i++){
			for (int j = 0; j< 8; j++)
				if(j>=5 && (j%2 == i%2)) {
					Grid[i][j] = board.Grid[i][j];
				}
				else if (j <= 2 && (j%2==i%2)){
					Grid[i][j] = board.Grid[i][j];
				}
				else{
					Grid[i][j] = board.Grid[i][j];
				}
		}
		turn = board.turn;
		lastCapture = board.lastCapture;
		capturePiece = board.capturePiece;
		selected = board.selected;
		b = new BoardState(this);
	}

	public void select(int x, int y){
		if (turn*Grid[x][y] > 0){
			selected = new int[] {x,y};
		}
		else if (selected[0] >= 0){
			Move m = new Move(selected[0], selected[1], x, y);
			b = new BoardState(this);
			if (b.checkValid(m)){
				m.change(this);
				System.out.println(Grid[m.xf()][m.yf()]);
				b = new BoardState(this);
				if (m.isCapture()){
					selected = capturePiece;
					if (!b.validMoves()){
						endTurn();
					}
				}
				else{
					endTurn();
				}
			}
		}
	}
	public void endTurn(){
		turn = turn*-1;
		selected = new int[] {-1, -1};
		capturePiece = new int[] {-1, -1};
		b = new BoardState(this);
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
		int p;
        for (int i= 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
            	p = Grid[i][j];
                if ((selected[0]==i) && (selected[1]==j)){
                    StdDraw.setPenColor(StdDraw.WHITE);
                }
                else if ((i + j) % 2 == 0){
                    StdDraw.setPenColor(StdDraw.GRAY);
                }
                else{
                    StdDraw.setPenColor(StdDraw.RED);   
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
	public int[] capturePiece(){
		return capturePiece;
	}
	public void setCapturePiece(int x, int y){
		capturePiece[0] = x;
		capturePiece[1] = y;
	}
	public int winner(){
		if (!b.validMoves()){
			return turn * -1;
		}
		else if (lastCapture > 40){
			return 3;
		}
		return 0;
	}
	public static void main(String args[]){
		StdDraw.setXscale(0, 8);
        StdDraw.setYscale(0, 8);
        int x;
        int y;
        Board board = new Board();
        while(board.winner() == 0){
            board.drawBoard();
            if (StdDraw.mousePressed()){
                x = (int) StdDraw.mouseX();
                y = (int) StdDraw.mouseY();
                board.select(x,y);
            }
            StdDraw.show(1);
        }
        System.out.println(board.winner());
    }
}