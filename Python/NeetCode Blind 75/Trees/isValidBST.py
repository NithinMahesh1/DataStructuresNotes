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
        # Basically we want to use recursive DFS again
        # Essentially we will have a left and right boundary we check our nodes with
        # This ensure that all values on the right side of the tree are greater than the root
        # and the opposite for the left side of the tree

        def valid(node,left,right):
            if node is None:
                return True
            # Here we check left and right through each iteration
            if not(left < node.val and right > node.val):
                return False
            
            # For the left subtree:
            #   Compare left to the left boundary
            #   Compare right side to the right node
            # For the right subtree
            #   Compare left side to root node
            #   Compare right side to the right boundary
            return valid(node.left, left, node.val) and valid(node.right, node.val, right)

        # We first set the boundaries to be -inifite to infinite
        # Since the that is the starting boundary for root
        # As we go down it is updated with new boundaries that we set
        return valid(root,float("-inf"),float("inf"))

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