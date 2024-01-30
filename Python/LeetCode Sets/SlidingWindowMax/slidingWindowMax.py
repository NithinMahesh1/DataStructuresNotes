# You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. 
# You can only see the k numbers in the window. 
# Each time the sliding window moves right by one position.

# Return the max sliding window.

 

# Example 1:

# Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
# Output: [3,3,5,5,6,7]
# Explanation: 
# Window position                Max
# ---------------               -----
# [1  3  -1] -3  5  3  6  7       3
#  1 [3  -1  -3] 5  3  6  7       3
#  1  3 [-1  -3  5] 3  6  7       5
#  1  3  -1 [-3  5  3] 6  7       5
#  1  3  -1  -3 [5  3  6] 7       6
#  1  3  -1  -3  5 [3  6  7]      7

# Example 2:
# Input: nums = [1], k = 1
# Output: [1]

# nums = [1,3,-1,-3,5,3,6,7]
# k = 3
# nums = [1]
# k = 1

# Push indices to window as we proceed to reach a count of k
# Once we reach len(window) == k then we get the max of window
# Append max to res then pop from window


# My solution which passes 37/51 tests and fails because of time limit exceeded

# window = []
# res = []

# if len(nums) == 1:
#     res = nums[0]

# else:
#     for i in range(0,len(nums)):
#         if len(window) < k:
#             window.append(nums[i])
#         elif len(window) == k:
#             maximum = max(window)
#             res.append(maximum)
#             window.pop(0)
#             window.append(nums[i])
#         if i+1 == len(nums):
#             maximum = max(window)
#             res.append(maximum)

        
# print(res)


nums = [1,3,-1,-3,5,3,6,7]
k = 3

from collections import deque

output = []
window = deque()
l = r = 0

# Answer here:
# https://www.youtube.com/watch?v=DfljaUwZsOk&ab_channel=NeetCode


while r < len(nums):
    # Pop smaller value from q
    # We always want the back of the queue to contain the largest element 
    # Compare the back of the queue with the next or curr num[r] val
    # Always append to deque until the right most val is greater than right position of deck
    # and keep in decreasing order  
    # Then we pop the right of deque and keep checking it is less than new val
    while window and nums[window[-1]] < nums[r]:
        print(nums[window[-1]])
        print(nums[r])
        window.pop()
    window.append(r)

    # remove left from window
    # If leftmost value of queue is out of bounds we have to pop left
    # Checking the length or index of the left is greater than the highest index in our window
    if l > window[0]:
        window.popleft()

    # left most value is appeneded to output
    # Also the greatest val since deque is in decreasing order
    if(r+1) >= k:
        output.append(nums[window[0]])
        l += 1
    r+=1

print(output)

