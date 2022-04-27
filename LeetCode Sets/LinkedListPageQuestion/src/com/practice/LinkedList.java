package com.practice;

public class LinkedList {
    Node head;

    public static LinkedList insert(LinkedList list, int data) {
        Node new_node =  new Node(data);
        new_node.next = null;

        if(list.head == null) {
            list.head = new_node;
        }
        else {
            Node last = list.head;
            while(last.next != null) {
                last = last.next;
            }
            last.next = new_node;
        }
        return list;
    }

    // Print List

    public static void printList(LinkedList list) {
        Node curr = list.head;

        while(curr != null) {
            System.out.println(curr.data);
            curr = curr.next;
        }
    }

    // List Size

    public static int size(LinkedList list) {
        Node curr = list.head;

        int counter = 0;
        while(curr != null) {
            curr = curr.next;
            counter++;
        }
        return counter;
    }

    // Insert At Position

    public static Node insertAt(LinkedList list , int position, int data) {
        Node headNode = list.head;

        if (position < 1)
            System.out.print("Invalid position");

        // if position is 1 then new node is
        // set in front of head node
        // head node is changing.
        if (position == 1) {
            Node newNode = new Node(data);
            newNode.next = headNode;
            list. head = newNode;
        } else {
            while (position-- != 0) {
                if (position == 1) {
                    // adding Node at required position
                    Node newNode = new Node(data);

                    // Making the new Node to point to
                    // the old Node at the same position
                    newNode.next = headNode.next;

                    // Replacing current with new Node
                    // to the old Node to point to the new Node
                    headNode.next = newNode;
                    break;
                }
                headNode = headNode.next;
            }
            if (position != 1)
                System.out.print("Position out of range");
        }
        return list.head;
    }

    // Delete Node At Position

    public static void deleteNode(LinkedList list, int position){
        if(list.head == null) {
            return;
        }

        Node temp = list.head;

        if(position == 0) {
            list.head = temp.next;
            return;
        }

        for(int i=1; temp != null && i < position-1; i++) {
            temp = temp.next;
        }

        if(temp == null || temp.next == null) {
            return;
        }

        Node next =  temp.next.next;

        temp.next = next;
    }

}
