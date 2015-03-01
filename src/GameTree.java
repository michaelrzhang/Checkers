import java.util.ArrayList;

public class GameTree{
    public GameTree parent;
    public BoardState b;
    public ArrayList<GameTree> branches;
    public int score;
    public int depth;
    public boolean active;  // True if we should continue searching
    public boolean max;  // True if trying to find the best move, False if looking for the worse move

    public GameTree(){
        branches = null;
        parent = null;
    }

    public GameTree(GameTree parent, BoardState b){
        this.parent = parent;
        this.b = b;
    }

    public void addBranch(BoardState b){
        branches.add(GameTree(b));
    }



}