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


class Solution:
    def threeSum(self, nums: list[int]) -> list[list[int]]:
        res = []

        if(len(nums) == 3):
            if(nums[0] + nums[1] + nums[2] == 0):
                res = list[nums]
                return res

        # First we sort the arr
        nums = nums.sort()


        # Then we take the first index and iterate L and R on the end indices
        # As we loop them we add the value of L + R + Curr
        for i in range(len(nums)):
            currList = []

            curr = nums[i]
            L = nums[curr+1] # come back to this and make sure we don't index past this
            R = nums[len(nums) - 1]
            # If the diff isn't 0 then we increment or decrement the L or R depending on the one with
            # a bigger diff value >=
            if(L + R + curr == 0):
                currList.append(L), currList.append(R), currList.append(curr)
                res.append(currList)
                continue
            diffL = curr - L
            diffR = curr - R
            if(diffR >= diffL):
                R -= 1
            if(diffL >= diffR):
                L -= 1
            if(curr <= len(nums) - 1 and L <= len(nums) - 1 and R <= len(nums)):
                curr += 1
                L += 1
                R += 1
            
        
        # If we get a match we add to a list of list
        
        
        
        # Continue looping and use the next index for Curr
        

        return []

    def run(self):
        nums = [-1,0,1,2,-1,-4]
        self.threeSum(nums)

def main():
    solution = Solution()
    solution.run()

main()