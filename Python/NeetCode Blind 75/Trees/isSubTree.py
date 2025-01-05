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
        # Similar to isSameTree but we need to check only the subtree vals
        # First we will recursively iterate left and right first checking the curr root node
        # if the curr root node == the subRoot node then we call isSameTree and compare
        if(subRoot is None):
            print("true")
            return True
        if(root is None):
            print("false")
            return False
        
        # After checking edge cases we need to check if the curr vals are equal
        # but also if the subtrees of them are the same
        if(root.val == subRoot.val and self.isSameTree(root,subRoot)):
            print("true")
            return True

        return self.isSubtree(root.left, subRoot) or self.isSubtree(root.right, subRoot)


    def isSameTree(self,left,right) -> bool:
        if(left is None and right is None):
            return True
        if(not left or not right):
            return False
        if(left.val != right.val):
            return False

        return self.isSameTree(left.left, right.left) and self.isSameTree(left.right, right.right)

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
    # root = [1,2,3,4,5,None,None,6]
    # subRoot = [2,4,5]
    solution.isSubtree(solution.buildTree(root),solution.buildTree(subRoot))

main()
