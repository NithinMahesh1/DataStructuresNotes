# Design an algorithm to encode a list of strings to a single string. 
# The encoded string is then decoded back to the original list of strings.

# Please implement encode and decode

# Example 1:
# Input: ["neet","code","love","you"]
# Output:["neet","code","love","you"]

# Example 2:
# Input: ["we","say",":","yes"]
# Output: ["we","say",":","yes"]


# TODO
# Basically we need to use some delimeter such as 4# to encode the strs
# We take basically append the number of chars for the str as the number + #
# This way when we decode we are checking the int first then after #
# we start to check for each char after and add it to a list


class Solution:

    def encode(self, strs: list[str]) -> str:
        returnStr = ""
        for item in strs:
            if(len(returnStr) == 0):
                returnStr = str(len(item)) + "#" + item
            else:
                returnStr =  returnStr + str(len(item)) + "#" + item
        return returnStr


    def decode(self, s: str) -> list[str]:
        # 4#neet4#code4#love3#you
        returnList = []
        appendStr = ""
        count = -1
        for char in s:
            if(char.isdigit()):
                count = int(char)
                continue
            if(count > 0 and char != "#"):
                appendStr = appendStr + char
                count -= 1
            if(count <= 0):
                returnList.append(appendStr)
                appendStr = ""

        return returnList


    def run(self):
        input = ["neet","code","love","you"]
        strVersion = self.encode(input)
        print("This is the encoding: ")
        print(strVersion)
        
        print("This is the decoding back: ")
        print(self.decode(strVersion))


def main():
    solution = Solution()
    solution.run()
    
main()