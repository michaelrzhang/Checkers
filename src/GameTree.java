import java.util.ArrayList;

public class GameTree{
    public BoardState parent;
    public BoardState root;
    public ArrayList<BoardState> branches;
    public int score;
    public int depth;
    public boolean active;  // True if we should continue searching
    public boolean max;  // True if trying to find the best move, False if looking for the worse move

    public GameTree(){
        branches = null;
    }

    public void addBranch(BoardState b){
        branches.add(b);
    }



}