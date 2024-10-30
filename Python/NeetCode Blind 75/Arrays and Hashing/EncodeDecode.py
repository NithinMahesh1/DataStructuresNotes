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
        return ""

    def decode(self, s: str) -> list[str]:
        return [""]

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