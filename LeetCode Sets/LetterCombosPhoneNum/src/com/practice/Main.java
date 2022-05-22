package com.practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    /*
        Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
        A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

        See Images for example

        Example:

        Input: digits = "23"
        Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
    */

    public static void main(String[] args) {
        String digits = "23";
        letterCombinations(digits);
    }

    public static List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        Map<String, char[]> map = new HashMap<>();

        map.put("2",new char[]{'a','b','c'});
        map.put("3", new char[]{'d','e','f'});
        map.put("4",new char[]{'g','h','i'});
        map.put("5", new char[]{'j','k','l'});
        map.put("6", new char[]{'m','n','o'});
        map.put("7", new char[]{'p','q','r','s'});
        map.put("8", new char[]{'t','u','v'});
        map.put("9", new char[]{'w','x','y', 'z'});


        if(digits.length() == 1) {
            char[] elems = map.get(digits);
            for(int i=0; i<elems.length; i++) {
                result.add(String.valueOf(elems[i]));
            }
        }

        else {
            // Pass the first digit then
            // Call recursive function checking the next index
            letterHelper(digits, result, map);
        }

        return result;
    }

    public static void letterHelper(String nums, List<String> answer, Map<String, char[]> map) {
        String concat = "";

        // All the values at the map index
        for(int i=0; i<nums.length(); i++) {
            char[] mapChars = map.get(nums.charAt(i));
            // All the chars in that index
            for(int j=0; j<mapChars.length; j++) {
                concat = concat + mapChars[j] + letterCombinations(String.valueOf(nums.charAt(i+1)), answer, map);
                answer.add();
            }
        }
    }
}
