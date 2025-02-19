// Given a node in a connected undirected graph, return a deep copy of the graph.

// Each node in the graph contains an integer value and a list of its neighbors.

// class Node {
//     public int val;
//     public List<Node> neighbors;
// }
// The graph is shown in the test cases as an adjacency list. 
// An adjacency list is a mapping of nodes to lists, used to represent a finite graph. 
// Each list describes the set of neighbors of a node in the graph.

// For simplicity, nodes values are numbered from 1 to n, where n is the total number of nodes in the graph. 
// The index of each node within the adjacency list is the same as the node's value (1-indexed).

// The input node will always be the first node in the graph and have 1 as the value.

// Example 1:
// Input: adjList = [[2],[1,3],[2]]

// Output: [[2],[1,3],[2]]
// Explanation: There are 3 nodes in the graph.
// Node 1: val = 1 and neighbors = [2].
// Node 2: val = 2 and neighbors = [1, 3].
// Node 3: val = 3 and neighbors = [2].

// Example 2:
// Input: adjList = [[]]

// Output: [[]]
// Explanation: The graph has one node with no neighbors.

// Example 3:
// Input: adjList = []

// Output: []
// Explanation: The graph is empty.

// Constraints:


// Definition for a Node.
public class Node {
    public int val;
    public IList<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new List<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new List<Node>();
    }

    public Node(int _val, List<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}


public class Solution {
    public Node CloneGraph(Node node) {
        // Use a dfs search and visit each node
        Node res = new Node();
        
        // Use a dictionary to store the original node as the key and the stored node as value
        Dictionary<Node,Node> copyOfGraph = new Dictionary<Node,Node>();
        return dfs(node,copyOfGraph);
    }

    public Node dfs(Node node, Dictionary<Node,Node> copyOfGraph) {
        if(node == null) {
            return null;
        }

        if(copyOfGraph.ContainsKey(node)) {
            return copyOfGraph[node];
        }

        Node copy = new Node(node.val);
        copyOfGraph[node] = copy;

        foreach(Node neighbor in node.neighbors) {
            // Need to recursively call the other nodes and their neighbors
            copy.neighbors.Add(dfs(neighbor,copyOfGraph));
        }

        return copy;
    }
}
