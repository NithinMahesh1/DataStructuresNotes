# The diameter of a binary tree is defined as the length of the longest path between any two nodes within the tree. 
# The path does not necessarily have to pass through the root.

# The length of a path between two nodes in a binary tree is the number of edges between the nodes.

# Given the root of a binary tree root, return the diameter of the tree.

# Example 1:
# Input: root = [1,null,2,3,4,5]
# Output: 3

# Example 2:
# Input: root = [1,2,3]
# Output: 2

from typing import Optional
from collections import deque
# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def diameterOfBinaryTree(self, root: Optional[TreeNode]) -> int:
        # We can use dfs to travese the tree
        # Get the left height and right height from each side of the tree
        # We then add them together and check the max() to see which is the largest
        # Once we have iterated the whole tree return
        self.diameter = 0

        def dfs(node):
            if node is None:
                return 0
            leftheight = dfs(node.left)
            rightheight = dfs(node.right)

            self.diameter = max(self.diameter,leftheight+rightheight)

            # The +1 is for accounting for the edge as well
            # Otherwise counter would be off by 1
            return 1+max(leftheight,rightheight)
        
    
        dfs(root)
        return self.diameter

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
    root = [1,None,2,3,4,5]
    solution = Solution()
    solution.diameterOfBinaryTree(solution.buildTrees(root))

main()