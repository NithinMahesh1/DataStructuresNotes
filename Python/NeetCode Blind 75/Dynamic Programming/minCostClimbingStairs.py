# You are given an array of integers cost where cost[i] is the cost of taking a step from the ith floor of a staircase. 
# After paying the cost, you can step to either the (i + 1)th floor or the (i + 2)th floor.

# You may choose to start at the index 0 or the index 1 floor.

# Return the minimum cost to reach the top of the staircase, i.e. just past the last index in cost.


# Example 1:
# Input: cost = [1,2,3]
# Output: 2
# Explanation: We can start at index = 1 and pay the cost of cost[1] = 2 and take two steps to reach the top. 
# The total cost is 2.

# Example 2:
# Input: cost = [1,2,1,2,1,1,1]
# Output: 4
# Explanation: Start at index = 0.
# * Pay the cost of cost[0] = 1 and take two steps to reach index = 2.
# * Pay the cost of cost[2] = 1 and take two steps to reach index = 4.
# * Pay the cost of cost[4] = 1 and take two steps to reach index = 6.
# * Pay the cost of cost[6] = 1 and take one step to reach the top.
# * The total cost is 4.

class Solution:
    def minCostClimbingStairs(self, cost: list[int]) -> int:
        # Very similar to climbing stairs
        # Basically instead we are getting the min of the ith value +1 or plus the other positions cost +2
        # So we implement it similarly

        # We want to append a 0 since there is at the end of the cost arr we can step one more
        # [1,2,3] 0
        cost.append(0)

        # We only need to loop from the 2 in the above logic
        # So we are doing -3 to start there since 3+0 will be 3 still
        for i in range(len(cost) - 3, -1,-1):
            cost[i] = min(cost[i] + cost[i+1], cost[i] + cost[i+2])

        # Need to get 
        return min(cost[0],cost[1])
    

def main():
    cost = [1,2,3]
    solution = Solution()
    solution.minCostClimbingStairs(cost)

main()