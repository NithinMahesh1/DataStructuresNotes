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
        Dictionary<int,List<int>> courseMap = new Dictionary<int, List<int>>();
        HashSet<int> visited = new HashSet<int>();

        // First we will need to loop through and add all courses to Dict
        foreach(int[] courses in prerequisites) {
            if(!courseMap.ContainsKey(courses[0])) {
                courseMap[courses[0]] = new List<int>();
            }
            List<int> templist = courseMap[courses[0]];
            courseMap[courses[0]] = templist;
        }

        // Loop the courseMap and use dfs to check the prereq courses
        // We need to check which ones have prereqs valid before as well
        for(int c=0; c<courseMap.Count; c++) {
            if(courseMap[c] == ) {

            }

        }

        return false;
    }
}