// You are given a rectangular island heights where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).

// The islands borders the Pacific Ocean from the top and left sides, and borders the Atlantic Ocean from the bottom and right sides.

// Water can flow in four directions (up, down, left, or right) from a cell to a neighboring cell with height equal or lower. 
// Water can also flow into the ocean from cells adjacent to the ocean.

// Find all cells where water can flow from that cell to both the Pacific and Atlantic oceans. 
// Return it as a 2D list where each element is a list [r, c] representing the row and column of the cell. You may return the answer in any order.

// Example 1:
// Input: heights = [
//   [4,2,7,3,4],
//   [7,4,6,4,7],
//   [6,3,5,3,6]
// ]
// Output: [[0,2],[0,4],[1,0],[1,1],[1,2],[1,3],[1,4],[2,0]]

// Example 2:
// Input: heights = [[1],[1]]
// Output: [[0,0],[0,1]]

public class Solution {
    public static void Main(string[] args) {
        
        int[][] heights = new int[][]{
            new int[] {4,2,7,3,4},
            new int[] {7,4,6,4,7},
            new int[] {6,3,5,3,6},
        };

        Solution obj = new Solution();
        obj.PacificAtlantic(heights);
    }
    public List<List<int>> PacificAtlantic(int[][] heights) {
        // Look at row 0 and column 0 -> append those to pacific hashset
        // Look at row n-1 amd column n-1 for atlantic hashset
        // We then use dfs to check from the oceans for example pacific
        // to the points meaning it will be checking values that are the opposite
        // greater than equal to values not less than or equal to

        HashSet<(int,int)> pacific = new HashSet<(int, int)>();
        HashSet<(int,int)> atlantic = new HashSet<(int, int)>();
        int rows = heights.Length;
        int columns = heights[0].Length;

        // Looping the columns:
        for(int c=0; c<columns; c++) {
            dfs(heights[0][c],0,c,heights[0][c],heights,pacific);
            dfs(heights[rows-1][c],rows-1,c,heights[rows-1][c],heights,atlantic);
        }

        // Looping rows first starting with first and last:
        for(int r=0; r<rows; r++) {
            // Need to pass previous height as we loop 
            // Starts at the first row
            dfs(heights[r][0],r,0,0,heights,pacific);
            // Since we are already looping same len of first row
            // We should loop Atlantic last row
            dfs(heights[r][columns-1],r,columns-1,0,heights,atlantic);
        }



        List<List<int>> res = new List<List<int>>();
        // Loop and compare the two hashsets
        // Result is the intersection of atlantic and pacific
        for(int i=0; i<rows; i++) {
            for(int j=0; j<columns; j++) {
                if(pacific.Contains((i,j)) && atlantic.Contains((i,j))) {
                    List<int> vals = new List<int>{ i,j };
                    res.Add(vals);
                }
            }
        }

        return res;
    }
    private void dfs(int height, int r, int c, int prevHeight, int[][] heights, HashSet<(int,int)> visited) {
        if(r < 0 || r >= heights.Length || c < 0 || c >= heights[r].Length 
                    || visited.Contains((r,c)) || heights[r][c] < prevHeight) {
            return;
        }

        visited.Add((r,c));
        dfs(heights[r][c],r+1,c,heights[r][c],heights,visited);
        dfs(heights[r][c],r-1,c,heights[r][c],heights,visited);
        dfs(heights[r][c],r,c+1,heights[r][c],heights,visited);
        dfs(heights[r][c],r,c-1,heights[r][c],heights,visited);
    }
}
