// There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive). 
// The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] 
// denotes a bi-directional edge between vertex ui and vertex vi. Every vertex pair is connected by at most one edge, 
// and no vertex has an edge to itself.

// You want to determine if there is a valid path that exists from vertex source to vertex destination.

// Given edges and the integers n, source, and destination, 
// return true if there is a valid path from source to destination, or false otherwise.

// Example 1:
// Input: n = 3, edges = [[0,1],[1,2],[2,0]], source = 0, destination = 2
// Output: true
// Explanation: There are two paths from vertex 0 to vertex 2:
// - 0 → 1 → 2
// - 0 → 2

// Exmaple 2:
// Input: n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3]], source = 0, destination = 5
// Output: false
// Explanation: There is no path from vertex 0 to vertex 5.

public class Solution {
    public bool ValidPath(int n, int[][] edges, int source, int destination) {
        bool isValid = false;
        // Simply need to traverse use dfs
        // Loop until we get to the source then we start traversing
        for(int i=0; i<edges.Length; i++) {
            for(int j=0; j<edges[i].Length; j++) {
                if(grid[i][j] == source) {
                    isValid = traverseEdges(grid,i,j,destination);
                }
            }
        }
        return isValid;
    }
    private bool traverseEdges(int[][] grid, int i, int j) {
        if(i < 0 || i >= grid.Length || j < 0 || j >= grid[i].Length) {
            return;
        }

        if(grid[i][j] == destination) {
            return true;
        }

        return false;
    }
}