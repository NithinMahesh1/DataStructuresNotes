# You are given an array of integers stones where stones[i] represents the weight of the ith stone.

# We want to run a simulation on the stones as follows:

# At each step we choose the two heaviest stones, with weight x and y and smash them togethers
# If x == y, both stones are destroyed
# If x < y, the stone of weight x is destroyed, and the stone of weight y has new weight y - x.
# Continue the simulation until there is no more than one stone remaining.

# Return the weight of the last remaining stone or return 0 if none remain.

# Example 1:
# Input: stones = [2,3,6,2,4]
# Output: 1
# Explanation:
# We smash 6 and 4 and are left with a 2, so the array becomes [2,3,2,2].
# We smash 3 and 2 and are left with a 1, so the array becomes [1,2,2].
# We smash 2 and 2, so the array becomes [1].

# Example 2:
# Input: stones = [1,2]
# Output: 1

import heapq
class Solution:
    def lastStoneWeight(self, stones: list[int]) -> int:
        # Use a max heap to sort the values
        # Pop the first two to represent x,y
        # If x == y then we continue looping
        # If x < y we subtract y - x and push to heap
        # return list after

        # We need to use neg vals so it sorts it in the opposite of min heap
        stones = [-stone for stone in stones]
        heapq.heapify(stones)
        
        while len(stones) > 1:
            x = heapq.heappop(stones)
            y = heapq.heappop(stones)

            if(x < y):
                heapq.heappush(stones,x-y)
            if(x == y):
                continue

        stones.append(0)
        return abs(stones[0])

def main():
    solution = Solution()
    stones = [2,3,6,2,4]
    # stones=[1,2]
    solution.lastStoneWeight(stones)
        
main()

