# You are given the root of a binary tree root. Invert the binary tree and return its root.

# Input: root = [1,2,3,4,5,6,7]
# Output: [1,3,2,7,6,5,4]

# Input: root = [3,2,1]
# Output: [3,1,2]

# Example 3:
# Input: root = []
# Output: []

from typing import Optional
from collections import deque
# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def invertTree(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
        # Need to use DFS algorithm
        # We can use inorder traversal since we want to swap left and right
        # Start at root -> left -> right traversal using DFS
        if not root: 
            return None

        root.left, root.right = root.right, root.left
        
        self.invertTree(root.left)
        self.invertTree(root.right)
        
        return root

    def buildTree(self, arr):
        tree = TreeNode(arr[0])
        queue = deque([tree])

        i = 1
        while queue != None and i < len(arr):
            node  = queue.popleft()

            if(arr[i] is not None):
                node.left = TreeNode(arr[i])
                queue.append(node.left)
            i += 1

            if(arr[i] is not None and i < len(arr)):
                node.right = TreeNode(arr[i])
                queue.append(node.right)
            i += 1

        return tree

def main():
    root = [1,2,3,4,5,6,7]
    solution = Solution()
    root = solution.buildTree(root)
    solution.invertTree(root)


main()