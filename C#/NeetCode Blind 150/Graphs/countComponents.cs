// There is an undirected graph with n nodes. There is also an edges array, where edges[i] = [a, b] 
// means that there is an edge between node a and node b in the graph.

// The nodes are numbered from 0 to n - 1.

// Return the total number of connected components in that graph.

// Example 1:
// Input:
// n=3
// edges=[[0,1], [0,2]]
// Output:
// 1

// Example 2:
// Input:
// n=6
// edges=[[0,1], [1,2], [2,3], [4,5]]
// Output:
// 2
public class Solution {
    public int count = 0;
    public static void Main(string[] args) {
        int n = 6;
        int[][] edges = new int[][]{
            new int[]{0,1},
            new int[]{1,2},
            new int[]{2,3},
            new int[]{4,5}
        };

        // int n = 3;
        // int[][] edges = new int[][]{
        //     new int[]{0,1},
        //     new int[]{0,2}
        // };

        Solution obj = new Solution();
        obj.CountComponents(n,edges);
    }

    public int CountComponents(int n, int[][] edges) {
        // Loop through and add all values to a dictionary first
        Dictionary<int,List<int>> edgeDict = new Dictionary<int, List<int>>();
        HashSet<int> visited = new HashSet<int>();
        HashSet<int> path = new HashSet<int>();

        for(int i=0; i<n; i++) {
            edgeDict[i] = new List<int>();
        }
        
        foreach(int[] pair in edges) {
            int edge1 = pair[0];
            int edge2 = pair[1];
            edgeDict[edge1].Add(edge2);
            edgeDict[edge2].Add(edge1);
        }

        for(int e=0; e<n; e++) {
            if(!dfs(e,edgeDict,visited,path)) {
                continue;
            }
        }

        return count;
    }
    private bool dfs(int e, Dictionary<int,List<int>> edgeDict, HashSet<int> visited, HashSet<int> path) {
        // If the path contains edge we return false and end recursion
        if(path.Contains(e)) {
            return false;
        }

        // If visited contains edge return true since we visited already but 
        // need to keep iterating
        if(visited.Contains(e)) {
            return true;
        }

        // Add to path
        path.Add(e);

        // Call dfs in a loop to continue checking adjacent edges
        foreach(int edge in edgeDict[e]) {
            if(!dfs(edge,edgeDict,visited,path)) {
                continue;
            }
        }

        // Remove from path
        path.Remove(e);
        if(path.Count == 0) {
            count++;
        }
        // Add to Visited after
        visited.Add(e);

        return true;
    }
}