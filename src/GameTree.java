package src;
import java.util.ArrayList;
import src.*;
public class GameTree{
    public GameTree parent;
    public Board board;
    public ArrayList<GameTree> branches;
    public double alpha;//min value
    public double beta;//max value
    public int depth;
    public boolean active;  // True if we should continue searching
    public boolean max;  // True if trying to find the best move, False if looking for the worse move

    public GameTree(){
        branches = null;
        parent = null;
        depth = 0;
        max = true;
        alpha = Integer.MIN_VALUE;
        beta = Integer.MAX_VALUE;
    }
    public GameTree(Board b){
        this.board = b;
        this.parent = parent;
        branches = new ArrayList<GameTree>();
        max = true;
        depth = 0;
        alpha = Integer.MIN_VALUE;
        beta = Integer.MAX_VALUE;
    }
    public GameTree(GameTree parent, Board b){
        this.parent = parent;
        max = !parent.max;
        this.board = b;
        branches = new ArrayList<GameTree>();
        depth = parent.depth+1;
        alpha = Integer.MIN_VALUE;
        beta = Integer.MAX_VALUE;
    }

    public void addBranch(Board b){
        branches.add(new GameTree(this, b));
    }
    public Board getBoard(){
        return board;
    }
    public void setAlpha(double a){
        alpha = a;
    }
    public void setBeta(double b){
        beta = b;
    }
    public double getAlpha(){
        return alpha;
    }
    public double getBeta(){
        return beta;
    }
    public int getDepth(){
        return depth;
    }
    public double eval(){
        double x = EvalBoard.evalBoard(board, -1);
        alpha = x;
        beta = x;
        return x;
    }
    public boolean isMaximizing(){
        return max;
    }
    public ArrayList<GameTree> getBranches(){
        return branches;
    }
    public void removeBranches(){
        branches = new ArrayList<GameTree>();
    }
}