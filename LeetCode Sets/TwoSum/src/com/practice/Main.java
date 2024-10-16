package com.practice;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * You can return the answer in any order.
 *
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 *
 * Input: nums = [3,2,4], target = 6
 * Output: [1,2]
 *
 * Input: nums = [3,3], target = 6
 * Output: [0,1]
 *
 * */

public class Main {

    public static int[] twoSum(int[] sums, int target) {
        int[] result = new int[2];

        Map<Integer, Integer> map = new HashMap<>();

        int count = 0;
        while(count != sums.length) {
            //subtract current values with target and check if map contains key
            int subtract = target-sums[count];
            if(map.containsKey(subtract)) {
                result[0] = map.get(subtract);
                result[1] = count;
            }
            else {
                map.put(sums[count],count);
                count++;
                continue;
            }
            count++;
        }

        System.out.println("[" +result[0] + "," +result[1] +"]");
        return result;
    }


    public static void main(String[] args) {
        int[] sums = new int[]{2,7,11,15};
        int target = 9;
//        int[] sums = new int[]{3,2,4};
//        int target = 6;

        twoSum(sums, target);
    }
}
