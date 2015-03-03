package src;
import java.util.ArrayList;
import java.util.HashSet;
public class ComputerMoves{
	ArrayList<Board> possibleBoards;
	Board current;
	public ComputerMoves(Board b){
		current = b;
		possibleBoards = new ArrayList<Board>();
		BoardState bs = new BoardState(current);
		HashSet<Move> moves = bs.moves();
		for (Move move : moves){
			if(move.isCapture()){
				multiCapture(move, current);
			}
			else{
				Board temp = move.changeBoard(current);
				temp.endTurn();
				possibleBoards.add(temp);
			}
		}
	}
	public void multiCapture(Move m, Board b){
		b = m.changeBoard(b);
		BoardState bs = new BoardState(b);
		if(bs.validMoves()){
			HashSet<Move> moves = bs.moves();
			for (Move move: moves){
				multiCapture(move, b);
			}
		}else {
			b.endTurn();
			possibleBoards.add(b);
		}
	}
	public ArrayList<Board> possibleBoards(){
		if (possibleBoards.size() == 0){
			current.endGame(); // still need to figure out
		}
		return possibleBoards;
	}
}









/*
for(Move move: moves){
			computerMoves.add(move);
		}
		boolean multi = true;
		while(multi){
			multi = computerMoves();
			change = false;
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
	}*/
