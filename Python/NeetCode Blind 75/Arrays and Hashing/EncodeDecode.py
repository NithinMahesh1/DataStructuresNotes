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
        # 4neet4code4love3you
        returnList, i = [], 0

        # Following is neetcodes solution
        # We need to understand this algorithm better
        # Check the solution video for more help: 
        # https://www.youtube.com/watch?v=B1k_sxOSgv8&ab_channel=NeetCode
        while i < len(s):
            j = i
            # Basically we loop s to get the amount of digits for count
            # This ensures if we have 10 digits we are counting up to 10 chars
            while s[j] != "#":
                j += 1
            # We get length is from the index of the first char
            # to the end where j is no longer a pound so we are at a string char
            length = int(s[i:j])
            # Basically truncating from the beginning index of after the int and #
            # So we are starting the str from after that then that index plus the length of string
            returnList.append(s[j + 1 : j + 1 + length])
            # After that we set i to the length of the end of the char in our large encoded string
            i = j + 1 + length

        return returnList
    

    def run(self):
        # input = ["neet","code","love","you"]
        input = ["we","say",":","yes","!@#$%^&*()"]
        strVersion = self.encode(input)
        print("This is the encoding: ")
        print(strVersion)
        
        print("This is the decoding back: ")
        print(self.decode(strVersion))


def main():
    solution = Solution()
    solution.run()
    
main()