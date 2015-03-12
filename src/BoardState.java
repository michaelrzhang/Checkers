package src;
import java.util.HashSet;
public class BoardState{
	int[][] pieces; // keeps track of pieces in board
	boolean forcedMoves; // True iff forced move
	int turn; // 1 is Red, up, -1 is Blue, down
	int[] capturePiece; // length two array
	HashSet<Move> moves;
	public BoardState(Board p){
		moves = new HashSet<Move>();
		pieces = p.getGrid();
		this.turn = p.getTurn();
		capturePiece = p.capturePiece();
		forcedMoves = false;
		// updates the hashSet under here
		if(capturePiece[0] < 0){
			totalMoves(); // set of possible moves for all pieces
		}else{
			pieceMoves(capturePiece[0],capturePiece[1]);
			// set of moves for one specific piece
		}
	}
	/** Checks if move is in set of possible */
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
				if(pieces[i][j] * turn > 0){ // checks if piece is your piece
					pieceMoves(i,j);
				}
			}
		}
		if(forcedMoves){ // removes all moves that are not forced moves
			// HashSet<Move> m = new HashSet<Move>(moves);
			for(Move move : new HashSet<Move>(moves)){
				if(!move.isCapture()){
					moves.remove(move);
				}
			}
		}
	}
	public void pieceMoves(int x, int y){
	/** Checks if an individual move can move */
		if(capturePiece[0] < 0 && !outOfBounds(x+turn, y+turn) && pieces[x+turn][y+turn] == 0){
			moves.add(new Move(x, y, x+turn, y+turn));
		}
		if(capturePiece[0] < 0 && !outOfBounds(x-turn, y+turn) && pieces[x-turn][y+turn] == 0){
			moves.add(new Move(x, y, x-turn, y+turn));
		}
		if(!outOfBounds(x+2*turn, y+2*turn) && pieces[x+turn][y+turn] * turn < 0 && pieces[x+2*turn][y+2*turn] == 0){
			moves.add(new Move(x, y, x+2*turn, y+2*turn));
			forcedMoves = true;
		}
		if(!outOfBounds(x-2*turn, y+2*turn) && pieces[x-turn][y+turn] * turn <0 && pieces[x-2*turn][y+2*turn] == 0){
			moves.add(new Move(x, y, x-2*turn, y+2*turn));
			forcedMoves = true;
		}
		/** Equivalents for kings */
		if (isKing(pieces[x][y])) {
			if(capturePiece[0] < 0 && !outOfBounds(x+turn, y-turn) && pieces[x+turn][y-turn] == 0){
				moves.add(new Move(x, y, x+turn, y-turn));
			}
			if(capturePiece[0] < 0  && !outOfBounds(x-turn, y-turn) && pieces[x-turn][y-turn] == 0){
				moves.add(new Move(x, y, x-turn, y-turn));
			}
			if(!outOfBounds(x+2*turn, y-2*turn) && pieces[x+turn][y-turn] * turn < 0 && pieces[x+2*turn][y-2*turn] == 0){
				moves.add(new Move(x, y, x+2*turn, y-2*turn));
				forcedMoves = true;
			}
			if(!outOfBounds(x-2*turn, y-2*turn) && pieces[x-turn][y-turn] * turn < 0 && pieces[x-2*turn][y-2*turn] == 0){
				moves.add(new Move(x, y, x-2*turn, y-2*turn));
				forcedMoves = true;
			}
		}
	}

	private boolean isKing(int x) {
		return Math.abs(x) == 2;
	}

	private boolean outOfBounds(int x, int y){
		return (x > 7 || x < 0 || y > 7 || y < 0);
	}
}