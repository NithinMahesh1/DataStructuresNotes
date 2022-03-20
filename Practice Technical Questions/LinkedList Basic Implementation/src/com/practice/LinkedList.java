package com.practice;

import com.sun.source.tree.WhileLoopTree;

public class LinkedList {
    Node head;

    public static LinkedList insert(LinkedList list, int info) {
        Node new_node = new Node(info);
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

    public static void printList(LinkedList list) {
        Node curr = list.head;

        while(curr != null) {
            System.out.println(curr.info);
            curr = curr.next;
        }
    }

    public static int size(LinkedList list) {
        Node curr = list.head;

        int counter = 0;
        while(curr != null) {
            curr = curr.next;
            counter++;
        }
        return counter;
    }

    public static void insertAt(LinkedList list, int info, int position) {
    // My Custom (but confusing implementation)
        /*
           Insert at position 2

           10->20->30->40
           10->insert->20->30->40
        */

        Node new_node = new Node(info);
        new_node.next = null;
        int listSize = list.size(list);

        if(position > listSize+1) {
            System.out.println("Cannot insert here position is greater than list size");
            return;
        }

        if(list.head == null) {
            list.head = new_node;
            return;
        }

        Node curr = list.head;
        Node prev = curr;
        Node next = curr.next;

        if(position == 1 || position == 0) {
            // position is insert at head
            curr = new_node;
            list.head = curr;
            curr.next = prev;
            return;
        }

        int counter = 1;
        while(counter != position) {

            if(counter == listSize) {
                curr.next = new_node;
                break;
            }

            curr = curr.next;
            next = curr.next;

            if(counter == 1) {
                prev = list.head;
            }
            else if(counter >= 2) {
                prev = prev.next;
            }

            counter++;
        }

        if(position == listSize+1) {
            prev = prev.next.next;
            curr = curr.next;
            return;
        }

        if(counter == listSize) {
            prev.next = new_node;
            new_node.next = curr;
            curr.next = next;
            return;
        }

        prev.next = new_node;
        new_node.next = curr;
        curr.next = next;

    }

    public static Node insertTest(LinkedList list , int position, int data) {
        // Much cleaner easy to read and understand solution

        Node headNode = list.head;

        if (position < 1)
            System.out.print("Invalid position");

        // if position is 1 then new node is
        // set infornt of head node
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

    public static LinkedList reverse(LinkedList list) {
        Node prev = null;
        Node curr = list.head;
        Node next = null;
        while(curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        list.head = prev;

        return list;
    }

    // Implement deleting a node at an index
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
