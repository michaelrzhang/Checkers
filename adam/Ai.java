package src;
public class Ai {
	int player;
	Board board;
	public Ai (Board b, int player) {
		this.board = b;
		this.player = p;
	}

	abstract public makeMove();
}