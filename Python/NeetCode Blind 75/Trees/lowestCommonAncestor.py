# Given a binary search tree (BST) where all node values are unique, 
# and two nodes from the tree p and q, return the lowest common ancestor (LCA) of the two nodes.

# The lowest common ancestor between two nodes 
# p and q is the lowest node in a tree T such that both p and q as descendants. 
# The ancestor is allowed to be a descendant of itself.

# Example 1:
# Input: root = [5,3,8,1,4,7,9,null,2], p = 3, q = 8
# Output: 5

# Example 2:
# Input: root = [5,3,8,1,4,7,9,null,2], p = 3, q = 4
# Output: 3
# Explanation: The LCA of nodes 3 and 4 is 3, since a node can be a descendant of itself.

from collections import deque 
# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def lowestCommonAncestor(self, root: TreeNode, p: TreeNode, q: TreeNode) -> TreeNode:
        # Starting at root if the curr is == to p or q then that is the "split" and we return the root as the LCA
        # If at the root curr node our left and right is equal to both p and q then we return the root node
        # If the p and q nodes are less than the root we want to search the left subtree since it is a BST
        # If the p and q are greater than the root then we want to traverse and search the right subtree
        curr = root
        left = curr.left
        right = curr.right
        if(curr.val == p.val or curr.val == q.val):
            return curr
        if(left.val == p and right.val == q or left.val == q and right.val == p):
            return curr
        if(p.val < curr.val and q.val < curr.val):
            return self.lowestCommonAncestor(curr.left,p,q)
        if(p.val > curr.val and q.val > curr.val):
            return self.lowestCommonAncestor(curr.right,p,q)

        return curr
        
    
    def buildTrees(self, arr):
        tree = TreeNode(arr[0])
        queue = deque([tree])

        i = 1
        while queue and i < len(arr):
            node = deque.popleft()

            if(arr[i]):
                tree.left = TreeNode(arr[i])
                queue.append(tree.left)
            i += 1

            if(i < len(arr) and arr[i]):
                tree.right = TreeNode(arr[i])
                queue.append(tree.right)
            i += 1

        return tree
    
def main():
    solution = Solution()
    root = solution.buildTrees([5,3,8,1,4,7,9,None,2])
    p = 3
    q = 8
    solution.lowestCommonAncestor(root)

main()