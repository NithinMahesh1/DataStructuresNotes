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
        Dictionary<int,List<int>> edgeDict = new Dictionary<int, List<int>>();
        HashSet<int> visited = new HashSet<int>();
        // List<int> removedEdge = new List<int>();

        // Initialize empty dictionary with list values to keep track of nodes and their
        // associated edges
        // for(int i=0; i<edges.Length; i++) {
        //     edgeDict[i] = new List<int>();
        // }

        // // Loop each value and append each edge to the dict
        // foreach(int[] pair in edges) {
        //     int node1 = pair[0];
        //     int node2 = pair[1];
        //     edgeDict[node1].Add(node1);
        //     edgeDict[node2].Add(node2);
        // }

        for(int e=0; e<edges.Length; e++) {
            int[] pair = edges[e];
            int node1 = pair[0];
            int node2 = pair[1];
            if(dfs(e,-1,edgeDict,visited)) {
                return new int[]{};
            }
        }

        // Loop now initialized dict and run dfs
        return new int[]{};
    }
    private bool dfs(int e, int prev, Dictionary<int,List<int>> edgeDict, HashSet<int> visited) {
        if(visited.Contains(e)) {
            return true;
        }

        visited.Add(e);

        foreach(int node in edgeDict[e]) {
            if(node == prev) {
                continue;
            }
            if(dfs(node,e,edgeDict,visited)) {
                return true;
            }
        }

        return false;
    }
}