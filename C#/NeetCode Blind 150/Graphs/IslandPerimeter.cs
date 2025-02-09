// You are given row x col grid representing a map where grid[i][j] = 1 represents l and 
// and grid[i][j] = 0 represents water.

// Grid cells are connected horizontally/vertically (not diagonally). 
// The grid is completely surrounded by water, and there is exactly one island 
// (i.e., one or more connected land cells).

// The island doesn't have "lakes", meaning the water inside isn't connected 
// to the water around the island. 
// One cell is a square with side length 1. 
// The grid is rectangular, width and height don't exceed 100. 
// Determine the perimeter of the island.

// Example 1:
// Input: grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
// Output: 16
// Explanation: The perimeter is the 16 yellow stripes in the image above.

// Example 2:
// Input: grid = [[1]]
// Output: 4

// Example 3:
// Input: grid = [[1,0]]
// Output: 4

public class Solution {
    public HashSet<(int,int)> visited = new HashSet<(int,int)>();
    public int perimeter = 0;

    public int IslandPerimeter(int[][] grid) {
        // Use DFS for this since its a graph problem
        // Visiting every cell in visit(i,j) and add to a perimeter value
        // Basically we keep traversing until we hit one node that is a 1
        // When we hit the node 1 we will check top, left, right, down
        // As we check each possible position to move to we add another 1 to our perimeter
        // We may want to use a visited hashset as well to keep track of nodes we visited


        // First traverse grid until we get to a 1:
        for (int i = 0; i < grid.Length; i++) {
            for (int j = 0; j < grid[0].Length; j++) {
                if (grid[i][j] == 1) {
                    // Start DFS from the first land cell
                    return dfs(grid, i, j);  
                }
            }
        }

        // In case there is no land
        return 0;  
    }
    public int dfs(int grid[][], int i, int j) {
        // Check for a 1 and return 1
        // Checking if we are out of bounds
        // if i < 0 we are outside top bound
        // if j < 0 we are outside left bound
        // if i >= grid length we are outside bottom bound
        // if j >= we are outside the bound of right bound
        // if grid[i][j] == 0 we that cell is water so we return
        if (i < 0 || j < 0 || i >= grid.Length || j >= grid[0].Length 
                || grid[i][j] == 0) {
            return 1;
        }

        // Check for a 0 and return 0 if we visited them already
        if(visited.Contains((i,j))) {
            return 0;
        }

        // If not add to visited set
        visited.Add((i,j));

        // Increment up, left, down, and right
        return dfs(grid,i,j+1) +
               dfs(grid,i+1,j) +
               dfs(grid,i,j-1) +
               dfs(grid,i-1,j);
    }
}