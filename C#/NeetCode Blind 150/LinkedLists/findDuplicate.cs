// You are given an array of integers nums containing n + 1 integers. 
// Each integer in nums is in the range [1, n] inclusive.

// Every integer appears exactly once, except for one integer which appears two or more times. 
// Return the integer that appears more than once.

// Example 1:
// Input: nums = [1,2,3,2,2]
// Output: 2

// Example 2:
// Input: nums = [1,2,3,4,4]
// Output: 4

// Follow-up: Can you solve the problem without modifying the array nums and using 
// O(1) extra space?

public class Solution {
    public static void Main(string[] args) {
        int[] nums = new int[]{1,2,3,2,2};
        Solution solution = new Solution();
        solution.FindDuplicate(nums);
    }
    public int FindDuplicate(int[] nums) {
        // Use a hashset to store each val
        // While we loop if we see a val in the hashset
        // return that val
        HashSet<int> set = new HashSet<int>();

        foreach(int val in nums){
            if(set.Contains(val)) {
                Console.WriteLine(val);
                return val;
            }
            set.Add(val);
        }

        return 1;
    }
}
