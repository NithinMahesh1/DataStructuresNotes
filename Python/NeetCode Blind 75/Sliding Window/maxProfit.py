# You are given an integer array prices where prices[i] is the price of NeetCoin on the ith day.
# You may choose a single day to buy one NeetCoin and choose a different day in the future to sell it.
# Return the maximum profit you can achieve. 
# You may choose to not make any transactions, in which case the profit would be 0.

# Example 1:
# Input: prices = [10,1,5,6,7,1]
# Output: 6
# Explanation: Buy prices[1] and sell prices[4], profit = 7 - 1 = 6.

# Example 2:
# Input: prices = [10,8,7,5,2]
# Output: 0
# Explanation: No profitable transactions can be made, thus the max profit is 0.


class Solution:
    def maxProfit(self, prices: list[int]) -> int:
        L = 0
        R = L + 1
        res = 0
        profit = 0
        while L < len(prices) - 1:
            res = max(res, profit)
            while R < len(prices):
                profit = prices[R] - prices[L]
                res = max(res, profit)
                R += 1
            L += 1
            R = L + 1
        
        print(res)
        return res


def main():
    solution = Solution()
    # prices = [10,1,5,6,7,1]
    # prices = [10,8,7,5,2]
    prices=[5,1,5,6,7,1,10]
    solution.maxProfit(prices)

main()