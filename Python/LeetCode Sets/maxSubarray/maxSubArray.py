# Given an integer array nums, find the subarray with the largest sum, and return its sum.

# Example 1:
# Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
# Output: 6
# Explanation: The subarray [4,-1,2,1] has the largest sum 6.

# Example 2:
# Input: nums = [1]
# Output: 1
# Explanation: The subarray [1] has the largest sum 1.

# Example 3:
# Input: nums = [5,4,-1,7,8]
# Output: 23
# Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.
 
# Constraints:
# 1 <= nums.length <= 105
# -104 <= nums[i] <= 104


# Follow up: If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.

nums = [5,4,-1,7,8]
maxSum = 0
currSum = nums[0]

if(len(nums) == 1):
    maxSum = nums[0]
if(len(nums) == 2):
    if(currSum < nums[1]):
        maxSum = nums[1]
    else:
        maxSum = currSum
else:
    for i in range(1,len(nums)):
        firstnum = nums[i]    
        currSum = currSum + firstnum

        if(currSum < 0):
            currSum = 0

        maxSum = max(maxSum, currSum)


print(maxSum)

# Another attempt with solution
# Cleaner solution and easy to read

# Keep adding values in subarrays
# However when we end up with a negative sum we don't account those values into our max sum
# Compare the current sum to the maxSum values
# Return the maxSum

# Check this solution for better understanding:
# https://www.youtube.com/watch?v=5WZl3MMT0Eg&ab_channel=NeetCode
# maxSum = nums[0] #Since the first value will never be empty in the input
# currSum = 0

# for num in nums:
#     # If we have negative curr sum then we skip those values
#     if(currSum < 0):
#         currSum = 0

#     currSum += num
#     maxSum = max(maxSum,currSum)


# print(maxSum)