# You are given the root of a binary tree.
# Return only the values of the nodes that are visible from the right side of the tree,
# ordered from top to bottom.

# Example 1:
# Input: root = [1,2,3]
# Output: [1,3]

# Example 2:
# Input: root = [1,2,3,4,5,6,7]
# Output: [1,3,7]

from typing import Optional
from collections import deque
# Definition for a binary tree node.
class TreeNode:        
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def rightSideView(self, root: Optional[TreeNode]) -> list[int]:
        # I think for this problem we need to use dfs 
        # Use depth as a counter
        # Count until our res and depth == each other
        # this is because each node we append to res is a level of depth
        res = []

        def dfs(node,depth):            
            if node is None:
                return None
            if depth == len(res):
                res.append(node.val)
            dfs(node.right,depth+1)
            dfs(node.left,depth+1)

        dfs(root,0)
        return res

    def buildTrees(self, arr) -> TreeNode:
        if arr is None:
            return TreeNode()

        root = TreeNode(arr[0])
        queue = deque([root])

        i = 1
        while queue and i < len(arr):
            node = queue.popleft()

            if(arr[i] is not None):
                node.left = TreeNode(arr[i])
                queue.append(node.left)
            i += 1

            if(i < len(arr) and arr[i] is not None):
                node.right = TreeNode(arr[i])
                queue.append(node.right)
            i += 1

        return root


def main():
    solution = Solution()
    # root = [1,2,3]
    # root = [1,2,3,4,5,6,7]
    # root=[]
    root=[1,2]
    solution.rightSideView(solution.buildTrees(root))

main()