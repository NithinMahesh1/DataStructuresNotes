# Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] 
# where nums[i] + nums[j] + nums[k] == 0, and the indices i, j and k are all distinct.

# The output should not contain any duplicate triplets. 
# You may return the output and the triplets in any order.

# Example 1:
# Input: nums = [-1,0,1,2,-1,-4]
# Output: [[-1,-1,2],[-1,0,1]]
# Explanation:
# nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
# nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
# nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
# The distinct triplets are [-1,0,1] and [-1,-1,2].

# Example 2:
# Input: nums = [0,1,1]
# Output: []
# Explanation: The only possible triplet does not sum up to 0.

# Example 3:
# Input: nums = [0,0,0]
# Output: [[0,0,0]]
# Explanation: The only possible triplet sums up to 0.

# https://www.youtube.com/watch?v=jzZsG8n2R9A&ab_channel=NeetCode
class Solution:
    def threeSum(self, nums: list[int]) -> list[list[int]]:
        res = []
        # Sort arr
        nums = sorted(nums)
            
        # We have an anchor point left most val to begin with
        # We get the sum of anchor + L + R
        # If the value is greater than 0 we decrement R and if less than 0 increment L

        for i, anchor in enumerate(nums):
            if i > 0 and anchor == nums[i-1]:
                # We are doing this to ensure we don't add the same val if there are duplicates
                continue

            L,R = i+1,len(nums) -1
            while L < R:
                sum = anchor + nums[L] + nums[R]
                if sum >0:
                    R -= 1
                elif sum < 0:
                    L += 1
                else:
                    res.append([anchor,nums[L],nums[R]])
                    # Now we deal with the case of incrementing the pointers
                    # basically increment on L pointer since they will be handled above
                    # since there each value only has one corresponding sum to 0 so only need
                    # to increment one of them
                    L += 1
                    while nums[L] == nums[L - 1] and L < R:
                        # keep shifting pointer skipping duplicates
                        # also l < r always so its not past the len
                        L += 1

        return res

    def run(self):
        nums = [-1,0,1,2,-1,-4]
        # nums=[0,1,1]
        # nums=[0,0,0]
        # nums=[0,0,0,0]
        print(self.threeSum(nums))

def main():
    solution = Solution()
    solution.run()

main()