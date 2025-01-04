# Given the roots of two binary trees p and q, 
# return true if the trees are equivalent, otherwise return false.

# Two binary trees are considered equivalent if they share the exact same structure and 
# the nodes have the same values.

# Example 1:
# Input: p = [1,2,3], q = [1,2,3]
# Output: true

# Example 2:
# Input: p = [4,7], q = [4,null,7]
# Output: false

# Example 3:
# Input: p = [1,2,3], q = [1,3,2]
# Output: false

from typing import Optional
from collections import deque
# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def isSameTree(self, p: Optional[TreeNode], q: Optional[TreeNode]) -> bool:
        # We will want to use DFS again
        if not q and not p:
            return True
        if not p or not q:
            return False
        if p.val != q.val:
            return False
        
        print(self.isSameTree(q.left,p.left) and self.isSameTree(q.right,p.right))
        return self.isSameTree(q.left,p.left) and self.isSameTree(q.right,p.right)
    
    def buildTree(self,arr) -> TreeNode:
        if(len(arr) == 0):
            return TreeNode()

        root = TreeNode(arr[0])
        queue = deque([root])

        i = 1
        while queue and i < len(arr):
            node = queue.popleft()

            if(arr[i]):
                root.left = TreeNode(arr[i])
                queue.append(node.left)
            i += 1

            if(i < len(arr) and arr[i]):
                root.right = TreeNode(arr[i])
                queue.append(node.right)
            i += 1

        return root        

def main():
    solution = Solution()
    # p = [1,2,3]
    # q = [1,2,3]
    p = [4,7]
    q = [4,None,7]
    p = solution.buildTree(p)
    q = solution.buildTree(q)
    solution.isSameTree(p,q)


main()