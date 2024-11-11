# Given a string s, return true if it is a palindrome, otherwise return false.

# A palindrome is a string that reads the same forward and backward. 
# It is also case-insensitive and ignores all non-alphanumeric characters.

# Example 1:
# Input: s = "Was it a car or a cat I saw?"
# Output: true
# Explanation: After considering only alphanumerical characters we have "wasitacaroracatisaw", which is a palindrome.

# Example 2:
# Input: s = "tab a cat"
# Output: false
# Explanation: "tabacat" is not a palindrome.

class Solution:
    def isPalindrome(self, s: str) -> bool:
        if(len(s) == 1):
            return True
        
        s = s.replace(" ", "").replace("?","").replace("!","").replace(":","").replace(",","").replace("'","").replace(".","")
        s = s.lower()
        str = ""
        for i in range(len(s)-1, -1, -1):
            str += s[i]
        
        if(str == s):
            return True

        return False

    def run(self):
        # s = "Was it a car or a cat I saw?"
        s="Madam, in Eden, I'm Adam"
        print(self.isPalindrome(s))


def main():
    solution = Solution()
    solution.run()

main()