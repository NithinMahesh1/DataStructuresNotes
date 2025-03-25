// You are given the head of a singly linked list head and a positive integer k.

// You must reverse the first k nodes in the linked list, 
// and then reverse the next k nodes, and so on. If there are fewer than k nodes left, 
// leave the nodes as they are.

// Return the modified list after reversing the nodes in each group of k.

// You are only allowed to modify the nodes' next pointers, not the values of the nodes.

//Definition for singly-linked list.

//Example 1:
// Input: head = [1,2,3,4,5,6], k = 3
// Output: [3,2,1,6,5,4]

// Example 2:
// Input: head = [1,2,3,4,5], k = 3
// Output: [3,2,1,4,5]

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int val=0, ListNode next=null) {
        this.val = val;
        this.next = next;
    }
}
 
public class Solution {
    public ListNode ReverseKGroup(ListNode head, int k) {
        
    }
    public static void Main(string[] args) {
        
    }
}


