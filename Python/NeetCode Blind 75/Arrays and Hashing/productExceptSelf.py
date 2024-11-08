# Given an integer array nums, return an array output 
# where output[i] is the product of all the elements of nums except nums[i].

# Each product is guaranteed to fit in a 32-bit integer.

# Follow-up: Could you solve it in O(n)
# O(n) time without using the division operation?

# Example 1:
# Input: nums = [1,2,4,6]
# Output: [48,24,12,8]

# Example 2:
# Input: nums = [-1,0,1,2,3]
# Output: [0,-6,0,0,0]


# Explanation:
# We need to multiply all the values in the arr except for curr index
# Append those values to a new arr
import numpy as np

class Solution:
    def productExceptSelf(self, nums: list[int]) -> list[int]:
        # https://www.youtube.com/watch?v=bNvIQI2wAjk&ab_channel=NeetCode
        # Use prefix and postfix
        # Basically we multiply all the values in prefix before each curr in a loop
        # Then we loop backwards and multiply all the values with prefix at that point in a loop
        # Then return the new res

        # Intialiaze an arr of each position of 1 and multiplied by the length
        res = [1] * len(nums)
        
        prefix = 1
        for i in range(len(nums)):
            res[i] = prefix
            prefix *= nums[i]
        
        postfix = 1
        for i in range(len(nums) -1, -1, -1):
            # The following is essentially using the same arr
            # That array already contains the prefix values
            # So we are multiplying the postfix points after curr val
            # by the prefix at that index in the array
            res[i] *= postfix
            # We have to continuously update the postfix
            # Multiply it by the current value in nums
            postfix *= nums[i]

        return res

    def run(self):
        nums = [1,2,4,6]
        # nums=[-1,0,1,2,3]
        print(self.productExceptSelf(nums))


def main():
    solution = Solution()
    solution.run()

main()