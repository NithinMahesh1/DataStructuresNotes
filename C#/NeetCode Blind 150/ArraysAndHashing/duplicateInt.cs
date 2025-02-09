// Given an integer array nums, return true if any value appears more than once in the array, otherwise return false.

// Example 1
// Input: nums = [1, 2, 3, 3]
// Output: true

// Example 2
// Input: nums = [1, 2, 3, 4]
// Output: false

public class Solution {
    public bool hasDuplicate(int[] nums) {
        // Loop through and use a dict to check if value exists
        // if it does return true and if not return False at the end of method

        HashSet<int> set = new HashSet<int>();

        foreach(int num in nums) {
            if(set.Contains(num)) {
                return true;
            }
            set.Add(num);
        }

        return false;
    }
}
