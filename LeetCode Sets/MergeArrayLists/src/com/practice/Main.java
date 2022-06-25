package com.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    // Given a list of lists n integers combine the lists return one full sorted list at the end
    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        list1.add(2);
        list1.add(5);
        list1.add(6);
        list1.add(8);
        list1.add(12);

        List<Integer> list2 = new ArrayList<>();
        list2.add(7);
        list2.add(8);
        list2.add(13);
        list2.add(14);
        list2.add(19);
        list2.add(20);
        list2.add(26);
        list2.add(30);

        List<Integer> list3 = new ArrayList<>();
        list3.add(1);
        list3.add(2);
        list3.add(3);
        list3.add(14);

        List<List<Integer>> listoflists = new ArrayList<>();
        listoflists.add(list1);
        listoflists.add(list2);
        listoflists.add(list3);

        mergeSortedLists(listoflists);
    }

    public static List<Integer> mergeSortedLists(List<List<Integer>> listoflists) {
        List<Integer> combinedList = new ArrayList();

        // Loop through each list
        // For each list get the inner lists ints and push to the priority queue
        // Run a while loop until queue is empty and pop to combinedList

        PriorityQueue<Integer> queue = new PriorityQueue<>();


        for(int i=0; i<listoflists.size(); i++) {
            List<Integer> inputList = new ArrayList<>();
            inputList = listoflists.get(i);
            int count = 0;
            while(count != inputList.size()) {
                int inputVal = inputList.get(count);
                queue.add(inputVal);
                count++;
            }
        }

        Integer[] arr = new Integer[queue.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = queue.remove();
        }


        combinedList = Arrays.asList(arr);
        System.out.println(combinedList.toString());

        return combinedList;
    }
}
