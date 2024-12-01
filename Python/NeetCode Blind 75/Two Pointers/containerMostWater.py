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

        L = 0
        R = len(heights) - 1
        res = 0
        # Stop looping when L = R - 1
        while L < R:
            # In order to get the difference take the R index and subtract from L index
            distance = R - L
            # Take the smaller val in heights between L and R then multiply by distance
            mult = 0
            if(heights[L] < heights[R]):
                mult = heights[L] * distance
                L += 1
            else:
                mult = heights[R] * distance
                R -= 1
            # Set the res to max of the res and the mult val
            res = max(res,mult)

        print(res)
        return res
        
        

def main():
    solution = Solution()
    # height = [1,7,2,5,4,7,3,6]
    height = [2,2,2]
    solution.maxArea(height)

main()