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
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def isValidBST(self, root:[TreeNode]) -> bool:
        # Use dfs to traverse 
        # First we compare the root with left node 
        # Then we compare root with right node
        # Using recursion we keep doing this left and right 
        # In any case where from left to right it is not incremental we output false
        isValid = False

        def dfs(root,isValid):
            if root is None: return True
            left = root.left
            right = root.right
            if left is None and right is None:
                isValid = True
                return isValid
            if((left == None and right != None) or (right == None and left != None)):
                isValid = False
                return isValid
            if left.val >= root.val or right.val <= root.val:
                isValid = False
                return isValid
            return dfs(left,right)


        print(dfs(root,isValid))
        return dfs(root,isValid)
    

def createATree(arr) -> [TreeNode]:
    tree = TreeNode(arr[0])
    queue = deque([tree])

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

    return tree


# root = [2,1,3]
# root = [5,1,4,None,None,3,6]
# root = [1,1]
# root = [0]
root = [0,-1]
tree = createATree(root)

obj = Solution()
obj.isValidBST(tree)