# Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

# Implement the MinStack class:

# MinStack() initializes the stack object.
# void push(int val) pushes the element val onto the stack.
# void pop() removes the element on the top of the stack.
# int top() gets the top element of the stack.
# int getMin() retrieves the minimum element in the stack.
# You must implement a solution with O(1) time complexity for each function.


# Example 1:

# Input
# ["MinStack","push","push","push","getMin","pop","top","getMin"]
# [[],[-2],[0],[-3],[],[],[],[]]

# Output
# [null,null,null,null,-3,null,0,-2]

# Explanation
# MinStack minStack = new MinStack();
# minStack.push(-2);
# minStack.push(0);
# minStack.push(-3);
# minStack.getMin(); // return -3
# minStack.pop();
# minStack.top();    // return 0
# minStack.getMin(); // return -2

# Your MinStack object will be instantiated and called as such:
# obj = MinStack()
# obj.push(val)
# obj.pop()
# param_3 = obj.top()
# param_4 = obj.getMin()


class MinStack:
    def __init__(self):
        self.array = []

    def push(self, val: int) -> None:
        self.array.append(val)

    def pop(self) -> None:
        if len(self.array) > 0:
            self.array.pop()

    def top(self) -> int:
        if len(self.array) > 0:
            return self.array[-1]
        return None

    def getMin(self) -> int:
        if len(self.array) > 0:
            return min(self.array)
        return None


obj = MinStack()
print("null")
obj.push(2)
print("null")

obj.push(0)
print("null")

obj.push(3)
print("null")

obj.push(0)
print("null")

param1 = obj.getMin()
print("param1: " +str(param1))
obj.pop()
print("null")
param2 = obj.getMin()
print("param2: " +str(param2))
obj.pop()
print("null")
param3 = obj.getMin()
print("param3: " +str(param3))
obj.pop()
print("null")
param4 = obj.getMin()
print("param4: " +str(param4))

# [null,null,null,null,null,0,null,0,null,2,null,2]
