# You are given a string s consisting of only uppercase english characters and an integer k. 
# You can choose up to k characters of the string and replace them with any other uppercase English character.
# After performing at most k replacements, return the length of the longest substring which contains only one distinct character.

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
        # Use a dict to store freq of each char
        # Also use a sliding window to increment L and R pointers
        dict = {}
        # window = 0
        res = 0
        
        # Basically we increment R then check most freq 
        L, R = 0,0
        while R <= len(s)-1:
            if(len(dict) == 0):
                dict[s[L]] = 1
                R += 1
                freq = 1
                continue
            # Take the window size (our res) - most freq <= k
            counter = Counter(dict)
            print(counter.most_common(1)[0][1])
            freq = freq - counter.most_common(1)[0][1]
            if(freq <= k):
                if(s[R] not in dict.keys()):
                    dict[s[R]] = 1
                else:
                    dict[s[R]] = dict[s[R]] + 1
                res += 1
                freq += 1
            else:
                L += 1
                R = L
            R += 1

        # Since K is the amount of values we can replace
        # return substring

        print(res)
        return res
        

def main():
    solution = Solution()
    s = "XYYX"
    k = 2
    solution.characterReplacement(s,k)

main()