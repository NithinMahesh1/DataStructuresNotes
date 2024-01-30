# Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

# An input string is valid if:
# Open brackets must be closed by the same type of brackets.
# Open brackets must be closed in the correct order.
# Every close bracket has a corresponding open bracket of the same type.

# Example 1:
# Input: s = "()"
# Output: true

# Example 2:
# Input: s = "()[]{}"
# Output: true

# Example 3:
# Input: s = "(]"
# Output: false

# str = '()'
# stack = []
# isValid = False

# for s in str:
#     if len(str) == 1:
#         break
#     elif s == ')':
#         if stack[-1] == '(':
#             isValid = True
#     elif s == '}':
#         if stack[-1] == '{':
#             isValid = True
#     elif s == ']':
#         if stack[-1] == '[':
#             isValid = True            
#     else:
#         isValid = False
#     stack.append(s)

# if isValid == False:
#     print("not valid")
# else:
#     print("valid")

# This is the final solution for leetcode:
# My solution above worked, but not for edge cases like input = '['
# This would see an open bracket, append, then never set a isValid to false
# So we need check at the end that the stack is empty then its true if not false

stack = []
isValid = True

for char in str:
    if char == ')':
        if stack and stack[-1] == '(':
            stack.pop()
        else:
            isValid = False
    elif char == '}':
        if stack and stack[-1] == '{':
            stack.pop()
        else:
            isValid = False
    elif char == ']':
        if stack and stack[-1] == '[':
            stack.pop()
        else:
            isValid = False
    else:
        stack.append(char)

if len(stack) != 0:
    print("not valid")
else:
    print("valid")