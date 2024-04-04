# You are given an integer array height of length n. 
# There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
# Find two lines that together with the x-axis form a container, such that the container contains the most water.
# Return the maximum amount of water a container can store.
# Notice that you may not slant the container.

# Example 1:
# Input: height = [1,8,6,2,5,4,8,3,7]
# Output: 49
# Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. 
# In this case, the max area of water (blue section) the container can contain is 49.

# Example 2:
# Input: height = [1,1]
# Output: 1

# Constraints:
# n == height.length
# 2 <= n <= 105
# 0 <= height[i] <= 104

height = [1,8,6,2,5,4,8,3,7]
# height = [1,1]
# height = [1,0,0,0,2]
# height = [0,1]

# Best way to solve this is to take take the left most value and right most value
# Check the max area with the newly calculated area
# If not the max we shift the left or right pointer depending on which is the min
# Continue calculating until we find our max

left = 0
right = len(height) - 1
maxArea = 0

while left < right:
    #calculate area
    area = min(height[left],height[right]) * (right - left)
    maxArea = max(maxArea,area)
    
    if(height[left] < height[right]):
        left+=1
    else:
        right-=1


print(maxArea)