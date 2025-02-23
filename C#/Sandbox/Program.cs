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
        // [1,2],[5,1],[1,3],[1,4]
        // int[][] edges = new int[][]{
        //     new int[]{1,2},
        //     new int[]{5,1},
        //     new int[]{1,3},
        //     new int[]{1,4}
        // };
        int[][] edges = new int[][]{
            new int[]{1,2},
            new int[]{2,3},
            new int[]{4,2}
        };

        Solution obj = new Solution();
        obj.FindCenter(edges);
    }
    public int FindCenter(int[][] edges) {
        // Can we just check every index and add to a Dictionary with a counter
        Dictionary<int,int> dict = new Dictionary<int, int>();
        for(int i=0; i<edges.Length; i++) {
            int edge = edges[i][0];
            int vertice = edges[i][1];
            if(dict.ContainsKey(edge)) {
                dict[edge] = dict[edge] + 1;
            }
            else if(dict.ContainsKey(vertice)) {
                dict[vertice] = dict[vertice] + 1;                
            }
            else {
                dict[edges[i][0]] = 1;
                dict[edges[i][1]] = 1;
            }
        }

        Console.WriteLine(dict.MaxBy(e => e.Value).Key);
        return dict.MaxBy(e => e.Value).Key;
    }
}