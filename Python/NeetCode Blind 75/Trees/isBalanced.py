# Given a binary tree, return true if it is height-balanced and false otherwise.

# A height-balanced binary tree is defined as a binary tree in which the left and right 
# subtrees of every node differ in height by no more than 1.

# Example 1:
# Input: root = [1,2,3,null,null,4]
# Output: true

# Example 2:
# Input: root = [1,2,3,null,null,4,null,5]
# Output: false

# Example 3:
# Input: root = []
# Output: true

from typing import Optional
from collections import deque
# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def isBalanced(self, root: Optional[TreeNode]) -> bool:
        # This is simple we just need to use a dfs method to check the height of left and right
        # If left - right < 0 then multiply by -1
        # If the the val of that is greater than 1 then we return False
        # Otherwise return True
        def dfs(node):
            if not node:
                return 0
            left = dfs(node.left)
            right = dfs(node.right)

            # If a subtree is unbalanced
            if left == -1 or right == -1 or abs(left - right) > 1:
                return -1
            
            # left and right are the heights of each node at each level
            # We then compare them in our above code to see if there are different by more than one
            # We need max to check what the most height is from that node and pass that to the curr node
            return max(left,right) + 1
            
        return dfs(root) != -1

    def buildTrees(self,arr):
        if arr is None:
            return None
        
        root = TreeNode(arr[0])
        queue = deque([root])

        i = 1
        while queue and i < len(arr):
            node = queue.popleft()

            if(arr[i]):
                node.left = TreeNode(arr[i])
                queue.append(node.left)
            i += 1

            if(i < len(arr) and arr[i]):
                node.right = TreeNode(arr[i])
                queue.append(node.right)
            i += 1
        
        return root

    
def main():
    solution = Solution()
    root = [1,2,3,None,None,4]
    # root=[1,2,3,None,None,4,None,5]
    solution.isBalanced(solution.buildTrees(root))

main()
