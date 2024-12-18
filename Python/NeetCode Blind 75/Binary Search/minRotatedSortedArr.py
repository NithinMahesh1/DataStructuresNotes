# You are given an array of length n which was originally sorted in ascending order. 
# It has now been rotated between 1 and n times. 
# For example, the array nums = [1,2,3,4,5,6] might become:

# [3,4,5,6,1,2] if it was rotated 4 times.
# [1,2,3,4,5,6] if it was rotated 6 times.

# Notice that rotating the array 4 times moves the last four elements of the array to the beginning. 
# Rotating the array 6 times produces the original array.

# Assuming all elements in the rotated sorted array nums are unique, return the minimum element of this array.

# A solution that runs in O(n) time is trivial, can you write an algorithm that runs in O(log n) time?


# Example 1:
# Input: nums = [3,4,5,6,1,2]
# Output: 1

# Example 2:
# Input: nums = [4,5,0,1,2,3]
# Output: 0

# Example 3:
# Input: nums = [4,5,6,7]
# Output: 4

# Prereq:
# Take a look at binary search
# Use sorted arr as such 1,2,3,4,5
# Set n=2
# Code up binary search and refresh memory before attempting this

class Solution:
    def findMin(self, nums: list[int]) -> int:
        # nums = [3,4,5,6,1,2]
        res = nums[0]
        l, r = 0, len(nums) - 1

        while l <= r:
            # This is because we are checking if left is greater than right
            # If left is less than we take res check for that min
            if nums[l] < nums[r]:
                res = min(res, nums[l])
                break
            
            m = (l + r) // 2
            res = min(res, nums[m])
            # Using our pointer mid if mid is greater or equal
            # then we need to search right side since smaller
            if nums[m] >= nums[l]:
                l = m + 1
            else:
                r = m - 1

        print(res)
        return res
        
def main():
    solution = Solution()
    nums = [3,4,5,6,1,2]
    # nums = [4,5,0,1,2,3]
    # nums = [4,5,6,7]
    # nums = [1,2]
    # nums = [1]
    # nums = [2,3,1]
    solution.findMin(nums)

main()