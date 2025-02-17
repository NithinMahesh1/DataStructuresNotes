// Given a 2D grid grid where '1' represents land and '0' represents water, count and return the number of islands.

// An island is formed by connecting adjacent lands horizontally or vertically and is surrounded by water. 
// You may assume water is surrounding the grid (i.e., all the edges are water).

// Example 1:
// Input: grid = [
//     ["0","1","1","1","0"],
//     ["0","1","0","1","0"],
//     ["1","1","0","0","0"],
//     ["0","0","0","0","0"]
//   ]
// Output: 1

// Example 2:
// Input: grid = [
//     ["1","1","0","0","1"],
//     ["1","1","0","0","1"],
//     ["0","0","1","0","0"],
//     ["0","0","0","1","1"]
//   ]
// Output: 4

public class Solution {
    public int NumIslands(char[][] grid) {
        // In this problem we traverse left, right, up, and down
        // We can traverse in these directions once we find a 1
        // So as we loop through the grid if we find a 1 we start checking
        // We want to use dfs for this to traverse as well
        if(grid == null || grid.Length == 0) {
            return 0;
        }

        int res = 0;
        // Need to pass grid[i][j] to dfs so we can traverse the grid
        for(int i=0; i<grid.Length; i++) {
            for(int j=0; j<grid[i].Length; j++) {
                if(grid[i][j] == '1') {
                    res++;
                    dfs(grid,i,j);
                }
            }
        }

        return res;
    }

    public void dfs(char[][] grid, int i, int j) {
        // If we get to the top bound, bottom bound, or right bound
        // then we stop iterating
        if(i >= grid.Length || j >= grid[0].Length || i < 0 || j < 0 || grid[i][j] == '0') {
            return;
        }

        // As we iterate land we will change them to 0's and 'sink' the land
        // This way we are only counting islands
        grid[i][j] = '0';
         
        // Continue dfs iterations in all bounds
        dfs(grid,i+1,j);
        dfs(grid,i-1,j);
        dfs(grid,i,j+1);
        dfs(grid,i,j-1);
    }
}
