package com.practice;

import java.util.Stack;

public class Main {

    /*
        [1] -> [2] -> [3] -> [4] -> [5]

        Remove at n = 2

        Output:
        [1] -> [2] -> [3] -> [5]

    */

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        int n = 2;

        list = list.insert(list,1);
        list = list.insert(list,2);
        list = list.insert(list,3);
        list = list.insert(list,4);
        list = list.insert(list,5);

        removeNthFromEnd(list.head, n);
    }

    public static LinkedList removeNthFromEnd(Node head, int n) {
        LinkedList resultHead = new LinkedList();
        Node curr = head;
        Stack<Integer> stack = new Stack<>();

        // Push all data to a stack
        // get the n-1 for what index of the stack we want to pop
        // As we pop we will start to insert to a new linked list
        while(curr != null && curr.next != null) {
            stack.push(curr.data);
            curr = curr.next;
        }

        int count = 0;
        while(!stack.isEmpty()) {
            if(count == n-1) {
                continue;
            }
            resultHead = resultHead.insert(resultHead, stack.pop());
        }



        return resultHead;
    }
}
