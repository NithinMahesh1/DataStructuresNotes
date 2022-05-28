package com.practice;

import java.util.Stack;

public class Main {

    /*
        [1] -> [2] -> [3] -> [4] -> [5]

        Remove at n = 2

        Output:
        [1] -> [2] -> [3] -> [5]

    */

    /*
        Note we have some edge cases such as head = [1,2]
        Which returns [1]

        This isn't really an edge case but my solution won't work for this.
        Better solution:

        Use two nodes starting with the curr at -1:
            1. Increment all the way to the nth node for the last node
            2. Increment all the way to the n-1th position for the prev node

        Sudo Code (works in leetcode):

           public ListNode removeNthFromEnd(ListNode head, int n) {
           //base case
        if(head == null){
            return head;
        }

        int count = 0;
        ListNode dummy = new ListNode(-1);
        //Adding a dummy node befor the head, gives us the ease to write to code
        //without having to handle seperately the case where we have to delete
        //head node or even if there is only one node in the list
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;//ahead of slow, and heance the name

        //We move the fast pointer until the count is <= n
        while(count <= n){
            count++;
            fast = fast.next;
        }
        //Then we move slow and fast at 1x speed, until fast is null
        //This places the slow at (n-1)th position form end
        //As we want to remove nth node, we reset links and we remove
        //the nth node
        while(fast != null){
            slow = slow.next;
            fast = fast.next;
        }
        //Reset the links and remove nth node
        slow.next = slow.next.next;
        //As head is at dummy.next, we return that
        return dummy.next;
    }


                HOWEVER STACK SOLUTION BELOW WORKS WAY BETTER
    */

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        int n = 2;

        list = list.insert(list,1);
        list = list.insert(list,2);
        list = list.insert(list,3);
        list = list.insert(list,4);
        list = list.insert(list,5);

        removeNthFromEnd(list, n);
    }

//    public static LinkedList removeNthFromEnd(LinkedList head, int n) {
//        LinkedList resultHead = new LinkedList();
//        Node curr = head.head;
//        int listSize = head.size(head);
//        int removeAt = (listSize - n);
//
//        // Size of the linkedlist - n = the index we need to remove at
//        // Skip and get the next node
//        // return the list
//
//        if(head == null) {
//            return resultHead;
//        }
//
//        if(listSize == 1) {
//            return resultHead;
//        }
//
//
//        int count = 0;
//        while(curr != null) {
//            count++;
//            if(count == 1 && n == 1) {
//                curr = curr.next;
//                continue;
//            }
//            if(count == removeAt) {
//                resultHead = resultHead.insert(resultHead, curr.data);
//                curr = curr.next.next;
//            }
//            else {
//                resultHead = resultHead.insert (resultHead, curr.data);
//                curr = curr.next;
//            }
//        }
//
//
//
//        return resultHead;
//    }

    // This is a beautiful solution that utilizes a stack of type LinkedList
    // We can remove the node at the index and still have all the other nodes connected at the right spot
    public static LinkedList removeNthFromEnd(LinkedList head, int n) {
        Node node = head.head;
        Stack<Node> stk = new Stack<>();
        while(node != null){
            stk.push(node);
            node = node.next;
        }
        int count = 0;
        while(count < n){
            stk.pop();
            count++;
        }
        // This next line handles if the stack is empty (left it like this since I don't know what should go here)
        // if(stk.isEmpty())return nodeHead.next;
        Node node1 = stk.pop();
        node1.next = node1.next.next;
        return head;
    }
}
