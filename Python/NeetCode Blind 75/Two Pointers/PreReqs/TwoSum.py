# Given an array of integers nums and an integer target, 
# return indices of the two numbers such that they add up to target.

# You may assume that each input would have exactly one solution, and you may not use the same element twice.

# You can return the answer in any order.

 
# Example 1:
# Input: nums = [2,7,11,15], target = 9
# Output: [0,1]
# Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].

# Example 2:
# Input: nums = [3,2,4], target = 6
# Output: [1,2]

# Example 3:
# Input: nums = [3,3], target = 6
# Output: [0,1]

class Solution:
    def twoSum(self, nums: list[int], target: int) -> list[int]:
        res = []
        # L and R pointers
        # Start L at first index and R at len{nums} - 1
        # decrement R and compare to L if none match target continue
        # when we continue we increment L and then start R again from beginning
        L = 0
        R = len(nums) - 1
        while L < len(nums) - 1:
            if(R == L):
                L += 1
                R = len(nums) - 1
            if(nums[L] + nums[R] == target):
                res.append(L)
                res.append(R)
                return res
            if(R > L):
                R -= 1

        return res

    def run(self):
        # nums = [2,7,11,15]
        # target = 9
        # nums = [3,2,4]
        # target = 6
        nums = [3,3]
        target = 6
        print(self.twoSum(nums,target))


def main():
    solution = Solution()
    solution.run()

main()