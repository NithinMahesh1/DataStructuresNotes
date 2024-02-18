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

class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


# This solution is from a leetcode submission
# Solution is actually pretty easy to understand
# Basically using recursion we keep calling the left and right nodes adding to our counter 'depth'
# Then we simply use the max to compare the largest depth on both left and right and return

class Solution:
    def maxDepth(self, root: TreeNode)-> int:
        def dfs(root, depth):
            if not root: return depth
            return max(dfs(root.left, depth + 1), dfs(root.right, depth + 1))
                       
        print(dfs(root,0))
        return dfs(root, 0)



def createTree(arr):
    # Set the root node in tree to arr[0]
    tree = TreeNode(arr[0])

    # Create a queue to store the nodes and pop as we add to tree
    queue = deque([tree])


    # Sudo Code:
    # i = 1
    # while arr[i] and i < len(arr):
    # if arr[] is not none
        # node.left = TreeNode(arr[i])
        # append to queue node.left
    # i =+ 1

    # if arr[] is not None and i < len(arr):
        # node.right = TreeNode(arr[i])
        # append to queue node.right
    # i += 1

    # return the tree


    # [3,9,20,None,None,15,7]
    i = 1
    while queue and i < len(arr):
        node = queue.popleft()

        if arr[i] is not None:
            node.left = TreeNode(arr[i])
            queue.append(node.left)
        i += 1

        # if arr[i] is not None and i < len(arr):
        if i < len(arr) and arr[i] is not None:
            node.right = TreeNode(arr[i])
            queue.append(node.right)
        i += 1

    return tree

            
values = [3,9,20,None,None,15,7]
# values = [1,None,2]
tree = TreeNode()
# inputTree = TreeNode(createTree(tree,values))
inputTree = createTree(values)

obj = Solution()
obj.maxDepth(inputTree)