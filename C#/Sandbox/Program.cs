// You are given an array prerequisites where prerequisites[i] = [a, b] 
// indicates that you must take course b first if you want to take course a.

// The pair [0, 1], indicates that must take course 1 before taking course 0.

// There are a total of numCourses courses you are required to take, labeled from 0 to numCourses - 1.

// Return true if it is possible to finish all courses, otherwise return false.

public class Solution {
    public static void Main(string[] args) {
        int numCourses = 2;
        int[][] prerequisites = new int[][]{
            new int[]{0,1}
        };

        Solution obj = new Solution();
        obj.CanFinish(numCourses,prerequisites);
    }
    public bool CanFinish(int numCourses, int[][] prerequisites) {
        // Need to detect if it is a cycle and if it is return false
        // e.g. 0,1 and 1,0 is 0 -> 1 -> 0
        // Use a hash map to add the courses as key and the vals as preqs
        // Iterate through and use a visit hashset - this will show us if our graph returns to a visited cell
        // means we return false and a cycle was detected

        Dictionary<int,List<int>> courseMap = new Dictionary<int, List<int>>();
        HashSet<int> visited = new HashSet<int>();

        // Loop through and populate courseMap
        foreach(int[] course in prerequisites) {
            if(!courseMap.ContainsKey(course[0])) {
                courseMap[course[1]] = new List<int>{ course[0] };
            }
            else {
                List<int> templist = courseMap[course[1]];
                templist.Add(course[0]);
            }
        }

        // Utilize dfs to traverse using the courseMap
        dfs(courseMap,visited);
        return false;
    }
    private bool dfs(Dictionary<int,List<int>> courseMap, HashSet<int> visited) {
        // Need to loop dictionary keys then dfs their values
        // If we get back to one that is visited we return false
        for(int i=0; i<courseMap.Keys.Count; i++) {
            if(visited.Contains(i)) {
                return false;
            }
            dfs();

            visited.Add(courseMap.FirstOrDefault(cs => cs.Value == courseMap[i]).Key);
        }

        return true;
    }
}