package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Stack;

public class Main {

    /*
    * Given the head of a singly linked list, return true if it is a palindrome or false otherwise.
    *
    * Input: head = [1,2,2,1]
    * Output: true
    *
    * Input: head = [1,2]
    * Output: false
    *
    * Constraints:
    * The number of nodes in the list is in the range [1,10^5]
    * 0 <= Node.val <= 9
    */

    public static boolean isPalindrome(ListNode head) {
         /**
         * This new implementation handles cases when we run into larger inputs
         * The idea is to use a two-pointer technique to find the middle of the linked list and then reverse the second half of the Linked List
         * Then you can compare the first half of the Linked List with the reverse second half to determine if it is a palindrome
         */

        if (head == null || head.next == null) {
            return true;
        }

        // Find the middle of the linked list
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Reverse the second half of the linked list
        ListNode prev = null;
        ListNode curr = slow;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        // Compare the first half of the linked list with the reversed second half of the linked list
        ListNode p1 = head;
        ListNode p2 = prev;
        while (p2 != null) {
            if (p1.val != p2.val) {
                return false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        return true;
    }

    // Using this helper to convert file input it ListNode list
    public static ListNode createLinkedList(int[] nodevals) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int i = 0; i < nodevals.length; i++) {
            curr.next = new ListNode(nodevals[i]);
            curr = curr.next;
        }
        return dummy.next;
    }


    public static void main(String[] args) {
        //ListNode head = new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(1))));
        //ListNode head = new ListNode(1, new ListNode(2));
        ListNode head = new ListNode();

        // This reads the InputExampleTestCase.txt 79/90 testcase that fails in leetcode previously
        try {
            String input = new String(Files.readAllBytes(Paths.get("src\\com\\company\\InputExampleTestCase.txt")));
            String[] inputArr = input.split("[,]"); // split input by whitespace
            int[] nodevals = new int[inputArr.length];
            for(int i=0; i<inputArr.length; i++) {
                nodevals[i] = Integer.parseInt(inputArr[i]);
            }
            head = createLinkedList(nodevals);
            boolean palindrome = isPalindrome(head);
            System.out.println(palindrome);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        boolean palindrome = isPalinrome(head);
//        System.out.print(palindrome);
    }
}
