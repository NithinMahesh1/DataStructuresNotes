# Given two strings s and t, return true if the two strings are anagrams of each other, otherwise return false.
# An anagram is a string that contains the exact same characters as another string, but the order of the characters can be different.

# Input: s = "racecar", t = "carrace"
# Output: true

# Input: s = "jar", t = "jam"
# Output: false

def isAnagram(s,t) -> bool:
    # Loop through s 
    # If it contains a val in t
    # Then we replace with blank value
    # If at the end of the loop t is an empty str return true
    if(len(s) != len(t)):
        return False

    i = len(s)-1
    while i >= 0:
        if(s[i] in t):
            delete = s[i]
            s = s.replace(delete,"",1)
            t = t.replace(delete,"",1)
        i -= 1
    
        if(len(s) == 0):
            return True

    return False
    

def main():
    # s = "racecar"
    # t = "carrace"
    # s = "jar"
    # t = "jam"
    # s = "bbcc"
    # t = "ccbc"
    s = "xx"
    t = "x"
    print(isAnagram(s,t))

main()