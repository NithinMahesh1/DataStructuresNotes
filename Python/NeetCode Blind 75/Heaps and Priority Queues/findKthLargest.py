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

        # Loop the nums list and pop until we get to the kth-1 val
        for i in range(k-1):
            heapq.heappop(nums)

        # Finally pop the kth largest value and negate the "-" we added
        largest = heapq.heappop(nums)
        print(-largest)
        return -largest

def main():
    nums = [2,3,1,5,4]
    k = 2
    solution = Solution()
    solution.findKthLargest(nums,k)

main()