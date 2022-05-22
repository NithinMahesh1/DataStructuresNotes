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
        String concat = "";

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
            // Use helper method
            // loop through first index
            // concat first char with each char in the helper method and add to result
            for(int i=0; i<digits.length(); i++) {
                char[] elems = map.get(String.valueOf(digits.charAt(i)));
                if(i+1 == digits.length()) {
                    break;
                }
                char[] nextElems = map.get(String.valueOf(digits.charAt(i+1)));
                for(int j=0; j<elems.length; j++) {
                    letterHelper(elems[j], nextElems, result);
                }
            }
        }

        System.out.print(result);
        return result;
    }

    public static List<String> letterHelper(char current, char[] elems, List<String> result) {
        String concat = "";

        for(int i=0; i<elems.length; i++) {
            concat = concat + current + elems[i];
            result.add(concat);
            concat = "";
        }

        return result;
    }

}
