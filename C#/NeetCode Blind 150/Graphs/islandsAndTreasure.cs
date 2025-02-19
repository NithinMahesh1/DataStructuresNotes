// You are given a m×n 2D grid initialized with these three possible values:
// 1. -1 - A water cell that can not be traversed.
// 2. 0 - A treasure chest.
// 3. INF - A land cell that can be traversed. We use the integer 2^31 - 1 = 2147483647 to represent INF.

// Fill each land cell with the distance to its nearest treasure chest. 
// If a land cell cannot reach a treasure chest than the value should remain INF.

// Assume the grid can only be traversed up, down, left, or right.

// Modify the grid in-place.

// Example 1:
// Input: [
//   [2147483647,-1,0,2147483647],
//   [2147483647,2147483647,2147483647,-1],
//   [2147483647,-1,2147483647,-1],
//   [0,-1,2147483647,2147483647]
// ]

// Output: [
//   [3,-1,0,1],
//   [2,2,1,-1],
//   [1,-1,2,-1],
//   [0,-1,3,4]
// ]

// Example 2:
// Input: [
//   [0,-1],
//   [2147483647,2147483647]
// ]

// Output: [
//   [0,-1],
//   [1,2]
// ]

public class Solution {
    public void islandsAndTreasure(int[][] grid) {
        // We can use a normal dfs traversal up, down, left, and right (avoiding -1)

        // Use two loops to traverse the grid
        // When we get to a value 2147483647 then we will want to start dfs traversal
        for(int i=0; i<grid.Length; i++) {
            for(int j=0; j<grid[i].Length; j++) {
                if(grid[i][j] == 2147483647) {
                    storeGridVals[new int[][]{ i,j }]
                    grid = dfs(grid,j,i);
                }
            }
        }

        return grid;
    }
    private int[][] dfs(int[][] grid,int i, int j) {
        // Check bounds of up, down, left, and right 
        if(i < 0 || i >= grid.Length || j < 0 || j >= grid[i].Length || grid[i][j] == -1) {
            return 0;
        }

        if(grid[i][j] == 0) {

        }
    }
}

static void main(string[] args) {
    Solution solution = new Solution();

    int[][] grid = new int[][] { {2147483647,-1,0,2147483647}, {2147483647,2147483647,2147483647,-1}, 
    {2147483647,-1,2147483647,-1}, {0,-1,2147483647,2147483647} };


    solution.islandsAndTreasure(grid);
}
