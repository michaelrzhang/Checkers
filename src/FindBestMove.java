package src;
import src.*;
import lib.stdlib.*;
import java.util.HashSet;
import java.util.ArrayList;
import java.lang.NullPointerException;
/**
 * Expads the tree
 * Evaluates the child
 * Alpha Beta pruning
 */
public class FindBestMove{
    /**
     * Things to consider (most important at top):
     * learning from opponent
     * evaluate value of position
     * opening moves
     * optimal end game playing (could search deeper in end game)
     */

    // Essentially want to choose the move with best score
    // 
            // This Pseudocode from wikipedia might be useful
            // 01 function alphabeta(node, depth, α, β, maximizingPlayer)
            // 02      if depth = 0 or node is a terminal node
            // 03          return the heuristic value of node
            // 04      if maximizingPlayer
            // 05          v := -∞
            // 06          for each child of node
            // 07              v := max(v, alphabeta(child, depth - 1, α, β, FALSE))
            // 08              α := max(α, v)
            // 09              if β ≤ α
            // 10                  break (* β cut-off *)
            // 11          return v
            // 12      else
            // 13          v := ∞
            // 14          for each child of node
            // 15              v := min(v, alphabeta(child, depth - 1, α, β, TRUE))
            // 16              β := min(β, v)
            // 17              if β ≤ α
            // 18                  break (* α cut-off *)
            // 19          return v
            // (* Initial call *)

            // alphabeta(origin, depth, -∞, +∞, TRUE)
    GameTree state;
    Board main;
    int turn;
    int strat;
    public FindBestMove(Board b, int turn, int strat){
        main = b;
        state = new GameTree(b);
        this.turn = turn;
        this.strat = strat;
    }
    /** 0 is the cutoff between winning and losing
    minimizing one triesot mimimize beta
    maximing one tries to maximize alpha */
    public static double alphabeta(GameTree gtree, int depth, int turn, int strat, long time){
        if (gtree.getDepth() == depth){
            return gtree.eval(turn, strat); 
        }
        else if (gtree.getWinner(turn) == 1){
            return Integer.MAX_VALUE;
        }
        else if (gtree.getWinner(turn) == -1){
            return Integer.MIN_VALUE;
        }
        else if (Math.abs(gtree.getWinner(turn)) == 3){
            return 0;
        }
        else if (gtree.isMaximizing()){
            double v = Integer.MIN_VALUE;
            for (GameTree gt : gtree.getBranches()){
                if (System.nanoTime() - time > 10 * Math.pow(10, 9)){
                    System.out.println(System.nanoTime() - time);
                    return v;
                }
                v = Math.max(v, alphabeta(gt,depth, turn,strat, time));
                gtree.setAlpha(Math.max(v, gtree.getAlpha()));
                if (gtree.getBeta() <= gtree.getAlpha()){
                    continue;
                }
            }
            return v;
        }
        else{
            double v = Integer.MAX_VALUE;
            for (GameTree gt : gtree.getBranches()){
                if (System.nanoTime() - time > 10 * Math.pow(10, 9)){
                    System.out.println(System.nanoTime() - time);
                    return v;
                }
                v = Math.min(v, alphabeta(gt,depth, turn,strat, time));
                gtree.setBeta(Math.max(v, gtree.getBeta()));
                if (gtree.getBeta() <= gtree.getAlpha()){
                    continue;
                }
            }
            return v;
        }
    }
    public Board findBest(int depth){
        // flatten();
        long time = System.nanoTime();
        findChild();
        depth += state.getDepth();
        expandAll(depth, turn);
        int i = 0;
        GameTree bran = new GameTree();
        double v;
        double max = Integer.MIN_VALUE;
        for (GameTree gt : state.getBranches()){
            v = alphabeta(gt, depth, turn,strat,time);
            if (v >= max){
                bran = gt;
                max = v;
            }
            i += 1;
            if (System.nanoTime() - time > 10 * Math.pow(10, 9)){
                System.out.println(System.nanoTime() - time);
                break;
            }
        }
        this.state = bran;
        return state.getBoard(); 
    }
    /** Creates children */
    public static void expand(GameTree gt, int turn){
        if (gt.getWinner(turn) == 0){
            for (Board b : (new ComputerMoves(gt.getBoard())).possibleBoards()){
                gt.addBranch(b);
            }
        }
        else {
            System.out.println("setting board");
            System.out.println(gt.getWinner(turn));
        }
    }
    /** Calls expand on each of its children 
        Stops under two conditions:
            depth (distance from the initial) reaches desired
            someone wins
    */

    public static void expandAll(GameTree gt, int depth, int turn){
        if (gt.getBoard() == null){
            System.out.println(turn);
            throw new IllegalArgumentException();
        }
        else if (gt.getWinner(turn) == 0){            
            expand(gt, turn);
        }
        if (gt.getDepth() == depth-1){
            // return branches;
        }
        // does not expand when someone won
        else if(gt.getBranches() == null){
        }
        else{
            for (GameTree b : gt.getBranches()){
                expandAll(b, depth, turn);
            }
            // return branches;
        }
    }
    public void expandAll(int depth, int turn){
        expandAll(state, depth, turn);
    }
    public void flatten(){
        state.removeBranches();
    }
    /** Finds the child that the opponent is using */
    public void findChild(){
        for (GameTree gt: state.getBranches()){
            if (main.equals(gt.getBoard())){
                state = gt;
                // state.cutParent();
                state.cutBranches();
                return;
            }           
        }
    }
}