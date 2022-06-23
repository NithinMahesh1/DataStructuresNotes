package com.practice;

import java.util.ArrayList;
import java.util.List;

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

        return combinedList;
    }
}
