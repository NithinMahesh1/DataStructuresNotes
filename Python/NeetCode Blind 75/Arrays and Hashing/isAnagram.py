# Given two strings s and t, return true if the two strings are anagrams of each other, otherwise return false.
# An anagram is a string that contains the exact same characters as another string, but the order of the characters can be different.

# Input: s = "racecar", t = "carrace"
# Output: true

# Input: s = "jar", t = "jam"
# Output: false

def isAnagram(s,t) -> bool:
    # Loop the first value backwards and compare it to t at that index
    # Also before we even loop check that the strings are the same length
    compare = ""
    if(len(s) == len(t)):
        for i in range(len(s)-1,-1,-1):
            compare = compare + s[i]
        if(compare == s):
            return True

    return False

def main():
    # s = "racecar"
    # t = "carrace"
    s = "jar"
    t = "jam"
    print(isAnagram(s,t))

main()