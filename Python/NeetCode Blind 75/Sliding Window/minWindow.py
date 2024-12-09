# Given two strings s and t, return the shortest substring of s such that every character in t, 
# including duplicates, is present in the substring. 
# If such a substring does not exist, return an empty string "".

# You may assume that the correct output is always unique.

# Example 1:
# Input: s = "OUZODYXAZV", t = "XYZ"
# Output: "YXAZ"
# Explanation: "YXAZ" is the shortest substring that includes "X", "Y", and "Z" from string t.

# Example 2:
# Input: s = "xyz", t = "xyz"
# Output: "xyz"

# Example 3:
# Input: s = "x", t = "xy"
# Output: ""

class Solution:
    def minWindow(self, s: str, t: str) -> str:
        # L and R pointer window
        # Start L at first occurence of t val
        # Once we get there increment R until all of t vals are touched
        # Take the min of the window as res so res = min(res,win)
        # Keep looping until R is at last val
        # Increment L to next occurence of t val once all vals are hit in last round
        l = 0, r = 0
        res = 0
        win = 0
        for l in range(len(s)):
            if(s[l] in t):
                # loop here from L and R = L + 1
                r = l + 1
                while r < len(s):
                    # increment r here until it reaches 2 more t vals
            l += 1



def main():
    s = "OUZODYXAZV"
    t = "XYZ"
    solution = Solution()
    solution.minWindow(s,t)

main()