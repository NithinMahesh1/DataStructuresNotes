// You are given two words, beginWord and endWord, and also a list of words wordList. 
// All of the given words are of the same length, consisting of lowercase English letters, 
// and are all distinct.

// Your goal is to transform beginWord into endWord by following the rules:
// * You may transform beginWord to any word within wordList, 
//   provided that at exactly one position the words have a different character, 
//   and the rest of the positions have the same characters.
// * You may repeat the previous step with the new word that you obtain, 
//   and you may do this as many times as needed.

// Return the minimum number of words within the transformation sequence needed to obtain the endWord, or 0 if no such sequence exists.

// Example 1:
// Input: beginWord = "cat", endWord = "sag", wordList = ["bat","bag","sag","dag","dot"]
// Output: 4
// Explanation: The transformation sequence is "cat" -> "bat" -> "bag" -> "sag".

// Example 2:
// Input: beginWord = "cat", endWord = "sag", wordList = ["bat","bag","sat","dag","dot"]
// Output: 0
// Explanation: There is no possible transformation sequence from "cat" to "sag" since the word "sag" is not 
// in the wordList.

public class Solution {
    public int count = 0;
    public static void Main(string[] args) {
        string beginWord = "cat";
        string endWord = "sag";
        string[] wordList = new string[]{"bat","bag","sag","dag","dot"};

        Solution obj = new Solution();
        obj.LadderLength(beginWord,endWord,wordList);
    }

    public int LadderLength(string beginWord, string endWord, IList<string> wordList) {
        // Need to map each char in the wordlist to a dict of edges that are undirected
        // We then need visited and res hashsets to keep track of the nodes
        // Visited will ensure we don't backtrack and res will allow us to check if we have all the chars we need
        // Also we will need to loop in this method (not the dfs method) and increment a count each time we get a character
        Dictionary<char,List<char>> edgeDict = new Dictionary<char,List<char>>();
        HashSet<char> visited = new HashSet<char>();
        HashSet<char> res = new HashSet<char>();
        int wordLength = wordList[0].Length;

        // Initialize from each word the chars based on their length
        // create an empty list to initialize them which we will populate after
        for(int s=0; s<wordList.Count; s++) {
            string word = wordList[s];
            for(int i=0;i<wordLength;i++) {
                char c = word[i];
                edgeDict[c] = new List<char>();
            }
        }

        foreach(string word in wordList) {
            // Loop through each word and all the chars
            for(int i=0; i<wordLength; i++) {
                char c1 = word[i];
                if(i+1 <= wordLength) {
                    char c2 = word[i+1];
                    edgeDict[c1].Add(c2);
                    edgeDict[c2].Add(c1);
                }
            }
        }
        

        return -1;
    }
}