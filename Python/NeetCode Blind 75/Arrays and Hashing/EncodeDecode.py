# Design an algorithm to encode a list of strings to a single string. 
# The encoded string is then decoded back to the original list of strings.

# Please implement encode and decode

# Example 1:
# Input: ["neet","code","love","you"]
# Output:["neet","code","love","you"]

# Example 2:
# Input: ["we","say",":","yes"]
# Output: ["we","say",":","yes"]

class Solution:
    def encode(self, strs) -> str:
        fullstr = ""
        for str in strs:
            if(len(fullstr) == 0):
                fullstr = str
            else:
                fullstr = fullstr + "," +str
        return fullstr

    def decode(self, str) -> list[str]:
        newList = []
        for item in str:
            newList.append(item)
        return newList

    def run(self):
        input = ["neet","code","love","you"]
        strVersion = self.encode(input)
        self.decode(strVersion)


def main():
    run = Solution.run(self)
    