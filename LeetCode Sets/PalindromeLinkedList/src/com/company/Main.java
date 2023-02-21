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

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */

    public static boolean isPalinrome(ListNode head) {
        Stack<String> reverseStack = new Stack<>();

        if(head == null) {
            return false;
        }

        String concatStr = "";
        String input = "";
        ListNode curr = head;

        while(curr != null) {
            reverseStack.push(Integer.toString(curr.val));
            input = input + Integer.toString(curr.val);
            curr = curr.next;
        }

        while(!reverseStack.isEmpty()) {
            concatStr = concatStr + reverseStack.pop();
        }


        if(concatStr.compareTo(input.toString()) == 0) {
            return true;
        }

        return false;
    }

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

        try {
            String input = new String(Files.readAllBytes(Paths.get("src\\com\\company\\InputExampleTestCase.txt")));
            String[] inputArr = input.split("[,]"); // split input by whitespace
            int[] nodevals = new int[inputArr.length];
            for(int i=0; i<inputArr.length; i++) {
                nodevals[i] = Integer.parseInt(inputArr[i]);
            }
            head = createLinkedList(nodevals);
            boolean palindrome = isPalinrome(head);
            System.out.println(palindrome);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        ListNode head = new ListNode(nodevals[0]);
//        ListNode curr = head;
//
//        for(int i=1; i<nodevals.length; i++) {
//            curr.next = new ListNode(nodevals[i]);
//            curr = curr.next;
//        }

//        boolean palindrome = isPalinrome(head);
//        System.out.print(palindrome);
    }
}
