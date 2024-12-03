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
        # We need to use a set so it does not take duplicates
        charSet = set()
        l = 0
        res = 0

        # Loop with right pointer forward
        for r in range(len(s)):
            while s[r] in charSet:
                # If there is a value in set as in there is duplicate
                # we will need to remove it from the set
                charSet.remove(s[l])
                # then we increment l one more
                l += 1
            # As we remove duplicates and update our window above
            # we also need to add more values to slide the window right s[r]
            charSet.add(s[r])
            # Got to compute the current window size if the current window size is greater
            # than what it is right now
            # so we do right - left to give us size + 1 since those are indices
            res = max(res,r - l + 1)

        return res


def main():
    solution = Solution()
    # s = "zxyzxyz"
    # s = "xxxx"
    # s="pwwkew"
    s="aab"
    solution.lengthOfLongestSubstring(s)

main()