package src;
import src.*;

public class BoardEvaluator{

    public static int countBluePieces(int[][] grid) {
        int blue = 0;
        for (int i = 0; i < grid.length; i += 1)
            for(int j = 0; j < grid[0].length; j += 1){
                if(grid[i][j] == -1){
                    blue += 1;
                }
            }
        return blue;
    }

    public static int countRedPieces(int[][] grid) {
        int red = 0;
        for (int i = 0; i < grid.length; i += 1)
            for(int j = 0; j < grid[0].length; j += 1){
                if(grid[i][j] == -1){
                    red += 1;
                }
            }
        return red;
    }

    public static int pawnDifference(int[][] grid){
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
    public static int kingDifference(int[][] grid){
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
    public static int safePawnDifference(int[][] grid){
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
    public static int safeKingDifference(int[][] grid){
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
    public static int promotionLineDifference(int[][] grid){
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