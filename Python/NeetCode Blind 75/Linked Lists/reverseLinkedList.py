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
        prev = None
        curr = head

        while curr:
            # Set next node
            nextnode = curr.next
            curr.next = prev # change pointer to previous
            prev = curr
            curr = nextnode
            

        return prev


    def printList(self,head):
        curr = head
        while(curr is not None):
            print(curr.val)
            curr = curr.next

    def buildLinkedList(self,arr):
        head = None
        curr = None
        for nums in arr:
            if curr == None:
                head = ListNode(nums)
                curr = head
            else:
                curr.next = ListNode(nums)
                curr = curr.next

        return head



def main():
    head = [0,1,2,3]
    solution = Solution()
    head = solution.buildLinkedList(head)
    # solution.printList(head)
    solution.printList(solution.reverseList(head))

main()