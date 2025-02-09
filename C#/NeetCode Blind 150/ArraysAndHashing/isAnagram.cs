// Given two strings s and t, 
// return true if the two strings are anagrams of each other, otherwise return false.

// An anagram is a string that contains the exact same characters as another string, 
// but the order of the characters can be different.

// Example 1:
// Input: s = "racecar", t = "carrace"
// Output: true

// Example 2:
// Input: s = "jar", t = "jam"
// Output: false


public class Solution {
    public bool IsAnagram(string s, string t) {
        // Simply sort the two strings
        // Compare both if they are the same return

        char[] sArr = s.ToCharArray();
        char[] tArr = t.ToCharArray();

        Array.Sort(sArr);
        Array.Sort(tArr);

        return sArr.SequenceEqual(tArr);
    }
}
