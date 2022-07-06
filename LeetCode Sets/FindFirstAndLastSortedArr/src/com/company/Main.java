package com.company;

public class Main {

    /*
        Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.
        If target is not found in the array, return [-1, -1].
        You must write an algorithm with O(log n) runtime complexity.

        Example 1:
        Input: nums = [5,7,7,8,8,10], target = 8
        Output: [3,4]

        Example 2:
        Input: nums = [5,7,7,8,8,10], target = 6
        Output: [-1,-1]

        Example 3:
        Input: nums = [], target = 0
        Output: [-1,-1]
    */

    public static void main(String[] args) {
        int[] nums = new int[]{5,7,7,8,8,10};
        int target = 8;

//        int[] nums = new int[]{5,7,7,8,8,10};
//        int target = 6;

//        int[] nums = new int[]{};
//        int target = 0;

//        int[] nums = new int[]{1};
//        int target = 1;

//        int[] nums = new int[]{2,2};
//        int target = 3;

        searchRange(nums, target);
    }

    public static int[] searchRange(int[] nums, int target) {
        /*
            int[] answers = new int[2]
            if(arr.length == 1 && target == the one element) return 0,0
            if(arr.length == 0) return -1,-1

            int index = binarySearch(nums);
            int duplicateIndex = index+1

            answers[0] = index;
            answers[1] = duplicateIndex
        */
        int[] answers = new int[2];

        if(nums.length == 1 && target == nums[0]) {
            answers[0] = 0;
            answers[1] = 0;
        }
        else if(nums.length == 0) {
            answers[0] = -1;
            answers[1] = -1;
        }

        else {
            int index = binarySearch(nums, target);
            if(index == 0) {
                answers[0] = -1;
                answers[1] = -1;
            }
            else {
                int duplicateIndex = index + 1;
                answers[0] = index;
                answers[1] = duplicateIndex;
            }
        }

        System.out.print(answers[0] + "," + answers[1]);
        return answers;
    }

    public static int binarySearch(int[] nums, int target) {
        /*
            if(target ! exist in array) return -1,-1 - how do I know when to stop the binary search

            while(low <= high) {
                mid = (low+high)/2
                if(arr[mid] == target) return mid
                if(arr[mid] < target) lower = mid+1
                if(arr[mid] > target) higher = mid-1
            }
        */
        int low = 0;
        int high = nums.length;
        int mid = 0;

        while(low <= high) {
            mid = (low+high)/2;

            if(mid >= nums.length) {
                return 0;
            }
            if(nums[mid] ==  target) {
                return mid;
            }
            if(nums[mid] < target) {
                low = mid+1;
            }
            if(nums[mid] > target) {
                high = mid-1;
            }
        }

        return mid;
    }
}
