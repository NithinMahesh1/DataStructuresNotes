// You are given a 2-D matrix grid. Each cell can have one of three possible values:

// 0 representing an empty cell
// 1 representing a fresh fruit
// 2 representing a rotten fruit
// Every minute, if a fresh fruit is horizontally or vertically adjacent to a rotten fruit, then the fresh fruit also becomes rotten.

// Return the minimum number of minutes that must elapse until there are zero fresh fruits remaining. 
// If this state is impossible within the grid, return -1.

// Example 1:
// Input: grid = [[1,1,0],[0,1,1],[0,1,2]]
// Output: 4

// Example 2:
// Input: grid = [[1,0,1],[0,2,0],[1,0,1]]
// Output: -1

public class Solution {
    public int OrangesRotting(int[][] grid) {
        Queue<(int,int)> rotten = new Queue<(int,int)>{};
        // Keep counter for fresh
        // This way we can check if there is a fresh fruit left after iterating
        // Means that there was a fruit isolated and we should return -1
        int fresh = 0;
        // Counter for time
        int time = 0;
      
        for(int i=0; i<grid.Length; i++) {
            for(int j=0; j<grid[i].Length; j++) {
                if(grid[i][j] == 2) {
                    rotten.Enqueue((i,j));
                }
                if(grid[i][j] == 1) {
                    fresh++;
                }
            }
        }

        // Define the directions we can increment:
        int[][] directions = new int[][]{
            new int[] {0,1},
            new int[] {0,-1},
            new int[] {1,0},
            new int[] {-1,0}
        };
        // While to loop queue pos
        // Also while the fresh value is greater 
        // (we will decrement the fresh as we make rotten)
        while(rotten.Count > 0 && fresh > 0) {
            int size = rotten.Count;
            for(int z=0; z<size; z++) {
                // Each iteration of this we move in the directions of rotting
                // We loop the row and columns from the rotted fruit
                var (i,j) = rotten.Dequeue();

                // Use pre defined directions
                // Loop and add the directions we can go in to rotten curr val
                for(int d=0; d<directions.Length; d++) {
                    // Change the row and column values in new positions
                    int row = i + directions[d][0];
                    int column = j + directions[d][1];
                    if(row < 0 || row >= grid.Length || column < 0 || column >= grid[row].Length 
                    || grid[row][column] != 1) {
                        // We are outside of the bounds
                        continue;
                    }

                    // Rot the fruit and add to the rotten queue
                    grid[row][column] = 2;
                    rotten.Enqueue((row,column));
                    fresh--;                    
                }
            }
            // After iterating we increment time
            time += 1;
        }

        return fresh == 0 ? time : -1;
    }
}
