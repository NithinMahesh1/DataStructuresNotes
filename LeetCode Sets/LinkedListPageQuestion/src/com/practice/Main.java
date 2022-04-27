package com.practice;

public class Main {

    /*
        Question Notes:
            - Given linked List in method maximum pages through one single node head
            - List is an even number of nodes
            - Returns int: maximum number of pages read on any day


        Example:

            First Day
            [1] -> [4] -> [3] -> [2]
            sum=1+2=3

            Second Day
            [4] -> [3]
            sum=4+3=7

            Since 7>3 the maximum pages read is 7

    */

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list = list.insert(list, 1);
        list = list.insert(list,4);
        list = list.insert(list,3);
        list = list.insert(list,2);

        Node head = list.head;

        maximumPages(head);
    }

    public static int maximumPages(Node head) {
        int maxPages = 0;
        Node curr = head;

        while(curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }


        // Add solution here

        /*
            1. Use a stack to push all the elements in LinkedList
            2. Get the first and last val in stack then

            Turns out we can get the last node of the linked list by O(1)
                - if the node points to null in a single linked list then it is null
        */



        return maxPages;
    }
}
