package src;
import src.*;

public class EvalBoard{

// 10-11-12-13-14
// 00-01-02-03-04
    public static double evalBoard(Board b){
        // hardcoding because can't think of way to put multiplier for given coordinates
        int[][] board = b.getGrid();
        int[][] multipliers = new int[8][8];
        for (int i = 0; i < 8; i += 2){
            multipliers[0][i] = 4;
        }
        multipliers[1][1] = 3;
        multipliers[1][3] = 3;
        multipliers[1][5] = 3;
        multipliers[1][7] = 4;

        multipliers[2][0] = 4;
        multipliers[2][2] = 2;
        multipliers[2][4] = 2;
        multipliers[2][6] = 3;

        multipliers[3][1] = 3;
        multipliers[3][3] = 1;
        multipliers[3][5] = 2;
        multipliers[3][7] = 4;

        multipliers[4][0] = 4;
        multipliers[4][2] = 2;
        multipliers[4][4] = 1;
        multipliers[4][6] = 3;

        multipliers[5][1] = 3;
        multipliers[5][3] = 2;
        multipliers[5][5] = 2;
        multipliers[5][7] = 4;

        multipliers[6][0] = 4;
        multipliers[6][2] = 3;
        multipliers[6][4] = 3;
        multipliers[6][6] = 3;

        for (int i = 1; i < 8; i += 2){
            multipliers[7][i] = 4;
        }

// should test evalboard
        double count = 0;
        int temp;
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                temp = board[i][j];
                if (temp != 0){
                    assert multipliers[i][j] != 0; // DEBUGGING: to avoid stupid error where there is no multiplier
                    if (Math.abs(temp) == 2){
                        count += temp * 2.5 * multipliers[i][j];
                    }
                    if (Math.abs(temp) == 1){
                        count += temp * 4 * multipliers[i][j];
                    }
                }
            }
        }
        return count * -1;
    }

    public double evalBoardHard(Board b){
        int[][] board = b.getGrid();
        return pawnDifference(board)*3 + kingDifference(board) * 6 + safePawnDifference(board) * 1 ;
    }
    public int pawnDifference(int[][] grid){
        int red = 0;
        int blue = 0;
        for (int i = 0; i < grid.length; i += 1)
            for(int j = 0; j < grid[0].length; j += 1){
                if(grid[i][j] == 1) {
                    red += 1;
                } else if(grid[i][j] == -1){
                    blue += 1;
                }
            }
        return red - blue;
    }
    public int kingDifference(int[][] grid){
        int red = 0;
        int blue = 0;
        for (int i = 0; i < grid.length; i += 1)
            for(int j = 0; j < grid[0].length; j += 1){
                if(grid[i][j] == 2) {
                    red += 1;
                } else if(grid[i][j] == -2){
                    blue += 1;
                }
            }
        return red - blue;
    }
    public int safePawnDifference(int[][] grid){
        int red = 0;
        int blue = 0;
        int[] edges = new int[] {0,7};
        for (int i : edges)
            for(int j = 0; j < grid[0].length; j += 1){
                if(grid[i][j] == 1) {
                    red += 1;
                } else if(grid[i][j] == -1){
                    blue += 1;
                }
            }
        return red - blue;
    }
    public int safeKingDifference(int[][] grid){
        int red = 0;
        int blue = 0;
        int[] edges = new int[] {0,7};
        for (int i : edges)
            for(int j = 0; j < grid[0].length; j += 1){
                if(grid[i][j] == 1) {
                    red += 1;
                } else if(grid[i][j] == -2){
                    blue += 1;
                }
            }
        return red - blue;
    }
    public int promotionLineDifference(int[][] grid){
        int red = 0;
        int blue = 0;
        for (int i = 0; i< grid.length; i += 1)
            for(int j = 0; j < grid[0].length; j += 1){
                if(grid[i][j] == 1) {
                    red += (7-j);
                } else if(grid[i][j] == -1){
                    blue += j;
                }
            }
        return red - blue;
    }
    public int unoccupiedPromotionLineDifference(int[][] grid){
        int red = 0;
        int blue = 0;
        for (int i = 0; i< grid.length; i += 1){
            if(grid[i][7] == 0){
                red += 1;
            }
            if(grid[i][0] == 0){
                blue += 1;
            }
        }    
        return red - blue;
    }




    

}