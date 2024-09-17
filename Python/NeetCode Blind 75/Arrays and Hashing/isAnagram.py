# Given two strings s and t, return true if the two strings are anagrams of each other, otherwise return false.
# An anagram is a string that contains the exact same characters as another string, but the order of the characters can be different.

# Input: s = "racecar", t = "carrace"
# Output: true

# Input: s = "jar", t = "jam"
# Output: false

def isAnagram(s,t) -> bool:
    # Loop through the values of first str
    # Check if "in" the second str
    # return false if there are any that are not in there

    if(len(s) == len(t)):
        for i in range(0,len(s),+1):
            if(s[i] in t):
                continue
            else:
                return False
    else:
        False

    return True

def main():
    # s = "racecar"
    # t = "carrace"
    s = "jar"
    t = "jam"
    print(isAnagram(s,t))

main()