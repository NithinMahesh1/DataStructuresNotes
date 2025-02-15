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
        // This is a graph problem since there are vertices going to edges
        // We need to use two arrays here one with incoming one outgoing
        // We count each list and increment a counter on either incoming or outgoing
        // At the end we need to figure out who the judge is based off of the following
        //  - They are n-1 count for incoming since n people trust the judge
        //  - There are 0 outgoing since the judge trusts no one else

        // Since it is 1 to n then we return 1 if only one person
        if(n == 1 && trust.Length == 0) {
            return 1;
        }

        int[] incoming = new int[n+1];
        int[] outgoing = new int[n+1];

        // Loop through trust and input incoming and outgoing values
        // Looping each list and then taking the people from the list t 
        foreach(var t in trust) {
            int a = t[0], b = t[1];
            // incrementing a count by index a or b
            incoming[b]++;
            outgoing[a]++;
        }

        for(int i=1; i<=n; i++) {
            if(incoming[i] == n-1 && outgoing[i] == 0) {
                return i;
            }
        }

        return -1;
    }
}