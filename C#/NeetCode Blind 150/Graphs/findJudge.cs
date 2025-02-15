// In a town, there are n people labeled from 1 to n. 
// There is a rumor that one of these people is secretly the town judge.

// If the town judge exists, then:

// The town judge trusts nobody.
// * Everybody (except for the town judge) trusts the town judge.
// * There is exactly one person that satisfies properties 1 and 2.
// * You are given an array trust where trust[i] = [ai, bi] representing that the person labeled ai trusts the person labeled bi. 
// If a trust relationship does not exist in trust array, then such a trust relationship does not exist.

// Return the label of the town judge if the town judge exists and can be identified, 
// or return -1 otherwise.

// Example 1:
// Input: n = 2, trust = [[1,2]]
// Output: 2

// Example 2:
// Input: n = 3, trust = [[1,3],[2,3]]
// Output: 3

// Example 3:
// Input: n = 3, trust = [[1,3],[2,3],[3,1]]
// Output: -1

public class Solution {
    public int FindJudge(int n, int[][] trust) {
        // n is the number of people so 1 - n people
        // Trust shows us who trusts who
        // Have a possibleJudge being a var that shows up more than once (hashset)
        // We can loop through the int[][] and store to a dict the k : index and V : array
        // Compare the values of the first array to the second -> if there is a match then return -1
        HashSet<int> possibleJudge = new HashSet<int>();
        Dictionary<int,int[]> pairs = new Dictionary<int, int[]>();        

        for(int i=0; i<trust.Length; i++) {
            int[] people = trust[i];
            for(int j=0; j < people.Length; j++) {
                if(!possibleJudge.ContainsKey(people[j])) {
                    possibleJudge.Add(people[j]);
                }
                if(possibleJudge.ContainsKey(people[j])) {
                    
                }
            }
        } 
    }
}