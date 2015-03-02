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
    public FindBestMove(Board b){
        main = b;
        state = new GameTree(b);
    }
    public static double alphabeta(GameTree gtree, int depth){
        if (gtree.getDepth() == depth){
            return gtree.eval(); 
        }
        else if (gtree.isMaximizing()){
            ArrayList<GameTree> branches = gtree.getBranches();
            double v = Integer.MIN_VALUE;
            for (GameTree gt : branches){
                v = Math.max(v, alphabeta(gt,depth));
                gtree.setAlpha(Math.max(v, gtree.getAlpha()));
                if (gtree.getBeta() <= gtree.getAlpha()){
                    continue;
                }
            }
            return v;
        }
        else{
            ArrayList<GameTree> branches = gtree.getBranches();
            double v = Integer.MAX_VALUE;
            for (GameTree gt : branches){
                v = Math.min(v, alphabeta(gt,depth));
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
        findChild();
        depth+= state.getDepth();
        expandAll(depth);
        int i = 0;
        int j = 0;
        double v;
        double max = Integer.MIN_VALUE;
        ArrayList<GameTree> branches = state.getBranches();
        for (GameTree gt : branches){
            v = alphabeta(gt, depth);
            if (v > max){
                j = i;
                max = v;
            }
            i += 1;
        }
        this.state = branches.get(j);
        ArrayList<GameTree> bran = state.getBranches();
        return state.getBoard(); 
    }
    public static ArrayList<GameTree> expand(GameTree gt){
        ComputerMoves cm = new ComputerMoves(gt.getBoard());
        ArrayList<Board> boards = cm.possibleBoards();
        for (Board b : boards){
            // b.drawBoard();
            // StdDraw.show(3000);
            gt.addBranch(b);
        }
        return gt.getBranches();
    }
    public static void expandAll(GameTree gt, int depth){
        ArrayList<GameTree> branches = expand(gt);
        if (gt.getDepth() == depth-1){
            // return branches;
        }
        else{
            for (GameTree b : branches){
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
        ArrayList<GameTree> branches = state.getBranches();
        for (GameTree gt: branches){
            if (main.equals(gt.getBoard())){
                state = gt;
                return;
            }           
        }
    }
}