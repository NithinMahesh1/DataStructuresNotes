package com.practice;

import java.util.HashMap;
import java.util.Map;

/**
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * For example, 2 is written as II in Roman numeral, just two ones added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.
 *
 * Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
 *
 * I can be placed before V (5) and X (10) to make 4 and 9.
 * X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * Given a roman numeral, convert it to an integer.
 *
 * Input: s = "III"
 * Output: 3
 * Explanation: III = 3.
 *
 * Input: s = "LVIII"
 * Output: 58
 * Explanation: L = 50, V= 5, III = 3.
 *
 * Input: s = "MCMXCIV"
 * Output: 1994
 * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 * */


public class Main {

    public static int romanToInt(String s) {
        Map<String, Integer> map = new HashMap<>();
        int result = 0;

        map.put("I",1);
        map.put("IV",4);
        map.put("V",5);
        map.put("IX",9);
        map.put("X",10);
        map.put("XL",40);
        map.put("L",50);
        map.put("XC",90);
        map.put("C",100);
        map.put("CD",400);
        map.put("D",500);
        map.put("CM",900);
        map.put("M",1000);

        int strSize = s.length()-1;
        int i = 0;
        String doubles = "";
        while(i <= strSize) {
            // check first and second indices for mapkeys
            if(i != strSize) {
                char curr = s.charAt(i);
                char next = s.charAt(i+1);
                doubles = "" +curr+ "" +next;
            }
            if(map.containsKey(doubles)) {
                result += map.get(s.substring(i,i+2));
                i = i+2;
            }
            // check curr index for mapkeys
            else {
                result += map.get(Character.toString(s.charAt(i)));
                i++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
//        String s = "MCMXCIV";
//        String s = "LVIII";
        String s = "MDCXCV";

        System.out.print(romanToInt(s));
    }
}
