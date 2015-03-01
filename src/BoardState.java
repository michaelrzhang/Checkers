package src;
import src.*;
import java.util.HashSet;
public class BoardState{
	int[][] pieces;
	Boolean forcedMoves;
	int turn; 
	HashSet<Move> moves;
	HashSet<Move> computerMoves;
	boolean change = false;
	public BoardState(Board p){
		moves = new HashSet<Move>();
		computerMoves = new HashSet<Move>();
		pieces = p.getGrid();
		this.turn = p.getTurn();
		forcedMoves = false;
		totalMoves();
		for(Move move: moves){
			computerMoves.add(move);
		}
		boolean multi = true;
		while(multi){
			multi = computerMoves();
			change = false;
		}
	}
	public boolean checkValid(Move m){
		return true;
	}
	public int sum(){
		int total = 0;
		for(int[] row : pieces){
			for(int piece : row){
				total += piece;
			}
		}
		return total;
	}
	public HashSet<Move> moves(){
		return moves;
	}
	public HashSet<Move> compMoves(){
		return computerMoves;
	}
	public boolean validMoves(){
		return !moves.isEmpty();
	}
	public void totalMoves(){
		for(int i = 0; i < pieces.length; i += 1){
			for(int j = 0; j < pieces[i].length; j += 1){
				if(pieces[i][j] == turn){
					pieceMoves(i,j, turn);
					if(pieces[i][j]==turn*2){
						pieceMoves(i,j,-1*turn);
					}
				}
			}
		}
		if(forcedMoves){
			for(Move move : moves){
				if(!move.isCapture()){
					moves.remove(move);
				}
			}
		}
	}
	public boolean computerMoves(){
		for(Move move: computerMoves){
			multiCapture(move, turn);
			if(Math.abs(pieces[move.xi()][move.yi()]) == 2){
				multiCapture(move, -1* turn);
			}
		}
		return change;
	}
	public void multiCapture(Move move, int turn){
		int x = move.xf();
		int y = move.yf();
		if(!outOfBounds(x+2*turn, y+2*turn) && pieces[x+turn][y+turn] == -1*turn && pieces[x+2*turn][y+2*turn] == 0){
			computerMoves.add(new Move(x, y, x+2*turn, y+2*turn));
			change = true;
			if(computerMoves.contains(move))
				computerMoves.remove(move);
		}
		if(!outOfBounds(x-2*turn, y+2*turn) && pieces[x+turn][y+turn] == -1*turn && pieces[x-2*turn][y+2*turn] == 0){
			moves.add(new Move(x, y, x-2*turn, y+2*turn));
			change = true;
			if(computerMoves.contains(move))
				computerMoves.remove(move);
		}
	}
	public void pieceMoves(int x, int y, int turn){
		if(!outOfBounds(x+turn, y+turn) && pieces[x+turn][y+turn] == 0){
			moves.add(new Move(x, y, x+turn, y+turn));
		}
		if(!outOfBounds(x-turn, y+turn) && pieces[x-turn][y+turn] == 0){
			moves.add(new Move(x, y, x-turn, y+turn));
		}
		if(!outOfBounds(x+2*turn, y+2*turn) && pieces[x+turn][y+turn] == -1*turn && pieces[x+2*turn][y+2*turn] == 0){
			moves.add(new Move(x, y, x+2*turn, y+2*turn));
			forcedMoves = true;
		}
		if(!outOfBounds(x-2*turn, y+2*turn) && pieces[x+turn][y+turn] == -1*turn && pieces[x-2*turn][y+2*turn] == 0){
			moves.add(new Move(x, y, x-2*turn, y+2*turn));
			forcedMoves = true;
		}
	}

	private boolean outOfBounds(int x, int y){
		return (x > 7 || x < 0 || y > 7 || y < 0);
	}
}