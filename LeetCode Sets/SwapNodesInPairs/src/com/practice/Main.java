package com.practice;

import java.util.Queue;
import java.util.Stack;

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
        LinkedList list = new LinkedList();

//        list = list.insert(list,1);
//        list = list.insert(list,2);
//        list = list.insert(list,3);
//        list = list.insert(list,4);

        swapPairs(list);
    }

    public static LinkedList swapPairs(LinkedList head) {
        // Use a queue to push second and then first element every two elements in the LinkedList
        LinkedList returnList = new LinkedList();
        Queue<Integer> queue = new java.util.LinkedList<>();
        Node curr = head.head;
        Node prev;

        if(head.size(head) == 0 || head.size(head) == 1) {
            return returnList;
        }

        while(curr != null && curr.next != null) {
            prev = curr;
            if(curr.data == head.head.data) {
                prev = prev.next;
                queue.add(prev.data);
                queue.add(curr.data);
                curr = curr.next;
            }
            curr = curr.next.next;
            queue.add(curr.data);
            prev = prev.next;
            queue.add(prev.data);
        }

        while(!queue.isEmpty()) {
            returnList = returnList.insert(returnList, queue.poll());
        }


        return returnList;
    }
}
