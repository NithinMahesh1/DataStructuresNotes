package com.Practice;

/*
    Given an integer x, return true if x is a palindrome, and false otherwise.

    Input: x = 121
    Output: true
    Explanation: 121 reads as 121 from left to right and from right to left.

    Input: x = -121
    Output: false
    Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.

    Input: x = 10
    Output: false
    Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
 */

public class Main {

    public static boolean palindrome(int x) {
        // Loop through x in reverse and save it as the reverseint
        // Compare the two ints to see if they are equal and output true

        String intToStr = Integer.toString(x);
        String compareStr = "";

        if(x < 0) {
            return false;
        }

        for(int i=intToStr.length()-1; i >= 0; i--) {
            compareStr = compareStr + intToStr.charAt(i);
        }

        try {
            long reverseInteger = Long.parseLong(compareStr);
            if (reverseInteger == x) {
                return true;
            }
        }
        catch (NumberFormatException e){
            if (compareStr.charAt(0) == 0) {
                return false;
            } else if (Integer.parseInt(compareStr) == x) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int x = 1234567899;
        System.out.println(palindrome(x));
    }
}
