package com.practice;

public class BinarySearch {

    public static int binarySearch(int x, int[] sortedArr) {

        int numOfArray = 0;
        for(int n : sortedArr) {
            numOfArray++;
        }

        int lower = 0, upper = numOfArray-1;
        int count = 0;
        while(lower <= upper) {
            int curr = (lower + upper)/2;
            IntegerComparator comparator = new IntegerComparator();
            int result = comparator.compare(x,sortedArr[curr]);

            if(result == 0) {
                count++;
                System.out.println("We found " +sortedArr[curr]+ " at index: " +curr);
                System.out.println("This took us " +count+ " steps to get here");
                return curr;
            }

            else if(result < 0) {
                upper = curr - 1;
            }

            else {
                lower = curr + 1;
            }
            count++;
        }

        return -1;
    }

}
