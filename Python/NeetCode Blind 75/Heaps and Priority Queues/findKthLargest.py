# Given an unsorted array of integers nums and an integer k, return the kth largest element in the array.

# By kth largest element, we mean the kth largest element in the sorted order, not the kth distinct element.

# Follow-up: Can you solve it without sorting?

# Example 1:
# Input: nums = [2,3,1,5,4], k = 2
# Output: 4

# Example 2:
# Input: nums = [2,3,1,1,5,5,4], k = 3
# Output: 4

import heapq
class Solution:
    def findKthLargest(self, nums: list[int], k: int) -> int:
        nums = [-n for n in nums]
        heapq.heapify(nums)

        # Loop the nums list and add a tuple of the

        print(nums[k])
        return 

def main():
    nums = [2,3,1,5,4]
    k = 2
    solution = Solution()
    solution.findKthLargest(nums,k)

main()