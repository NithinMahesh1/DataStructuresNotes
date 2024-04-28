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
from collections import deque

class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

def createTree(arr) -> [TreeNode]:
    tree = TreeNode(arr[0])
    queue = deque([tree])

    i = 1
    while queue != None and i < len(arr):
        node  = queue.popleft()

        if(arr[i] is not None):
            node.left = TreeNode(arr[i])
            queue.append(node.left)
        i += 1

        if(arr[i] is not None and i < len(arr)):
            node.right = TreeNode(arr[i])
            queue.append(node.right)
        i += 1

    return tree


def maxDepth(root: TreeNode)-> int:
    # Utilize DFS for this approach since we want to get farthest point
    # Traverse root, left, right using recursion
    depth = 0

    def dfs(root,depth):
        if root is None: 
            return depth
        return max(dfs(root.left,depth + 1),dfs(root.right,depth + 1))

    print(dfs(root,depth))
    return dfs(root,depth)




root = [3,9,20,None,None,15,7]
tree = createTree(root)
maxDepth(tree)