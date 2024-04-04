# Given the root of a binary tree, determine if it is a valid binary search tree (BST).

# A valid BST is defined as follows:

# The left 
# subtree
#  of a node contains only nodes with keys less than the node's key.
# The right subtree of a node contains only nodes with keys greater than the node's key.
# Both the left and right subtrees must also be binary search trees.
 
# Example 1:
# Input: root = [2,1,3]
# Output: true
# Check tree1.jpg 

# Example 2:
# Input: root = [5,1,4,null,null,3,6]
# Output: false
# Explanation: The root node's value is 5 but its right child's value is 4.
# Check tree2.jpg

from collections import deque
# Definition for a binary tree node. 
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution:
    def isValidBST(self, root: TreeNode) -> bool:
        def traverseTree(root,i):
            if root is None: 
                return True
            i += 1
            left = traverseTree(root.left,i)
            right = traverseTree(root.right,i)
            # Instead call another method to compare left and right as we recursively iterate tree
            isBST = compare(left,right,root)
            if isBST is False:
                return False
            return True
        
        def compare(left,right,root):
            leftNode = left.val
            rightNode = right.val

            if leftNode > root.val and rightNode < root.val:
                return False 
            return True


        traverseTree(root,0)
        return compare

# Build the tree method
# Use DFS to traverse tree root, left, right pre order traversal
# If we can traverse the tree and compare the left < root and right > root at each level
    # Then it is a bst
        


# Build tree:
def createTree(arr):
    root = TreeNode(arr[0])
    queue = deque([root])

    i = 1
    while queue and i < len(arr):
        node = queue.popleft()

        if arr[i] and i < len(arr):
            node.left = TreeNode(arr[i])
            queue.append(node.left)
        i += 1

        if arr[i] is not None and i < len(arr):
            node.right = TreeNode(arr[i])
            queue.append(node.right)
        i += 1

        return node
    


values = [2,1,3]
root = createTree(values)

obj = Solution()
obj.isValidBST(root)


    

