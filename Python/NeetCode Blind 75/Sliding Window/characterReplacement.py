# You are given a string s consisting of only uppercase english characters and an integer k. 
# You can choose up to k characters of the string and replace them with any other uppercase English character.
# After performing at most k replacements, 
# return the length of the longest substring which contains only one distinct character.

# Example 1:
# Input: s = "XYYX", k = 2
# Output: 4
# Explanation: Either replace the 'X's with 'Y's, or replace the 'Y's with 'X's.

# Example 2:
# Input: s = "AAABABB", k = 1
# Output: 5

from collections import Counter
class Solution:
    def characterReplacement(self, s: str, k: int) -> int:
        # Use window and dict
        # Check len(window) - most freq <= k
        # Increase R as that condition is still met
        # Once it is not we increment L
        count = {}
        res = 0

        l=0
        for r in range(len(s)):
            count[s[r]] = 1 + count.get(s[r],0)

            # In the case where we are past the size of replacements with our window - freq
            # we will loop the size of the r - l which is window size
            # while the len(window) - most freq (max of this since we only want max) > k
            while(r - l + 1) - max(count.values()) > k:
                # Decrement and remove the left most val since we need to shift window
                count[s[l]] -= 1
                # Move the left pointer over one on the string
                l += 1

            # Max of the res vs the window size
            res = max(res,r-l + 1)

        return res

def main():
    solution = Solution()
    s = "XYYX"
    k = 2 
    solution.characterReplacement(s,k)

main()
