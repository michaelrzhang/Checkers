package src;
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
}