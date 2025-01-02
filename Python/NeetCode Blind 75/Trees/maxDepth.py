# Given the root of a binary tree, return its depth.

# The depth of a binary tree is defined as the number of nodes along the longest path from the root node down to the farthest leaf node.

# Example 1:
# Input: root = [1,2,3,null,null,4]
# Output: 3

# Example 2:
# Input: root = []
# Output: 0

from typing import Optional
from collections import deque

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def maxDepth(self, root: Optional[TreeNode]) -> int:
        # We are going to use DFS for this since it traverses to the bottom of the tree
        depth = 0

        def dfs(root,depth):
            if root is None:
                return depth
            return max(dfs(root.left,depth+1),dfs(root.right,depth+1))
        
        print(dfs(root,depth))
        return dfs(root,depth)


    def buildTree(self,arr) -> TreeNode:
        if(len(arr) == 0):
            return TreeNode()

        root = TreeNode(arr[0])
        queue = deque([root])

        i = 1
        while queue != None and i < len(arr):
            node = queue.popleft()

            if(arr[i]):
                node.left = TreeNode(arr[i])
                queue.append(node.left)
            i += 1

            if(i < len(arr)):
                node.right = TreeNode(arr[i])
                queue.append(node.right)
            i += 1

        return root


def main():
    solution = Solution()
    # root = [1,2,3,None,None,4]
    root = []
    root = solution.buildTree(root)
    solution.maxDepth(root)

main()