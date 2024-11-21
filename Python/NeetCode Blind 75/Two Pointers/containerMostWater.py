# You are given an integer array heights where heights[i] represents the height of the ith bar.
# You may choose any two bars to form a container. Return the maximum amount of water a container can store.

# Example 1
# Input: height = [1,7,2,5,4,7,3,6]
# Output: 36

# Example 2
# Input: height = [2,2,2]
# Output: 4

class Solution:
    def maxArea(self, heights: list[int]) -> int:
        # Use a L and R pointer 
        # Use a counter i starting at 1 while we loop and subtract L index from last index (len(R) - 1)
        # We loop and multiply the greater of L and R by the counter val then use max() until we loop to end
        # Then we return the max var res
        
        counter = 1
        multiply = 0
        res = 0
        for i in range(len(heights)):
            L = i
            R = len(heights) - 1
            if(heights[L] < heights[R]):
                multiply = heights[L]
            if(heights[R] < heights[L]):
                multiply = heights[R]
            diff = multiply * ((R+1) - counter)
            res = max(res,diff)
            counter += 1
        
        return res

def main():
    solution = Solution()
    height = [1,7,2,5,4,7,3,6]
    # height = [2,2,2]
    solution.maxArea(height)

main()