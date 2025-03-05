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
        // Two cases we are looking for:
        // 1. We visited all the nodes meaning they are all connected
        // 2. There are no cycles in the graph
        // Also we need to make sure as we check we will need to keep track of prev nodes
        // this ensures we can still detect cycle but also as we return back up a node we know
        // it was already visited

        Dictionary<int,List<int>> treeNodes = new Dictionary<int, List<int>>();
        HashSet<int> visited = new HashSet<int>();

        for(int i=0; i<n; i++) {
            treeNodes[i] = new List<int>();
        }

        foreach(int[] nodes in edges) {
            int node1 = nodes[0];
            int node2 = nodes[1];
            // We will add both since they are pairs stated in problem
            treeNodes[node1].Add(node2);
            treeNodes[node2].Add(node1);
        }

        var res = dfs(0,-1,treeNodes,visited) && visited.Count == n ? true : false;

        return res;
    }
    private bool dfs(int i, int prev, Dictionary<int,List<int>> treeNodes, HashSet<int> visited) {
        // If curr node is in visited then we return false
        if(visited.Contains(i)) {
            return false;
        }

        // If not we will need to add it to visited
        visited.Add(i);

        foreach(int node in treeNodes[i]) {
            if(node == prev) {
                continue;
            }
            if(!dfs(node,i,treeNodes,visited)) {
                return false;
            }
        }

        return true;
    }
}