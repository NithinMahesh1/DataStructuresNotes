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
            new int[]{1,2},
            new int[]{2,0},
        };

        Solution obj = new Solution();
        obj.FindOrder(numCourses,prerequisites);
    }
    public int[] FindOrder(int numCourses, int[][] prerequisites) {
        Dictionary<int,List<int>> courseDict = new Dictionary<int, List<int>>();
        HashSet<int> visited = new HashSet<int>();
        HashSet<int> path = new HashSet<int>();
        int[] res = new int[]{};

        // Populate the dictionary with lists and empty lists
        for(int n=0; n<numCourses; n++) {
            courseDict[n] = new List<int>();
        }

        // Populate with keys as prereqs and courses as values
        foreach(int[] courses in prerequisites) {
            int course = courses[0];
            int prereqs = courses[1];
            courseDict[course].Add(prereqs);
        }

        for(int c=0; c<numCourses; c++) {
            res = dfs(c,courseDict,visited,path,res);
            if(res.Length == 0) {
                return res;
            }
        }

        return res;
    }
    private int[] dfs(int currCourse, Dictionary<int,List<int>> courseDict, HashSet<int> visited, HashSet<int> path, int[] res) {
        // If the course prereqs are empty then we append to res and continue
        // also if we see a visited value
        // also remove node from visited
        // and clear path
        if(courseDict[currCourse].Count == 0 || visited.Contains(currCourse)) {
            res = res.Append(currCourse).ToArray();
            visited.Remove(currCourse);
            path = new HashSet<int>{};
            // Loop the prereqs
            foreach(int prereq in courseDict[currCourse]) {
                dfs(prereq,courseDict,visited,path,res);
            }
        }

        // If we get to a path we have seen then we return []
        if(path.Contains(currCourse)) {
            return new int[]{};
        }

        // Otherwise we return res
        return res;
    }
}