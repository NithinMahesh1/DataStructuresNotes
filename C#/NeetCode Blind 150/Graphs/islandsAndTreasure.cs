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
    public static void Main(string[] args) {
        int[][] grid = new int[][]{
            new int[]{2147483647,-1,0,2147483647},
            new int[]{2147483647,2147483647,2147483647,-1},
            new int[]{2147483647,-1,2147483647,-1},
            new int[]{0,-1,2147483647,2147483647},
        };

        Solution obj = new Solution();
        obj.islandsAndTreasure(grid);
    }
    public void islandsAndTreasure(int[][] grid) {
        // In problems where we are trying to find distance to something we should be using BFS
        // We also need a queue set to keep track of the treasure chests and a hashset to keep track of the visited cell
        // Proper way to do this is using the treasure chest's "0" as the starting points
        // From there we should count and replace the 2147483647 values with the distance from the 0
        Queue<(int,int)> queue = new Queue<(int,int)>();
        HashSet<(int,int)> visited = new HashSet<(int,int)>();

        // Loop to find the treasure cells first
        for(int i=0; i<grid.Length; i++) {
            for(int j=0; j<grid[i].Length; j++) {
                if(grid[i][j] == 0) {
                    queue.Enqueue((i,j));
                    visited.Add((i,j));
                }
            }
        }

        int dist = 0;
        // Start a while loop from the treasure chests and start setting the distances        
        while(queue.Count != 0) {
            // Get grid position from queue
            for(int z=0; z<queue.Count; z++) {
                // We are popping the the treasure chests from the queue 
                var (i,j) = queue.Dequeue();
                
                // For each treasure chest we are changing to be the current distance
                // We end up updating the distance each iteration of treasure chests from queue
                grid[i][j] = dist;

                // Increment the positions of the grid (traversal)
                traverseGrid(grid,i+1,j,visited,queue);
                traverseGrid(grid,i-1,j,visited,queue);
                traverseGrid(grid,i,j+1,visited,queue);
                traverseGrid(grid,i,j-1,visited,queue);
            }
            // Here we increment the distance for each cell from the treasure chest
            dist += 1;
        }

    }
    private void traverseGrid(int[][] grid, int i, int j, HashSet<(int,int)> visited, Queue<(int,int)> queue) {
        if(i < 0 || i >= grid.Length || j < 0 || j >= grid[i].Length || grid[i][j] == -1) {
            return;
        }

        // Mark it as visited
        visited.Add((i,j));
        queue.Enqueue((i,j));
    }
}