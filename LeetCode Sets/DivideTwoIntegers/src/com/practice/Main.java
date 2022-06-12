package com.practice;

import java.util.ArrayList;
import java.util.List;

public class Main {

    /*
        Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod operator.

        The integer division should truncate toward zero, which means losing its fractional part. For example, 8.345 would be truncated to 8, and -2.7335 would be truncated to -2.

        Return the quotient after dividing dividend by divisor.

         Note: Assume we are dealing with an environment that could only store integers within the 32-bit signed integer range:
         [−2^31, 2^31 − 1]. For this problem, if the quotient is strictly greater than 2^31 - 1, then return 2^31 - 1, and if the quotient is strictly less than -2^31, then return -2^31.

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
        int dividend = 7;
        int divisor = -3;

        divide(dividend, divisor);
    }

    
    // Note that we can also use Math.abs() to convert a negative int to a postive one
    // Another thing to note we should also be taking into account the Integer.MIN_VALUE. As such:
    //          if (dividend == Integer.MIN_VALUE && divisor == -1)
    //              return Integer.MAX_VALUE;
    public static int divide(int dividend, int divisor) {
        // Essentially how many times we multiply the divisor to get the closest possible value
        boolean resIsNegative = false;

        if(dividend < 0 || divisor < 0) {
            resIsNegative = true;
        }
        if(divisor < 0) {
            String posDivisor = String.valueOf(divisor);
            posDivisor = posDivisor.substring(1);
            divisor = Integer.parseInt(posDivisor);
        }
        if(dividend < 0) {
            String posDividend = String.valueOf(dividend);
            posDividend = posDividend.substring(1);
            dividend = Integer.parseInt(posDividend);
        }


        int sum = divisor;
        int count = 0;

        while(sum <= dividend) {
            count++;
            if(sum + divisor > dividend) {
                break;
            }
            sum = sum + divisor;
        }

        if(resIsNegative) {
            System.out.println("The quotient is: " +"-"+ +count);
            return 0-count;
        }

        System.out.println("The quotient is: " +count);
        return count;
    }
}
