# Given the beginning of a singly linked list head, reverse the list, and return the new beginning of the list.

# Example 1:
# Input: head = [0,1,2,3]
# Output: [3,2,1,0]

# Example 2:
# Input: head = []
# Output: []


# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution:
    def reverseList(self, head: ListNode) -> ListNode:
        return 0

    def printList(self,head):
        # Build the list first
        curr = ListNode()
        currHead = None
        
        for val in head:
            if currHead == None:
                currhead = ListNode(head)
                curr = currhead
            else:
                curr.next = ListNode(val)
                curr = curr.next

        while(curr is not None):
            print(curr.val)
            curr = curr.next

def main():
    head = [0,1,2,3]
    solution = Solution()
    solution.printList(head)
    solution.reverseList(head)
    

main()