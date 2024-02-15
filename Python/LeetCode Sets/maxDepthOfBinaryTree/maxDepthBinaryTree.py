# Given the root of a binary tree, return its maximum depth.
# A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

# Example 1:
# <see picture in folder>

# Input: root = [3,9,20,null,null,15,7]
# Output: 3

# Example 2:
# Input: root = [1,null,2]
# Output: 2
 

# Constraints:
# The number of nodes in the tree is in the range [0, 104].
# -100 <= Node.val <= 100


# Definition for a binary tree node.
from collections import deque
from typing import Optional

class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def maxDepth(self, TreeNode)-> int:
        root = TreeNode()
        self.queue = deque()

        # return traverseTree(root)
        return -1


def createTree(tree,arr):
    # Create a TreeNode instance to represent the root
    tree = TreeNode(arr[0])
    # Create a deque to hold the tree nodes
    queue = deque([tree])
    # Index to keep track of the current position in the array
    i = 1

    while queue and i < len(arr):
        node = queue.popleft()

        if arr[i] is not None:
            node.left = TreeNode(arr[i])
            queue.append(node.left)
        i += 1

        if i < len(arr) and arr[i] is not None:
            node.right = TreeNode(arr[i])
            queue.append(node.right)
        i += 1

    print(tree)

            
values = [3,9,20,None,None,15,7]
tree = TreeNode()
createTree(tree,values)