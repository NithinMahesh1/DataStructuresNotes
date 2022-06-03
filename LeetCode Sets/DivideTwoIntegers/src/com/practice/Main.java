package com.practice;

import java.util.ArrayList;
import java.util.List;

public class Main {

    /*
        Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod operator.

        The integer division should truncate toward zero, which means losing its fractional part. For example, 8.345 would be truncated to 8, and -2.7335 would be truncated to -2.

        Return the quotient after dividing dividend by divisor.

         Note: Assume we are dealing with an environment that could only store integers within the 32-bit signed integer range:
         [−231, 231 − 1]. For this problem, if the quotient is strictly greater than 231 - 1, then return 231 - 1, and if the quotient is strictly less than -231, then return -231.

         Example 1:
         Input: dividend = 10, divisor = 3
         Output: 3
         Explanation: 10/3 = 3.33333.. which is truncated to 3.

         Example 2:
         Input: dividend = 7, divisor = -3
         Output: -2
         Explanation: 7/-3 = -2.33333.. which is truncated to -2.
    */

    public static void main(String[] args) {
        int dividend = 10;
        int divisor = 3;

        divide(dividend, divisor);
    }

    public static int divide(int dividend, int divisor) {
        // Use an Arraylist of values from -2^31 (figure out how to get the exponent in code) to 2^31 -1
            // Going to need a loop to create those values and add to the list
        // Multiply each value by the divisor to find what result is closest to the dividend
        // Instead of using multiplication add the divisor how many times we are multiplying by

        ArrayList<Integer> divideBy = new ArrayList<>();
        double start = Math.pow(2,-31);
        double end = Math.pow(2,31)-1;


        for(double i=start; i<end; i++) {
//            System.out.println((int) i);
            divideBy.add((int) i);
        }



        return -1;
    }
}
