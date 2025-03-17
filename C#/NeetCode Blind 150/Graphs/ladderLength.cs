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

using System.ComponentModel;

public class Solution {
    // Useful youtube video of explanation:
    // https://www.youtube.com/watch?v=JOOu3W37QJg&ab_channel=CrackingFAANG    
    public static void Main(string[] args) {
        string beginWord = "cat";
        string endWord = "sag";
        string[] wordList = new string[]{"bat","bag","sag","dag","dot"};

        Solution obj = new Solution();
        obj.LadderLength(beginWord,endWord,wordList);
    }

    public int LadderLength(string beginWord, string endWord, IList<string> wordList) {
        // First we need to create adjacency lists with string of each word but with * replacing one character in the string
        // building this with key being the * string and values (list of them) being all the values that can match that
        // e.g. cat would have the following keys *at, c*t, and ca* and if we take *at for example it would have the vals: [bat]
        // Once we create this graph we will need to traverse it using bfs to find the shortest path
        // Since we want to find the shortest path we want to use BFS
        Dictionary<string,List<string>> graph = new Dictionary<string, List<string>>();

        if (!wordList.Contains(endWord)) {
            return 0;
        }

        // Add the begin word since wordlist doesn't contain it
        // and we account for that being the first count
        HashSet<string> wordSet = new HashSet<string>(wordList);
        wordList.Add(beginWord);

        foreach(string word in wordSet) {
            for(int c=0;c<word.Length;c++) {
                // first substring takes the values from 0 to c-1
                string pattern = word.Substring(0,c) + "*" + word.Substring(c+1);
                if(!graph.ContainsKey(pattern)) {
                    graph[pattern] = new List<string>();
                }
                graph[pattern].Add(word);
            }
        }

        HashSet<string> visited = new HashSet<string>();
        Queue<string> queue = new Queue<string>();
        queue.Enqueue(beginWord);
        visited.Add(beginWord);
        int res = 1;
        while(queue.Count != 0) {
            // since size is dynamically changed
            int size = queue.Count;

            for(int i=0;i<size;i++) {
                // Here we loop the words in queue
                // If we get the end word we return res
                string word = queue.Dequeue();
                if(word == endWord) {
                    return res; 
                }
                for(int j=0; j<word.Length; j++) {
                    // Now we loop the neighbors
                    string pattern = word.Substring(0,j) + "*" + word.Substring(j+1);
                    if(graph.ContainsKey(pattern)) {
                        foreach(string neighbor in graph[pattern]) {
                            if(!visited.Contains(neighbor)) {
                                visited.Add(neighbor);
                                queue.Enqueue(neighbor);
                            }
                        }
                    }
                }
            }
            res++;
        }


        return 0;
    }
}