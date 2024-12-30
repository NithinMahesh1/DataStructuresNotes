# You are given the beginning of a linked list head, and an integer n.

# Remove the nth node from the end of the list and return the beginning of the list.

# Example 1:
# Input: head = [1,2,3,4], n = 2
# Output: [1,2,4]

# Example 2:
# Input: head = [5], n = 1
# Output: []

# Example 3:
# Input: head = [1,2], n = 2
# Output: [2]

# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution:
    def removeNthFromEnd(self, head: ListNode, n: int) -> ListNode:
        
    
    def buildList(self,arr):
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
        
    def printList(self,head):
        curr = head
        while curr:
            print(curr.val)
            curr = curr.next


def main():
    head, n = [1,2,3,4], 2
    head = Solution.buildList(head)
    Solution.printList(Solution.removeNthFromEnd(head))

main()