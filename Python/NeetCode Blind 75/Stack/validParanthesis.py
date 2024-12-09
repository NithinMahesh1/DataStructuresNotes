# You are given a string s consisting of the following characters: '(', ')', '{', '}', '[' and ']'.

# The input string s is valid if and only if:

# Every open bracket is closed by the same type of close bracket.
# Open brackets are closed in the correct order.
# Every close bracket has a corresponding open bracket of the same type.
# Return true if s is a valid string, and false otherwise.

# Example 1:
# Input: s = "[]"
# Output: true

# Example 2:
# Input: s = "([{}])"
# Output: true

# Example 3:
# Input: s = "[(])"
# Output: false
# Explanation: The brackets are not closed in the correct order.


class Solution:
    def isValid(self, s: str) -> bool:
        # Create a dict of all the closing bracket as keys and their vals as the open version
        # e.g. } : {, ] : [, ) : (
        # We push to a stack for all opening brackets (check with if statements)
        # Once we get to a close bracket we check if the dict val of that equals top of stack
        # if so we pop the stack
        # Keep doing this until one does not match then is not valid return false

        dict = {
            "}" : "{",
            "]" : "[",
            ")" : "("
        }
        stack = []

        if(len(s) == 1 or s[0] == ")" or s[0] ==  "}" or s[0] ==  "]"):
            return False

        for i in range(len(s)):
            if(s[i] == "(" or s[i] ==  "{" or s[i] ==  "["):
                stack.append(s[i])
            if(s[i] in dict.keys()):
                if(dict[s[i]] == stack[len(stack)-1]):
                    stack.pop()
                else:
                    return False

        return True

def main():
    solution = Solution()
    # s = "[]"
    # s = "([{}])"
    # s = "[(])"
    s="(("
    solution.isValid(s)

main()