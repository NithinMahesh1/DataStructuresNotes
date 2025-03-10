// You are given a connected undirected graph with n nodes labeled from 1 to n. Initially, 
// it contained no cycles and consisted of n-1 edges.

// We have now added one additional edge to the graph. 
// The edge has two different vertices chosen from 1 to n, 
// and was not an edge that previously existed in the graph.

// The graph is represented as an array edges of length n where edges[i] = [ai, bi] 
// represents an edge between nodes ai and bi in the graph.

// Return an edge that can be removed so that the graph is still a connected non-cyclical graph. 
// If there are multiple answers, return the edge that appears last in the input edges.

// Example 1:
// Input: edges = [[1,2],[1,3],[3,4],[2,4]]
// Output: [2,4]

// Example 2:
// Input: edges = [[1,2],[1,3],[1,4],[3,4],[4,5]]
// Output: [3,4]

using System.ComponentModel;
using System.Runtime.InteropServices;

public class Solution {
    public int count = 0;
    public static void Main(string[] args) {
        int[][] edges = new int[][]{
            new int[]{1,2},
            new int[]{1,3},
            new int[]{3,4},
            new int[]{2,4}
        };

        Solution obj = new Solution();
        obj.FindRedundantConnection(edges);
    }

    public int[] FindRedundantConnection(int[][] edges) {
        // Basically we need to detect a cycle in the graph
        // Once we detect a cycle (we can keep appending this to a path)
        // we will then remove the last edge in that cycle
        // Thats why in example 2 we would elimate 3,4 since that is the end of the cycle
        Dictionary<int,List<int>> dict = new Dictionary<int, List<int>>();

        // Loop and initialize with empty list
        // We do 2 times the length since both edges (undirected)
        // will be added
        for(int i=0; i<edges.Length*2; i++) {
            dict[i] = new List<int>();
        }

        // Iterate through each edge and add it to graph
        foreach(int[] pair in edges) {
            int node1 = pair[0];
            int node2 = pair[1];
            dict[node1].Add(node2);
            dict[node2].Add(node1);

            // For each new edge check if adding it creates a cycle
            bool[] visited = new bool[edges.Length+1];
            if(dfs(node1,-1,dict,visited)) {
                // If we detect a cycle we return that edge
                // since it would be the last edge in the cycle
                return new int[]{node1,node2};
            }
        }

        return new int[]{};
    }    
    private bool dfs(int curr, int prev, Dictionary<int,List<int>> dict, bool[] visited) {
        // Return true since we have detected cycle and add it to our current visited list
        // that checks if adding our new value 
        if(visited[curr]) {
            return true;
        }

        // Otherwise this is marked as visited
        visited[curr] = true;


        foreach(int edge in dict[curr]) {
            // We then check if edge is prev then skip 
            // since we have already checked that node when backtracking in the stack
            if(edge == prev) {
                continue;
            }
            if(dfs(edge,curr,dict,visited)) {
                return true;
            }
        }

        return false;
    }
}