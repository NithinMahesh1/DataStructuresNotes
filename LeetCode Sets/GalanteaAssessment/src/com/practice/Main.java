package com.practice;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        int[] A = new int[]{2,5,3,2,4,1};


        solution(A);
    }

    public static int solution(int[] A) {
        // First split array into increasing and decreasing sets
        // Find the largest num of values in each set to get the total

        //[2,5]
        //[5,3,2]
        //[2,4,1]

        //{2,5,3,2,4,1};

        // Loop through the array
        // Compare each index to the next index val
        // If the first number - the second number is - then we add that to a list
        // Once the index - the next number is + then we - and add all those values to the new list

        List<List<Integer>> finalList = new ArrayList<>();

        List<Integer> decreasingLists = new ArrayList<>();
        List<Integer> increasingLists =  new ArrayList<>();

//        for(int i=0; i<A.length; i++) {
//
//
//            if(i+1 > A.length) {
//                break;
//            }
//
//            int prev = A[i];
//            int next = A[i+1];
//
//            if(prev - next < 0) {
//                // It is negative
//                decreasingLists.add(prev);
//                decreasingLists.add(next);
//            }
//            if(prev - next > 0) {
//                // It is positive
//                increasingLists.add(prev);
//                increasingLists.add(next);
//            }
//
//
//        }

        for(int i=0; i<A.length; i++) {
            int pointerOne = A[i];
            for(int j=i+1; j<A.length; j++) {
                int pointerForward = A[j];
                pointerOne = A[j-1];
                if(pointerOne - pointerForward < 0) {
                    //negative
                    decreasingLists.add(pointerOne);
                    decreasingLists.add(pointerForward);
                }
                if(pointerOne - pointerForward > 0) {
                    //positive
                    increasingLists.add(pointerOne);
                    increasingLists.add(pointerForward);
                }
            }
        }

        return -1;
    }
}
