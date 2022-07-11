package com.practice;

public class Main {

    public static void main(String[] args) {
        LinkedList list = new LinkedList();

        list = list.insert(list,10);
        list = list.insert(list, 20);
        list = list.insert(list, 40);
        list = list.insert(list, 50);
        list = list.insert(list, 60);
        list = list.insert(list, 70);


        list.insertAt(list, 30, 3);
        list.deleteNode(list, 7);

        list.reverse(list);

        System.out.println("This is our final list");
        list.printList(list);
    }
}
