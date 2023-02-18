package com.practice;
/*
    You are given the heads of two sorted linked lists list1 and list2.
    Merge the two lists in a one sorted list. The list should be made by splicing together the nodes of the first two lists.
    Return the head of the merged linked list.

    Input: list1 = [1,2,4], list2 = [1,3,4]
    Output: [1,1,2,3,4,4]

    Input: list1 = [], list2 = []
    Output: []

    Input: list1 = [], list2 = [0]
    Output: [0]
 */

public class Main {

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

    public static void printList(ListNode mergedList) {
        ListNode curr = mergedList;

        while(curr != null) {
            System.out.print(curr.val);
            curr = curr.next;
        }
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode mergedList = new ListNode();
        ListNode curr = mergedList;
        ListNode currList1 = list1;
        ListNode currList2 = list2;

        if(list1 == null && list2 == null) {
            return list1;
        }

        if(list1 == null && list2 != null) {
            return list2;
        }
        else if(list2 == null && list1 != null) {
            return list1;
        }

        while(currList1 != null && currList2 != null) {
            if(currList1.val <= currList2.val) {
                curr.next = new ListNode(currList1.val);
                currList1 = currList1.next;
            }
            else {
                curr.next =  new ListNode(currList2.val);
                currList2 = currList2.next;
            }

            curr = curr.next;
        }

        if (currList1 != null) {
            curr.next = currList1;
        } else {
            curr.next = currList2;
        }

        return mergedList.next;
    }

    public static void main(String[] args) {
        ListNode list1 = new ListNode(1, new ListNode(2,new ListNode(4)));
        ListNode list2 = new ListNode(1, new ListNode(3, new ListNode(4)));

//        ListNode list1 = new ListNode();
//        ListNode list2 = new ListNode(0);

        ListNode mergedList = mergeTwoLists(list1, list2);

        printList(mergedList);
    }
}
