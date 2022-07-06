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

//        int[] nums = new int[]{2,2};
//        int target = 2;

        searchRange(nums, target);
    }

    /*
        This solution works since we are basically implementing two binary searches:
            - One of them is indexed to the left most occurrence
            - One of them is indexed to the right most occurrence

         Same algorithm just changing the indices around, so we search right then left and place the pointer either before or after

    */

    public static int[] searchRange(int[] nums, int target) {
        int[] res = new int[2];
        int first = firstOcc(nums, target);
        int last = lastOcc(nums, target);
        res[0] = first;
        res[1] = last;
        return res;
    }

    public static int firstOcc(int[] nums, int target) {
        int res = -1;
        int left = 0;
        int right = nums.length - 1;

        while(left <= right) {
            int mid = (right+left)/2;
            if(nums[mid] == target) {
                res = mid;
                right = mid - 1;
            }
            if(nums[mid] < target) {
                left = mid + 1;
            }
            if(nums[mid] > target) {
                right = mid - 1;
            }
        }

        return res;
    }

    public static int lastOcc(int[] nums, int target) {
        int res = -1;
        int left = 0;
        int right = nums.length - 1;

        while(left <= right) {
            int mid = (left+right)/2;
            if(nums[mid] == target) {
                res = mid;
                left = mid + 1;
            }
            if(nums[mid] < target) {
                left = mid + 1;
            }
            if(nums[mid] > target) {
                right =  mid - 1;
            }
        }

        return res;
    }

}
