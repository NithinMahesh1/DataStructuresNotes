package com.practice;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        MinIntHeap heap = new MinIntHeap();
        int test = (0-1)/2;

        System.out.print("This is what (1-2)/2 is: " +test);

        heap.add(4);
        heap.add(8);
        heap.add(3);
        heap.add(10);
        heap.add(9);
        heap.add(7);
        heap.add(15);
        heap.add(9);
        heap.add(20);
        heap.add(13);

        heap.poll();


     }
}
