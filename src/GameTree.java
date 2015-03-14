package src;
import java.util.ArrayList;
import src.*;

/** Alternates between choosing best move for you and worst move for you */
public class GameTree{
    public GameTree parent;
    public Board board;
    public ArrayList<GameTree> branches;
    public double alpha; //Integer.min value, value that you want to maximize
    public double beta; //Integer.max value, value that you want to minimize
    public int depth;
    public boolean active;  // True if we should continue searching
    public int max;  // True if trying to find the best move, False if looking for the worse move
    public int winner = -10;
    
    /** Should never actually use this constructor*/
    public GameTree(){
        branches = new ArrayList<GameTree>();
        parent = null;
        depth = 0;
        max = 1;
        alpha = Integer.MIN_VALUE;
        beta = Integer.MAX_VALUE;
    }
    /** Root board */
    public GameTree(Board b){
        this.board = b;
        this.parent = null;
        branches = new ArrayList<GameTree>();
        max = 1;
        depth = 0;
        alpha = Integer.MIN_VALUE;
        beta = Integer.MAX_VALUE;
    }
    /** All other boards */
    public GameTree(GameTree parent, Board b){
        this.parent = parent;
        max = parent.max * -1;
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
    public double eval(int player, int strat){
        double x = EvalBoard.eval(board, player, strat);
        alpha = x;
        beta = x;
        return x;
    }
    public void cutParent(){
        parent = null;
    }
    public void cutBranches(){
        branches.clear();
    }
    public int maxing(){
        return max;
    }
    public boolean isMaximizing(){
        if (max == 1) {
            return true;
        } else {
            return false;
        }
    }
    public ArrayList<GameTree> getBranches(){
        return branches;
    }
    public void removeBranches(){
        branches = new ArrayList<GameTree>();
    }
    /** No valid moves left, returns the winner */
    /**
     * -1 Blue won   0 Tie     1 Red won
     * @param  player the current player
     */
    public int getWinner(int player){
        if (winner == -10){
            winner = board.winner() * player;
        }
        return winner; 
    }
    public GameTree findChild(Board main){
        for (GameTree gt: getBranches()){
            if (main.equals(gt.getBoard())){
                return gt;
            }           
        }
        return this;
    }
}