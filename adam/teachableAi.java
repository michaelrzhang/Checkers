package adam;
import src.*;
public class teachableAi {
	int player;
	Board main;
	int strat;
	GameTree gametree;
	adamBestMove movefinder;
	public teachableAi (Board main, GameTree gametree, int player, int strat) {
		this.main = main;
		this.gametree = gametree;
		this.player = player;
		this.strat = strat;
		movefinder = new adamBestMove(main, gametree, player, strat);
	}

	public teachableAi () {}

	public void setAi (Board main, GameTree gametree, int player, int strat) {
		this.main = main;
		this.gametree = gametree;
		this.player = player;
		this.strat = strat;
	}
	public void makeMove(GameTree gametree, int player, int strat) {
		main.setBoard(new adamBestMove(main, gametree.findChild(main),player,strat).findBest(5));
	}
	public void makeMove () {
		main.setBoard(movefinder.findBest(7));
	}
}