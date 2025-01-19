# You are given two integer arrays preorder and inorder.

# * preorder is the preorder traversal of a binary tree
# * inorder is the inorder traversal of the same tree
# * Both arrays are of the same size and consist of unique values.

# Rebuild the binary tree from the preorder and inorder traversals and return its root.

# Example 1:
# Input: preorder = [1,2,3,4], inorder = [2,1,3,4]
# Output: [1,2,3,null,null,null,4]

# Example 2:
# Input: preorder = [1], inorder = [1]
# Output: [1]

from typing import Optional
from collections import deque
# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def buildTree(self, preorder: list[int], inorder: list[int]) -> Optional[TreeNode]:
        # The preorder first value gives us the root of the tree
        # The inorder values show us the left and right of the tree
        # Basically the mid index in the inorder arr shows what values are on the left subtree (left side)
        # And then from the mid and after is the right subtree
        if not preorder or not inorder:
            return None

        root = TreeNode(preorder[0]) # gives us the root of the tree
        mid = inorder.index(preorder[0]) # Using the root we get the index of the root val from preorder
        # We are going to use recursion and loop both sides 
        # Traverse left 1:mid+1 is 
        root.left = self.buildTree(preorder[1:mid+1],inorder[:mid])
        root.right = self.buildTree(preorder[mid+1:],inorder[mid+1:])
        return root
    

class Solution:
    def buildTree(self, preorder: list[int], inorder: list[int]) -> Optional[TreeNode]:
        # The preorder first value gives us the root of the tree
        # The inorder values show us the left and right subtrees of the tree
        # In the inorder traversal, the values before the root are in the left subtree,
        # and the values after the root are in the right subtree.
        if not preorder or not inorder:
            return None  # Base case: if either traversal is empty, there's no tree to build

        root = TreeNode(preorder[0])  # The first element of preorder is always the root of the current subtree
        mid = inorder.index(preorder[0])  # Find the root's index in the inorder array
        # The inorder array is divided into:
        #   * Left subtree: all elements before the root's index (inorder[:mid])
        #   * Right subtree: all elements after the root's index (inorder[mid+1:])

        # The preorder array is divided into:
        #   * Left subtree: the next `mid` elements, because these correspond to the left subtree in inorder
        #   * Right subtree: the remaining elements after the left subtree
        #
        # Explanation of the slicing:
        # - preorder[1:mid+1]: Takes the first `mid` elements after the root (these are the left subtree nodes)
        # - inorder[:mid]: Includes all elements of the left subtree in inorder
        root.left = self.buildTree(preorder[1:mid+1], inorder[:mid])  # Recursively build the left subtree
        
        # - preorder[mid+1:]: Takes all remaining elements after the left subtree in preorder
        # - inorder[mid+1:]: Includes all elements of the right subtree in inorder
        root.right = self.buildTree(preorder[mid+1:], inorder[mid+1:])  # Recursively build the right subtree
        
        return root



def main():
    solution = Solution()
    preorder = [1,2,3,4] 
    inorder = [2,1,3,4]
    solution.buildTree(preorder,inorder)

main()