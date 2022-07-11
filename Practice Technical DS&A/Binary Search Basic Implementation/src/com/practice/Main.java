package com.practice;

public class Main {

    public static void main(String[] args) {
        // Find Target 41 using binary search algorithm

        int[] sortedArr = new int[] {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47};

        BinarySearch.binarySearch(41, sortedArr);
    }
}
