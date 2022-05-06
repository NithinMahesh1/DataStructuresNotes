package com.practice;


import java.util.*;

public class Main {

    public static void main(String[] args) {
//        String s = "babad";
        String s = "abacdgfdcaba";
        longestPalindrome(s);
    }

    public static String longestPalindrome(String s) {
        // Create a reverse string of s
        // Loop through and compare both strings and indices
        // if the index at s is the same as index at t then we add char to new string

        String returnString = "";
        String reverse = new StringBuilder(s).reverse().toString();


        for(int i=0; i<s.length(); i++) {
            for(int j=i; j<reverse.length(); j++) {
                if (s.charAt(i) == reverse.charAt(j)) {
                    returnString = returnString + s.charAt(j);
                }
                break;
            }
        }


        System.out.println(returnString);
        return returnString;
    }

}