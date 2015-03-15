package src;

/** Class that we should extend for our AI? */
public abstract class Ai {
	int player;  // is 1 red and -1 blue?
	Board board;
	public Ai (Board b, int player) {
		this.board = b;
		this.player = player;
	}

	abstract public void makeMove();
}