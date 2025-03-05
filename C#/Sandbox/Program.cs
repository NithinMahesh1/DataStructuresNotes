// Given n nodes labeled from 0 to n - 1 and a list of undirected edges 
// (each edge is a pair of nodes), 
// write a function to check whether these edges make up a valid tree.

// Example 1:
// Input:
// n = 5
// edges = [[0, 1], [0, 2], [0, 3], [1, 4]]
// Output:
// true

// Example 2:
// Input:
// n = 5
// edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]]
// Output:
// false

// Note:
// You can assume that no duplicate edges will appear in edges. 
// Since all edges are undirected, [0, 1] is the same as [1, 0] 
// and thus will not appear together in edges.

using System.Collections;

public class Solution {
    public static void Main(string[] args) {
        int n = 5;
        int[][] edges = new int[][]{
            new int[]{0,1},
            new int[]{0,2},
            new int[]{0,3},
            new int[]{1,4}
        };

        Solution obj = new Solution();
        obj.ValidTree(n,edges);
    }
    public bool ValidTree(int n, int[][] edges) {
        // So basically we will need to traverse using DFS
        // We will traverse also need to set a dictionary first
        // containing the first node and all the nodes associated to it in a list
        // We also need to make sure every node is visited == n (meaning it is connected)

        Dictionary<int,List<int>> treeNodes = new Dictionary<int,List<int>>();
        HashSet<int> visited = new HashSet<int>();
        HashSet<int> path = new HashSet<int>();

        for(int e=0; e<n; e++) {
            treeNodes[e] = new List<int>();
        }

        // Populate the values in dictionary
        foreach(int[] nodes in edges) {
            int node1 = nodes[0];
            int node2 = nodes[1];
            treeNodes[node1].Add(node2);            
        }

        // Loop through edges by n and run dfs on each
        for(int i=0; i<n; i++) {
            // Use the dfs bool to check
            if(!dfs(i,n,treeNodes,visited,path)) {
                return false;
            }
        }

        return true;
    }
    private bool dfs(int e, int n, Dictionary<int,List<int>> treeNodes, HashSet<int> visited, HashSet<int> path) {
        // if path contains value then we return false
        if(path.Contains(e)) {
            return false;
        }

        // if visited we return true
        if(visited.Contains(e) || visited.Count == n) {
            return true;
        }

        // add to path edge we visited
        path.Add(e);

        // Loop the other edges associated in dict
        foreach(int edge in treeNodes[e]) {
            if(!dfs(edge,n,treeNodes,visited,path)) {
                return false;
            }
        }

        // Remove from path after
        // Add to visited
        path.Remove(e);
        visited.Add(e);

        return true;
    }
}