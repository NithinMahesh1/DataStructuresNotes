# Given the roots of two binary trees root and subRoot, 
# return true if there is a subtree of root with the same structure 
# and node values of subRoot and false otherwise.

# A subtree of a binary tree tree is a tree that consists of a node in tree and all of this node's descendants. 
# The tree tree could also be considered as a subtree of itself.

# Example 1:
# Input: root = [1,2,3,4,5], subRoot = [2,4,5]
# Output: true

# Example 2:
# Input: root = [1,2,3,4,5,null,null,6], subRoot = [2,4,5]
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
    def isSubtree(self, root: Optional[TreeNode], subRoot: Optional[TreeNode]) -> bool:
        # Similar to isSameTree but this time we check if there is a subtree 
        # and no left or right vals to it
        # We should use dfs similarly as well

        # We need to check if the current node (root) == subRoot (the curr root node)

        if(root is None and subRoot is None):
            return True
        if(root.val == subRoot.val):
            # If we find an identical root for both root and subRoot
            # Then we check if the left and right vals are the same
            if(root.left == subRoot.left and root.right == subRoot.right):
                # Check if they have children and compare those
                if(self.isSubtree(root.left,subRoot.left) == self.isSubtree(root.right,subRoot.right)):
                    return True
                
        self.isSubtree(root.left,subRoot.left)
        self.isSubtree(root.right,subRoot.right)
                
        return self.isSubtree(root,subRoot)



    def buildTree(self, arr):
        tree = TreeNode(arr[0])
        queue = deque([tree])

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

        return tree

def main():
    solution = Solution()
    root = [1,2,3,4,5]
    subRoot = [2,4,5]
    solution.isSubtree(solution.buildTree(root),solution.buildTree(subRoot))

main()
