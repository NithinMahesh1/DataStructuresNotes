# Given a binary tree, return true if it is height-balanced and false otherwise.

# A height-balanced binary tree is defined as a binary tree in which the left and right 
# subtrees of every node differ in height by no more than 1.

# Example 1:
# Input: root = [1,2,3,null,null,4]
# Output: true

# Example 2:
# Input: root = [1,2,3,null,null,4,null,5]
# Output: false

# Example 3:
# Input: root = []
# Output: true

from typing import Optional
from collections import deque
# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def isBalanced(self, root: Optional[TreeNode]) -> bool:
        # This is simple we just need to use a dfs method to check the height of left and right
        # If left - right < 0 then multiply by -1
        # If the the val of that is greater than 1 then we return False
        # Otherwise return True
        self.res = False

        if root is None:
            return True

        def dfs(node,depth):
            if node is None:
                return depth
            
            left = dfs(node.left,depth+1)
            right = dfs(node.right,depth+1)

            diffOfSides = left - right

            if diffOfSides < 0:
                diffOfSides = diffOfSides * -1
            if(diffOfSides == 1 or diffOfSides == 0):
                return True

            return False


        return dfs(root,0)
        # return self.res

    def buildTrees(self,arr):
        if arr is None:
            return None
        
        root = TreeNode(arr[0])
        queue = deque([root])

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
        
        return root

    
def main():
    solution = Solution()
    # root = [1,2,3,None,None,4]
    root=[1,2,3,None,None,4,None,5]
    solution.isBalanced(solution.buildTrees(root))

main()
