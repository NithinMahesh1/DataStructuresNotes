# Within a binary tree, a node x is considered good if the path from the root of the tree 
# to the node x contains no nodes with a value greater than the value of node x

# Given the root of a binary tree root, return the number of good nodes within the tree.

# Example 1:
# Input: root = [2,1,1,3,null,1,5]
# Output: 3

# Example 2:
# Input: root = [1,2,-1,3,4]
# Output: 4

from collections import deque
# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def goodNodes(self, root: TreeNode) -> int:
        # We can use dfs again for this
        # Basically store the root node and compare each node after root
        # If any of our nodes as we traverse every node in the tree are greater 
        # then we increment the count
        count = [0]
        
        def dfs(node,compareTo):
            if node is None:
                return None
            if node.val >= compareTo:
                count[0] += 1

            maxCount = max(compareTo, node.val)
            dfs(node.left,maxCount)
            dfs(node.right,maxCount)
        
        dfs(root,root.val)
        print(count[0])
        return count[0]
    
    def buildTree(self,arr):
        root = TreeNode(arr[0])
        queue = deque([root])

        i = 1
        while queue and i < len(arr):
            node = queue.popleft()
            
            if(arr[i]):
                node.left = TreeNode(arr[i])
                queue.append(node.left)
            i+=1

            if(i < len(arr) and arr[i]):
                node.right = TreeNode(arr[i])
                queue.append(node.right)
            i+=1

        return root


def main():
    solution = Solution()
    # root = [2,1,1,3,None,1,5]
    root = [1,2,-1,3,4]
    solution.goodNodes(solution.buildTree(root))

main()