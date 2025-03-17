// You are given the head of a linked list of length n. Unlike a singly linked list, 
// each node contains an additional pointer random, which may point to any node in the list, or null.

// Create a deep copy of the list.

// The deep copy should consist of exactly n new nodes, each including:
//    * The original value val of the copied node
//    * A next pointer to the new node corresponding to the next pointer of the original node
//    * A random pointer to the new node corresponding to the random pointer of the original node
// Note: None of the pointers in the new list should point to nodes in the original list.

// Return the head of the copied linked list.

// In the examples, the linked list is represented as a list of n nodes. Each node is represented as a pair of 
// [val, random_index] where random_index is the index of the node (0-indexed) that the random pointer points to, 
// or null if it does not point to any node.

// Example 1:
// Input: head = [[3,null],[7,3],[4,0],[5,1]]
// Output: [[3,null],[7,3],[4,0],[5,1]]

// Example 2:
// Input: head = [[1,null],[2,2],[3,2]]
// Output: [[1,null],[2,2],[3,2]]


// Definition for a Node.
using System.Runtime.InteropServices;

public class Node {
    public int val;
    public Node next;
    public Node random;
    
    public Node(int _val) {
        val = _val;
        next = null;
        random = null;
    }
}

public class Solution {
    public static void Main(string[] args) {
        int?[][] input = new int?[][]{
            new int?[] {3,null},
            new int?[] {7,3},
            new int?[] {4,0},
            new int?[] {5,1}
        };
        Solution solution = new Solution();
        Node head = solution.buildList(input);
    }
    public Node buildList(int?[][] input) {
        // Loop and create individual nodes for each list
        // Add them to a dictionary Key being indices and Value being nodes
        // Another loop will then allow me to put those values into the correct ran indices
        Dictionary<int,Node> linkedDict = new Dictionary<int, Node>();

        foreach(int?[] pair in input) {
            Node curr = new Node(pair[0].Value);
            int index = pair[1] ?? -1;
            linkedDict.Add(index,curr);
        }

        Node node = new Node(-1);
        for(int i=0; i<input.Length; i++) {
            // Create a new node
            node = new Node(linkedDict.Values.First().val);

            // Remove the values as we loop dict
            linkedDict.Remove(linkedDict.Keys.First());

            // Populate the random value
            // The key is the index associated
            if(linkedDict.Keys.First() == -1) {
                node.random = null;
            }
            else {
                node.random = linkedDict[linkedDict.Keys.First()];
            }

            // Point it to the next node
            node =  node.next;
        }

        return node;
    }
    public Node copyRandomList(Node head) {
        Node curr = head;
        
        while(curr.val != null) {
            Console.WriteLine(curr.val);
            Console.WriteLine(curr.random);
            curr = curr.next;
        }

        return head;
    }
}
