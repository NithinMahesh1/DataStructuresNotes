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
        if(len(nums) == 1):
            return nums[0]
        if(len(nums) == 2):
            return min(nums[0], nums[1])

        # Since we know it has to be done in Olog(n)
        # we will end up using binary search to sort it

        # 3 4 5 1 2
        # L   M   R
        # nums[M] (index 2) >= nums[L] -> Set L to M and search right side
        # 3 4 5 1 2
        #     L M R 
        # (L + R)/2 (or basically (2+4)/2) = 3 -> so nums[M] (nums[3]) <= nums[L] (nums[2])
        # Since 1 <= 5 -> L = M
        # 3 4 5 1 2
        #       L R
        # If left is R - 1 then we return our min compare to current L
        L,R = 0,len(nums)-1
        res = nums[L]
        while L < R:
            if(L == R-1):
                res = min(res,nums[M])
                break
            M = int((L+R)/2)
            if(nums[M] >= nums[L]):
                res = min(res,nums[M])
                L = M
                continue
            if(nums[M] <= nums[L]):
                res = min(res,nums[M])
                R = M
                continue

        
        print(res)
        return res
        
def main():
    solution = Solution()
    # nums = [3,4,5,6,1,2]
    # nums = [4,5,0,1,2,3]
    # nums = [4,5,6,7]
    # nums = [1,2]
    # nums = [1]
    nums = [2,3,1]
    solution.findMin(nums)

main()