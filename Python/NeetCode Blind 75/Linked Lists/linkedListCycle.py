# Given the beginning of a linked list head, return true if there is a cycle in the linked list. 
# Otherwise, return false.

# There is a cycle in a linked list if at least one node in the list that can be visited again by following the next pointer.

# Internally, index determines the index of the beginning of the cycle, if it exists. 
# The tail node of the list will set it's next pointer to the index-th node. If index = -1, 
# then the tail node points to null and no cycle exists.

# Note: index is not given to you as a parameter.

# Example 1:
# Input: head = [1,2,3,4], index = 1
# Output: true
# Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).

# Example 2:
# Input: head = [1,2], index = -1
# Output: false

# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution:
    def hasCycle(self, head: ListNode) -> bool:
        # Loop through linked list and if we reach null then return false
        # Use a hash set and append the node to the hash set 
        hashset = set()
        curr = head
        while curr:
            print(curr.val)
            if curr in hashset:
                return True
            else:
                hashset.add(curr)
            curr = curr.next

        return False

    
    def buildLinkedList(self,arr):
        curr = None
        head = None

        for nums in arr:
            if head == None:
                head = ListNode(nums)
                curr = head
            else:
                curr.next = ListNode(nums)
                curr = curr.next
        return head

def main():
    head, index = [1,2,3,4], 1
    solution = Solution()
    head = solution.buildLinkedList(head)
    print(solution.hasCycle(head))


main()