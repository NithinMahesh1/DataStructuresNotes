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

    public static int[] returnIndices = new int[2];

    public static void main(String[] args) {
        int[] nums = new int[]{5,7,7,8,8,10};
        int target = 8;

//        int[] nums = new int[]{5,7,7,8,8,10};
//        int target = 6;

//        int[] nums = new int[]{};
//        int target = 0;

        searchRange(nums, target);
    }

    public static int[] searchRange(int[] nums, int target) {
        // Binary search helper method to get the first occurrence target
        // Once we get the first one, we check the index after to make sure

        // Since it is sorted the last element will always be one plus the targets indices
        int[] resultArr = new int[2];

        if(nums.length == 0 && target == 0) {
            resultArr[0] = -1;
            resultArr[1] = -1;
            System.out.print(resultArr[0] + "," + resultArr[1]);
            return resultArr;
        }

        binarySearch(nums, target);
        resultArr = getIndexOf();

        System.out.print(resultArr[0] + "," + resultArr[1]);
        return resultArr;
    }

    public static int[] getIndexOf() {
        return returnIndices;
    }

    public static int[] setIndex(int index) {
        if(index == -1) {
            returnIndices[0] = -1;
            returnIndices[1] = -1;
        }
        else {
            int lastIndex = index + 1;
            returnIndices[0] = index;
            returnIndices[1] = lastIndex;
        }

        return returnIndices;
    }

    public static int binarySearch(int[] nums, int target) {
        int low = 0;
        int high = nums.length;
        int mid = 0;

        while(low <= high) {
            mid = low+high/2;

            if(nums[mid] == target) {
                setIndex(mid);
                return mid;
            }
            if(nums[mid] < target) {
                low = mid+1;
            }
            if(nums[mid] > target) {
                high = mid-1;
            }
         }

        setIndex(-1);
        return -1;
    }
}
