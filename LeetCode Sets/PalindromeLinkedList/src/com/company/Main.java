package com.company;

public class Main {

    /*
    * Given the head of a singly linked list, return true if it is a palindrome or false otherwise.
    *
    * Input: head = [1,2,2,1]
    * Output: true
    *
    * Input: head = [1,2]
    * Output: false
    */

    public static boolean isPalinrome(ListNode head) {
        // loop through and add to array list
        // use reverse string to compare the reverse with the normal
        if(head == null) {
            return false;
        }


        String concatStr = "";
        StringBuilder reversed = new StringBuilder();
        ListNode curr = head;

        while(curr != null) {
            concatStr = concatStr + Integer.toString(curr.val);
            curr = curr.next;
        }

        reversed.append(concatStr);
        reversed.reverse();

        if(concatStr.compareTo(reversed.toString()) == 0) {
            return true;
        }

        return false;
    }


    public static void main(String[] args) {
        //ListNode head = new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(1))));
        ListNode head = new ListNode(1, new ListNode(2));

        boolean palindrome = isPalinrome(head);
        System.out.print(palindrome);
    }
}
