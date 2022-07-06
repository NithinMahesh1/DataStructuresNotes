package com.practice;

import java.util.Arrays;

public class Main {

    /*
        Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix. This matrix has the following properties:

        Integers in each row are sorted from left to right.
        The first integer of each row is greater than the last integer of the previous row.

        Example 1:
        Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
        Output: true

        Example 2:
        Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
        Output: false
    */

    public static void main(String[] args) {
        int[][] matrix =  new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,60}};
        int target = 3;

//        int target = 13;

        boolean answer = searchMatrix(matrix, target);
        System.out.println("The final solution is " +answer);
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        // Loop through each array in the matrix
        // Within each row we will call a binary search method and search it
        // If we find it within one of the rows we will return true

        /*
            forEach(row) {
                int[] currRow = row;
                if(searchExists(currRow)) {
                    return true;
                }
            }
        */

        int rowsLength = matrix.length;

        for(int i=0; i<rowsLength; i++) {
            int[] rows = new int[rowsLength];
            rows = matrix[i];
            if(searchExists(rows, target)) {
                return true;
            }
        }

        return false;
    }

    public static boolean searchExists(int[] rows, int target) {
        int low = 0;
        int high = rows.length - 1;

        while(low <= high) {
            int mid = (low+high)/2;

            if(rows[mid] == target) {
                System.out.println(rows[mid] + " is contained in matrix");
                return true;
            }
            if(rows[mid] < target) {
                low = mid + 1;
            }
            if(rows[mid] > target) {
                high = mid - 1;
            }
        }

        return false;
    }
}
