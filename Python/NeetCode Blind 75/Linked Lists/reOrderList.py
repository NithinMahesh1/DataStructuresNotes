# You are given the head of a singly linked-list.

# The positions of a linked list of length = 7 for example, can intially be represented as:

# [0, 1, 2, 3, 4, 5, 6]

# Reorder the nodes of the linked list to be in the following order:

# [0, 6, 1, 5, 2, 4, 3]

# Notice that in the general case for a list of length = n the nodes are reordered to be in the following order:

# [0, n-1, 1, n-2, 2, n-3, ...]

# You may not modify the values in the list's nodes, but instead you must reorder the nodes themselves.

# Example 1:
# Input: head = [2,4,6,8]
# Output: [2,8,4,6]

# Example 2:
# Input: head = [2,4,6,8,10]
# Output: [2,10,4,8,6]

# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution:
    def reorderList(self, head: ListNode) -> None:
        # [0, 1, 2, 3, 4, 5, 6]
        # [0, n-1, 1, n-2, 2, n-3, ...]
        # Basically we split the list into two sections
        # First half and second half with second half traversing in reverse
        # Also use a slow and faster pointer to first traverse and create two lists
        # The slow one will traverse at normal speed and fast will go two faster
        slow = head
        fast = head.next

        while fast and fast.next:
            slow = slow.next
            fast = fast.next.next

        second = slow.next
        # Set the next to null since we are splitting the lists
        slow.next = None
        prev = None
        while second:
            temp = second.next
            second.next = prev
            prev = second
            second = temp

        # Merge the two halves of the list
        second = prev
        first = head
        while second:
            temp1 = first.next
            temp2 = second.next
            first.next = second
            second.next = temp1
            first = temp1
            second = temp2

        # Also we dont return anything since no extra memory is used
        # We are modifying it in place        


    def buildLinkedList(self,arr):
        curr = None
        head = None
        for nums in arr:
            if curr == None:
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
    # head = [2,4,6,8]
    head = [0,1,2,3,4,5,6]
    solution = Solution()
    head = solution.buildLinkedList(head)
    solution.printList(solution.reorderList(head))



main()