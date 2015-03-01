package src;
import java.util.HashSet;
public class BoardState{
	int[][] pieces;
	Boolean forcedMoves;
	int turn; 
	int[] capturePiece;
	HashSet<Move> moves;
	public BoardState(Board p){
		moves = new HashSet<Move>();
		pieces = p.getGrid();
		this.turn = p.getTurn();
		capturePiece = p.capturePiece();
		forcedMoves = false;
		if(capturePiece[0] < 0){
			totalMoves();
		}else{
			pieceMoves(capturePiece[0],capturePiece[1]);
		}
	}
	public boolean checkValid(Move m){
		for (Move move : moves){
			if (move.equals(m)){
				return true;
			}
		}
		return false;
	}
	public HashSet<Move> moves(){
		return moves;
	}
	public boolean validMoves(){
		return !moves.isEmpty();
	}
	public void totalMoves(){
		for(int i = 0; i < pieces.length; i += 1){
			for(int j = 0; j < pieces[i].length; j += 1){
				if(pieces[i][j] == turn){
					pieceMoves(i,j);
				}
			}
		}
		if(forcedMoves){
			HashSet<Move> m = new HashSet<Move>(moves);
			for(Move move : m){
				if(!move.isCapture()){
					moves.remove(move);
				}
			}
		}
	}
	public void pieceMoves(int x, int y){
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
		if(!outOfBounds(x-2*turn, y+2*turn) && pieces[x-turn][y+turn] == -1*turn && pieces[x-2*turn][y+2*turn] == 0){
			moves.add(new Move(x, y, x-2*turn, y+2*turn));
			forcedMoves = true;
		}
		if(Math.abs(pieces[x][y]) == 2 && !outOfBounds(x+turn, y-turn) && pieces[x+turn][y-turn] == 0){
			moves.add(new Move(x, y, x+turn, y-turn));
		}
		if(Math.abs(pieces[x][y]) == 2 && !outOfBounds(x-turn, y-turn) && pieces[x-turn][y-turn] == 0){
			moves.add(new Move(x, y, x-turn, y-turn));
		}
		if(Math.abs(pieces[x][y]) == 2 && !outOfBounds(x+2*turn, y-2*turn) && pieces[x+turn][y-turn] == -1*turn && pieces[x+2*turn][y-2*turn] == 0){
			moves.add(new Move(x, y, x+2*turn, y-2*turn));
			forcedMoves = true;
		}
		if(Math.abs(pieces[x][y]) == 2 && !outOfBounds(x-2*turn, y-2*turn) && pieces[x-turn][y-turn] == -1*turn && pieces[x-2*turn][y-2*turn] == 0){
			moves.add(new Move(x, y, x-2*turn, y-2*turn));
			forcedMoves = true;
		}
	}
	private boolean outOfBounds(int x, int y){
		return (x > 7 || x < 0 || y > 7 || y < 0);
	}
}