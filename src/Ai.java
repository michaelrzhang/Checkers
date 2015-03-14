package src;
public abstract class Ai {
	int player;
	Board board;
	public Ai (Board b, int player) {
		this.board = b;
		this.player = player;
	}

	abstract public void makeMove();
}