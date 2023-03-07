package com.practice;

public class Main {
    /**
     * Write a function to find the longest common prefix string amongst an array of strings.
     * If there is no common prefix, return an empty string "".
     *
     * Input: strs = ["flower","flow","flight"]
     * Output: "fl"
     *
     * Input: strs = ["dog","racecar","car"]
     * Output: ""
     * Explanation: There is no common prefix among the input strings.
     *
     * */

    public static String longestCommonPrefix(String[] strs) {
        // loop each word in array
        // loop each word to compare using indexOf and see if they have matches
        // If they don't then we are checking it is not equal to 0 since indexOf returns -1 if not matched
        // We then remove one char each time in the while until we are left with what matches

        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                System.out.println(strs[i].indexOf(prefix));
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }

    public static void main(String[] args) {
        String[] strs = new String[]{"flower","flow","flight"};
//        String[] strs = new String[]{"dog","racecar","car"};

        System.out.println(longestCommonPrefix(strs));
    }
}
