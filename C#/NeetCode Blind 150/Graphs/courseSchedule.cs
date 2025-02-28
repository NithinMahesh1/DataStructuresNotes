// You are given an array prerequisites where prerequisites[i] = [a, b] 
// indicates that you must take course b first if you want to take course a.

// The pair [0, 1], indicates that must take course 1 before taking course 0.

// There are a total of numCourses courses you are required to take, labeled from 0 to numCourses - 1.

// // Return true if it is possible to finish all courses, otherwise return false.

// Example 1:
// Input: numCourses = 2, prerequisites = [[0,1]]
// Output: true
// Explanation: First take course 1 (no prerequisites) and then take course 0.

// Example 2:
// Input: numCourses = 2, prerequisites = [[0,1],[1,0]]
// Output: false
// Explanation: In order to take course 1 you must take course 0, and to take course 0 you must take course 1. So it is impossible.

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
        Dictionary<int,List<int>> courseDict = new Dictionary<int, List<int>>();
        // Track fully processed nodes using visited
        HashSet<int> visited = new HashSet<int>();
        // We also need to declare a path var to keep track of recursive stack visits
        HashSet<int> path = new HashSet<int>();

        for(int i=0; i<numCourses; i++) {
            // Initialize empty lists since some courses will not have prereqs
            // but we need to keep track of empty ones for proper lookups
            courseDict[i] = new List<int>();
        }

        // Now we populate the dictionary with the actual courses
        // and their associated prereqs
        foreach(int[] courses in prerequisites) {
            int course = courses[0];
            int prereq = courses[1];
            courseDict[prereq].Add(course);
        }

        for(int c=0; c<numCourses; c++) {
            if(!dfs(c,visited,path,courseDict)) {
                return false;
            }
        }

        return true;
    }
    private bool dfs(int course, HashSet<int> visited, HashSet<int> path,Dictionary<int,List<int>> courses) {
        // Means we have detected a cycle course in the recursive stack
        if(path.Contains(course)) {
            return false;
        }

        // This means we have a valid course that was previously checked
        if(visited.Contains(course)) {
            return true;
        }

        // Mark our current visited course in the recusive stack as visited
        path.Add(course);

        // Iterate the course prereqs
        foreach(int prereq in courses[course]) {
            if(!dfs(prereq,visited,path,courses)){
                return false;
            }
        }

        // If we get here it means that we need to remove from the path
        // We also need to mark this course as a fully visited path by adding it to visit set
        path.Remove(course);
        visited.Add(course);

        return true;
    }
}