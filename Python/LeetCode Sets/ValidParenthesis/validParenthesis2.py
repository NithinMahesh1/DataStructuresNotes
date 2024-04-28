# Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', 
# determine if the input string is valid.

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



# s = "([)]"
# s = "()[]{}"
# s = "(]"
# s = "()"
# s = "{[]}"
# s = "["
s = "(("

# Add all closing values to a dictionary
#   e.g. key: ( value: )
# Loop through and append if stack is empty first
# If the current char is a key then we append and set isValid to True
# It the current char is a closing value and matches the last stack value's dict get value e.g. <"{"> -> <"}"> then we pop
# Otherwise we set to false since there is no match
# Also since we pop the stack at each match if at the end the stack is not empty it means there are open brackets only

dict = {
    "(" : ")",
    "{" : "}",
    "[" : "]",
}
stack = []
isValid = False

for char in s:
    if(len(stack) == 0):
        isValid = True
        stack.append(char)
    elif(dict.get(char,None) != None):
        isValid = True
        stack.append(char)
    elif(dict.get(stack[-1]) == char):
        stack.pop()
    else:
        isValid = False

if(len(stack) != 0):
    isValid = False

print(isValid)