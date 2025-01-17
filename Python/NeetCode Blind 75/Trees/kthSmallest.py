# Given the root of a binary search tree, and an integer k,
# return the kth smallest value (1-indexed) in the tree.

# A binary search tree satisfies the following constraints:

# The left subtree of every node contains only nodes with keys less than the node's key.
# The right subtree of every node contains only nodes with keys greater than the node's key.
# Both the left and right subtrees are also binary search trees.

# Example 1:
# Input: root = [2,1,3], k = 1
# Output: 1

# Example 2:
# Input: root = [4,3,5,2,null], k = 4
# Output: 5

from typing import Optional
from collections import deque
# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def kthSmallest(self, root: Optional[TreeNode], k: int) -> int:
        # Using DFS in order traversal left, root, right
        # Using iterative approach instead of recursive
        # We basically go all the way left and at each root we push to stack
        # We keep pushing roots until root.left is None then we pop the stack and increment k += 1
        # This naturally makes a sorted order and as we iterate to i == k then we return that root.val
        stack = [root]
        i = 0
        node = root

        while stack:
            if(node.left is None and node.right is None):
                node = stack.pop()
                node = node.right
            if(i == k):
                print(node.val)
                return node.val
            if(node.left is not None):
                stack.append(node.left)
                node = stack.pop()
                i += 1
            if(node.right is not None):
                stack.append(node.right)
                node = stack.pop()
                i += 1

        return 0

        
    def buildTrees(self,arr) -> TreeNode:
        root = TreeNode(arr[0])
        queue = deque([root])

        i=1
        while(queue and i < len(arr)):
            node = queue.popleft()

            if(arr[i]):
                node.left = TreeNode(arr[i])
                queue.append(node.left)
            i += 1

            if(arr[i] and i < len(arr)):
                node.right = TreeNode(arr[i])
                queue.append(node.right)
            i += 1

        return root


def main():
    solution = Solution()
    root = [2,1,3]
    k = 1
    solution.kthSmallest(solution.buildTrees(root),k)


main()