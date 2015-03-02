package src;
import src.*;
import lib.stdlib.*;
import java.util.HashSet;
import java.util.ArrayList;
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
    public static double alphabeta(GameTree gtree, int depth, int turn, int strat, long time){
        if (gtree.getDepth() == depth){
            return gtree.eval(turn, strat); 
        }
        else if (gtree.isMaximizing()){
            double v = Integer.MIN_VALUE;
            for (GameTree gt : gtree.getBranches()){
                if (System.nanoTime() - time > 1 * Math.pow(10, 9)){
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
                if (System.nanoTime() - time > 1 * Math.pow(10, 9)){
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
        depth+= state.getDepth();
        expandAll(depth);
        int i = 0;
        GameTree bran = new GameTree();
        double v;
        double max = Integer.MIN_VALUE;
        for (GameTree gt : state.getBranches()){
            v = alphabeta(gt, depth, turn,strat,time);
            if (v > max){
                bran = gt;
                max = v;
            }
            i += 1;
            if (System.nanoTime() - time > 1 * Math.pow(10, 9)){
                System.out.println(System.nanoTime() - time);
                break;
            }
        }
        this.state = bran;
        state.cutParent();
        return state.getBoard(); 
    }
    public static void expand(GameTree gt){
        for (Board b : (new ComputerMoves(gt.getBoard())).possibleBoards()){
            // b.drawBoard();
            // StdDraw.show(3000);
            gt.addBranch(b);
        }
        // return gt.getBranches();
    }
    public static void expandAll(GameTree gt, int depth){
        expand(gt);
        if (gt.getDepth() == depth-1){
            // return branches;
        }
        else{
            for (GameTree b : gt.getBranches()){
                expandAll(b, depth);
            }
            // return branches;
        }
    }
    public void expandAll(int depth){
        expandAll(state, depth);
    }
    public void flatten(){
        state.removeBranches();
    }
    public void findChild(){
        for (GameTree gt: state.getBranches()){
            if (main.equals(gt.getBoard())){
                state = gt;
                state.cutParent();
                state.cutBranches();
                return;
            }           
        }
    }
}