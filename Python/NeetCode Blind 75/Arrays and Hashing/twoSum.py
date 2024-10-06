# Given an array of integers nums and an integer target, return the indices i and j 
# such that nums[i] + nums[j] == target and i != j.

# You may assume that every input has exactly one pair of indices i and j 
# that satisfy the condition.

# Return the answer with the smaller index first.

# Example 1:
# Input: 
# nums = [3,4,5,6], target = 7

# Output: [0,1]

# Explanation: nums[0] + nums[1] == 7, so we return [0, 1].

# Example 2:
# Input: nums = [4,5,6], target = 10

# Output: [0,2]

# Example 3:
# Input: nums = [5,5], target = 10

# Output: [0,1]

def twoSum(nums,target) -> list[int,int]:
    # Push to dictionary
    # subtract target from nums 
    # Check if in dictionary and return indices

    dict = {}

    for i in range(0, len(nums), +1):
        list = []
        diff = target - nums[i]
        if(len(dict) == 0):
            dict[i] = nums[i]
        
        if(diff in dict.values()):
            list.append(dict.get(diff, []))
            list.append(i)
            

def main():
    nums = [3,4,5,6]
    target = 7

    twoSum(nums,target)


main()
