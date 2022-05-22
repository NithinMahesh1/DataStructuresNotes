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

//        FindSumTarget(target, arr);
        twoSum(arr,target);
    }

    public static int[] twoSum(int[] nums, int target) {

        Map<Integer,Integer> map = new HashMap<>();
        int arr[]=new int[2];

        for(int i=0;i<nums.length;i++){
            int ele = target-nums[i];

            if(map.containsKey(ele)){
                arr[0]=i;
                arr[1]=map.get(ele);
                break;
            }
            map.put(nums[i],i);
        }

        return arr;
    }

    /*
        1. Use a temp int[] arr and int index (that is a pointer)
        2. Loop through temp arr with index pointing at 0 then incrementing each time the rest of the values don't equal the target
        3. If we find a sum in the current iteration we will add both the values to the result List and then remove them from the temp arr
        4. If we do not find a sum with the current index we will increment the index and continue our loop


       ALSO NOTE ONCE WE GET ONE ITERATION TO THE END OF THE TEMP ARR
    */
    public static List<List<Integer>> FindSumTarget(int target, int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> concat = new ArrayList<>();
        int[] temp = arr.clone();

        Map<Integer, Integer> map = new HashMap<>();

        for(int i=0; i<temp.length; i++) {
            map.put(i,temp[i]);
        }


        for(int i=0; i<map.size(); i++) {
            int j = 0;
            for(j = i+1; j<map.size(); j++) {
                if(map.get(i) + map.get(j) == target) {
                    concat.add(temp[i]);
                    concat.add(temp[j]);
                    map.remove(i,map.get(i));
                    map.remove(j,map.get(j));
                    result.add(concat);
                    concat = new ArrayList<>();
                    break;
                }
            }
//            i = j;
        }

        System.out.print(result);
        return result;
    }
}
