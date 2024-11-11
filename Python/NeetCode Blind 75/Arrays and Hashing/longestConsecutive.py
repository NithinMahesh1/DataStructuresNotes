# Given an array of integers nums, return the length of the longest consecutive sequence of elements.

# A consecutive sequence is a sequence of elements in which each element is exactly 1 greater than the previous element.

# You must write an algorithm that runs in O(n) time.


# Example 1:
# Input: nums = [2,20,4,10,3,4,5]
# Output: 4
# Explanation: The longest consecutive sequence is [2, 3, 4, 5].

# Example 2:
# Input: nums = [0,3,2,5,4,6,1,1]
# Output: 7



# https://www.youtube.com/watch?v=P6RZZMu_maU&ab_channel=NeetCode
class Solution:
    def longestConsecutive(self, nums: list[int]) -> int:
        # Basically we need to sort them in order
        # Remove duplicates
        # Then as we loop the arr we will check the curr and last val is diff by 1

        # [2,20,4,10,3,4,5]
        # [2,3,4,5] -> 4
        
        # nums=[9,1,4,7,3,-1,0,5,8,-1,6]
        # {0, 1, 3, 4, 5, 6, 7, 8, 9, -1}
        numSet = set(nums)
        longest = 0
        for n in nums:
            if(n-1) not in numSet:
                length = 0
                while(n+length) in numSet:
                    length += 1
                longest = max(length,longest)
        return longest
        
    def run(self):
        # nums = [2,20,4,10,3,4,5]
        # nums = [0,3,2,5,4,6,1,1]
        # nums = [0,-1]
        # nums = [-2,-1]
        # nums = [2,1]
        nums=[9,1,4,7,3,-1,0,5,8,-1,6]
        print(self.longestConsecutive(nums))

def main():
    solution = Solution()
    solution.run()

main()