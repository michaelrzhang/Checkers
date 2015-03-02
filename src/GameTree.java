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
        this.board = board;
        this.parent = parent;
        max = true;
        depth = 0;
        alpha = Integer.MIN_VALUE;
        beta = Integer.MAX_VALUE;
    }
    public GameTree(GameTree parent, Board b){
        this.parent = parent;
        max = !parent.max;
        this.board = board;
        depth = parent.depth+1;
        alpha = Integer.MIN_VALUE;
        beta = Integer.MAX_VALUE;
    }

    public void addBranch(Board b){
        branches.add(GameTree(b));
    }
    public Board getBoard(){
        return board;
    }
    public void setAlpha(int a){
        alpha = a;
    }
    public void setBeta(int b){
        beta = b;
    }
    public int getDepth(){
        return depth;
    }
    public void eval(){
        double x = EvalBoard.evalBoardSimple(board);
        alpha = x;
        beta = x;
    }
    public boolean isMaximizing(){
        return max;
    }
    public ArrayList<GameTree> getBranches(){
        return branches;
    }
}