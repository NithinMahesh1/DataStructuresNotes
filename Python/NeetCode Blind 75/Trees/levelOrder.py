# Given a binary tree root, return the level order traversal of it as a nested list, 
# where each sublist contains the values of nodes at a particular level in the tree, from left to right.

# Example 1:
# Input: root = [1,2,3,4,5,6,7]
# Output: [[1],[2,3],[4,5,6,7]]

# Example 2:
# Input: root = [1]
# Output: [[1]]

# Example 3:
# Input: root = []
# Output: []

from typing import Optional
from collections import deque
# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def levelOrder(self, root: Optional[TreeNode]) -> list[list[int]]:
        # Simply use DFS to traverse left and right of trees
        # As we loop through need to create a new list of the left and right nodes and append to returnList
        res = []

        def dfs(node, depth):
            if node is None:
                return []
            if len(res) == depth:
                res.append([])
            
            res[depth].append(node.val)
            dfs(node.left,depth+1)
            dfs(node.right,depth+1)
        
        dfs(root,0)
        return res


    def buildTrees(self, arr):
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
    root = [1,2,3,4,5,6,7]
    solution.levelOrder(solution.buildTrees(root))

main()