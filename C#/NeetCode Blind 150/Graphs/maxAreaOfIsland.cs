// You are given a matrix grid where grid[i] is either a 0 (representing water) or 1 (representing land).

// An island is defined as a group of 1's connected horizontally or vertically. 
// You may assume all four edges of the grid are surrounded by water.

// The area of an island is defined as the number of cells within the island.

// Return the maximum area of an island in grid. If no island exists, return 0.

// Exmaple 1:
// Input: grid = [
//   [0,1,1,0,1],
//   [1,0,1,0,1],
//   [0,1,1,0,1],
//   [0,1,0,0,1]
// ]

// Output: 6
// Explanation: 1's cannot be connected diagonally, so the maximum area of the island is 6.

public class Solution {
    public int MaxAreaOfIsland(int[][] grid) {
        // Same as the other island grid problems
        // Basically use dfs to traverse top, bottom, left, and right
        // In this method we will take the max each time we check them
        if(grid == null || grid.Length == 0) {
            return 0;
        }

        int res = 0;
        // Traverse the grid
        for(int i=0; i<grid.Length; i++) {
            for(int j=0; j<grid[i].Length; j++) {
                if(grid[i][j] == 1) {
                    res = Math.Max(res,dfs(grid,i,j));
                }                
            }
        }

        return res;
    }

    public int dfs(int[][] grid, int i, int j) {
        // Checks for bounds and if 0
        if(i < 0 || i >= grid.Length || j < 0 || j >= grid[i].Length || grid[i][j] == 0) {
            return 0;
        }

        // Set to 0 after visiting so we don't check that island again        
        grid[i][j] = 0;

        // Set counter for current land cell
        int count = 1;

        count += dfs(grid,i+1,j);
        count += dfs(grid,i-1,j);
        count += dfs(grid,i,j+1);
        count += dfs(grid,i,j-1);

        return count;
    }
}
