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
        // Look at row 0 and column 0 -> Append the cells that are valid (all of them) for pacific
        // Keep appending the pacific ones to a hash set
        // Do the same for the atlantic ones
        // At the end we loop and take the intersection of the values both have

        // We need a queue, and hashset for each one
        HashSet<(int,int)> pacific = new HashSet<(int, int)>();
        Queue<(int,int)> pacificQ = new Queue<(int, int)>();
        HashSet<(int,int)> atlantic = new HashSet<(int, int)>();
        Queue<(int,int)> atlanticQ = new Queue<(int, int)>();

        HashSet<(int,int)> visited = new HashSet<(int, int)>();

        int[][] directions = new int[][]{
            new int[] {0,1},
            new int[] {0,-1},
            new int[] {1,0},
            new int[] {-1,0}
        };
        // BFS for pacific
        for(int r=0; r<heights.Length; r++) {
            for(int c=0; c<heights[r].Length; c++) {
                if(r == 0 || c == 0) {
                    pacific.Add((r,c));
                    pacificQ.Enqueue((r,c));
                    visited.Add((r,c));
                }
                while(pacificQ.Count > 0) {
                    foreach(int[] direction in directions) {
                        int row = r + direction[0];
                        int column = c + direction[1];

                    }
                }
            }
        }

        // BFS for atlantic 


        // Get intersection


        return new List<List<int>>();
    }
}