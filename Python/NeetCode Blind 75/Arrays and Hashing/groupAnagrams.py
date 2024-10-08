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


def groupAnagrams(strs) -> list[list[str]]:
    # Dict with the key as the num of char
    # and val as the List of strs that are anagrams to that
    dict = {}
    for str in strs:
        vals = []
        if(len(dict) == 0):
            vals.append(str)
            dict[len(str)] = vals
            continue

        if(len(str) in dict.keys()):
            # Grab the vals (the list var)
            # Get one of the vals as a var
            # Use this var to compare with the current str
            # while loop that value to check each char
            # Add to list if it equals
            listDictVals = dict.get(len(str))
            dictVal = listDictVals[0]
            
            i = len(str) - 1
            actualStr = str
            while i >= 0:
                #act
                #cat
                if(str[i] in dictVal):
                    remove = str[i]
                    str = str.replace(remove,"",1)
                    dictVal = dictVal.replace(remove,"",1)
                if(i == 0 & len(str) == 0 & len(dictVal) == 0):
                    vals = dict.get(len(actualStr))
                    vals.append(actualStr)
                else:
                    dict[0] = actualStr
                i -= 1
        else:
            vals.append(str)
            dict[len(str)] = vals

    return list(dict.values)
        


def main():
    strs = ["act","pots","tops","cat","stop","hat"]
    print(groupAnagrams(strs))

main()