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
class Solution:
    def productExceptSelf(self, nums: list[int]) -> list[int]:
        # Multiply all the vals
        # but somehow without division exclude the curr val
        
        return []

    def run(self):
        nums = [1,2,4,6]
        print(self.productExceptSelf(nums))


def main():
    solution = Solution()
    solution.run()

main()