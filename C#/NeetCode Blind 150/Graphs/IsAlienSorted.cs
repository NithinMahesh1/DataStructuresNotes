// In an alien language, surprisingly, they also use English lowercase letters, 
// but possibly in a different order. 
// The order of the alphabet is some permutation of lowercase letters.

// Given a sequence of words written in the alien language, and the order of the alphabet, 
// return true if and only if the given words are sorted lexicographically in this 
// alien language.

// Example 1:
// Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
// Output: true
// Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.

// Example 2:
// Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
// Output: false
// Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.

// Example 3:
// Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
// Output: false
// Explanation: The first three characters "app" match, and the second string is shorter (in size.) 
// According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' 
// is defined as the blank character which is less than any other character (More info).

public class Solution {
    public bool IsAlienSorted(string[] words, string order) {
        // Basically two conditions:
        // One is that the if w1 is < w2 then true if > then false
        // The other is w1 letter must come before w2 first letter and so forth
        // We can use a hashset to loop the string order
        
        Dictionary<char,int> alienDict = new Dictionary<char,int>();
        int z = 0;
        foreach(char s in order) {
            alienDict[s] = z;
        }

        // Loop the list of words
        for(int i=0; i<words.Length; i++) {
            string w1 = words[i];
            string w2 = words[i+1];
            // Loop the chars in the words
            for(int j=0; j<w1.Length; j++) {
                if(j == w2.Length) {
                    // This is because it means 
                    // we are still looping in bounds of w1
                    return false;
                }
                if(alienDict[w1[j]] > alienDict[w2[j]]) {
                    return false;
                }
            }
        }

        return true;
    }
}