package com.practice;

import com.sun.source.tree.NewArrayTree;

public class Main {
    /*  Given two linkedlist nodes that are in reverse order

        E.g.
        [2] -> [4] -> [3]
        [5] -> [6] -> [4]

        342 + 465 = 807

        Expected Output:
        [7] -> [0] -> [8]

    */


    public static void main(String[] args) {
        LinkedList list1 = new LinkedList();
        LinkedList list2 = new LinkedList();

//        Data Set 1:
//        list1.insert(list1,2);
//        list1.insert(list1,4);
//        list1.insert(list1,3);
//
//        list2.insert(list2,5);
//        list2.insert(list2,6);
//        list2.insert(list2,4);

//      Data Set 2:
        list1.insert(list1,9);
        list1.insert(list1,9);
        list1.insert(list1,9);
        list1.insert(list1,9);
        list1.insert(list1,9);
        list1.insert(list1,9);
        list1.insert(list1,9);

        list2.insert(list2,9);
        list2.insert(list2,9);
        list2.insert(list2,9);
        list2.insert(list2,9);

        addTwoNumbers(list1, list2);
    }

    public static LinkedList addTwoNumbers(LinkedList l1, LinkedList l2) {
        LinkedList result = new LinkedList();

        // Traverse both lists in one loop
            // Store each value as string for the ints
        // Use string.reverse to reverse lists
        // Sum the resulting ints
        // Convert to string
        // Loop each string as int
            // Create new linked list with each int

        LinkedList list1 = l1;
        LinkedList list2 = l2;

        Node curr1 = l1.head;
        Node curr2 = l2.head;

        String concat1 = "";
        String concat2 = "";


        while(curr1!=null) {
            concat1 = concat1 + String.valueOf(curr1.data);
            curr1 = curr1.next;
        }

        while(curr2!=null) {
            concat2 = concat2 + String.valueOf(curr2.data);
            curr2 = curr2.next;
        }

        String reverse1 = new StringBuilder(concat1).reverse().toString();
        String reverse2 =  new StringBuilder(concat2).reverse().toString();

        int add1 = Integer.parseInt(reverse1);
        int add2 = Integer.parseInt(reverse2);

        String traverse = new StringBuilder(String.valueOf(add1+add2)).reverse().toString();

        for(int i=0; i<traverse.length(); i++) {
            char eachNum = traverse.charAt(i);
            int res = Character.getNumericValue(eachNum);
            result.insert(result,res);
        }


        return result;
    }
}
