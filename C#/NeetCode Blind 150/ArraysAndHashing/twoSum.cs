// Given an array of integers nums and an integer target, 
// return the indices i and j such that nums[i] + nums[j] == target and i != j.

// You may assume that every input has exactly one pair of indices i and j that satisfy the condition.

// Return the answer with the smaller index first.

// Example 1:
// Input: 
// nums = [3,4,5,6], target = 7
// Output: [0,1]
// Explanation: nums[0] + nums[1] == 7, so we return [0, 1].

// Example 2:
// Input: nums = [4,5,6], target = 10
// Output: [0,2]

// Example 3:
// Input: nums = [5,5], target = 10
// Output: [0,1]


public class Solution {
    public int[] TwoSum(int[] nums, int target) {
        // Use a Dict to store the values as we loop
        Dictionary<int,int> set = new Dictionary<int,int>();

        // Loop and subtract the target - num see if contains in Dictionary
        // If it does we add it to res
        for(int i=0; i<nums.Length; i++) {
            int diff = target - nums[i];
            // If target - num == dict.contains() add to res
            if(set.ContainsKey(target-nums[i])) {
                return new int[]{set[diff], i};
            }
            // Else we add to dictionary
            set[nums[i]] = i;
        }

        return new int[]{};
    }
}
