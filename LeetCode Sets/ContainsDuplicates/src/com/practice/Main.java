package com.practice;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {

    /*
        Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.

        Constraints:
        1 <= nums.length <= 105
        -109 <= nums[i] <= 109

        Example 1:
        Input: nums = [1,2,3,1]
        Output: true

        Example 2:
        Input: nums = [1,2,3,4]
        Output: false

        Example 3:
        Input: nums = [1,1,1,3,3,4,3,2,4,2]
        Output: true
    */

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,1};
        int[] nums2 = new int[]{1,2,3,4};
        int[] nums3 = new int[]{1,1,1,3,3,4,3,2,4,2};

        containsDuplicate(nums3);
    }

    public static boolean containsDuplicate(int[] nums) {
        int[] arr = nums;
        Arrays.sort(arr);
        int moreThanOnce = 0;
        int prev = -1;

        for(int i=0; i<arr.length; i++) {
            if(i == 0) {
                prev++;
                continue;
            }
            else if(arr[i] == arr[prev]) {
                System.out.print("There is more one value duplicated");
                moreThanOnce++;
                return true;
            }
            prev++;
        }

        System.out.print("There is not a duplicate");
        return false;
    }
}
