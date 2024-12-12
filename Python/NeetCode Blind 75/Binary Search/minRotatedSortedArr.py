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
        # Since we know it has to be done in Olog(n)
        # we will end up using binary search to sort it

        # Since there is a pivot point and it is partially sorted 
        # the logic will look something like this:
        # L = 0 and R = len(nums)-1
        # if (L+R)/2 >= nums[L] or if the mid is >= nums[L] we search right
        # this is because there is that pivot point and we want to search the section with
        # lower values
        # and that means:
        # else: search left

        # Sudo Code:
        # L, R = 0, len(nums)-1
        # mid = (l+R)/2
        # if(mid >= nums[L]):
        #   L = 



        
def main():
    solution = Solution()
    nums = [3,4,5,6,1,2]
    solution.findMin(nums)