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
        l,r = 0,0
        window = 0
        freq = 0
        dict = {}

        for r in range(0,len(s)-1,1):
            if(len(dict) == 0):
                dict[s[r]] = 1
                r += 1
                window = 1
                continue
            if(window <= k):
                if(s[r] in dict.keys()):
                    dict[s[r]] = dict[s[r]] + 1
                else:
                    dict[s[r]] = 1
                r += 1
                window = max(window,window + 1)
            else:
                l += 1
                window = max(window,window + 1)
            c = Counter(dict)
            freq = c.most_common(1)[0][1]
            window = max(window,window - freq)

        return window


def main():
    solution = Solution()
    s = "XYYX"
    k = 2    
    solution.characterReplacement(s,k)

main()
