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
        if(len(s) == 1 and s == t):
            return s
        if(len(s) < len(t)):
            return ""
        # L and R pointer window
        # Start L at first occurence of t val
        l,r = 0,0
        tremove = t
        win = ""
        count = 0
        dict = {}
        for l in range(len(s)):
            # We loop until L is in t
            if(s[l] in t):
                # Add count, remove our placeholder tremove (using to check what vals we hit from t)
                # Increment win with our str
                # Set R to be ahead of l
                tremove = tremove.replace(s[l],"",1)
                count += 1
                win = win + s[l]
                r = l + 1
                if(len(tremove) == 0):
                    dict[win] = count
                # Begin moving R until we are out of vals from tremove
                while r <= len(s)-1 and len(tremove) != 0:
                    # Add to count to keep track of the vals in dict that will help us with min res
                    # Add vals for our return substring key in dict
                    count += 1
                    win = win + s[r]
                    if(s[r] in tremove):
                        # Remove from our reference tremove if we get another t val
                        tremove = tremove.replace(s[r],"",1)
                    if(len(tremove) == 0):
                        # Reset everything so we can loop L until end of str
                        # Plus we need to check for more substrings to get min
                        dict[win] = count
                        win = ""
                        count = 0
                        tremove = t
                        break
                    r += 1
            l += 1

        # We get the minimum value from dict
        # Then using lambda that statement is taking any key from dict
        # and returns its corresponding value
        if(dict):
            res = min(dict, key=lambda x: dict[x])
        else:
            res = ""
        
        print(res)
        return res
    


def main():
    # s = "OUZODYXAZV"
    # t = "XYZ"
    # s = "xyz"
    # t = "xyz"
    # s = "x"
    # t = "xy"
    # s="a"
    # t="aa"
    s="aa"
    t="aaa"
    solution = Solution()
    solution.minWindow(s,t)

main()