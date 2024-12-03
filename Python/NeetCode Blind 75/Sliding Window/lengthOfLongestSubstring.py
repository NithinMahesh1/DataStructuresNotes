# Given a string s, find the length of the longest substring without duplicate characters.
# A substring is a contiguous sequence of characters within a string.

# Example 1:
# Input: s = "zxyzxyz"
# Output: 3
# Explanation: The string "xyz" is the longest without duplicate characters.

# Example 2:
# Input: s = "xxxx"
# Output: 1

class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        res = 0
        # L and R pointers
        curr = 0
        dict = {}
        counter = 0
        # Loop and use a dictionary adding unique vals
        # dict needs index + 1 as val and key as the s val
        # if it is in dict then we stop looping
        while curr < len(s):
            # Also take max of the substring then change L to be R and R back to L + 1
            val = s[curr]
            
            if(val not in dict.keys()):
                dict[val] = curr + 1
                counter += 1
                res = max(res,counter)
            else:
                dict = {}
                counter = 0

            curr += 1

        print(res)
        return res

def main():
    solution = Solution()
    # s = "zxyzxyz"
    # s = "xxxx"
    # s="pwwkew"
    s="aab"
    solution.lengthOfLongestSubstring(s)

main()