package com.practice;

public class Main {

    /*
        Question Notes:
            - Given linked List in method maximum pages through one single node head
            - List is an even number of nodes
            - Returns int: maximum number of pages read on any day

        Note: Try to create a solution that has a space complexity of O(1)

        Example:

            First Day
            [1] -> [4] -> [3] -> [2]
            sum=1+2=3

            Second Day
            [4] -> [3]
            sum=4+3=7

            Since 7>3 the maximum pages read is 7

    */

    public static int counter = 0;
    public static int size = 0;
    public static int sum = 0;

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list = list.insert(list, 1);
        list = list.insert(list,3);
        list = list.insert(list,5);
        list = list.insert(list,5);
        list = list.insert(list,6);
        list = list.insert(list,1);

        Node head = list.head;

        maximumPages(head);
    }

    public static Node loop(Node curr) {
        Node last = curr;
        Node prev = null;
        counter = 0;

        while(last != null && last.next != null) {
            counter++;
            System.out.println(last.data + " ");
            if(last.next.next == null){
                size = counter-1;
                return prev = last;
            }
            last = last.next;
        }

//        size = counter-1;
//        if() {
//
//        }

        return last;
    }

    public static void removeLast(Node head) {
        Node pointer = head;

        while(head != null && head.next != null) {
            if(pointer.next == null) {
                head.next = null;
                pointer = null;
                return;
            }
            pointer = pointer.next;
//            pointer = null;
        }
    }

    public static int maximumPages(Node head) {
        int maxPages = 0;
        Node last = null;
        Node prev = null;
        Node nextHead = head.next;


        // Helper method needed for looping
        // Each iteration we need to change the size value until it is 2 - then we stop looping
        prev = loop(head);
        last = prev.next;

        System.out.println("this is the first node " +head.data);
        System.out.println("this is the prev node " +prev.data);
        System.out.println("this is the last node " +last.data);


        sum = head.data + last.data;

        if(size != 2) {
            removeLast(head);
            head = head.next;
            loop(head);
        }
        else if(size == 2) {
//            head = head.next;
            prev = loop(head.next);
            head = head.next;
            last = prev;
            int isLarger = head.data + last.data;
            if(isLarger > sum) {
                maxPages = isLarger;
                System.out.print("maxpages is " +maxPages);
                return maxPages;
            }

            maxPages = sum;
        }
//        System.out.print(sum);



        // If last.next is null
            // Create sum for day
            // if the sum is greater than previous sum
                // then maxPages = new sum
            // else
                // maxPages = sum

        // else if size is 2 or greater we call the helper again




        // Add solution here

        /*
            1. Use a stack to push all the elements in LinkedList
            2. Get the first and last val in stack then

            Turns out we can get the last node of the linked list by O(1)
                - if the node points to null in a single linked list then it is null
        */



        System.out.print("maxpages is " +maxPages);
        return maxPages;
    }
}
