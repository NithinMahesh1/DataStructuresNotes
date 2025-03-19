// You are given two non-empty linked lists, l1 and l2, where each represents a non-negative integer.

// The digits are stored in reverse order, e.g. the number 123 is represented as 3 -> 2 -> 1 -> in the linked list.

// Each of the nodes contains a single digit. 
// You may assume the two numbers do not contain any leading zero, except the number 0 itself.

// Return the sum of the two numbers as a linked list.

// Example 1:
// Input: l1 = [1,2,3], l2 = [4,5,6]
// Output: [5,7,9]
// Explanation: 321 + 654 = 975.

// Example 2:
// Input: l1 = [9], l2 = [9]
// Output: [8,1]

// Definition for singly-linked list.
using System.ComponentModel.DataAnnotations;
using Microsoft.VisualBasic;

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int val=0, ListNode next=null) {
        this.val = val;
        this.next = next;
    }
 }
public class Solution {
    public static void Main(string[] args) {
        // int[] l1 = new int[] {1,2,3};
        // int[] l2 = new int[] {4,5,6};
        int[] l1 = new int[]{9};
        int[] l2 = new int[]{9};

        Solution solution = new Solution();
        (ListNode,ListNode) linkedLists = solution.buildLists(l1,l2);
        ListNode res = solution.AddTwoNumbers(linkedLists.Item1,linkedLists.Item2);
    }
    public (ListNode,ListNode) buildLists(int[] input1, int[] input2) {
        ListNode list1 = null;
        ListNode list2 = null;
        
        ListNode curr1 = null;
        foreach(int num in input1) {
            if(curr1 == null) {
                list1 = new ListNode(num);
                curr1 = list1;
            }
            else {
                curr1.next = new ListNode(num);
                curr1 = curr1.next;
            }
        }

        ListNode curr2 = null;
        foreach(int num2 in input2) {
            if(curr2 == null) {
                list2 = new ListNode(num2);
                curr2 = list2;
            }
            else {
                curr2.next = new ListNode(num2);
                curr2 = curr2.next;
            }
        }

        return (list1,list2);
    }
    public ListNode AddTwoNumbers(ListNode l1, ListNode l2) {
        // Need to loop through and add the values then append to new ListNode
        // There will be cases such as one list may be greater than the other
        // Also if we have two vals that for example 8+7 we would need to carry the digit
        // to the next place and add it to the other two vals

        ListNode res = new ListNode();
        ListNode curr = res;

        int carry = 0;
        while(l1 != null || l2 != null || carry != 0) {
            // Since either l1 or l1 can be null
            // we an use ternary checks to set them
            int val1 = l1 != null ? l1.val : 0;
            int val2 = l2 != null ? l2.val : 0;

            // Create the new val
            int val = val1 + val2 + carry;
            // The division gives us the carry over since no remainder
            carry = val / 10;
            // We can use modulus to separate the remainder
            // And then make that the val
            val = val % 10;
            curr.next = new ListNode(val);

            // Move pointers
            curr = curr.next;
            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }
        
        return res.next;
    }
}
