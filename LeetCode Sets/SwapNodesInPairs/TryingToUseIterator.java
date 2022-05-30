package com.practice;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    /*
        Given a linked list, swap every two adjacent nodes and return its head.
        You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)

        Input:
        [1] -> [2] -> [3] -> [4]

        Output:
        [2] -> [1] -> [4] -> [3]

    */

    public static void main(String[] args) {
        LinkedList<Integer> head = new LinkedList<Integer>();

        head.add(1);
        head.add(2);
        head.add(3);
        head.add(4);

//        When using the java.util.LinkedList class we want to use an iterator to traverse the nodes
//        Iterator it = head.iterator();
//
//        while(it.hasNext()) {
//            System.out.println(it.next());
//        }

        swapPairs(head);
    }

    public static LinkedList<Integer> swapPairs(LinkedList<Integer> head) {
        // Use a queue and iterator to push second and then first element every two elements in the LinkedList

        Queue queue = new LinkedList<Integer>();
        Iterator<Integer> prev = head.iterator();
//        Iterator<Integer> prev;
        Iterator<Integer> curr = head.iterator();

        while(curr.hasNext()) {
            prev = curr;
            prev.next();
            queue.add(prev.next());
            queue.add(curr.next());
            curr.next();
        }

        while(!queue.isEmpty()) {
            System.out.println(queue.poll());
        }

        return head;
    }
}
