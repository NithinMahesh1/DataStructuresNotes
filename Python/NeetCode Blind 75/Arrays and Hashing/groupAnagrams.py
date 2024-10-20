# Given an array of strings strs, group all anagrams together into sublists. 
# You may return the output in any order.

# An anagram is a string that contains the exact same characters as another string, 
# but the order of the characters can be different.

# Example 1:
# Input: strs = ["act","pots","tops","cat","stop","hat"]
# Output: [["hat"],["act", "cat"],["stop", "pots", "tops"]]

# Example 2:
# Input: strs = ["x"]
# Output: [["x"]]

# Example 3: 
# Input: strs = [""]
# Output: [[""]]

from collections import defaultdict

def groupAnagrams(strs) -> list[list[str]]:
    # Need to use a default dict
    # Sort the chars and add to key
    dict = defaultdict(list)

    for str in strs:
        dict["".join(sorted(str))].append(str)

    return dict.values()
    
def main():
    strs = ["act","pots","tops","cat","stop","hat"]
    # strs = ["",""]
    # strs = ["x"]
    # strs = [""]
    print(groupAnagrams(strs))

main()