package src;
import src.*;
import java.util.*;
public class Board{
	public int[][] Grid = new int[8][8]; //the Board > 0 black < 0 red
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
	public Board(boolean empty){
		for (int i = 0; i< 8; i++)
			for (int j = 0; j< 8; j++)
				Grid[i][j] = 0;	
		turn = 1;
		lastCapture = 0;
		capturePiece = new int[] {-1,-1};
		selected = new int[] {-1,-1};
		b = new BoardState(this);
	}
	public void addPiece(int[] location, int type){
		Grid[location[0]][location[1]] = type; 
	}
	public Board(Board board){
		for (int i = 0; i< 8; i++){
			for (int j = 0; j< 8; j++){
				Grid[i][j] = board.Grid[i][j];
			}
		}
		turn = board.turn;
		lastCapture = board.lastCapture;
		capturePiece = new int[] {board.capturePiece[0], board.capturePiece[1]};
		selected = new int[] {board.selected[0], board.selected[1]};
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
	public void endGame(){
		System.out.println(winner());
	}
	public boolean equals(Board other){
		for(int i = 0; i<8; i++){
			for(int j=0; j<8;j++){
				if(Grid[i][j] != other.Grid[i][j]){
					return false;
				}
			}
		}
		if (turn != other.getTurn()){
			return false;
		}
		return true;
	}
	public void setBoard(Board b){
		if (b == null){
			endGame();
		}
		else{
			for (int i = 0; i< 8; i++){
				for (int j = 0; j< 8; j++){
					Grid[i][j] = b.Grid[i][j];
				}
			}
			turn = b.turn;
			lastCapture = b.lastCapture;
			capturePiece = b.capturePiece;
			selected = b.selected;
			this.b = new BoardState(this);
		}
	}
	public void setLastCapture(int x){
		lastCapture = x;
	}
	public void addLastCapture(int x){
		lastCapture += x;
	}
    public void printBoard(){
        int val;
        for (int i = 0; i< 8; i++){
            for (int j = 0; j< 8; j++){
                val = Grid[i][j];
                if (val < 0){
                    System.out.print(" | B" + Math.abs(val));
                }
                else if (val > 0){
                    System.out.print(" | R" + val);
                }
                else{
                    System.out.print(" |   ");
                }
            }
            System.out.println();
        }
    }

}