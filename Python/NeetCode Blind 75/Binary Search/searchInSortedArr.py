# You are given an array of length n which was originally sorted in ascending order. 
# It has now been rotated between 1 and n times. 
# For example, the array nums = [1,2,3,4,5,6] might become:
# [3,4,5,6,1,2] if it was rotated 4 times.
# [1,2,3,4,5,6] if it was rotated 6 times.

# Given the rotated sorted array nums and an integer target, 
# return the index of target within nums, or -1 if it is not present.

# You may assume all elements in the sorted rotated array nums are unique,

# A solution that runs in O(n) time is trivial, can you write an algorithm that runs in O(log n) time?

# Example 1:
# Input: nums = [3,4,5,6,1,2], target = 1
# Output: 4

# Example 2:
# Input: nums = [3,5,6,0,1,2], target = 4
# Output: -1


class Solution:
    def search(self, nums: list[int], target: int) -> int:
        L,R = 0,len(nums)-1

        while L <= R:
            mid = (L+R)//2
            if target == nums[mid]:
                return mid
            
            # Left sorted portion
            # We checking within the bounds of the leftmost
            if nums[L] <= nums[mid]:
                if target > nums[mid] or target < nums[L]:
                    # Since target is greater than mid or less than left meaning
                    # it would be on the right side since its a rotated arr
                    # So we search the right side
                    L = mid + 1
                else:
                    R = mid - 1
            
            # Right sorted portion
            else:
                if target < nums[mid] or target > nums[R]:
                    R = mid - 1
                else:
                    L = mid + 1                

        return -1

def main():
    # nums = [3,4,5,6,1,2]
    # target = 1
    # nums = [3,5,6,0,1,2]
    # target = 4
    nums = [4,5,6,7,0,1,2]
    target = 0
    solution = Solution()
    solution.search(nums,target)

main()