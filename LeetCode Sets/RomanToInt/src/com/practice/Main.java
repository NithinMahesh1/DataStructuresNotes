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
        map.put("L",50);
        map.put("C",100);
        map.put("CD",400);
        map.put("D",500);
        map.put("CM",900);
        map.put("M",1000);


        // Loop and check the current index with the next one if it is in the map
        // if not then just check map for the current val
        String concat = "";

        for(int i=0; i<s.length(); i++) {
            if(i+1 < s.length()) {
                String twovals = s.substring(i,i+2);
                if(map.containsKey(twovals)) {
                    // if there is a key in the map with these two then get the val
                    // concat to return string
                    concat += map.get(twovals);
                }
                else {
                    // If there is not then use the current index key to get the val
                    // concat to return string
                    concat += map.get(Character.toString(s.charAt(i)));
                }
            }
        }

        result = Integer.parseInt(concat);

        return result;
    }

    public static void main(String[] args) {
        String s = "MCMXCIV";

        romanToInt(s);
    }
}
