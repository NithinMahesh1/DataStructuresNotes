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
    public static void Main(string[] args) {
        int[][] grid = new int[][]{
            new int[]{1,1,0},
            new int[]{0,1,1},
            new int[]{0,1,2}
        };

        Solution obj = new Solution();
        obj.OrangesRotting(grid);
    }
    public int OrangesRotting(int[][] grid) {
        // Loop and start at the first rotting banana 2
        // Use BFS and check for 1's and convert to 2's
        // Increment counter each iteration
        HashSet<(int,int)> visited = new HashSet<(int, int)>{};
        Queue<(int,int)> q = new Queue<(int, int)>{};
        int counter = 0;
        bool rotted = false;

        for(int i=0; i<grid.Length; i++) {
            for(int j=0; j<grid[i].Length; j++) {
                if(grid[i][j] == 2) {
                    q.Enqueue((i,j));
                    visited.Add((i,j));
                }
                else if(grid[i][j] == 1) {
                    counter += 1;
                }
            }
        }

        if(counter == 0) {
            return -1;
        }

        while(q.Count != 0) {
            int size = q.Count;
            for(int z=0; z<size; z++) {
                var (i,j) = q.Dequeue();
                grid[i][j] = 2;
                rotted = bfs(grid,i+1,j,q,visited);
                rotted = bfs(grid,i-1,j,q,visited);
                rotted = bfs(grid,i,j+1,q,visited);
                rotted = bfs(grid,i,j-1,q,visited);
            }
            if(rotted) {
                counter += 1;
            }            
        }

        return counter;
    }
    private bool bfs(int[][] grid, int i, int j, Queue<(int,int)> q, HashSet<(int,int)> visited)  {
        if(i < 0 || i >= grid.Length || j < 0 || j >= grid[i].Length || grid[i][j] == 0 || visited.Contains((i,j))) {
            return false;
        }

        q.Enqueue((i,j));
        visited.Add((i,j));

        return true;
    }
}