// You are given an array prerequisites where prerequisites[i] = [a, b] indicates that you must take course b first if you want to take course a.

// For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
// There are a total of numCourses courses you are required to take, labeled from 0 to numCourses - 1.

// Return a valid ordering of courses you can take to finish all courses. If there are many valid answers, return any of them. 
// If it's not possible to finish all courses, return an empty array.

// Example 1:
// Input: numCourses = 3, prerequisites = [[1,0]]
// Output: [0,1,2]
// Explanation: We must ensure that course 0 is taken before course 1.

// Example 2:
// Input: numCourses = 3, prerequisites = [[0,1],[1,2],[2,0]]
// Output: []
// Explanation: It's impossible to finish all courses.

public class Solution {
    public static void Main(string[] args) {
        int numCourses = 3;
        int[][] prerequisites = new int[][]{
            new int[]{0,1},
        };

        Solution obj = new Solution();
        obj.FindOrder(numCourses,prerequisites);
    }
    public int[] FindOrder(int numCourses, int[][] prerequisites) {
        Dictionary<int,List<int>> courseDict = new Dictionary<int, List<int>>();
        HashSet<int> visited = new HashSet<int>();
        HashSet<int> path = new HashSet<int>();
        List<int> res = new List<int>{};

        // Populate the dictionary with lists and empty lists
        for(int n=0; n<numCourses; n++) {
            courseDict[n] = new List<int>();
        }

        // Actually populate dictionary with courses and prereqs
        foreach(int[] courses in prerequisites) {
            int course = courses[0];
            int prereqs = courses[1];
            courseDict[course].Add(prereqs);
        }

        for(int c=0; c<numCourses; c++) {
            if(!dfs(c,courseDict,visited,path,res)) {
                return new int[]{};
            }
        }

        return res.ToArray();
    }
    private bool dfs(int currCourse, Dictionary<int,List<int>> courseDict, HashSet<int> visited, HashSet<int> path, List<int> res) {
        // If path contains a course then return false
        if(path.Contains(currCourse)) {
            return false;
        }

        // If visited contains a course it means we need to loop those courses
        // so we return true
        if(visited.Contains(currCourse)) {
            return true;
        }

        // We need to add it to the path since we just handled it
        path.Add(currCourse);

        // Check if there even are any courses to loop:
        if(courseDict.ContainsKey(currCourse)){
            // Handle looping it's prereqs
            // If we run into one that if false we return false
            foreach(int prereq in courseDict[currCourse]) {
                if(!dfs(prereq,courseDict,visited,path,res)) {
                    return false;
                }
            }
        }

        // Need to remove from the path after we process
        path.Remove(currCourse);
        // Add to visited
        visited.Add(currCourse);
        // Also add to our output
        res.Add(currCourse);

        return true;
    }
}