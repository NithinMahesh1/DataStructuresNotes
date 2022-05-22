package com.practice;

import java.util.*;

public class Main {

    /*
        Write a program that takes an array and a target number and returns any pairs whose sum equals the target number

        For Example:

        arr = [5,7,3,9,4,6]
        target = 12

        Output:
        return [[5,7],[3,9]]
    */

    public static void main(String[] args) {
//	    int[] arr = new int[]{5,7,3,9,4,6};
//        int target = 12;

        int[] arr = new int[]{0,-1,2,-3,1};
        int target = -2;

        FindSumTarget(target, arr);
    }

    /*
        1. Iterate array and for each element subtract it with target then check the map to see if there is another index containing this
        2. If there is we get both elements from the map and then remove at those following indices
    */
    public static List<List<Integer>> FindSumTarget(int target, int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> sums = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();

        for(int i=0; i<arr.length; i++) {
            int elem = target-arr[i];

            if(map.containsKey(elem)) {
                sums.add(elem);
                sums.add(arr[i]);
                result.add(sums);
                sums = new ArrayList<>();
            }
            map.put(arr[i], i);
        }

        System.out.println(result);
        return result;
    }
}
