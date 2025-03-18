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
using System.Reflection;
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
        Node res = solution.copyRandomList(head);
    }
    public Node buildList(int?[][] input) {
        // Loop and create individual nodes for each list
        // Add them to a dictionary Key being indices and Value being nodes
        // Another loop will then allow me to put those values into the correct ran indices
        Dictionary<int,Node> linkedDict = new Dictionary<int, Node>();
        for(int i=0; i<input.Length; i++) {
            linkedDict[i] = new Node(input[i][0].Value);
        }

        // Populate next and random values to each node in dict
        for(int j=0; j<input.Length; j++) {   
            // Set to the next index node         
            if(j < input.Length - 1) {
                linkedDict[j].next = linkedDict[j+1];
            }
            // Then we set our current node to the random pointer
            if(input[j][1] != null) {
                // We get the node by using the index in inputs to link to the node val
                // from our dictionary
                linkedDict[j].random = linkedDict[input[j][1].Value];
            }            
        }

        // Since the first index is the first node we return this
        return linkedDict[0];
    }
    public Node copyRandomList(Node head) {
        // Need a dictionary to loop through and add key as index
        // for each value in the key it will be a tuple (node,random.val)
        Dictionary<int,(Node,int)> indexNodeDict = new Dictionary<int,(Node,int)>();
        Node temp = head;
        int tempCount = 0;
        while(temp != null) {
            Node random = temp.random;
            int ranvalue = -1;
            if(random != null) {
                ranvalue = random.val;
            }
            indexNodeDict[tempCount] = (temp,ranvalue);
            tempCount++;
            temp = temp.next;
        }

        // Loop dictionary and build linked list
        // We need a head node for start, then prev and curr
        // prev will keep track of the last node to connect to our curr
        // curr will handle setting the random node
        Node res = indexNodeDict[0].Item1;
        Node prev = res;
        Node curr = res;
        int count = 0;
        while(curr != null) {
            if() {

            }
            Node random = new Node(indexNodeDict[count].Item2);
            if(random.val == -1) {
                random = null;
            }
            curr.random = random;
            curr = curr.next;
            prev.next = curr;
            prev = prev.next;
            count++;
        }


        return res;
    }
}
