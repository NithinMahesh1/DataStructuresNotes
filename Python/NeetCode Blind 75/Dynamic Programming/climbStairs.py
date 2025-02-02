# You are given an integer n representing the number of steps to reach the top of a staircase. 
# You can climb with either 1 or 2 steps at a time.

# Return the number of distinct ways to climb to the top of the staircase.

# Example 1:
# Input: n = 2
# Output: 2
# Explanation:
# 1 + 1 = 2
# 2 = 2

# Example 2:
# Input: n = 3
# Output: 3
# Explanation:
# 1 + 1 + 1 = 3
# 1 + 2 = 3
# 2 + 1 = 3

class Solution:
    def climbStairs(self, n: int) -> int:
        # Basically we use a decision tree
        # We recusively would traverse either left +1 or right +2
        # Since we will end up having the same values at multiple points 
        # we can use DP to store the number of options before hand
        # this keeps our algorithm to O(n) which is better than traversing a tree at O(2^n)

        # This is the base case using bottom up approach
        # At any point if n=5 for example we it would be 1 step for each 5 and 4
        # Even if we had n=100, both 100 and 99 would be 1 and 1
        one, two = 1,1

        # Since we computed the last two values we do n-1 to calculate the last 4 (in n=5)
        for i in range(n-1):
            temp = one
            one = one + two
            two = temp
        
        return one 

def main():
    solution = Solution()
    n = 2
    solution.climbStairs(n)

main()