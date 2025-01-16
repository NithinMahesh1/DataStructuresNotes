# Given the root of a binary tree, return true if it is a valid binary search tree, otherwise return false.

# A valid binary search tree satisfies the following constraints:

# The left subtree of every node contains only nodes with keys less than the node's key.
# The right subtree of every node contains only nodes with keys greater than the node's key.
# Both the left and right subtrees are also binary search trees.

# Example 1:
# Input: root = [2,1,3]
# Output: true

# Example 2:
# Input: root = [1,2,3]
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
    def isValidBST(self, root: Optional[TreeNode]) -> bool:
        # Similar to levelorder 
        # We iterate tree and compare left to root then right to root
        # Need to do this recursively so we compare each and if one subtree does not match
        # then we would return False
        if(root is None):
            return True
        if(root and root.left is None and root.right is None):
            return True
        
        queue = deque([root])

        while queue:
            node = queue.popleft()
            left = node.left
            right = node.right

            if(left == None or left.val >= node.val):
                print("False")
                return False
            if(right == None or right.val <= node.val):
                print("False")
                return False
        
        print("True")
        return True


    def buildTrees(self,arr) -> TreeNode:
        root = arr[0]
        queue = deque([root])

        i = 1
        while queue and i < len(arr):
            node = TreeNode(queue.popleft())

            if(arr[i]):
                node.left = TreeNode(arr[i])
                queue.append(node.left)
            i += 1

            if(i < len(arr) and arr[i]):
                node.right = TreeNode(arr[i])
                queue.append(node.right)
            i += 1

        return node

def main():
    solution = Solution()
    # root = [2,1,3]
    # root = [1,2,3]
    # root=[1,None,1]
    # root=[2,2,2]
    root=[0,-1]
    solution.isValidBST(solution.buildTrees(root))


main()