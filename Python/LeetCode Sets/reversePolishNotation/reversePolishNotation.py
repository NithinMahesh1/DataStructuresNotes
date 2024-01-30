# You are given an array of strings tokens that represents an arithmetic expression in a Reverse Polish Notation.

# Evaluate the expression. Return an integer that represents the value of the expression.

# Note that:

# The valid operators are '+', '-', '*', and '/'.
# Each operand may be an integer or another expression.
# The division between two integers always truncates toward zero.
# There will not be any division by zero.
# The input represents a valid arithmetic expression in a reverse polish notation.
# The answer and all the intermediate calculations can be represented in a 32-bit integer.


# Example 1:
# Input: tokens = ["2","1","+","3","*"]
# Output: 9
# Explanation: ((2 + 1) * 3) = 9

# Example 2:
# Input: tokens = ["4","13","5","/","+"]
# Output: 6
# Explanation: (4 + (13 / 5)) = 6

# Example 3:
# Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
# Output: 22
# Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
# = ((10 * (6 / (12 * -11))) + 17) + 5
# = ((10 * (6 / -132)) + 17) + 5
# = ((10 * 0) + 17) + 5
# = (0 + 17) + 5
# = 17 + 5
# = 22

# Loop through and
# Push to stack numbers
# If it is a operator and there is only two things in the stack we push both and the second pop is the second value <operator> first value
# If there are values in the stack and we get an operator in the loop then we pop the new value and it will be curr value <operator> the new value

# tokens = ["2","1","+","3","*"]
# tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
# tokens = ["4","13","5","/","+"]
tokens = ["18"]
operands = set({'+','-','*','/'})
stack = []

for token in tokens:
    if token not in operands:
        # Need to convert to int and ensure the calculations below are not string
        stack.append(int(token))
    
    else:
        curr = stack.pop()
        second = stack.pop()

        if token == '+':
            stack.append(second + curr)
        if token == '-':
            stack.append(second - curr)
        if token == '*':
            stack.append(second * curr)
        if token == '/':
            stack.append(int(second/curr))

if stack and len(stack) == 1:
    print(stack[-1])
else:
    print(0)